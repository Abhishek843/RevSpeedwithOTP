package com.alibou.security.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "No_plans")
public class NoPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "noPlan")
    private User user;

    @Column(name = "plan_type")
    private String planType;

    // Other fields and methods

    // Getter and Setter for 'customerData'
}
