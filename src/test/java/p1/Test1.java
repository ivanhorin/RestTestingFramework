package p1;

import controllers.PetController;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.PetModel;
import models.PetNotFoundModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class Test1 {

   @Test
   public void test1(){
      RestAssured.given()
            .baseUri("https://petstore.swagger.io/")
            .basePath("/v2/pet")
            .contentType(ContentType.JSON)
            .header("api_key","1254527582752")
            .body("{\n" +
                  " \"id\": "+ 1 + ",\n" +
                  " \"name\": \"Gosha\",\n" +
                  " \"photoUrls\": [],\n" +
                  " \"tags\": [],\n" +
                  " \"status\": \"available\"\n" +
                  "}")
            .when().post()
            .then()
            .body("id", Matchers.equalTo(1));
          /*  .extract().response()
            .prettyPrint();*/
   }

   @Test
   public void addNewPetTest(){
      int testPetId = Integer.valueOf(RandomStringUtils.randomNumeric(5));
      String testPetName = RandomStringUtils.randomAlphabetic(5);

      PetModel testPet = new PetModel(testPetId, testPetName, null, new ArrayList<>(), new ArrayList<>(), "Available");
      PetController petController = new PetController(testPet);
      PetModel petResponse = petController.postNewPet();
      Assert.assertEquals(petResponse,testPet);
   }

   @Test
   public void deletePetTest(){
      int testPetId = Integer.valueOf(RandomStringUtils.randomNumeric(5));
      String testPetName = RandomStringUtils.randomAlphabetic(5);

      PetNotFoundModel expectedResponse = new PetNotFoundModel(1,"error","Pet not found");

      PetModel testPet = new PetModel(testPetId, testPetName, null, new ArrayList<>(), new ArrayList<>(), "Available");
      PetController petController = new PetController(testPet);
      PetModel petResponse = petController.postNewPet();
      Assert.assertEquals(petResponse,testPet);
      petController.deletePet();
      PetNotFoundModel actualResponse = (PetNotFoundModel) petController.getPet();
      Assert.assertEquals(actualResponse,expectedResponse);
   }




}


