package com.alibou.security.Service;

import com.alibou.security.repository.PaymentRepository;
import com.alibou.security.user.Payments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    public Payments savePayment(Payments payments){
        return paymentRepository.save(payments);
    }

}
