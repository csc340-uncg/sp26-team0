package com.csc340.local_harvest_hub.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csc340.local_harvest_hub.entity.Farmer;
import com.csc340.local_harvest_hub.service.FarmerService;
import com.csc340.local_harvest_hub.service.ProduceBoxService;
import com.csc340.local_harvest_hub.service.ReviewService;
import com.csc340.local_harvest_hub.service.SubscriptionService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;

import org.springframework.ui.Model;

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

  @GetMapping("/dashboard")
  public String getDashboard(HttpSession session, Model model) {
    Long farmerId = (Long) session.getAttribute("farmerId");
    if (farmerId == null) {
      return "redirect:/signin";
    }
    Farmer farmer = farmerService.getFarmerById(farmerId).orElse(null);
    model.addAttribute("currentFarmer", farmer);
    if (farmer != null && farmer.getFarm() != null) {

      model.addAttribute("totalBoxes", produceBoxService.getProduceBoxesByFarmId(farmer.getFarm().getFarmId()).size());
      model.addAttribute("totalSubscriptions",
          subscriptionService.getSubscriptionsByFarmId(farmer.getFarm().getFarmId()).size());
      model.addAttribute("averageRating", reviewService.getAverageRatingForFarm(farmer.getFarm().getFarmId()));
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

}
