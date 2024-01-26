
package com.alibou.security.repository;

import com.alibou.security.entity.Users;

import java.util.List;
import java.util.Optional;

import com.alibou.security.user.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface  PaymentRepository extends JpaRepository<Payments, Integer> {


}