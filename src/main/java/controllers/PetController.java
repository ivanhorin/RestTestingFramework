package controllers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.PetModel;
import models.PetNotFoundModel;

import static io.restassured.RestAssured.given;

public class PetController {

   private PetModel pet;

   public PetController(PetModel testPet){
      RestAssured.requestSpecification =  new RequestSpecBuilder()
            .setBaseUri("https://petstore.swagger.io/")
            .setBasePath("/v2/pet")
            .addHeader("api_key","1254527582752")
            .log(LogDetail.ALL)
            .setContentType(ContentType.JSON).build();
      /*RestAssured.responseSpecification = new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();*/
      this.pet = testPet;
   }

   public PetModel postNewPet(){
      return given().body(pet)
            .when().post()
            .then()
            .extract()
            .response()
            .as(PetModel.class);
   }

   public void deletePet (){
      given().delete(String.valueOf(pet.getId()));
   }

   public Object getPet(){
      Response response = given().get(String.valueOf(pet.getId()));

      if(response.statusCode() == 200){
         return response.as(PetModel.class);
      } else {
         return response.as(PetNotFoundModel.class);
      }
   }

   public PetModel updatePet() {
      return given()
            .body(pet)
            .put().as(PetModel.class);
   }





}
