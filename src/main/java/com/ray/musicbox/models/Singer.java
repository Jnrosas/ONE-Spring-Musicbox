package com.ray.musicbox.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Singer {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @Column(unique = true)
   private String name;

   @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private List<Song> song;


   public Singer(){};

   public Singer(String name) {
      this.name = name;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<Song> getSong() {
      return song;
   }

   public void setSong(List<Song> song) {
      this.song = song;
   }

   @Override
   public String toString() {
      return "id= " + id +
            ", name= " + name +
            ", song= " + song;
   }
}
