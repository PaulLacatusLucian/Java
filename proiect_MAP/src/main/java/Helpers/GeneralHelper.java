package Helpers;

import Domain.Album;
import Domain.Artist;
import Domain.Song;
import Domain.User;
import Domain.Genre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GeneralHelper {
    public static boolean isValidEmail(String email) {
        String[] parts = email.split("@");
        if (parts.length != 2) {
            return false;
        }

        if (parts[0].length() < 2) {
            return false;
        }
        return true;
    }

    public static boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char character : password.toCharArray()) {
            if (Character.isUpperCase(character)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(character)) {
                hasLowercase = true;
            } else if (Character.isDigit(character)) {
                hasDigit = true;
            } else if (isSpecialCharacter(character)) {
                hasSpecialChar = true;
            }

            if (hasUppercase && hasLowercase && hasDigit && hasSpecialChar) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSpecialCharacter(char character) {
        String specialCharacters = "!@#$%?_-+=^&*()[]{}<>;:'/";
        return specialCharacters.indexOf(character) >= 0;
    }

    public static ArrayList<Song> iaSongs() {
        ArrayList<UUID> featuring = new ArrayList<>();

        Song song1 = new Song(
                UUID.randomUUID(),
                "Draperiile",
                null,
                180,
                LocalDate.of(2023, 10, 30),
                "Aceasta este o piesa minunata.",
                featuring
        );

        Song song2 = new Song(
                UUID.randomUUID(),
                "Drog",
                null,
                240,
                LocalDate.of(2023, 11, 15),
                "Versuri pentru piesa a doua.",
                featuring
        );
        featuring.add(UUID.randomUUID());

        Song song3 = new Song(
                UUID.randomUUID(),
                "Alexandru viata mea?",
                null,
                300,
                LocalDate.of(2023, 12, 1),
                "Aceasta este o piesa speciala cu versuri frumoase.",
                featuring
        );
        featuring.add(UUID.randomUUID());
        featuring.add(UUID.randomUUID());

        Song song4 = new Song(
                UUID.randomUUID(),
                "Impodobeste mama bradu!",
                null,
                210,
                LocalDate.of(2023, 9, 20),
                null,
                featuring
        );

        Song song5 = new Song(
                UUID.randomUUID(),
                "alta melodie",
                null,
                420,
                LocalDate.of(2023, 8, 10),
                "Versuri pentru piesa a cincea.",
                featuring
        );
        ArrayList<Song> songs = new ArrayList<Song>();
        songs.add(song1);
        songs.add(song2);
        songs.add(song3);
        songs.add(song4);
        songs.add(song5);
        return songs;
    }

    public static Album iaAlbum() {
        Artist guta = iaArtist();
        Album fericire = new Album("Fericire", guta.getId(), LocalDate.of(2023, 8, 10), null);
        Song song1 = new Song(fericire.getId(), "LALELE", guta.getId(), 200, LocalDate.of(2023, 8, 10), "KAKAEKAKEKAEKALALALAL alLALLA", null);
        fericire.setSongs(song1);
        return fericire;
    }

    public static Artist iaArtist() {
        Artist artist = new Artist("Guta", "Ne place mult!");
        return artist;
    }

    public static List<User> iaUseri() {
        User user1 = new User("alexandru", "alexutusefutau@gmail.com", "Parola2021!", LocalDate.of(2023, 8, 11));
        user1.setUserType("User");
        User user2 = new User("Paul", "paul.lacatus@gmail.com", "Parola2021!", LocalDate.of(2023, 8, 11));
        user2.setUserType("Admin");
        User user3 = new User("udar", "alexutusefutau@gmail.com", "Parola2021!", LocalDate.of(2023, 8, 11));
        user3.setUserType("User");
        User user4 = new User("radu.gajdos", "gajdosradu@gmail.com", "Parola2021!", LocalDate.of(2003, 6, 11));
        user4.setUserType("Admin");

        List<User> result = new ArrayList<>();
        result.add(user1);
        result.add(user2);
        result.add(user3);
        result.add(user4);
        return result;
    }

    public static List<Genre> iaGenre() {
        Genre genre1 = new Genre("Pop", "Pop music: a genre characterized by catchy melodies, relatable lyrics, and widespread appeal, often driven by commercial success and popular culture influence.");
        Genre genre2 = new Genre("Manele", "Manele music: a genre originating in Romania, known for its blend of Balkan folk, Turkish, and Roma influences, featuring emotional vocals and often controversial lyrics.");
        Genre genre3 = new Genre("Rock", "Rock music: a diverse genre marked by amplified instruments, dynamic rhythms, and rebellious spirit, spanning various subgenres from classic and punk to alternative and metal.");
        List<Genre> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);
        genres.add(genre3);

        return genres;
    }
}

     