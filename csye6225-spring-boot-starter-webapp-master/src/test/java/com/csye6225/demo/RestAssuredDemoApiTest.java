package com.csye6225.demo;

import io.restassured.RestAssured;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class RestAssuredDemoApiTest {

  @Test
  public void defaultTest() {
    System.out.println("---------******Demo test******--------------");
  }

  @Ignore
  @Test
  public void testGetHomePage() throws URISyntaxException {
    RestAssured.when().get(new URI("http://localhost:8080/")).then().statusCode(200);
  }

}
