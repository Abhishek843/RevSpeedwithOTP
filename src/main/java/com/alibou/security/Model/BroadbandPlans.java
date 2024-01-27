package com.alibou.security.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class BroadbandPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String billing_cycle;
    private String plan_name;
    private String plan_speed;

    public BroadbandPlans(String billing_cycle, String plan_name, String plan_speed, double plan_price, String plan_type) {
        this.billing_cycle = billing_cycle;
        this.plan_name = plan_name;
        this.plan_speed = plan_speed;
        this.plan_price = plan_price;
        this.plan_type = plan_type;
    }

    private double plan_price;
    private String plan_type;





}
