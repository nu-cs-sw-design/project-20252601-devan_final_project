import java.util.ArrayList;
import java.util.List;
import java.io.*;

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

    public void saveToFile(String filename) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (Show s : shows) {
                StringBuilder sb = new StringBuilder();
                sb.append(emptyIfNull(s.getArtistOrEventName())).append("||")
                        .append(emptyIfNull(s.getDate())).append("||")
                        .append(emptyIfNull(s.getVenue())).append("||")
                        .append(emptyIfNull(s.getCity())).append("||")
                        .append(s.isFestival()).append("||")
                        .append(s.isFavorite()).append("||");

                // Experience fields
                if (s.getExperience() != null) {
                    sb.append(s.getExperience().getRating());
                }
                sb.append("||");
                if (s.getExperience() != null) {
                    sb.append(emptyIfNull(s.getExperience().getFriends()));
                }
                sb.append("||");
                if (s.getExperience() != null) {
                    sb.append(emptyIfNull(s.getExperience().getNotes()));
                }

                out.println(sb.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving shows to file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            // No data yet, that's fine
            return;
        }

        shows.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|\\|", -1); // keep empty strings
                if (parts.length < 9) {
                    continue; // skip malformed lines
                }

                String name = parts[0];
                String date = parts[1];
                String venue = parts[2];
                String city = parts[3];
                boolean isFestival = Boolean.parseBoolean(parts[4]);
                boolean isFavorite = Boolean.parseBoolean(parts[5]);

                String ratingStr = parts[6];
                String friends = parts[7];
                String notes = parts[8];

                Show show = new Show(name, date, venue, city, isFestival);
                show.setFavorite(isFavorite);

                if (!ratingStr.isEmpty()) {
                    try {
                        int rating = Integer.parseInt(ratingStr);
                        Experience exp = new Experience(rating, notes, friends);
                        show.setExperience(exp);
                    } catch (NumberFormatException e) {
                        // ignore bad rating for now
                    }
                }

                shows.add(show);
            }
        } catch (IOException e) {
            System.out.println("Error loading shows from file: " + e.getMessage());
        }
    }

    private String emptyIfNull(String s) {
        return s == null ? "" : s;
    }

}
