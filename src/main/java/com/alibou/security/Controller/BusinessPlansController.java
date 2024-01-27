//package com.alibou.security.Controller;
//
//import com.alibou.security.Model.BusinessPlans;
//import com.alibou.security.Service.BusinessPlansService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//    @RestController
//
//    public class BusinessPlansController {
//        @Autowired
//        private BusinessPlansService businessPlanService;
//
//        @PostMapping("/business-plans")
//        public ResponseEntity<BusinessPlans> savePlan(@RequestBody BusinessPlans businessPlans){
//            BusinessPlans savedPlan = businessPlanService.savePlan(businessPlans);
//            return new ResponseEntity<>(savedPlan, HttpStatus.CREATED);
//        }
//    }
//
