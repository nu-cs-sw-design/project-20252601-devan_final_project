public class Experience {

    private int rating; // maybe 1-10
    private String notes;
    private String friends; // just a comma-separated string for now (messy on purpose)

    public Experience(int rating, String notes, String friends) {
        this.rating = rating;
        this.notes = notes;
        this.friends = friends;
    }

    public int getRating() {
        return rating;
    }

    public String getNotes() {
        return notes;
    }

    public String getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        return "Rating: " + rating +
                ", Friends: " + friends +
                ", Notes: " + notes;
    }
}
