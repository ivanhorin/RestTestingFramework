package models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PetModel {

   @JsonProperty("id")
   private Integer id;
   @JsonProperty("name")
   private String name;
   @JsonProperty("category")
   private String category;
   @JsonProperty("photoUrls")
   private List<String> photoUrls;
   @JsonProperty("tags")
   private List<String> tags;
   @JsonProperty("status")
   private String status;

   public PetModel(Integer id, String name, String category, List<String> photoUrls, List<String> tags, String status) {
      this.id = id;
      this.name = name;
      this.category = category;
      this.photoUrls = photoUrls;
      this.tags = tags;
      this.status = status;
   }

   public Integer getId() {
      return id;
   }
   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Object getPhotoUrls() {
      return photoUrls;
   }

   public void setPhotoUrls(List<String> photoUrls) {
      this.photoUrls = photoUrls;
   }

   public Object getTags() {
      return tags;
   }

   public void setTags(List<String> tags) {
      this.tags = tags;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

}
