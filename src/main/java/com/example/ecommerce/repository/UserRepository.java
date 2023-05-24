package com.example.ecommerce.repository;

import com.example.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author caodinh
 */
public interface UserRepository extends JpaRepository<User,String> {

}
