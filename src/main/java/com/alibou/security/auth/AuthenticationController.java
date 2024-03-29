package com.alibou.security.auth;

import com.alibou.security.Service.PaymentService;
import com.alibou.security.Service.UsersService;
import com.alibou.security.dto.LoginDto;
import com.alibou.security.dto.RegisterDto;
import com.alibou.security.help.Email;
import com.alibou.security.help.VerificationRequest;
import com.alibou.security.help.EmailServices;
import com.alibou.security.user.ChangePasswordRequest;
import com.alibou.security.user.Payments;
import com.alibou.security.user.User;
import com.alibou.security.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {

   @Autowired
    private EmailServices emailService;
   @Autowired
   private UserService uservice;
   @Autowired
   private UsersService usersService;
   @Autowired
   private PaymentService paymentService;


  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }
    @PutMapping("/update-user-details")
    public ResponseEntity<?> updateUserDetails(
            @RequestBody  UpdateRequest userDetailsRequest
    ) {
        try {
            // Call the UserService method to update user details
            System.out.println(userDetailsRequest);
            service.updateUserDetails(userDetailsRequest);



            return ResponseEntity.ok("User details updated successfully.");
        } catch (ChangeSetPersister.NotFoundException e) {
            // Handle the case where the user is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user details");
        }
    }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }
  @PostMapping("/savepayments")
    public Payments savePay(@RequestBody Payments payments){
      return paymentService.savePayment(payments);
  }

    @PostMapping("/newregister")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        return new ResponseEntity<>(usersService.register(registerDto), HttpStatus.OK);
    }
    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request

    ) {
        uservice.changePassword(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/verify-account")

    public ResponseEntity<String> verifyAccount(@RequestBody VerificationRequest verificationRequest) {
        String email=verificationRequest.getEmail();
        String otp=verificationRequest.getOtp();
        return new ResponseEntity<>(usersService.verifyAccount(email, otp), HttpStatus.OK);
    }
    @PutMapping("/regenerate-otp")
    public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
        return new ResponseEntity<>(usersService.regenerateOtp(email), HttpStatus.OK);
    }
    @PutMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(usersService.login(loginDto), HttpStatus.OK);
    }
    @PostMapping(value = "/sendemail")
//    @Timed(value="sendEmail.time",description="email sending")
    public ResponseEntity<?> sendEmail(@RequestBody Email email){
        System.out.println(email);
        boolean result =this.emailService.sendEmail(email.getSubject(), email.getMessage(),email.getToMail());
        if(result) {
//            registry.counter("sendEmail.counter").increment();
            return ResponseEntity.ok("Email is sent successfully.");

        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email Not send");
        }
    }
}















