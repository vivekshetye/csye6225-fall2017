package com.csye6225.demo.repository;

import com.csye6225.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Mihir Patil,     001220443, patil.m@husky.neu.edu
 * Vivek Shetye,    001237626, shetye.v@husky.neu.edu
 * Pushkar Khedekar,001225610, khedekar.p@husky.neu.edu
 * Atul Takekar,    001220479, takekar.a@husky.neu.edu
 **/
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Long>{
    User findByEmail(String email);
}