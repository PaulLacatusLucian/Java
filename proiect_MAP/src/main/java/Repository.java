import Domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public interface Repository<ObjectType> {
    void createObject(ObjectType obj);

    ArrayList<ObjectType> readAll();

    void updateObject(ObjectType obj, Object identifier);

    ObjectType deleteObject(Object identifier);
}


class UserRepo implements Repository<User> {
    private static UserRepo instance;
    private ArrayList<User> userList;

    private UserRepo() {
        userList = new ArrayList<>();
    }

    public static UserRepo getInstance() {
        if (instance == null)
            instance = new UserRepo();
        return instance;
    }

    @Override
    public void createObject(User user) {
        userList.add(user);
    }

    @Override
    public ArrayList<User> readAll() {
        return new ArrayList<>(userList);
    }

    @Override
    public void updateObject(User updatedUser, Object identifier) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals((UUID) identifier)) {
                userList.set(i, updatedUser);
                return;
            }
        }
    }

    @Override
    public User deleteObject(Object identifier) {
        User deletedUser = null;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals((UUID) identifier)) {
                deletedUser = userList.remove(i);
                break;
            }
        }
        return deletedUser;
    }
}

class SongRepo implements Repository<Song> {
    private static SongRepo instance;
    private static ArrayList<Song> songList;

    private SongRepo() {
        songList = new ArrayList<>();
    }

    public static SongRepo getInstance() {
        if (instance == null)
            instance = new SongRepo();
        return instance;
    }

    public static ArrayList<Song> getSongsByArtist(UUID artistId) {
        ArrayList<Song> songsByArtist = new ArrayList<>();
        System.out.println(songList);
        for (Song song : songList) {
            if (song.getArtistId() != null) {
                if (song.getArtistId().equals(artistId)) {
                    songsByArtist.add(song);
                }
            }
        }

        return songsByArtist;
    }


    @Override
    public void createObject(Song song) {
        songList.add(song);
    }

    @Override
    public ArrayList<Song> readAll() {
        return new ArrayList<>(songList);
    }

    @Override
    public void updateObject(Song updatedSong, Object identifier) {
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).getId().equals((UUID) identifier)) {
                songList.set(i, updatedSong);
                return;
            }
        }
    }

    @Override
    public Song deleteObject(Object identifier) {
        Song deletedSong = null;
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).getId().equals((UUID) identifier)) {
                deletedSong = songList.remove(i);
                break;
            }
        }
        return deletedSong;
    }

    public static ArrayList<Song> searchForSong(String songName) {
        ArrayList<Song> result = new ArrayList<>();
        for (Song song : songList) {
            if (song.getTitle().toLowerCase().contains(songName.toLowerCase())) {
                result.add(song);
            }
        }
        return result;
    }

}

class AlbumRepo implements Repository<Album> {
    private static AlbumRepo instance;
    private static ArrayList<Album> albumList;

    private AlbumRepo() {
        albumList = new ArrayList<>();
    }

    public static AlbumRepo getInstance() {
        if (instance == null)
            instance = new AlbumRepo();
        return instance;
    }


    @Override
    public void createObject(Album album) {
        albumList.add(album);
    }

    @Override
    public ArrayList<Album> readAll() {
        return new ArrayList<>(albumList);
    }

    @Override
    public void updateObject(Album updatedAlbum, Object identifier) {
        for (int i = 0; i < albumList.size(); i++) {
            if (albumList.get(i).getId().equals((UUID) identifier)) {
                albumList.set(i, updatedAlbum);
                return;
            }
        }
    }

    @Override
    public Album deleteObject(Object identifier) {
        Album deletedAlbum = null;
        for (int i = 0; i < albumList.size(); i++) {
            if (albumList.get(i).getId().equals((UUID) identifier)) {
                deletedAlbum = albumList.remove(i);
                break;
            }
        }
        return deletedAlbum;
    }

    public static ArrayList<Album> searchForAlbum(String albumName) {
        ArrayList<Album> result = new ArrayList<>();
        for (Album album : albumList) {
            if (album.getTitle().toLowerCase().contains(albumName.toLowerCase()))
                result.add(album);
        }
        return result;
    }

}

