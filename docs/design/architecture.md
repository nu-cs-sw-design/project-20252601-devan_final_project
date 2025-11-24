
Quality Attributes (initial list + scenarios)
1. Usability
   1. Scenario: A first-time user opens the app and can add their first show and see it
      in their history within 1–2 minutes without reading documentation.
2. Modifiability
   1. Scenario: Later, I might want to add new fields to a show (e.g., ticket price, seat location) or add new features like attaching a setlist. These changes should be localized mainly to the domain classes and maybe one or two UI flows, not require rewriting the whole application.
3. Data Persistence / Reliability
   1. Scenario: When the user logs multiple shows and closes the app, the data should still be available the next time they open it. If the app crashes mid-session, previously saved shows should not be lost.
4. Performance (for typical data sizes)
   1. Scenario: A user with around a few hundred shows can open their history and
      filter it without noticeable lag on a normal laptop.
5. Portability / Simplicity of Deployment
   1. Scenario: The app should run as a simple Java application without requiring a complex setup. For now, a single JAR or basic run configuration is sufficient.
      
Ranking of Quality Attributes (most important first)
1. Usability – if the basic flows (add show, view history) are confusing, people won’t use it.
2. Modifiability – I expect the features and data fields to evolve as I think of new ideas, so I
   want the design to be easy to change.
3. Data Persistence / Reliability – losing a user’s show history would be very frustrating.
4. Performance – data sizes will be small at first, so performance matters but is not the
   main concern.
5. Portability / Deployment – I care about keeping it simple, but this is mostly satisfied by
   using plain Java.
   
Architectural Patterns (initial thoughts)
   1. I plan to use a layered architecture: a presentation layer (UI), a domain layer (shows, experiences, filtering logic), and a data layer (reading/writing shows to a file or simple storage). This matches the examples from the course and keeps concerns separated.
2. On top of that, I will loosely follow an MVC-style structure: the “view” (UI) will call into a “controller” class that coordinates use cases, and the domain classes will act as the “model”. This should help with modifiability and make it easier to refactor later.
