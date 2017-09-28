package com.csye6225.demo.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Mihir Patil,     001220443, patil.m@husky.neu.edu
 * Vivek Shetye,    001237626, shetye.v@husky.neu.edu
 * Pushkar Khedekar,001225610, khedekar.p@husky.neu.edu
 * Atul Takekar,    001220479, takekar.a@husky.neu.edu
 **/

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;
  }

}
