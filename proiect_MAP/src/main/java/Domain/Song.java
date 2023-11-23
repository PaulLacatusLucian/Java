package Domain;

import Observers.Observer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Song{

    private final UUID songId;
    private UUID albumId;
    private UUID artistId;
    private UUID genreId;
    private String title;
    private int duration;
    private LocalDate releaseDate;
    private String lyrics;
    private ArrayList<UUID> featuring;



    public Song(UUID albumId, String title, UUID artistId, int duration, LocalDate releaseDate, String lyrics, ArrayList<UUID> featuring) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }

        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be a positive integer.");
        }

        if (releaseDate == null) {
            throw new IllegalArgumentException("Release date cannot be null.");
        }

        this.songId = UUID.randomUUID();
        this.albumId = albumId;
        this.title = title;
        this.artistId = artistId;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.lyrics = lyrics;
        this.featuring = featuring;
    }


    public UUID getId() {
        return songId;
    }

    public UUID getAlbum() {
        return albumId;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setAlbum(UUID albumId) {
        this.albumId = albumId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public UUID getArtistId() {
        return artistId;
    }

    public void setArtist(UUID albumId) {
        this.albumId = albumId;
    }

    public ArrayList<UUID> getFeaturing() {
        return featuring;
    }

    public void addFeaturing(UUID artistId) {
        featuring.add(artistId);
    }

    public void setFeaturing(ArrayList<UUID> featuring) {
        this.featuring = featuring;
    }

    public UUID getSongId() {
        return songId;
    }

    public void setGenreId(UUID genreId) {
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "Domain.Song = " + "songId = " + songId +
                ", albumId = " + albumId +
                ", title = '" + title + '\'' +
                ", artistId = '" + artistId +
                ", albumId = '" + albumId +
                ", duration = " + duration +
                ", releaseDate = " + releaseDate +
                ", lyrics = '" + lyrics + '\n';
    }
}

