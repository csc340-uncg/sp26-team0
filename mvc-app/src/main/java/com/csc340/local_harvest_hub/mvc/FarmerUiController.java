package com.csc340.local_harvest_hub.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csc340.local_harvest_hub.entity.Farmer;
import com.csc340.local_harvest_hub.service.FarmerService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/farmer")
public class FarmerUiController {

  @Autowired
  private FarmerService farmerService;

  private Long farmerId = 3L; // Simulating a logged-in farmer with ID 3

  @GetMapping("/dashboard")
  public String getDashboard(Model model) {
    Farmer farmer = farmerService.getFarmerById(farmerId).orElse(null);
    // System.out.println("Retrieved Farmer: " + farmer);
    model.addAttribute("currentFarmer", farmer);
    model.addAttribute("message", "Welcome to the Farmer Dashboard!");
    return "farmer/dashboard";
  }

}
