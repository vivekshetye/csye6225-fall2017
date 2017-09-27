package com.csye6225.demo.controllers;

import com.csye6225.demo.model.User;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.csye6225.demo.service.UserService;

import java.util.Date;

@Controller
public class HomeController {

  @Autowired
  private UserService userService;

  private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

  @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public String welcome(@RequestHeader(value="email", required = false) String email, @RequestHeader(value="password", required = false) String password) {
    JsonObject jsonObject = new JsonObject();
    User userExists = userService.findUserByEmail(email);
    if(userExists != null) {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      if(encoder.matches(password, userExists.getPassword()))
        jsonObject.addProperty("message", "you are logged in. current time is " + new Date().toString());
      else
        jsonObject.addProperty("message", "unauthorized");
    }else {
      jsonObject.addProperty("message", "you are not logged in!!!");
    }
    return jsonObject.toString();

  }

  @RequestMapping(value = "/user/register", method = RequestMethod.POST, produces = "application/json")
  @ResponseBody
  public String register(@RequestBody User user) {
    JsonObject jsonObject = new JsonObject();
    User userExists = userService.findUserByEmail(user.getEmail());
    if (userExists != null) {
      jsonObject.addProperty("message", "Account already exist !");
    } else {
      User createUser = new User();
      createUser.setEmail(user.getEmail());
      createUser.setPassword(user.getPassword());
      userService.saveUser(createUser);
      jsonObject.addProperty("message", "Account created successfully");
    }
    return jsonObject.toString();
  }

}