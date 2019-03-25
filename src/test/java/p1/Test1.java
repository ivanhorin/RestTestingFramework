package p1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

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
}


