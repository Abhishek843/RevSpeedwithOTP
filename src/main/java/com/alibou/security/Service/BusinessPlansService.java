package com.alibou.security.Service;

import com.alibou.security.Model.BroadbandPlans;
import com.alibou.security.Model.BusinessPlans;
import com.alibou.security.exception.ResourceNotFoundException;
import com.alibou.security.repository.BusinessPlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BusinessPlansService {

    @Autowired
    BusinessPlansRepository businessPlansRepository;

    public List<BusinessPlans> getBusinessPlans(){
        return businessPlansRepository.findAll();
    }

    public BusinessPlans getPlanById(Long id) throws ResourceNotFoundException {
        return businessPlansRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with Id :: " + id));
    }
    public BusinessPlans savePlan(BusinessPlans businessPlans){
        return businessPlansRepository.save(businessPlans);
    }

    public BusinessPlans updateBusinessPlans(Long id, BusinessPlans businessPlansDetails) throws ResourceNotFoundException {
        BusinessPlans businessPlans = businessPlansRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found for this id :: " + id));
        businessPlans.setBilling_cycle(businessPlansDetails.getBilling_cycle());
        businessPlans.setPlan_name(businessPlansDetails.getPlan_name());
        businessPlans.setPlan_speed(businessPlansDetails.getPlan_speed());
        businessPlans.setPlan_price(businessPlansDetails.getPlan_price());
        businessPlans.setPlan_type(businessPlans.getPlan_type());

        return businessPlansRepository.save(businessPlans);
    }

    public Map<String, Boolean> deletePlan(Long id) throws ResourceNotFoundException {
        BusinessPlans businessPlans = businessPlansRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found for this id :: " + id));

        businessPlansRepository.delete(businessPlans);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

