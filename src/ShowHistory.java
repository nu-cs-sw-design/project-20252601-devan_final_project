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
}
