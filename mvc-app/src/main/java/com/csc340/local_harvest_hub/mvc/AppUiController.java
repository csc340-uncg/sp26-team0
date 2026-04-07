package com.csc340.local_harvest_hub.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.csc340.local_harvest_hub.entity.Farm;
import com.csc340.local_harvest_hub.entity.ProduceBox;
import com.csc340.local_harvest_hub.service.FarmService;
import com.csc340.local_harvest_hub.service.ProduceBoxService;

@Controller
public class AppUiController {
  @Autowired
  private FarmService farmService;
  @Autowired
  private ProduceBoxService produceBoxService;

  @GetMapping({ "", "/", "/home" })
  public String home(Model model) {
    List<Farm> featuredFarms = farmService.getAllFarms().stream().limit(3).toList();
    List<ProduceBox> featuredBoxes = produceBoxService.getAllProduceBoxes().stream().limit(3).toList();
    model.addAttribute("farms", featuredFarms);
    model.addAttribute("boxes", featuredBoxes);
    return "home";
  }

  @GetMapping("/signup")
  public String signup() {
    return "signup";
  }

  @GetMapping("/signin")
  public String signin() {
    return "signin";
  }

}
