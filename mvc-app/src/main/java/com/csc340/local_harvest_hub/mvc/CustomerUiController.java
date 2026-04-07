package com.csc340.local_harvest_hub.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csc340.local_harvest_hub.entity.Customer;
import com.csc340.local_harvest_hub.entity.ProduceBox;
import com.csc340.local_harvest_hub.entity.Review;
import com.csc340.local_harvest_hub.entity.Subscription;
import com.csc340.local_harvest_hub.service.CustomerService;
import com.csc340.local_harvest_hub.service.ProduceBoxService;
import com.csc340.local_harvest_hub.service.ReviewService;
import com.csc340.local_harvest_hub.service.SubscriptionService;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/customer")
public class CustomerUiController {

  @Autowired
  private CustomerService customerService;

  @Autowired
  private SubscriptionService subscriptionService;

  @Autowired
  private ProduceBoxService produceBoxService;

  @Autowired
  private ReviewService reviewService;

  @GetMapping("/dashboard")
  public String getDashboard(HttpSession session, Model model) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/signin";
    }
    Customer customer = customerService.getCustomerById(customerId).orElse(null);
    if (customer == null) {
      return "redirect:/signin";
    }

    model.addAttribute("currentCustomer", customer);

    // Get customer's subscriptions
    List<Subscription> allSubscriptions = subscriptionService.getSubscriptionsByCustomerId(customerId);
    List<Subscription> activeSubscriptions = allSubscriptions.stream()
        .filter(s -> s.getStatus() == Subscription.SubscriptionStatus.ACTIVE)
        .toList();
    model.addAttribute("activeSubscriptions", activeSubscriptions);
    model.addAttribute("totalSubscriptions", allSubscriptions.size());

    // Get recent reviews from this customer
    List<Review> recentReviews = allSubscriptions.stream()
        .flatMap(s -> s.getReviews() != null ? s.getReviews().stream() : java.util.stream.Stream.empty())
        .sorted((r1, r2) -> r2.getCreatedAt().compareTo(r1.getCreatedAt()))
        .limit(2)
        .toList();
    model.addAttribute("recentReviews", recentReviews);

    return "customer/dashboard";
  }

  @GetMapping("/signup")
  public String signup(Model model) {
    model.addAttribute("customer", new Customer());
    return "customer/signup";
  }

  @PostMapping("/signup")
  public String signup(Customer customer) {
    customerService.createCustomer(customer);
    return "redirect:/signin";
  }

  @PostMapping("/signin")
  public String signin(@RequestParam String email, @RequestParam String password, HttpSession session) {
    try {
      Customer customer = customerService.getCustomerByEmail(email);
      if (customer != null) {
        session.setAttribute("customerId", customer.getUserId());
        return "redirect:/customer/dashboard";
      }
      return "redirect:/signin?error";
    } catch (Exception e) {
      return "redirect:/signin?error";
    }
  }

  @GetMapping("/profile")
  public String getProfileSettings(HttpSession session, Model model) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/signin";
    }
    Customer customer = customerService.getCustomerById(customerId).orElse(null);
    if (customer == null) {
      return "redirect:/signin";
    }
    model.addAttribute("customer", customer);
    return "customer/profile-settings";
  }

  @PostMapping("/profile")
  public String updateProfileSettings(HttpSession session, @RequestParam String name,
      @RequestParam String email, @RequestParam String password) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/signin";
    }

    Customer customerDetails = new Customer();
    customerDetails.setName(name);
    customerDetails.setEmail(email);
    if (password != null && !password.isEmpty()) {
      customerDetails.setPasswordHash(password);
    }
    customerService.updateCustomer(customerId, customerDetails);
    return "redirect:/customer/dashboard?success";
  }

  @GetMapping("/products")
  public String browseProducts(HttpSession session, Model model) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/signin";
    }

    List<ProduceBox> allBoxes = produceBoxService.getAllProduceBoxes();
    model.addAttribute("produceBoxes", allBoxes);
    return "customer/browse-products";
  }

  @GetMapping("/products/{boxId}")
  public String viewProductDetail(HttpSession session, @PathVariable Long boxId, Model model) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/signin";
    }

    ProduceBox box = produceBoxService.getProduceBoxById(boxId).orElse(null);
    if (box == null) {
      return "redirect:/customer/products";
    }

    model.addAttribute("produceBox", box);
    model.addAttribute("subscription", new Subscription());
    return "customer/product-detail";
  }

  @PostMapping("/products/{boxId}/subscribe")
  public String subscribeToBox(HttpSession session, @PathVariable Long boxId,
      @RequestParam String cadence, @RequestParam LocalDate startDate) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/signin";
    }

    Customer customer = customerService.getCustomerById(customerId).orElse(null);
    ProduceBox box = produceBoxService.getProduceBoxById(boxId).orElse(null);

    if (customer == null || box == null) {
      return "redirect:/customer/products";
    }

    Subscription subscription = new Subscription();
    subscription.setCustomer(customer);
    subscription.setProduceBox(box);
    subscription.setCadence(Subscription.Cadence.valueOf(cadence.toUpperCase()));
    subscription.setStartDate(startDate);
    subscription.setStatus(Subscription.SubscriptionStatus.ACTIVE);

    subscriptionService.createSubscription(subscription);
    return "redirect:/customer/subscriptions?success";
  }

  @GetMapping("/subscriptions")
  public String getSubscriptionsManagement(HttpSession session, Model model) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/signin";
    }

    Customer customer = customerService.getCustomerById(customerId).orElse(null);
    if (customer == null) {
      return "redirect:/signin";
    }

    List<Subscription> subscriptions = subscriptionService.getSubscriptionsByCustomerId(customerId);
    model.addAttribute("subscriptions", subscriptions);
    model.addAttribute("customer", customer);
    return "customer/subscriptions-management";
  }

  @PostMapping("/subscriptions/{subscriptionId}/update")
  public String updateSubscription(HttpSession session, @PathVariable Long subscriptionId,
      @RequestParam String status, @RequestParam(required = false) LocalDate endDate) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/signin";
    }

    Subscription subscription = subscriptionService.getSubscriptionById(subscriptionId).orElse(null);
    if (subscription == null || !subscription.getCustomer().getUserId().equals(customerId)) {
      return "redirect:/customer/subscriptions";
    }

    subscription.setStatus(Subscription.SubscriptionStatus.valueOf(status.toUpperCase()));
    if (endDate != null) {
      subscription.setEndDate(endDate);
    }

    subscriptionService.updateSubscription(subscriptionId, subscription);
    return "redirect:/customer/subscriptions?updated";
  }

  @GetMapping("/reviews")
  public String getMyReviews(HttpSession session, Model model) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/signin";
    }

    Customer customer = customerService.getCustomerById(customerId).orElse(null);
    if (customer == null) {
      return "redirect:/signin";
    }

    List<Subscription> subscriptions = subscriptionService.getSubscriptionsByCustomerId(customerId);
    List<Review> myReviews = subscriptions.stream()
        .flatMap(s -> s.getReviews() != null ? s.getReviews().stream() : java.util.stream.Stream.empty())
        .toList();

    model.addAttribute("reviews", myReviews);
    model.addAttribute("customer", customer);
    return "customer/my-reviews";
  }

  @GetMapping("/subscriptions/{subscriptionId}/review")
  public String reviewForm(HttpSession session, @PathVariable Long subscriptionId, Model model) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/signin";
    }

    Subscription subscription = subscriptionService.getSubscriptionById(subscriptionId).orElse(null);
    if (subscription == null || !subscription.getCustomer().getUserId().equals(customerId)) {
      return "redirect:/customer/reviews";
    }

    List<Review> existingReviews = subscription.getReviews() != null ? subscription.getReviews() : new java.util.ArrayList<>();

    model.addAttribute("subscription", subscription);
    model.addAttribute("existingReview", existingReviews.isEmpty() ? null : existingReviews.get(0));
    model.addAttribute("review", new Review());
    return "customer/review-form";
  }

  @PostMapping("/subscriptions/{subscriptionId}/review")
  public String submitReview(HttpSession session, @PathVariable Long subscriptionId, Review review) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/signin";
    }

    Subscription subscription = subscriptionService.getSubscriptionById(subscriptionId).orElse(null);
    if (subscription == null || !subscription.getCustomer().getUserId().equals(customerId)) {
      return "redirect:/customer/reviews";
    }

    List<Review> existingReviews = subscription.getReviews() != null ? subscription.getReviews() : new java.util.ArrayList<>();

    if (!existingReviews.isEmpty()) {
      // Update existing review
      Review existingReview = existingReviews.get(0);
      existingReview.setFreshnessRating(review.getFreshnessRating());
      existingReview.setDeliveryRating(review.getDeliveryRating());
      existingReview.setValueRating(review.getValueRating());
      existingReview.setComment(review.getComment());
      reviewService.updateReview(existingReview.getReviewId(), existingReview);
    } else {
      // Create new review
      review.setSubscription(subscription);
      reviewService.createReview(review);
    }

    return "redirect:/customer/reviews?submitted";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }

}
