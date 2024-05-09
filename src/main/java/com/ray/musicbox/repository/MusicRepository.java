package com.ray.musicbox.repository;

import com.ray.musicbox.models.Singer;
import com.ray.musicbox.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MusicRepository extends JpaRepository<Singer, Long> {
   Optional<Singer> findByNameContainingIgnoreCase(String name);

   @Query("select so from Singer si join si.song so order by so.id")
   List<Song> allSongsOrderById();

   @Query("select so from Singer si join si.song so where si.name ilike %:name% order by so.id")
   List<Song> allSongsBySingerOrderById(String name);

   @Query("select so from Singer si join si.song so where so.title ilike :title")
   Optional<Song> findSong(String title);

}






