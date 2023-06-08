package com.example.ecommerce.repository;

import com.example.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * @author caodinh
 */
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User getUserByUsername(@Param("email") String email);

    @Query(value = "SELECT MAX(CAST(SUBSTRING(ma, 5) AS UNSIGNED)) FROM user", nativeQuery = true)
    String getMaxMaUser();
}
