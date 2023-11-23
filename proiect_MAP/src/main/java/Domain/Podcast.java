package Domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Podcast extends Song {
    public Podcast(UUID albumId, String title, UUID artistId, int duration, LocalDate releaseDate, String lyrics, ArrayList<UUID> featuring) {
        super(albumId, title, artistId, duration, releaseDate, lyrics, featuring);
    }
}
