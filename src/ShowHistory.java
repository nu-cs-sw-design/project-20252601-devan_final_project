import java.util.ArrayList;
import java.util.List;

public class ShowHistory {

    // In-memory only for now; no persistence yet
    private List<Show> shows = new ArrayList<>();

    public void addShow(Show show) {
        if (show != null) {
            shows.add(show);
        }
    }

    public List<Show> getAllShows() {
        return shows;
    }

    public Show findShowByName(String name) {
        if (name == null) {
            return null;
        }
        for (Show s : shows) {
            if (s.getArtistOrEventName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    // This is mixing domain logic with printing (intentionally messy)
    public void printAllShows() {
        if (shows.isEmpty()) {
            System.out.println("No shows logged yet.");
            return;
        }
        System.out.println("Your show history:");
        int i = 1;
        for (Show s : shows) {
            System.out.println(i + ". " + s.toString());
            i++;
        }
    }

    public void printShowsByArtist(String artist) {
        if (artist == null || artist.isEmpty()) {
            System.out.println("Artist name cannot be empty.");
            return;
        }

        boolean found = false;
        int i = 1;
        for (Show s : shows) {
            if (s.getArtistOrEventName().equalsIgnoreCase(artist)) {
                if (!found) {
                    System.out.println("Shows for artist/event: " + artist);
                }
                System.out.println(i + ". " + s.toString());
                found = true;
            }
            i++;
        }

        if (!found) {
            System.out.println("No shows found for artist/event: " + artist);
        }
    }

    public void printShowsByYear(String year) {
        if (year == null || year.isEmpty()) {
            System.out.println("Year cannot be empty.");
            return;
        }

        boolean found = false;
        int i = 1;
        for (Show s : shows) {
            String date = s.getDate(); // date stored as String
            if (date != null && date.startsWith(year)) {
                if (!found) {
                    System.out.println("Shows in year: " + year);
                }
                System.out.println(i + ". " + s.toString());
                found = true;
            }
            i++;
        }

        if (!found) {
            System.out.println("No shows found in year: " + year);
        }
    }

}
