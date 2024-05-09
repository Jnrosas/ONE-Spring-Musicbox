package com.ray.musicbox.models;

import jakarta.persistence.*;

@Entity
public class Song {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   private String title;
   private double duration;

   @Enumerated(EnumType.STRING)
   private Category genre;

   @ManyToOne()
   private Singer singer;


   public Song(){};

   public Song(String title, double duration, String genre) {
      this.title = title;
      this.duration = duration;
      this.genre = Category.fromString(genre);
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public double getDuration() {
      return duration;
   }

   public void setDuration(double duration) {
      this.duration = duration;
   }

   public Category getGenre() {
      return genre;
   }

   public void setGenre(Category genre) {
      this.genre = genre;
   }

   public Singer getSinger() {
      return singer;
   }

   public void setSinger(Singer singer) {
      this.singer = singer;
   }


   @Override
   public String toString() {
      return "id= " + id +
            ", Title= " + title +
            ", Singer= " + singer.getName() +
            ", Duration= " + duration +
            ", Genre= " + genre;

   }
}
