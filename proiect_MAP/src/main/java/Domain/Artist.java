package Domain;

import Observers.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Artist implements Subject{

    private List<User> observers = new ArrayList<>();
    private final UUID artistId;
    private String name;
    private String bio;
    //TODO country
    private List<Song> songs = new ArrayList<>();

    @Override
    public void addObserver(User user) {
        observers.add(user);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (User observer : this.observers) {
            observer.update(this);
        }
    }

    public Artist(String name, String bio) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        if (bio == null || bio.isEmpty()) {
            throw new IllegalArgumentException("Bio cannot be null or empty.");
        }

        this.artistId = UUID.randomUUID();
        this.name = name;
        this.bio = bio;
    }


    public UUID getId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Song> getSongs() {
        return songs;
    }


    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
    public void addSong(Song song) {
        this.songs.add(song);
        notifyObservers();
    }

    public List<User> getObservers() {
        return observers;
    }

    @Override
    public String toString() {
        return "Domain.Artist{" +
                "artistId=" + artistId +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}

