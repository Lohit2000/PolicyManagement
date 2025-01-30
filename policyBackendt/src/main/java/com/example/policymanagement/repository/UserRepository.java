package com.example.policymanagement.repository;
import com.example.policymanagement.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByEmail(String email);
   // @Query(value = "SELECT name,email,contact_number,date_of_registration,is_active, id,address ,role FROM User_Info", nativeQuery = true)
  //  Optional<UserInfo> findAllExceptPassword();

}
