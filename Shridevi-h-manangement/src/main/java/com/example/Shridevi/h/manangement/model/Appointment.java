package com.example.Shridevi.h.manangement.model;

import java.time.LocalDate;

//package com.example.shridevi.hospitalmanagement.model;


import jakarta.persistence.*;



@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String doctorName;
    private LocalDate appointmentDate; // ✅ add this

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    //public LocalDate getAppointmentDate() { return appointmentDate; }
    //public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }


    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
}
