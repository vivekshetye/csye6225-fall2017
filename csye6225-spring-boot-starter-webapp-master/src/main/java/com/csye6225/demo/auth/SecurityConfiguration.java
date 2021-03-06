package com.csye6225.demo.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * Mihir Patil,     001220443, patil.m@husky.neu.edu
 * Vivek Shetye,    001237626, shetye.v@husky.neu.edu
 * Pushkar Khedekar,001225610, khedekar.p@husky.neu.edu
 * Atul Takekar,    001220479, takekar.a@husky.neu.edu
 **/

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  @Qualifier("dataSource")
  private DataSource dataSource;

  @Autowired
  private BasicAuthEntryPoint basicAuthEntryPoint;

  @Value("${spring.queries.users-query}")
  private String usersQuery;



  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/user/register").permitAll()
            .antMatchers("/resetPassword").permitAll()
            .antMatchers("/").permitAll()
            .antMatchers("/user/task").permitAll()
            .antMatchers("/user/task/{id}/attachments").permitAll()
            .antMatchers("/user/task/{id}").permitAll()
            .antMatchers("/user/task/{id}/attachments/{idAttachments}").permitAll()
            .anyRequest().authenticated();

  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth)
          throws Exception {
    auth.
            jdbcAuthentication()
            .usersByUsernameQuery(usersQuery)
            .dataSource(dataSource)
            .passwordEncoder(bCryptPasswordEncoder);
  }

  @Bean
  public HttpSessionStrategy httpSessionStrategy() {
    return new HeaderHttpSessionStrategy();
  }

}