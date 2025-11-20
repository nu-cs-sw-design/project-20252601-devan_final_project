import java.util.Objects;

public class Show {

    private String artistOrEventName;
    private String date; // keeping as String for now to avoid dealing with date types
    private String venue;
    private String city;
    private boolean festival;
    private boolean favorite;
    private Experience experience; // can be null if not logged yet

    public Show(String artistOrEventName, String date, String venue, String city, boolean festival) {
        this.artistOrEventName = artistOrEventName;
        this.date = date;
        this.venue = venue;
        this.city = city;
        this.festival = festival;
        this.favorite = false;
        this.experience = null;
    }

    // Very simple, not super-safe setters/getters (intentionally a bit messy)
    public String getArtistOrEventName() {
        return artistOrEventName;
    }

    public void setArtistOrEventName(String artistOrEventName) {
        this.artistOrEventName = artistOrEventName;
    }

    public String getDate() {
        return date;
    }

    public String getVenue() {
        return venue;
    }

    public String getCity() {
        return city;
    }

    public boolean isFestival() {
        return festival;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        String type = festival ? "Festival" : "Show";
        String base = type + ": " + artistOrEventName + " @ " + venue + " (" + city + ") on " + date;
        if (favorite) {
            base += " [FAVORITE]";
        }
        if (experience != null) {
            base += " | Rating: " + experience.getRating();
        }
        return base;
    }

    // Just in case we want to compare by name + date (not super robust)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Show)) return false;
        Show show = (Show) o;
        return Objects.equals(artistOrEventName, show.artistOrEventName)
                && Objects.equals(date, show.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistOrEventName, date);
    }
}
