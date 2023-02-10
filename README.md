# Scoreboard coding exercise

## General idea

The code attempts to mimic the business logic layer of the classic [3-tier architecture](https://en.wikipedia.org/wiki/Multitier_architecture).

The static domain model is (mostly) separated from the logic itself to resemble a real world ORM-based app 
and logic is enclosed in the _ScoreboardService_ class.

A simple command line application is purely for demonstration purposes and is using the data provided in the example
(for the sake of simplicity it's contained in the same maven module).

## Other assumptions and simplifications
- DAO layer was omitted for simplicity and replaced with an in-memory reference to the Scoreboard class.
We assume that _Scoreboard_ and _ScoreboardService_ are in a 1:1 relationship. A more OOP-classic-like alternative
could be merging them together.
- To resemble real world, _Match_ and _Team_ also inherit from _EntityBase_. For the _Scoreboard_ class, we currently omit this part
due to the assumption it being a singleton-like object. 
- Teams should have a _TeamService_ (and _TeamDAO_) that would handle logic and validation e.g. for duplicate names - skipped for simplicity.
- Main class tries to demonstrate the principle of Dependency Injection handled normally by a container.
- Exposing public setters in the Match class seemingly breaks encapsulation but a real world app this would be
provided by using an API facade (layer) that would prevent from manipulating domain objects directly. Of course,
this doesn't protect us from poor programmers.
- _equals_ and _hashcode_ for the entities are implemented to adhere [hibernate style](https://docs.jboss.org/hibernate/stable/core.old/reference/en/html/persistent-classes-equalshashcode.html).
- min and max scores could be moved to a config file or config entity.