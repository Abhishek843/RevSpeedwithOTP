package com.alibou.security.Service;

import com.alibou.security.repository.HomePlansRepository;
import com.alibou.security.user.BusinessPlans;
import com.alibou.security.user.HomePlans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePlansService {

    @Autowired
    private HomePlansRepository homePlansRepository;

    public List<HomePlans> getHomePlans(){
        return homePlansRepository.findAll();
    }

}
