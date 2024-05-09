package com.ray.musicbox.models;

public enum Category {
   POP("Pop"),
   ROCK("Rock"),
   METAL("Metal"),
   HIPHOP("hip-hop"),
   RAP("Rap"),
   REGGAE("Raggaeton"),
   CLASSICAL("Classical");

   private String category;

   Category(String category) {
      this.category = category;
   }

   public static Category fromString(String text) {
      for (Category category : Category.values()) {
         if (category.category.equalsIgnoreCase(text)) {
            return category;
         }
      }
      throw new IllegalArgumentException("No category found: " + text);
   }
}
