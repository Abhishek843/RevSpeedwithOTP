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
@Table(name = "home_plans")
public class HomePlans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "billing_cycle")
    private String billing_cycle;

    @Column(name = "plan_name")
    private String plan_name;

    @Column(name = "plan_speed")
    private String plan_speed;

    @Column(name = "plan_data")
    private String plan_data;

    @Column(name = "plan_price")
    private double plan_price;

    @Column(name = "ott_benefit_1")
    private String ott_benefit_1;

    @Column(name = "ott_benefit_2")
    private String ott_benefit_2;
//
//    @OneToMany(mappedBy = "home_plan_id")
//    private List<User> UserList;

    // Other fields and methods

}
