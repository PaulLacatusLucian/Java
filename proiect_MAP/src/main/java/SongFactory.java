import Domain.*;
import Helpers.GeneralHelper;

import java.util.*;
import java.time.LocalDate;

public class SongFactory {
    public void createPopSong(UUID albumId, String title, UUID aritstId , int duration, LocalDate releaseDate, String lyrics, ArrayList<UUID> featuring ) {
        UUID popGenreId = null;
        for (Genre genre : GenreRepo.getInstance().readAll()) {
            if (genre.getName().equals("Pop")) {
                popGenreId = genre.getId();
            }
        }
        Song newSong = new Song(albumId, title, aritstId, duration, releaseDate, lyrics, featuring);
        newSong.setGenreId(popGenreId);
    }
}