class PlaylistRepo implements Repository<Playlist> {
    private static PlaylistRepo instance;
    private static ArrayList<Playlist> playlistList;

    private PlaylistRepo() {
        playlistList = new ArrayList<>();
    }

    public static PlaylistRepo getInstance() {
        if (instance == null)
            instance = new PlaylistRepo();
        return instance;
    }


    @Override
    public void createObject(Playlist playlist) {
        playlistList.add(playlist);
    }

    @Override
    public ArrayList<Playlist> readAll() {
        return new ArrayList<>(playlistList);
    }

    @Override
    public void updateObject(Playlist updatedPlaylist, Object identifier) {
        for (int i = 0; i < playlistList.size(); i++) {
            if (playlistList.get(i).getId().equals((UUID) identifier)) {
                playlistList.set(i, updatedPlaylist);
                return;
            }
        }
    }

    @Override
    public Playlist deleteObject(Object identifier) {
        Playlist deletedPlaylist = null;
        for (int i = 0; i < playlistList.size(); i++) {
            if (playlistList.get(i).getId().equals((UUID) identifier)) {
                deletedPlaylist = playlistList.remove(i);
                break;
            }
        }
        return deletedPlaylist;
    }

    public static ArrayList<Playlist> searchForAlbum(String playlistName) {
        ArrayList<Playlist> result = new ArrayList<>();
        for (Playlist playlist : playlistList) {
            if (playlist.getTitle().toLowerCase().contains(playlistName.toLowerCase()))
                result.add(playlist);
        }
        return result;
    }
}

class ArtistRepo implements Repository<Artist> {
    private static ArtistRepo instance;
    private static ArrayList<Artist> artistsList;

    private ArtistRepo() {
        artistsList = new ArrayList<>();
    }

    public static ArtistRepo getInstance() {
        if (instance == null)
            instance = new ArtistRepo();
        return instance;
    }


    @Override
    public void createObject(Artist artist) {
        artistsList.add(artist);
    }

    @Override
    public ArrayList<Artist> readAll() {
        return new ArrayList<>(artistsList);
    }

    @Override
    public void updateObject(Artist updatedArtistlist, Object identifier) {
        for (int i = 0; i < artistsList.size(); i++) {
            if (artistsList.get(i).getId().equals((UUID) identifier)) {
                artistsList.set(i, updatedArtistlist);
                return;
            }
        }
    }

    @Override
    public Artist deleteObject(Object identifier) {
        Artist deletedArtist = null;
        for (int i = 0; i < artistsList.size(); i++) {
            if (artistsList.get(i).getId().equals((UUID) identifier)) {
                deletedArtist = artistsList.remove(i);
                break;
            }
        }
        return deletedArtist;
    }

    public static ArrayList<Artist> searchForArtist(String artistName) {
        ArrayList<Artist> result = new ArrayList<>();
        for (Artist artist : artistsList) {
            if (artist.getName().toLowerCase().contains(artistName.toLowerCase()))
                result.add(artist);
        }
        return result;
    }
}

class GenreRepo implements Repository<Genre> {
    private static GenreRepo instance;
    private ArrayList<Genre> genresList;

    private GenreRepo() {
        genresList = new ArrayList<>();
    }

    public static GenreRepo getInstance() {
        if (instance == null)
            instance = new GenreRepo();
        return instance;
    }


    @Override
    public void createObject(Genre genre) {
        genresList.add(genre);
    }

    @Override
    public ArrayList<Genre> readAll() {
        return new ArrayList<>(genresList);
    }

    @Override
    public void updateObject(Genre updatedGenreList, Object identifier) {
        for (int i = 0; i < genresList.size(); i++) {
            if (genresList.get(i).getId().equals((UUID) identifier)) {
                genresList.set(i, updatedGenreList);
                return;
            }
        }
    }

    @Override
    public Genre deleteObject(Object identifier) {
        Genre deletedGenre = null;
        for (int i = 0; i < genresList.size(); i++) {
            if (genresList.get(i).getId().equals((UUID) identifier)) {
                deletedGenre = genresList.remove(i);
                break;
            }
        }
        return deletedGenre;
    }
}