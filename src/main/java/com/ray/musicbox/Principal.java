package com.ray.musicbox;

import com.ray.musicbox.models.Singer;
import com.ray.musicbox.models.Song;
import com.ray.musicbox.repository.MusicRepository;
import org.hibernate.NonUniqueResultException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
   private Scanner scanner = new Scanner(System.in);
   private Song song;
   private Singer singer;
   private final MusicRepository repository;

   public Principal(MusicRepository repository) {
      this.repository = repository;
   }

   public void menu() {
      var option = -1;
      while (option != 0) {
         var menu = """
               
               1 - Register new Singer
               2 - Register new Song
               3 - Display all songs ordered by Id
               4 - Display all songs by x singer
               0 - Exit
               
               Your selection: 
               """;
         System.out.println(menu);
         option = scanner.nextInt();
         scanner.nextLine();

         switch (option) {
            case 1:
               registerNewSinger();
               break;
            case 2:
               registerNewSong();
               break;
            case 3:
               displayAllSongs();
               break;
            case 4:
               displayAllSongsBySinger();
               break;
            case 0:
               System.out.println("\nClosing app");
               break;
            default:
               System.out.println("\nInvalid option");
         }
      }
   }

   private void displayAllSongsBySinger() {
      System.out.println("\nEnter the singer's name: ");
      var singerName = scanner.nextLine();
      List<Song> songSought = repository.allSongsBySingerOrderById(singerName);
      songSought.forEach(System.out::println);
   }

   private void displayAllSongs() {
      List<Song> songs = repository.allSongsOrderById();
      songs.forEach(System.out::println);
   }

   private void registerNewSong() {
      var aux = true;
      while (aux) {
         System.out.println("\nWhose singer song do you wish to enter?: ");
         var singerName = scanner.nextLine();
         Optional<Singer> singer = repository.findByNameContainingIgnoreCase(singerName);

         if (singer.isPresent()) {
            System.out.println("\nEnter song's Title: ");
            String title = scanner.nextLine();
            try {
               Optional<Song> songTitle = repository.findSong(title);
               if (songTitle.isEmpty()) {
                  System.out.println("\nEnter song's Duration in min: ");
                  double duration = scanner.nextDouble();
                  scanner.nextLine();

                  System.out.println("\nEnter song's Genre: ");
                  String genre = scanner.nextLine();

                  song = new Song(title, duration, genre);
                  song.setSinger(singer.get());
                  singer.get().getSong().add(song);
                  repository.save(singer.get());

                  System.out.println("\nKeep registering? Y/N: ");
                  var answer = scanner.nextLine();
                  if (answer.equalsIgnoreCase("N")) {
                     aux = false;
                  }
               }
            }
            catch (NonUniqueResultException | IncorrectResultSizeDataAccessException e){
               System.out.println("Song already exists, do you wish to conitnue? Y/N");
               var answer = scanner.nextLine();
               if (answer.equalsIgnoreCase("n")) {
                  aux = false;
               }
            }
         } else System.out.println("\nSinger not found");
      }
   }

   private void registerNewSinger() {
      var aux = true;
      while (aux) {
         System.out.println("\nEnter singer's Name: ");
         var name = scanner.nextLine();

         Optional<Singer> singerN = repository.findByNameContainingIgnoreCase(name);
         if (singerN.isEmpty()) {
            singer = new Singer(name);
            repository.save(singer);
            System.out.println("\nKeep registering? Y/N: ");
            var answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("N")) {
               aux = false;
            }
         }
         else {
            System.out.println("Singer already exists, do you wish to continue? Y/N");
            var answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("n")) {
               aux = false;
            }
         }
      }
   }
}
