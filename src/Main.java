import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {

        DatabaseManager MusicLibrary = new DatabaseManager();
        boolean exit = false;
        int userOption;
        ArrayList<String> results;

        do {
            System.out.println("Enter Integer Option: \n" +
                    "1. Find songs an artist featured on\n" +
                    "2. What albums a group has produced\n" +
                    "3. Delete a song\n" +
                    "4. Create a new music artist\n" +
                    "5. View highest rated songs\n" +
                    "6. View highest rated books\n" +
                    "7. Show all songs created by a group\n" +
                    "8. Search albums based on genre\n" +
                    "9. Find group founded the earliest\n" +
                    "10. What is the longest song in the library");

            userOption = reader.nextInt();
            System.out.println();

            switch (userOption) {
                case 1:
                    System.out.println("Input creator ID:");
                    int creatorID = reader.nextInt();
                    try {
                        results = MusicLibrary.findArtistSongsFeaturedIn(creatorID);
                        for (String s: results) {
                            System.out.println(s);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case 2:
                    System.out.println("Input group ID:");
                    int groupID = reader.nextInt();
                    try {
                        results = MusicLibrary.findAlbumsByGroup(groupID);
                        for (String s: results) {
                            System.out.println(s);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case 3:
                    System.out.println("Input song ID:");
                    int songID = reader.nextInt();
                    try {
                        MusicLibrary.deleteSong(songID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case 4:

                    break;
                case 5:
                    try {
                        results = MusicLibrary.findHighestRatedSongs();
                        for (String s: results) {
                            System.out.println(s);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case 6:
                    try {
                        results = MusicLibrary.findHighestRatedBooks();
                        for (String s: results) {
                            System.out.println(s);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case 7:
                    System.out.println("Input group ID:");
                    groupID = reader.nextInt();
                    try {
                        results = MusicLibrary.findSongsByGroup(groupID);
                        for (String s: results) {
                            System.out.println(s);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case 8:
                    System.out.println("Input genre name:");
                    String genre = reader.nextLine();
                    try {
                        results = MusicLibrary.findAlbumsByGenre(genre);
                        for (String s: results) {
                            System.out.println(s);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 9:
                    try {
                        System.out.println(MusicLibrary.findEarliestGroup());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case 10:
                    try {
                        System.out.println(MusicLibrary.findLongestSong());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case 11:
                    exit = true;
                    System.out.println("Program Closed.");

                    break;
                default:
                    System.out.println("Invalid option passed.");
                    break;
            }

            System.out.println();
            System.out.println();


        } while (!exit);
    }
}