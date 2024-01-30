package com.alibou.security.Service;

import com.alibou.security.repository.BusinessPlansRepository;
import com.alibou.security.user.BusinessPlans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessPlansService {


    @Autowired
    BusinessPlansRepository businessPlansRepository;
    public List<BusinessPlans> getBusinessPlans(){
        return businessPlansRepository.findAll();
    }
}
