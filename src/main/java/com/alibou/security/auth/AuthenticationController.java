package com.alibou.security.auth;

import com.alibou.security.Model.BroadbandPlans;
import com.alibou.security.Model.BusinessPlans;
import com.alibou.security.Service.BroadbandPlansService;
import com.alibou.security.Service.BusinessPlansService;
import com.alibou.security.Service.UsersService;
import com.alibou.security.dto.LoginDto;
import com.alibou.security.dto.RegisterDto;
import com.alibou.security.exception.ResourceNotFoundException;
import com.alibou.security.help.Email;
import com.alibou.security.help.VerificationRequest;
import com.alibou.security.help.EmailServices;
import com.alibou.security.user.ChangePasswordRequest;
import com.alibou.security.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Data
public class AuthenticationController {
   @Autowired
    private EmailServices emailService;
   @Autowired
   private UserService uservice;
   @Autowired
   private UsersService usersService;
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

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }

    @Autowired
    private BroadbandPlansService broadbandPlansService;

  @GetMapping("/broadband-plans")

  public List<BroadbandPlans> getBroadbandPlans() {
      return broadbandPlansService.getBroadbandPlans();
  }

  @GetMapping("/broadband-plans/{id}")
  public ResponseEntity<BroadbandPlans> getPlanById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
      BroadbandPlans broadbandPlans = broadbandPlansService.getPlanById(id);
      return ResponseEntity.ok().body(broadbandPlans);
  }
    @PostMapping("/broadband-plans")
    public ResponseEntity<BroadbandPlans> savePlan(@RequestBody BroadbandPlans broadbandPlans){
        
        BroadbandPlans savedPlan =  broadbandPlansService.savePlan(broadbandPlans);
        return new ResponseEntity<>(savedPlan, HttpStatus.CREATED);
    }

    @PutMapping("/broadband-plans/{id}")
    public ResponseEntity<BroadbandPlans> updateBroadbandPlan(@PathVariable(value = "id") Long id,
                                                   @Valid @RequestBody BroadbandPlans broadbandPlans) throws ResourceNotFoundException {
        BroadbandPlans updatedPlan = broadbandPlansService.updateBroadbandPlans(id, broadbandPlans);
        return ResponseEntity.ok(updatedPlan);
    }


    @DeleteMapping("/broadband-plans/{id}")
    public Map<String,Boolean> deleteBroadbandPlanWithId(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
      return broadbandPlansService.deletePlan(id);

    }

    @Autowired
    private BusinessPlansService businessPlansService;

    @GetMapping("/business-plans")

    public List<BusinessPlans> getBusinessPlans() {
        return businessPlansService.getBusinessPlans();
    }

    @GetMapping("/business-plans/{id}")
    public ResponseEntity<BusinessPlans> getBusinessPlanById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        BusinessPlans businessPlans = businessPlansService.getPlanById(id);
        return ResponseEntity.ok().body(businessPlans);
    }

    @PostMapping("/business-plans")
    public ResponseEntity<BusinessPlans> savePlan(@RequestBody BusinessPlans businessPlans){
        BusinessPlans savedPlan = businessPlansService.savePlan(businessPlans);
        return new ResponseEntity<>(savedPlan, HttpStatus.CREATED);
    }

    @PutMapping("/business-plans/{id}")
    public ResponseEntity<BusinessPlans> updateBusinessPlan(@PathVariable(value = "id") Long id,
                                                              @Valid @RequestBody BusinessPlans businessPlans) throws ResourceNotFoundException {
        BusinessPlans updatedPlan = businessPlansService.updateBusinessPlans(id, businessPlans);
        return ResponseEntity.ok(updatedPlan);
    }


    @DeleteMapping("/business-plans/{id}")
    public Map<String,Boolean> deleteBusinessPlanWithId(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
        return businessPlansService.deletePlan(id);

    }

}
