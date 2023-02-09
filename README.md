# scoreboard

## assumptions

- in a real world app probably all domain objects would have an id
- ScoreboardService would be stateless but here I assume it would be a sigleton
with a reference to a single scoreboard
- teams should have a TeamService that would handle logic and validation e.g. for duplicate names