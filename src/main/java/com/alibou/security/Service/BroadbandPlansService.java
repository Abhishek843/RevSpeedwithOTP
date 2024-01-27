package com.alibou.security.Service;

import com.alibou.security.Model.BroadbandPlans;
import com.alibou.security.Model.BusinessPlans;
import com.alibou.security.exception.ResourceNotFoundException;
import com.alibou.security.repository.BroadbandPlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BroadbandPlansService {

    @Autowired
    private BroadbandPlansRepository broadbandPlansRepository;

        public List<BroadbandPlans> getBroadbandPlans(){
            return broadbandPlansRepository.findAll();
        }

    public BroadbandPlans getPlanById(Long id) throws ResourceNotFoundException {
        return broadbandPlansRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with Id :: " + id));
    }

    public BroadbandPlans savePlan(BroadbandPlans broadbandPlans){
        return broadbandPlansRepository.save(broadbandPlans);
    }

    public BroadbandPlans updateBroadbandPlans(Long id, BroadbandPlans broadbandPlansDetails) throws ResourceNotFoundException {
        BroadbandPlans broadbandPlans = broadbandPlansRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found for this id :: " + id));
        broadbandPlans.setBilling_cycle(broadbandPlansDetails.getBilling_cycle());
        broadbandPlans.setPlan_name(broadbandPlansDetails.getPlan_name());
        broadbandPlans.setPlan_speed(broadbandPlansDetails.getPlan_speed());
        broadbandPlans.setPlan_price(broadbandPlansDetails.getPlan_price());
        broadbandPlans.setPlan_type(broadbandPlans.getPlan_type());

        return broadbandPlansRepository.save(broadbandPlans);
    }

    public Map<String, Boolean> deletePlan(Long id) throws ResourceNotFoundException {
        BroadbandPlans broadbandPlans = broadbandPlansRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found for this id :: " + id));

        broadbandPlansRepository.delete(broadbandPlans);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
