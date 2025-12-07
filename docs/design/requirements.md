1. Adding New Types of Export Formats (e.g., JSON, HTML, Markdown summaries)
   1. Change Description:
   Users may want their concert summaries exported as formatted files, such as JSON or HTML, instead of plain text. 
   2. How the Design Handles It: This change is handled very well. The design already uses the Strategy Pattern through the SummaryExporter interface. Adding a new summary type only requires writing a new class (e.g., JsonSummaryExporter) that implements the interface. No changes are needed in DefaultShowService, ShowController, or the UI. This is exactly the kind of extension point the design was built to support. 
   3. Result: Supported easily: open for extension, closed for modification.
2. Switching to a Database Instead of a Flat File (e.g., SQLite or MySQL)
   1. Change Description: The system might need more reliable or scalable storage, replacing the file-based repository with a database-backed one. 
   2. How the Design Handles It: The design handles this change well. The use of the ShowRepository interface means that the persistence logic is fully abstracted. A new DatabaseShowRepository could be introduced without modifying any higher layers. The only place that needs updating is the composition root (Main), where the concrete repository implementation is chosen. 
   3. Result: Supported well through the Repository pattern.
3. Supporting Multiple Users, Each With Their Own Show History
   1. Change Description: The system could expand to allow several users, each with separate accounts or profiles. 
   2. How the Design Handles It: This change is partially supported and partially not. The design currently assumes one global show list, with no notion of “user.” Supporting multiple users would require adding a User domain class and associating each show with a user ID. The repositories would also need to filter by user, and the service layer would need updated method signatures (e.g., addShow(User user, Show show)). 
   3. Result: Not directly supported: requires modifications to the domain model, repositories, and service interfaces. The design remains extendable, but this is not a minimally disruptive change.
4. Adding New Types of Filters (e.g., by rating, by friend name, by genre)
   1. Change Description: Users might want more advanced filtering options beyond artist, year, city, or event type. 
   2. How the Design Handles It: The ShowFilter class supports current filter fields, but adding new filters may require modifying the class to hold new attributes and updating the repository’s findByFilter implementation. The design encapsulates the filtering logic in one place, but it is not easily open to new filter types without modification. A more flexible design (e.g., separate Filter objects or a specification pattern) would handle this better. 
   3. Result: Partially supported: requires modifying existing classes, but the impact is localized.
5. Allowing Shows to Include More Detailed Event Data (e.g., ticket price, seat location, setlist)
   1. Change Description: Users may want the system to track additional details for each show or festival. 
   2. How the Design Handles It: The current domain model (Show, Experience) would need new fields and possibly new behaviors. This is a typical domain model evolution and is not something the design tries to hide. However, because the domain model is clearly separated from other layers, changes will mostly affect only the Show class and the repository’s serialization logic. The UI would also need corresponding updates. 
   3. Result: Supported in practice, but requires modifying domain and persistence classes. This is an acceptable trade-off: domain models naturally evolve over time.
6. Adding a Graphical User Interface (GUI) Instead of a Console UI
   1. Change Description: A future version might replace the console interface with a GUI (JavaFX, Swing, web UI, etc.). 
   2. How the Design Handles It: This change is very well supported thanks to the three-layer separation. The GUI would entirely replace MainView, but the ShowController, ShowService, repository, domain model, and exporters would all remain unchanged. Presentation logic is isolated from the rest of the system, which means switching to a completely different view technology is feasible without rewriting core functionality. 
   3. Result: Strongly supported: presentation layer is cleanly separated.
