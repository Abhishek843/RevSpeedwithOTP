package com.alibou.security.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

  @Query("FROM User u WHERE u.email=:email")
  User findByEmaill(String email);

//  @Query("UPDATE User u SET u.firstname = :firstname, u.lastname = :lastname, u.phone = :phone, u.address = :address WHERE u.email = :email")
//  int updateUserDetails(@Param("email") String email,
//                        @Param("firstname") String firstname,
//                        @Param("lastname") String lastname,
//                        @Param("phone") String phone,
//                        @Param("address") String address);

}
