package com.example.Shridevi.h.manangement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shridevi.h.manangement.model.Appointment;
import com.example.Shridevi.h.manangement.repository.AppointmentRepository;


@RestController
@RequestMapping("/apiManipal")
public class HospitalRestController {    
     @Autowired
     private ProductRepository productRepo;
     
     @Autowired
     private AppointmentRepository appointmentRepo;
     
    @GetMapping("/welcome") // Handles GET requests
    public String displayWelcomeMessage()
    { return "Hello, Spring Boot!";
   
    }    
   
    @PostMapping("/addProduct")
     public Product addProduct(@RequestBody Product product) {
            return productRepo.save(product);
        }    
     
         @GetMapping("/getProducts")
        public List<Product> getAllProducts() {
            return productRepo.findAll();
        }

        /*
         * deleteProduct to delete product using id
         * Access id using PathVariable: Extracts {id} from the
         * URL and assigns it to the id variable.
         * ResponseEntity is a Spring class used to build HTTP responses
         * with status codes, headers, and bodies.
         * Void means no content is returned in the body
         * (which is standard for DELETE).
         * productRepo.findById(id):
         * Fetches a product from the database by its ID.
         * Returns an Optional<Product> (a container that may or may
         * not contain a value).
         * .orElse(null):If the product is not found, null is returned.
         * If found, the actual Product object is returned.
         * ResponseEntity.notFound().build() returns a response with:
            Status: 404
              Body: empty
         * ResponseEntity.noContent().build();
            Returns an HTTP response with:
            Status: 204 No Content
            Body: none


         */
         @DeleteMapping("/delete/{id}")
         public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
             Product product = productRepo.findById(id).orElse(null);
             System.out.println("inside delete**** " +product.getId());
             if (product == null) {
                 return ResponseEntity.notFound().build(); // 404
             }

             productRepo.delete(product);
             return ResponseEntity.noContent().build(); // 204
         }
        

         // -------------------- APPOINTMENTS -------------------- //
         @PostMapping("/addAppointments")
         public Appointment addAppointment(@RequestBody Appointment appointment) {
             return appointmentRepo.save(appointment);
         }

         @GetMapping("/getAppointments")
         public List<Appointment> getAllAppointments() {
             return appointmentRepo.findAll();
         }
      // DELETE - Delete Appointment
         @DeleteMapping("/deleteAppointment/{id}")
         public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
             if (!appointmentRepo.existsById(id)) {
                 return ResponseEntity.notFound().build();
             }
             appointmentRepo.deleteById(id);
             return ResponseEntity.noContent().build();
         }
         

}