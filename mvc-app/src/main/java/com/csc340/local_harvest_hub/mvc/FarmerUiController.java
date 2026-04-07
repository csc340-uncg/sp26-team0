package com.csc340.local_harvest_hub.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csc340.local_harvest_hub.entity.Farm;
import com.csc340.local_harvest_hub.entity.Farmer;
import com.csc340.local_harvest_hub.entity.ProduceBox;
import com.csc340.local_harvest_hub.entity.Review;
import com.csc340.local_harvest_hub.service.FarmService;
import com.csc340.local_harvest_hub.service.FarmerService;
import com.csc340.local_harvest_hub.service.ProduceBoxService;
import com.csc340.local_harvest_hub.service.ReviewService;
import com.csc340.local_harvest_hub.service.SubscriptionService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/farmer")
public class FarmerUiController {

  @Autowired
  private FarmerService farmerService;

  private Long farmerId = 3L; // Simulating a logged-in farmer with ID 3

  @Autowired
  private ProduceBoxService produceBoxService;

  @Autowired
  private SubscriptionService subscriptionService;

  @Autowired
  private ReviewService reviewService;

  @Autowired
  private FarmService farmService;

  @GetMapping("/dashboard")
  public String getDashboard(HttpSession session, Model model) {
    Long farmerId = (Long) session.getAttribute("farmerId");
    if (farmerId == null) {
      return "redirect:/signin";
    }
    Farmer farmer = farmerService.getFarmerById(farmerId);
    model.addAttribute("currentFarmer", farmer);
    if (farmer != null && farmer.getFarm() != null) {
      model.addAttribute("totalBoxes", produceBoxService.getProduceBoxesByFarmId(farmer.getFarm().getFarmId()).size());
      model.addAttribute("totalSubscriptions",
          subscriptionService.getSubscriptionsByFarmId(farmer.getFarm().getFarmId()).size());
      model.addAttribute("averageRating", reviewService.getAverageRatingForFarm(farmer.getFarm().getFarmId()));

      // Get latest 2 reviews
      java.util.List<Review> allReviews = reviewService.getReviewsByFarmId(farmer.getFarm().getFarmId());
      java.util.List<Review> recentReviews = allReviews.stream()
          .sorted((r1, r2) -> r2.getCreatedAt().compareTo(r1.getCreatedAt()))
          .limit(2)
          .toList();
      model.addAttribute("recentReviews", recentReviews);
    }
    return "farmer/dashboard";
  }

  @GetMapping("/signup")
  public String signup(Model model) {
    model.addAttribute("farmer", new Farmer());
    return "farmer/signup";
  }

  @PostMapping("/signup")
  public String signup(Farmer farmer) {
    farmerService.createFarmer(farmer);
    return "redirect:/signin";
  }

  @PostMapping("/signin")
  public String signin(@RequestParam String email, @RequestParam String password, HttpSession session) {
    try {
      Farmer farmer = farmerService.authenticate(email, password);
      session.setAttribute("farmerId", farmer.getUserId());
      return "redirect:/farmer/dashboard";
    } catch (Exception e) {
      return "redirect:/signin?error";
    }
  }

  @GetMapping("/farm/setup")
  public String setupFarmForm(HttpSession session, Model model) {
    Long farmerId = (Long) session.getAttribute("farmerId");
    if (farmerId == null) {
      return "redirect:/signin";
    }
    Farmer farmer = farmerService.getFarmerById(farmerId);
    if (farmer != null && farmer.getFarm() != null) {
      return "redirect:/farmer/dashboard";
    }

    return "farmer/farm-setup";
  }

  @PostMapping("/farm/setup")
  public String setupFarm(HttpSession session, @RequestParam String name, @RequestParam String location,
      @RequestParam String description) {
    Long farmerId = (Long) session.getAttribute("farmerId");
    if (farmerId == null) {
      return "redirect:/signin";
    }

    Farmer farmer = farmerService.getFarmerById(farmerId);
    Farm farm = new Farm();
    farm.setFarmer(farmer);
    farm.setName(name);
    farm.setLocation(location);
    farm.setDescription(description);
    farmService.createFarm(farm);
    return "redirect:/farmer/dashboard";
  }

