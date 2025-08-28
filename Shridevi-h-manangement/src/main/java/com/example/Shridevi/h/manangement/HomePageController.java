package com.example.Shridevi.h.manangement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Shridevi.h.manangement.model.Appointment;

@Controller
@RequestMapping("")
public class HomePageController {

    @Autowired	
    ProductRepository repo;

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("product", new Product()); // For the modal form
        model.addAttribute("products", repo.findAll()); // Patient list
        return "home";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        System.out.println("***** inside addproduct");
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute Product product) {
        repo.save(product);
        return "redirect:/"; // After saving, redirect to home
    }
    
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
           System.out.println("***** deleteProduct "+ id);
           
    repo.deleteById(id);
    return "redirect:/";
    }
       
        @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
    System.out.println("***** showEditForm "+ id);
            Product product = repo.findById(id).orElseThrow();
    model.addAttribute("product", product);
    return "edit";
    } 
       
       @PostMapping("/edit")
       public String updateProduct(@ModelAttribute Product product) {
       repo.save(product);
       return "redirect:/";
       }
       
      /* @GetMapping("/book")
       public String showBookingForm(@RequestParam(value = "doctor", required = false) String doctorName, Model model) {
       Appointment appointment = new Appointment();
       appointment.setDoctorName(doctorName); // pre-fill doctor name
       model.addAttribute("appointment", appointment);
        return "book";  // will render book.html from templates/
       }*/
      
       
    
}
