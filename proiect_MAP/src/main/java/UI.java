import Domain.*;
import Helpers.GeneralHelper;

import java.lang.reflect.Array;
import java.util.*;
import java.time.LocalDate;

public class UI {

    public static void startUI() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        int account;

        Controller<User> userController = new Controller<>(UserRepo.getInstance());
        Controller<Song> songController = new Controller<>(SongRepo.getInstance());
        Controller<Album> albumController = new Controller<>(AlbumRepo.getInstance());
        Controller<Playlist> playlistController = new Controller<>(PlaylistRepo.getInstance());
        Controller<Artist> artistController = new Controller<>(ArtistRepo.getInstance());
        Controller<Genre> genreController = new Controller<>(GenreRepo.getInstance());
        UserFactory userFactory = new RegularUserFactory();
        UserFactory adminFactory = new AdminUserFactory();


        ArrayList<Song> songs = GeneralHelper.iaSongs();
        for (Song song : songs)
            songController.createObject(song);

        artistController.createObject(GeneralHelper.iaArtist());
        Artist guta = artistController.readAll().get(0);
        Song melodieGuta = new Song(null, "ASMDMLASDA", guta.getId(), 120, LocalDate.of(2003,03,03), "asdasdasd",null);
        songController.createObject(melodieGuta);

        albumController.createObject(GeneralHelper.iaAlbum());
        List<User> useri = GeneralHelper.iaUseri();
        for (User user : useri) {
            userController.createObject(user);
        }

        List<Genre> genres = GeneralHelper.iaGenre();
        for (Genre genere : genres)
            genreController.createObject(genere);

        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tWelcome to SpotyNai!\n");
        System.out.println("1. Sign up\n2. Log in");

        System.out.print("Enter your choice: ");
        account = scanner.nextInt();