  @GetMapping("/boxes/new")
  public String newBoxForm(HttpSession session, Model model) {
    Long farmerId = (Long) session.getAttribute("farmerId");
    if (farmerId == null) {
      return "redirect:/signin";
    }
    Farmer farmer = farmerService.getFarmerById(farmerId);
    if (farmer.getFarm() == null) {
      return "redirect:/farmer/dashboard";
    }
    model.addAttribute("produceBox", new ProduceBox());
    return "farmer/new-box";
  }

  @PostMapping("/boxes/new")
  public String createBox(HttpSession session, ProduceBox produceBox) {
    Long farmerId = (Long) session.getAttribute("farmerId");
    if (farmerId == null) {
      return "redirect:/signin";
    }
    Farmer farmer = farmerService.getFarmerById(farmerId);
    if (farmer.getFarm() == null) {
      return "redirect:/farmer/dashboard";
    }
    produceBox.setFarm(farmer.getFarm());
    produceBoxService.createProduceBox(produceBox);
    return "redirect:/farmer/dashboard";
  }

  @GetMapping("/profile")
  public String getProfileSettings(HttpSession session, Model model) {
    Long farmerId = (Long) session.getAttribute("farmerId");
    if (farmerId == null) {
      return "redirect:/signin";
    }
    Farmer farmer = farmerService.getFarmerById(farmerId);
    model.addAttribute("farmer", farmer);
    return "farmer/profile-settings";
  }

  @PostMapping("/profile")
  public String updateProfileSettings(HttpSession session, @RequestParam String email,
      @RequestParam String password, @RequestParam String bio) {
    Long farmerId = (Long) session.getAttribute("farmerId");
    if (farmerId == null) {
      return "redirect:/signin";
    }
    Farmer farmerDetails = new Farmer();
    farmerDetails.setEmail(email);
    if (password != null && !password.isEmpty()) {
      farmerDetails.setPasswordHash(password);
      ;
    }
    farmerDetails.setBio(bio);
    farmerService.updateFarmer(farmerId, farmerDetails);
    return "redirect:/farmer/dashboard?success";
  }

  @GetMapping("/farm/settings")
  public String getFarmSettings(HttpSession session, Model model) {
    Long farmerId = (Long) session.getAttribute("farmerId");
    if (farmerId == null) {
      return "redirect:/signin";
    }
    Farmer farmer = farmerService.getFarmerById(farmerId);
    if (farmer.getFarm() == null) {
      return "redirect:/farmer/farm/setup";
    }
    model.addAttribute("farm", farmer.getFarm());
    return "farmer/farm-settings";
  }

  @PostMapping("/farm/settings")
  public String updateFarmSettings(HttpSession session, @RequestParam String name,
      @RequestParam String location, @RequestParam String description) {
    Long farmerId = (Long) session.getAttribute("farmerId");
    if (farmerId == null) {
      return "redirect:/signin";
    }
    Farmer farmer = farmerService.getFarmerById(farmerId);
    if (farmer.getFarm() == null) {
      return "redirect:/farmer/farm/setup";
    }
    Farm farmDetails = new Farm();
    farmDetails.setName(name);
    farmDetails.setLocation(location);
    farmDetails.setDescription(description);
    farmService.updateFarm(farmer.getFarm().getFarmId(), farmDetails);
    return "redirect:/farmer/dashboard?success";
  }

  @GetMapping("/products")
  public String getProductManagement(HttpSession session, Model model) {
    Long farmerId = (Long) session.getAttribute("farmerId");
    if (farmerId == null) {
      return "redirect:/signin";
    }
    Farmer farmer = farmerService.getFarmerById(farmerId);
    if (farmer.getFarm() == null) {
      return "redirect:/farmer/farm/setup";
    }
    model.addAttribute("farm", farmer.getFarm());
    model.addAttribute("produceBoxes", farmer.getFarm().getProduceBoxes());
    return "farmer/product-management";
  }

