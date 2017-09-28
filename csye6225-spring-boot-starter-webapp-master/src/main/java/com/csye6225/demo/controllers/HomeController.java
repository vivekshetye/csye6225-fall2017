package com.csye6225.demo.controllers;

import com.csye6225.demo.model.User;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.csye6225.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Date;

@Controller
public class HomeController {

  @Autowired
  private UserService userService;

  private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

  @RequestMapping(value="/", method= RequestMethod.GET, produces= "application/json")
  @ResponseBody
  public String welcome(HttpServletRequest request, HttpServletResponse response){

    JsonObject jsonObject = new JsonObject();
    //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String header = request.getHeader("Authorization");
    if (header != null && header.contains("Basic")) {
      assert header.substring(0, 6).equals("Basic");
      String basicAuthEncoded = header.substring(6);
      String basicAuthAsString = new String(Base64.getDecoder().decode(basicAuthEncoded.getBytes()));
      final String[] credentialValues = basicAuthAsString.split(":", 2);
      User userExists = userService.findUserByEmail(credentialValues[0]);
        if (userExists != null) {
          BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
          if (encoder.matches(credentialValues[1], userExists.getPassword()) || credentialValues[1].equals(userExists.getPassword()))
            jsonObject.addProperty("message", "You are logged in. Current time is: " + new Date().toString());
          else
            jsonObject.addProperty("message", "Incorrect credentials");
        } else {
            jsonObject.addProperty("message", "Incorrect credentials");
        }
      } else {
      jsonObject.addProperty("message", "You are not logged in !!");
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
      jsonObject.addProperty("email", createUser.getEmail());
      jsonObject.addProperty("password", createUser.getPassword());
    }
    return jsonObject.toString();
  }

}