        switch (account) {
            case 1:
                System.out.println("You selected Option 1 (Sign up).");
                System.out.println("Choose user type:\n1. Regular User\n2. Admin User");
                int userTypeChoice = scanner.nextInt();
                String userType;

                if (userTypeChoice == 1) {
                    userType = "User";
                } else {
                    userType = "Admin";
                }

                String username = "";
                boolean userNameIsOk = false;
                while (!userNameIsOk) {
                    System.out.print("Enter username: ");
                    username = scanner.next();

                    userNameIsOk = true;
                    for (User existingUser : userController.readAll()) {
                        if (existingUser.getUsername().equals(username)) {
                            System.out.println("Username already taken, try something else.");
                            userNameIsOk = false;
                            break;
                        }
                    }
                }

                String email = "";
                boolean emailIsOk = false;
                while (!emailIsOk) {
                    System.out.print("Enter email: ");
                    email = scanner.next();

                    emailIsOk = true;
                    for (User existingUser : userController.readAll()) {
                        if (existingUser.getEmail().equals(email)) {
                            System.out.println("Email already taken, try something else.");
                            emailIsOk = false;
                            break;
                        }
                    }

                    if (emailIsOk && !GeneralHelper.isValidEmail(email)) {
                        System.out.println("Invalid email format, try again.");
                        emailIsOk = false;
                    }
                }

                String password;
                do {
                    System.out.print("Enter password: ");
                    password = scanner.next();

                    if (!GeneralHelper.isValidPassword(password)) {
                        System.out.println("The password is not meeting the requirements");
                    }
                } while (!GeneralHelper.isValidPassword(password));

                System.out.print("Enter date of birth (yyyy-MM-dd): ");
                LocalDate dateOfBirth = LocalDate.parse(scanner.next());
                User newUser=null;
                if(userType.equals("User")){
                    newUser = userFactory.createUser(username, email, password, dateOfBirth);
                } else if (userType.equals("Admin")) {
                    newUser = adminFactory.createUser(username, email, password, dateOfBirth);
                }
                userController.createObject(newUser);
                System.out.println("User created: " + newUser);
            case 2:
                int maxLoginAttempts = 3;

                for (int loginAttempt = 1; loginAttempt <= maxLoginAttempts; loginAttempt++) {
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.next();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.next();

                    User loggedInUser = null;
                    for (User existingUser : userController.readAll()) {
                        if (existingUser.getUsername().equals(loginUsername) && existingUser.getPassword().equals(loginPassword)) {
                            loggedInUser = existingUser;
                        }
                    }
                    if (loggedInUser != null) {
                        System.out.println("Login successful. Welcome, " + loggedInUser.getUsername() + "!");
                        if (Objects.equals(loggedInUser.getUserType(), "Admin")) {
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tWelcome to SpotyNai!\n");
                            System.out.println("What would you like to do?\n");
                            System.out.println("1. Create a user\t\t2. Create a song\t\t3. Create an album");
                            System.out.println("4. Create a playlist\t5. List all users\t\t6. List all songs\t\t7. List all albums\t\t8. List all playlists");
                            System.out.println("9. Update a user\t\t10. Delete a user\t\t11. Create an artist\t12. Delete an artist\t13. Update an artist");
                            System.out.println("14. List all artists\t15. Create genre\t\t16. Delete genre\t\t17. Update genre\t\t18. List all genres");
                            System.out.println("19. Subscribe Artist\t20. Renew users membership\t21. Get membership info\t22. Exit");


                            do {
                                System.out.print("Enter your choice (1-22): ");
                                choice = scanner.nextInt();

                                switch (choice) {
                                    case 1:
                                        System.out.println("You selected Option 1 (Create a user).");
                                        System.out.print("Enter username: ");
                                        String username1 = scanner.next();
                                        System.out.print("Enter email: ");
                                        String email1 = scanner.next();
                                        System.out.print("Enter password: ");
                                        String password1 = scanner.next();
                                        System.out.print("Enter date of birth (yyyy-MM-dd): ");
                                        LocalDate dateOfBirth1 = LocalDate.parse(scanner.next());
                                        System.out.print("Enter users role: ");
                                        String userTyp = scanner.next();
                                        User newUser1 = new User(username1, email1, password1, dateOfBirth1);
                                        newUser1.setUserType(userTyp);
                                        userController.createObject(newUser1);
                                        System.out.println("Domain.User created: " + newUser1);
                                        break;
                                    case 2:
                                        System.out.println("You selected Option 2 (Create a song).");
                                        System.out.print("Enter title: ");
                                        String title = scanner.next();
                                        System.out.print("Enter artistId: ");
                                        UUID artist = UUID.fromString(scanner.next());
                                        System.out.print("Enter albumId: ");
                                        String album = scanner.next();
                                        System.out.print("Enter duration (in seconds): ");
                                        int duration = scanner.nextInt();
                                        System.out.print("Enter release date (yyyy-MM-dd): ");
                                        LocalDate releaseDate = LocalDate.parse(scanner.next());
                                        System.out.print("Enter lyrics: ");
                                        scanner.nextLine();
                                        String lyrics = scanner.nextLine();


                                        ArrayList<UUID> featuring2 = new ArrayList<>();
                                        System.out.print("Enter featuring artists (comma-separated UUIDs): ");
                                        String featuringInput = scanner.next();
                                        String[] featuringIds = featuringInput.split(",");
                                        for (String artistId2 : featuringIds) {
                                            featuring2.add(UUID.fromString(artistId2));
                                        }

                                        Song newSong = new Song(UUID.fromString(album), title, artist, duration, releaseDate, lyrics, featuring2);
                                        songController.createObject(newSong);
                                        System.out.println("Domain.Song created: " + newSong);
                                        for (Artist artist2 : artistController.readAll())
                                            if (artist2.getId().equals(artist)) {
                                                artist2.addSong(newSong);
                                            }
                                        break;
                                    case 3:
                                        System.out.println("You selected Option 3 (Create an album).");
                                        System.out.print("Enter title: ");
                                        String albumTitle = scanner.next();
                                        System.out.print("Enter artist: ");
                                        String albumArtist = scanner.next();
                                        System.out.print("Enter release date (yyyy-MM-dd): ");
                                        LocalDate albumReleaseDate = LocalDate.parse(scanner.next());

                                        ArrayList<UUID> featuring3 = new ArrayList<>();
                                        System.out.print("Enter featuring artists (comma-separated UUIDs): ");
                                        String featuringInput2 = scanner.next();
                                        String[] featuringIds2 = featuringInput2.split(",");
                                        for (String artistId4 : featuringIds2) {
                                            featuring3.add(UUID.fromString(artistId4));
                                        }

                                        Album newAlbum = new Album(albumTitle, UUID.fromString(albumArtist), albumReleaseDate, featuring3);
                                        albumController.createObject(newAlbum);
                                        System.out.println("Domain.Album created: " + newAlbum);
                                        break;
                                    case 4:
                                        System.out.println("You selected Option 4 (Create a playlist).");
                                        System.out.print("Enter playlist title");
                                        String songTitleInput = scanner.next();

                                        Playlist newPlaylist = new Playlist(songTitleInput);
                                        playlistController.createObject(newPlaylist);
                                        System.out.println("Domain.Playlist created: " + newPlaylist);
                                        break;
                                    case 5:
                                        System.out.println("You selected Option 5 (List all users).");
                                        ArrayList<User> allUsers = userController.readAll();
                                        for (User user : allUsers) {
                                            System.out.println(user);
                                        }
                                        break;
                                    case 6:
                                        System.out.println("You selected Option 6 (List all songs).");
                                        ArrayList<Song> allSongs = songController.readAll();
                                        for (Song song : allSongs) {
                                            System.out.println(song);
                                        }
                                        break;
                                    case 7:
                                        System.out.println("You selected Option 7 (List all albums).");
                                        ArrayList<Album> allAlbums = albumController.readAll();
                                        for (Album album1 : allAlbums) {
                                            System.out.println(album1);
                                        }
                                        break;
                                    case 8:
                                        System.out.println("You selected Option 8 (List all playlists).");
                                        ArrayList<Playlist> allPlaylists = playlistController.readAll();
                                        for (Playlist playlist : allPlaylists) {
                                            System.out.println(playlist);
                                        }
                                        break;
                                    case 9:
                                        System.out.println("You selected Option 9 (Update a user).");
                                        System.out.print("Enter the UUID of the user to update: ");
                                        UUID userIdToUpdate = UUID.fromString(scanner.next());
                                        System.out.print("Enter updated username: ");
                                        username = scanner.next();
                                        System.out.print("Enter updated email: ");
                                        email = scanner.next();
                                        System.out.print("Enter updated password: ");
                                        password = scanner.next();
                                        System.out.print("Enter updated date of birth (yyyy-MM-dd): ");
                                        LocalDate date = LocalDate.parse(scanner.next());
                                        User user = new User(username, email, password, date);
                                        userController.updateObject(user, userIdToUpdate);
                                        System.out.println("Domain.User updated: " + user);
                                        break;
                                    case 10:
                                        System.out.println("You selected Option 10 (Delete a user).");
                                        System.out.print("Enter the UUID of the user to delete: ");
                                        UUID userIdToDelete = UUID.fromString(scanner.next());
                                        User deletedUser = userController.deleteObject(userIdToDelete);
                                        if (deletedUser != null) {
                                            System.out.println("Domain.User deleted: " + deletedUser);
                                        } else {
                                            System.out.println("Domain.User not found with the specified UUID.");
                                        }
                                        break;
                                    case 11:
                                        System.out.println("You selected Option 11 (Create an artist).");
                                        System.out.print("Enter name: ");
                                        String artistName = scanner.next();
                                        System.out.print("Enter the artist bio: ");
                                        scanner.nextLine();
                                        String artistBio = scanner.nextLine();
                                        Artist newArtist = new Artist(artistName, artistBio);
                                        artistController.createObject(newArtist);
                                        System.out.println("Domain.Artist created: " + newArtist);
                                        break;
                                    case 12:
                                        System.out.println("You selected Option 12 (Delete an artist).");
                                        System.out.print("Enter the UUID of the artist to delete: ");
                                        UUID artistIdToDelete = UUID.fromString(scanner.next());
                                        Artist deletedArtist = artistController.deleteObject(artistIdToDelete);
                                        if (deletedArtist != null) {
                                            System.out.println("Domain.Artist deleted: " + deletedArtist);
                                        } else {
                                            System.out.println("Domain.Artist not found with the specified UUID.");
                                        }
                                        break;
                                    case 13:
                                        System.out.println("You selected Option 9 (Update an artist).");
                                        System.out.print("Enter the UUID of the user to update: ");
                                        UUID artistIdToUpdate = UUID.fromString(scanner.next());
                                        System.out.print("Enter updated name: ");
                                        String name = scanner.next();
                                        System.out.print("Enter updated bio: ");
                                        String bio = scanner.next();
                                        Artist _newArtist = new Artist(name, bio);
                                        artistController.updateObject(_newArtist, artistIdToUpdate);
                                        System.out.println("Domain.Artist updated: " + _newArtist);
                                        break;
                                    case 14:
                                        System.out.println("You selected Option 14 (List all artists).");
                                        ArrayList<Artist> allArtists = artistController.readAll();
                                        for (Artist artist1 : allArtists) {
                                            System.out.println(artist1);
                                        }
                                        break;
                                    case 15:
                                        System.out.println("You selected Option 15 (Create genre).");
                                        System.out.print("Enter name: ");
                                        String genreName = scanner.next();
                                        System.out.print("Enter description: ");
                                        scanner.nextLine();
                                        String genreDescription = scanner.nextLine();
                                        Genre newGenre = new Genre(genreName, genreDescription);
                                        genreController.createObject(newGenre);
                                        System.out.println("Domain.Genre created: " + newGenre);
                                        break;
                                    case 16:
                                        System.out.println("You selected Option 16 (Delete genre).");
                                        System.out.print("Enter the UUID of the genre to delete: ");
                                        UUID genreIdToDelete = UUID.fromString(scanner.next());
                                        Genre deletedGenre = genreController.deleteObject(genreIdToDelete);
                                        if (deletedGenre != null) {
                                            System.out.println("Domain.Genre deleted: " + deletedGenre);
                                        } else {
                                            System.out.println("Domain.Genre not found with the specified UUID.");
                                        }
                                        break;
                                    case 17:
                                        System.out.println("You selected Option 17 (Update genre");
                                        System.out.print("Enter the UUID of the user to update: ");
                                        UUID genreIdUpdated = UUID.fromString(scanner.next());
                                        System.out.print("Enter updated name: ");
                                        String updatedName = scanner.next();
                                        System.out.print("Enter updated bio: ");
                                        String updatedDescription = scanner.next();
                                        Genre _newGenre = new Genre(updatedName, updatedDescription);
                                        genreController.updateObject(_newGenre, genreIdUpdated);
                                        System.out.println("Domain.Artist updated: " + _newGenre);
                                    case 18:
                                        System.out.println("You selected Option 17 (List all genres).");
                                        ArrayList<Genre> allGenres = genreController.readAll();
                                        for (Genre genre : allGenres) {
                                            System.out.println(genre);
                                        }
                                    case 19:
                                        User user69 = new User();
                                        System.out.println("Who are you?");
                                        System.out.println(userController.readAll());
                                        UUID userUUID = UUID.fromString(scanner.next());
                                        System.out.println("Which artist do you want to follow? ");
                                        for (User user2 : userController.readAll())
                                            if (user2.getId().equals(userUUID))
                                                user69 = user2;
                                        System.out.println(artistController.readAll());
                                        UUID artistUUID = UUID.fromString(scanner.next());
                                        for (Artist artist2 : artistController.readAll())
                                            if (artist2.getId().equals(artistUUID))
                                                artist2.addObserver(user69);
                                        break;
                                    case 20:
                                        User user68 = new User();
                                        System.out.println("Who are you?");
                                        System.out.println(userController.readAll());
                                        UUID userUUID1 = UUID.fromString(scanner.next());
                                        for (User user2 : userController.readAll())
                                            if (user2.getId().equals(userUUID1))
                                                user68 = user2;
                                        if (user68.getMembership() == null) {
                                            Membership membership = new Membership();
                                            user68.setMembership(membership);
                                        }

                                        System.out.println("How do you want to pay 30 RON? (Visa, MasterCard, Revolut)");
                                        String paymentMet = scanner.next();
                                        if (paymentMet.equals("Visa")) {
                                            VisaPayment visaPayment = new VisaPayment();
                                            user68.renewMembership(visaPayment);
                                        } else if (paymentMet.equals("MasterCard")) {
                                            MasterCardPayment masterCardPayment = new MasterCardPayment();
                                            user68.renewMembership(masterCardPayment);
                                        } else if (paymentMet.equals("Revolut")) {
                                            RevolutPayment revolutPayment = new RevolutPayment();
                                            user68.renewMembership(revolutPayment);
                                        } else
                                            throw new IllegalArgumentException("Wrong payment methode");
                                        break;
                                    case 21:
                                        User user67 = new User();
                                        System.out.println("Who are you?");
                                        System.out.println(userController.readAll());
                                        UUID userUUID2 = UUID.fromString(scanner.next());
                                        for (User user2 : userController.readAll())
                                            if (user2.getId().equals(userUUID2))
                                                user67 = user2;
                                        if (user67.getMembership() != null)
                                            System.out.println(user67.getMembership().toString());
                                        else
                                            throw new IllegalArgumentException("This user has no membership");
                                        break;
                                    case 22:
                                        System.out.println("Goodbye!");
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please select a valid option (1-22).");
                                }
                            } while (choice != 22);
                            scanner.close();
                        } else if (Objects.equals(loggedInUser.getUserType(), "User")) {
                            int userChoice;
                            System.out.println("What would you like to do?\n");
                            System.out.printf("%-25s%-25s%-35s\n", "1. Search song", "2. Search artist", "3. Search playlist");
                            System.out.printf("%-25s%-30s%-35s\n", "4. Create playlist", "5. View all songs by artist", "6. Explore Genres");
                            System.out.printf("%-25s%-25s%-35s\n", "7. Subscribe to Artists", "8. Renew Membership", "9. Log out");


                            do {
                                System.out.println("Please eneter your choice: ");
                            userChoice = scanner.nextInt();
                                switch (userChoice) {
                                    case 1:
                                        String songName;
                                        System.out.println("Give the songs name: ");
                                        songName = scanner.next();
                                        ArrayList<Song> songs1 = SongRepo.searchForSong(songName);
                                        System.out.println(songs1);

                                        break;
                                    case 2:
                                        String artistName;
                                        System.out.println("Give the artist name: ");
                                        artistName = scanner.next();
                                        ArrayList<Artist> artistSearch =  ArtistRepo.searchForArtist(artistName);
                                        if(artistSearch.isEmpty()){
                                            System.out.println("No artist found...");
                                        } else {
                                            System.out.println("No artist found...");
                                        }
                                        break;
                                    case 3:
                                        String playListName;
                                        System.out.println("Give the playlists name: ");
                                        playListName = scanner.next();
                                        ArrayList<Playlist> playlists = PlaylistRepo.searchForAlbum(playListName);;
                                        if(playlists.isEmpty()){
                                            System.out.println("No songs found...");
                                        }else {
                                            System.out.println(playlists);
                                        }
                                        break;
                                    case 4:
                                        System.out.println("You selected Option 4 (Create a playlist).");
                                        System.out.print("Enter playlist title");
                                        String songTitleInput = scanner.next();

                                        Playlist newPlaylist = new Playlist(songTitleInput);
                                        playlistController.createObject(newPlaylist);
                                        System.out.println("Domain.Playlist created: " + newPlaylist);
                                        break;
                                    case 5:
                                        System.out.println("Get songs using artist name");
                                        System.out.println("Give artist name: ");
                                        String artistName1 = scanner.next();
                                        ArrayList<Artist> matchingArtists = new ArrayList<>();
                                        ArtistRepo.searchForArtist(artistName1);
                                        matchingArtists = ArtistRepo.searchForArtist(artistName1);
                                        if (matchingArtists.size() > 1) {
                                            System.out.println("There are more artist with the same name:");
                                            int contor = 1;
                                            for (Artist matchingArtist : matchingArtists) {
                                                System.out.println(matchingArtist.getName() + " " + contor);
                                                contor += 1;
                                            }
                                            int correctOne;
                                            System.out.println("Choose one:");
                                            correctOne = scanner.nextInt();
                                            System.out.println(SongRepo.getSongsByArtist(matchingArtists.get(correctOne).getId()));
                                        } else {
                                            System.out.println(SongRepo.getSongsByArtist(matchingArtists.get(0).getId()));
                                        }
                                        break;
                                    case 6:
                                        System.out.println(GenreRepo.getInstance().readAll());
                                        break;
                                    case 7:
                                        System.out.println("Which artist do you want to follow? ");
                                        System.out.println(artistController.readAll());
                                        UUID artistUUID = UUID.fromString(scanner.next());
                                        for (Artist artist2 : artistController.readAll())
                                            if (artist2.getId().equals(artistUUID))
                                                artist2.addObserver(loggedInUser);
                                        break;
                                    case 8:
                                        if (loggedInUser.getMembership() == null) {
                                            Membership membership = new Membership();
                                            loggedInUser.setMembership(membership);
                                            System.out.println("How do you want to pay 30 RON? (Visa, MasterCard, Revolut)");
                                            String paymentMet = scanner.next();
                                            if (paymentMet.equals("Visa")) {
                                                VisaPayment visaPayment = new VisaPayment();
                                                loggedInUser.renewMembership(visaPayment);
                                            } else if (paymentMet.equals("MasterCard")) {
                                                MasterCardPayment masterCardPayment = new MasterCardPayment();
                                                loggedInUser.renewMembership(masterCardPayment);
                                            } else if (paymentMet.equals("Revolut")) {
                                                RevolutPayment revolutPayment = new RevolutPayment();
                                                loggedInUser.renewMembership(revolutPayment);
                                            } else
                                                throw new IllegalArgumentException("Wrong payment methode");
                                            break;
                                        }
                                    case 9:
                                        System.out.println("Log out succesful!");
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please select a valid option (1-9).");

                                }
                            }while (userChoice!=9);

                        } else {
                            System.out.println("Invalid username or password. Login attempt " + loginAttempt + " of " + maxLoginAttempts + " failed.");
                            if (loginAttempt == maxLoginAttempts) {
                                System.out.println("Maximum login attempts reached. Exiting...");
                                System.exit(0);
                            }
                        }
                    } else if (loggedInUser == null) {
                        System.out.println("Invalid username or password. Login attempt " + loginAttempt + " of " + maxLoginAttempts + " failed.");
                        if (loginAttempt == maxLoginAttempts) {
                            System.out.println("Maximum login attempts reached. Exiting...");
                            System.exit(0);
                        }
                    }
                }
        }
    }
}
