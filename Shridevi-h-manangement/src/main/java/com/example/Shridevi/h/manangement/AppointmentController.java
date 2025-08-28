package com.example.Shridevi.h.manangement;

import com.example.Shridevi.h.manangement.model.Appointment;
import com.example.Shridevi.h.manangement.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/book")
    public String showBookingForm(@RequestParam(value = "doctor", required = false) String doctorName, Model model) {
        Appointment appointment = new Appointment();
        appointment.setDoctorName(doctorName);
        model.addAttribute("appointment", appointment);
        return "book"; // book.html
    }

	
    @PostMapping("/submitAppointment")
    public String submitAppointment(@ModelAttribute Appointment appointment, RedirectAttributes redirectAttributes) {
        appointmentRepository.save(appointment);
        redirectAttributes.addFlashAttribute("showModal", true);
        return "redirect:/book";
    }

 // Display all appointments
    @GetMapping("/appointments")
    public String viewAppointments(Model model) {
        model.addAttribute("appointments", appointmentRepository.findAll());
        return "appointments"; // loads appointments.html
    }
 // Delete appointment by ID
    @GetMapping("/appointments/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
        return "redirect:/appointments"; // refresh list after delete
    }



}
