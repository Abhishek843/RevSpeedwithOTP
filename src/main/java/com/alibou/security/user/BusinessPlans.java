package com.alibou.security.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "business_plans")
public class BusinessPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "billing_cycle")
    private String billingCycle;

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "plan_speed")
    private String planSpeed;

    @Column(name = "plan_price")
    private double planPrice;

    @Column(name = "plan_type")
    private String planType;

    @OneToMany(mappedBy = "businessPlan")
    private List<User> UserList;

    // Other fields and methods

}