  @GetMapping("/boxes/edit/{boxId}")
  public String editBoxForm(HttpSession session, @PathVariable Long boxId, Model model) {
    Long farmerId = (Long) session.getAttribute("farmerId");
    if (farmerId == null) {
      return "redirect:/signin";
    }
    Farmer farmer = farmerService.getFarmerById(farmerId);
    if (farmer.getFarm() == null) {
      return "redirect:/farmer/farm/setup";
    }
    ProduceBox box = produceBoxService.getProduceBoxById(boxId).orElse(null);
    if (box == null || !box.getFarm().getFarmId().equals(farmer.getFarm().getFarmId())) {
      return "redirect:/farmer/products";
    }
    model.addAttribute("produceBox", box);
    return "farmer/edit-box";
  }

  @PostMapping("/boxes/edit/{boxId}")
  public String updateBox(HttpSession session, @PathVariable Long boxId, ProduceBox updatedBox) {
    Long farmerId = (Long) session.getAttribute("farmerId");
    if (farmerId == null) {
      return "redirect:/signin";
    }
    Farmer farmer = farmerService.getFarmerById(farmerId);
    ProduceBox box = produceBoxService.getProduceBoxById(boxId).orElse(null);
    if (box == null || !box.getFarm().getFarmId().equals(farmer.getFarm().getFarmId())) {
      return "redirect:/farmer/products";
    }
    produceBoxService.updateProduceBox(boxId, updatedBox);
    return "redirect:/farmer/products?success";
  }

  @GetMapping("/boxes/delete/{boxId}")
  public String deleteBox(HttpSession session, @PathVariable Long boxId) {
    Long farmerId = (Long) session.getAttribute("farmerId");
    if (farmerId == null) {
      return "redirect:/signin";
    }
    Farmer farmer = farmerService.getFarmerById(farmerId);
    ProduceBox box = produceBoxService.getProduceBoxById(boxId).orElse(null);
    if (box == null || !box.getFarm().getFarmId().equals(farmer.getFarm().getFarmId())) {
      return "redirect:/farmer/products";
    }
    produceBoxService.deleteProduceBox(boxId);
    return "redirect:/farmer/products?deleted";
  }

  @GetMapping("/reviews")
  public String getReviewManagement(HttpSession session, Model model) {
    Long farmerId = (Long) session.getAttribute("farmerId");
    if (farmerId == null) {
      return "redirect:/signin";
    }
    Farmer farmer = farmerService.getFarmerById(farmerId);
    if (farmer.getFarm() == null) {
      return "redirect:/farmer/farm/setup";
    }
    java.util.List<Review> reviews = reviewService.getReviewsByFarmId(farmer.getFarm().getFarmId());
    model.addAttribute("farm", farmer.getFarm());
    model.addAttribute("reviews", reviews);
    return "farmer/review-management";
  }

  @PostMapping("/reviews/{reviewId}/reply")
  public String addReply(HttpSession session, @PathVariable Long reviewId, @RequestParam String replyText) {
    Long farmerId = (Long) session.getAttribute("farmerId");
    if (farmerId == null) {
      return "redirect:/signin";
    }
    Farmer farmer = farmerService.getFarmerById(farmerId);
    Review existingReview = reviewService.getReviewById(reviewId).orElse(null);

    if (existingReview == null || !existingReview.getSubscription().getProduceBox().getFarm().getFarmId()
        .equals(farmer.getFarm().getFarmId())) {
      return "redirect:/farmer/reviews";
    }
    existingReview.setReplyText(replyText);
    reviewService.updateReview(reviewId, existingReview);
    return "redirect:/farmer/reviews?replied";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }

}
