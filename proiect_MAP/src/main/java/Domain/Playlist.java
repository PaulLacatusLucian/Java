package Domain;

import java.util.ArrayList;
import java.util.UUID;

public class Playlist {
    private final UUID playlistId;
    private String title;
    private ArrayList<UUID> songs;

    public Playlist(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }

        this.playlistId = UUID.randomUUID();
        this.songs = new ArrayList<>();
        this.title = title;
    }


    public UUID getId() {
        return playlistId;
    }

    public ArrayList<UUID> getSongs() {
        return songs;
    }

    public void setSongs(UUID songId) {
        this.songs.add(songId);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Domain.Playlist" +
                "playlistId = " + playlistId +
                ", songs = " + songs;
    }

    // fara setSongs numa user-ul pune songs
}
