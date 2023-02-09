# Scoreboard coding exercise

## General idea

The code attempts to mimic the business logic layer of the classic [3-tier architecture](https://en.wikipedia.org/wiki/Multitier_architecture).

The static domain model is (mostly) separated from the logic itself to resemble a real world ORM-based app 
and logic is enclosed in the ScoreboardService class.

A simple command line application is purely for demonstration purposes and is using the data provided in the example
(for the sake of simplicity it's contained in the same maven module).

## Other assumptions and simplifications
- DAO layer was omitted for simplicity and replaced with an in-memory reference to the Scoreboard class.
We assume that Scoreboard and ScoreboardService are in a 1:1 relationship. A more OOP-classic-like alternative
could be merging them together.
- To resemble real world, Match and Team also inherit from EntityBase. For the Scoreboard class, we currently omit this part
due to the assumption it being a singleton-like object. 
- Teams should have a TeamService (and TeamDAO) that would handle logic and validation e.g. for duplicate names - skipped for simplicity.
- Main class tries to demonstrate the principle of Dependency Injection handled normally by a container.
- Exposing public setters in the Match class seemingly breaks encapsulation but a real world app this would be
provided by using an API facade (layer) that would prevent from manipulating domain objects directly. Of course,
this doesn't protect us from poor programmers (if we should hire such, is a topic for another time).