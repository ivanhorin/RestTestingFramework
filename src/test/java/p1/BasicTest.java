package p1;

import controllers.PetController;
import models.CategoryModel;
import models.PetModel;
import models.PetNotFoundModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class BasicTest {

   PetModel testPet;
   PetController petController;

   @BeforeTest
   public void createPetModel(){
      int testPetId = Integer.valueOf(RandomStringUtils.randomNumeric(5));
      String testPetName = RandomStringUtils.randomAlphabetic(5);
      testPet = new PetModel(testPetId, testPetName, null, new ArrayList<>(), new ArrayList<>(), "Available");
      petController = new PetController();
   }

   @Test()
   public void addNewPetTest(){
      PetModel petResponse = petController.postNewPet(testPet);
      Assert.assertEquals(petResponse,testPet);
   }

   @Test
   public void deletePetTest(){
      PetNotFoundModel expectedResponse = new PetNotFoundModel(1,"error","Pet not found");
      PetModel petResponse = petController.postNewPet(testPet);
      Assert.assertEquals(petResponse,testPet);
      petController.deletePetById(testPet.getId());
      PetNotFoundModel actualResponse = (PetNotFoundModel) petController.getPetById(testPet.getId());
      Assert.assertEquals(actualResponse,expectedResponse);
   }

   @Test
   public void updatePetTest() {
      petController.postNewPet(testPet);
      testPet.setName("Bob");
      PetModel petResponse = petController.updatePet(testPet);
      Assert.assertEquals(petResponse,testPet);
   }

   @Test
   public void failingTest() {
      PetModel petResponse = petController.postNewPet(testPet);
      Assert.assertEquals(petResponse,
            new PetModel(32, "lala", new CategoryModel("Moo",3), new ArrayList<>(), new ArrayList<>(), "Available"));
   }


}


