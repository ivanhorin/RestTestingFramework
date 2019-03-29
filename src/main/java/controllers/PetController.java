package controllers;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.PetModel;
import models.PetNotFoundModel;
import static testdata.RequestSpecDataReader.*;

import static io.restassured.RestAssured.given;

public class PetController {


   public PetController(){
      RestAssured.requestSpecification =  new RequestSpecBuilder()
            .setBaseUri(getBaseUri())
            .setBasePath(getBasePath())
            .addHeaders(getBaseHeader())
            .log(LogDetail.ALL)
            .setContentType(ContentType.JSON).build();
   }

   @Step("POST pet with body: {0}")
   public PetModel postNewPet(PetModel pet){
      return given().body(pet)
            .when().post()
            .then()
            .extract()
            .response()
            .as(PetModel.class);
   }

   @Step("DELETE pet with id: {0}")
   public void deletePetById (int petId) {
      given().delete(String.valueOf(petId));
   }

   @Step("GET pet with id: {0}")
   public Object getPetById (int petId){
      Response response = given().get(String.valueOf(petId));

      if(response.statusCode() == 200){
         return response.as(PetModel.class);
      } else {
         return response.as(PetNotFoundModel.class);
      }
   }

   @Step("UPDATE pet with dody: {0}")
   public PetModel updatePet(PetModel pet) {
      return given()
            .body(pet)
            .put().as(PetModel.class);
   }





}
