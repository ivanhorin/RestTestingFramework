package controllers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import models.PetModel;

import static io.restassured.RestAssured.given;

public class PetController {

   public PetController(){
      RestAssured.requestSpecification =  new RequestSpecBuilder()
            .setBaseUri("https://petstore.swagger.io/")
            .setBasePath("/v2/pet")
            .addHeader("api_key","1254527582752")
            .setContentType(ContentType.JSON).build();
   }

   public PetModel postNewPet(PetModel pet){
      return given().body(pet)
            .when().post()
            .then().extract().response().as(PetModel.class);
   }

}
