import java.util.Scanner;

public class Controller {

    private ShowHistory history;
    private Scanner scanner;

    public Controller() {
        this.history = new ShowHistory();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    addShowWorkflow();
                    break;
                case "2":
                    viewHistoryWorkflow();
                    break;
                case "3":
                    logExperienceWorkflow();
                    break;
                case "4":
                    markFavoriteWorkflow();
                    break;
                case "0":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("=== Live Music Tracker ===");
        System.out.println("1. Add a new show/festival");
        System.out.println("2. View show history");
        System.out.println("3. Log experience for a show");
        System.out.println("4. Mark a show as favorite");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    // Very basic, no validation yet (we'll refactor later)
    private void addShowWorkflow() {
        System.out.println("Adding a new show/festival...");

        System.out.print("Artist/event name: ");
        String name = scanner.nextLine();

        System.out.print("Date (e.g., 2025-11-19): ");
        String date = scanner.nextLine();

        System.out.print("Venue: ");
        String venue = scanner.nextLine();

        System.out.print("City: ");
        String city = scanner.nextLine();

        System.out.print("Is this a festival? (y/n): ");
        String festivalInput = scanner.nextLine();
        boolean isFestival = festivalInput.equalsIgnoreCase("y");

        Show show = new Show(name, date, venue, city, isFestival);
        history.addShow(show);

        System.out.println("Show added!");
    }

    private void viewHistoryWorkflow() {
        history.printAllShows();
    }

    private void logExperienceWorkflow() {
        System.out.print("Enter the artist/event name for the show you want to log: ");
        String name = scanner.nextLine();

        Show show = history.findShowByName(name);
        if (show == null) {
            System.out.println("Show not found in your history.");
            return;
        }

        System.out.print("Rating (1-10): ");
        int rating = readIntWithFallback();

        System.out.print("Friends who attended (comma-separated, or leave blank): ");
        String friends = scanner.nextLine();

        System.out.print("Notes about the show: ");
        String notes = scanner.nextLine();

        Experience exp = new Experience(rating, notes, friends);
        show.setExperience(exp);

        System.out.println("Experience logged for " + show.getArtistOrEventName() + ".");
    }

    private void markFavoriteWorkflow() {
        System.out.print("Enter the artist/event name for the show to favorite: ");
        String name = scanner.nextLine();

        Show show = history.findShowByName(name);
        if (show == null) {
            System.out.println("Show not found in your history.");
            return;
        }

        show.setFavorite(true);
        System.out.println("Marked as favorite: " + show.getArtistOrEventName());
    }

    // Super simple helper, no real error handling yet (messy)
    private int readIntWithFallback() {
        try {
            String line = scanner.nextLine();
            return Integer.parseInt(line);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid number, defaulting rating to 5.");
            return 5;
        }
    }
}
