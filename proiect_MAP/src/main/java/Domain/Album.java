package Domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Album {
    private final UUID albumId;
    private String title;
    private LocalDate releaseDate;
    private ArrayList<UUID> songs;
    private UUID artistId;
    private ArrayList<UUID> featuring;

    public Album(String title, UUID artistId, LocalDate releaseDate, ArrayList<UUID> featuring) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }

        if (artistId == null) {
            throw new IllegalArgumentException("Domain.Artist ID cannot be null.");
        }

        if (releaseDate == null) {
            throw new IllegalArgumentException("Release date cannot be null.");
        }

        this.albumId = UUID.randomUUID();
        this.title = title;
        this.artistId = artistId;
        this.releaseDate = releaseDate;
        this.songs = new ArrayList<>();
        this.featuring = featuring;
    }


    public UUID getId() {
        return albumId;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setArtistId(UUID artistId) {
        this.artistId = artistId;
    }

    public ArrayList<UUID> getSongs() {
        return songs;
    }

    public void setSongs(Song song) {
        if (song.getArtistId().equals(this.artistId))
            this.songs.add(song.getId());
        else
            throw new IllegalArgumentException();
    }

    public ArrayList<UUID> getFeaturing(){return featuring;}

    public void addFeaturing(UUID artistId){featuring.add(artistId);}

    public void setFeaturing(ArrayList<UUID> featuring){this.featuring=featuring;}


    @Override
    public String toString() {
        return "albumId: " + albumId + '\n' +
                "title: '" + title + '\'' + '\n' +
                "artist: " + artistId + '\n' +
                "featuring: " + featuring + '\n' +
                "songs: " +  songs + '\n' +
                "releaseDate=" + releaseDate + '\n';
    }
}
