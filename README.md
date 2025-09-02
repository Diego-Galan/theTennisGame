# The Tennis Game

A minimalist, single-class Kotlin project that implements the scoring logic for a single game of tennis. This project was designed to be a clean, readable, and testable code sample suitable for technical interviews.

## What's here

This repository contains a `TennisGame` class with a clean API to award points and get the current score. It is fully tested and includes a simple console-based demo. The focus is on clear, entry-level code with no external dependencies beyond Kotlin and JUnit.

## Quick Start

You can test and run the project using the included Gradle wrapper.

```bash
# Clean previous builds and run all unit tests
./gradlew clean test

# Run the console-based demo application
./gradlew run
```

## Project Structure

- `build.gradle.kts`: The build script defining dependencies (Kotlin 1.9.24, JUnit 5) and application settings.
- `src/main/kotlin/`:
  - `TennisGame.kt`: The core class with the scoring logic.
  - `Demo.kt`: A `main()` function to demonstrate the `TennisGame` class in action.
- `src/test/kotlin/`:
  - `TennisGameTest.kt`: Unit tests covering all scoring scenarios.

## API Surface

The public API of the `TennisGame` class is minimal:

```kotlin
// Awards a point to player 1 or 2.
fun awardPoint(playerId: Int)

// Returns the current score as a formatted string.
fun getScore(): String

// Resets the game to "Love–Love".
fun reset()
```

## Scoring Rules

The implementation follows standard tennis scoring rules for a single game:
1.  **Normal Score**: Points map from 0 to 3 as "Love", "15", "30", and "40".
2.  **Deuce**: If both players reach 40, the score is "Deuce". This also applies to subsequent ties at higher scores (e.g., 5-5).
3.  **Advantage**: After Deuce, the player who scores next gains "Advantage". If the other player scores, the score returns to "Deuce".
4.  **Win**: A player wins if they have at least 4 points and are at least 2 points ahead of their opponent.

## Design Notes

- **Single Responsibility**: The `TennisGame` class is responsible only for game logic, not for presentation (UI) or application flow.
- **Immutability of State**: While the class itself is mutable, the public API doesn't expose internal score counters, promoting encapsulation.
- **Private Helpers**: Internal logic is broken down into small, readable private functions (`hasWinner`, `isDeuce`, `getLeadingPlayer`, etc.), making the main `getScore` method a clean `when` expression.
- **Defensive Programming**: Input to `awardPoint` is validated to ensure it is either 1 or 2. Internal mapping functions also validate their inputs to prevent illegal states.

## Demo Usage

The `Demo.kt` file provides a simple, human-readable walkthrough of the game's features. It can be run with `./gradlew run` and produces the following output, covering three key scenarios:

```
DEMO 1: Straight win for Player 1
Start                               -> Love–Love
P1 scores (1)                       -> 15–Love
...
P1 scores (4)                       -> Player 1 wins

DEMO 2: Deuce/Advantage cycles; Player 2 wins
Start                               -> Love–Love
...
P2 scores 3 times (reaching Deuce)  -> Deuce
P1 scores -> Advantage P1           -> Advantage Player 1
P2 scores -> Deuce                  -> Deuce
...
P2 scores -> Player 2 wins          -> Player 2 wins

DEMO 3: High ties remain Deuce
3–3 -> Deuce                        -> Deuce
P1 scores -> Adv P1                 -> Advantage Player 1
P2 scores -> Deuce (4–4)            -> Deuce
...
```

## Testing

Unit tests are written with `kotlin.test` and run on the JUnit 5 platform. They cover:
- Initial state ("Love-Love").
- Normal scoring sequences.
- Reaching Deuce.
- Advantage, returning to Deuce, and winning from Advantage.
- A straight win (e.g., 4-0).
- The game correctly ignoring points awarded after a win has occurred.

## Interview Walkthrough

This project can be used to demonstrate several core software engineering skills:

1.  **Problem Decomposition**: "I started by breaking down the rules of tennis into four distinct states: Normal, Deuce, Advantage, and Win. The `getScore` method implements this logic with a `when` expression, checking for each state in order of priority."
2.  **API Design**: "I designed a minimal public API (`awardPoint`, `getScore`, `reset`) to hide the internal implementation (the two score counters). This encapsulates the state and makes the class easy to use."
3.  **Readability & Helpers**: "Instead of putting all the logic in one large function, I created small, private helper functions like `hasWinner` and `isDeuce`. This makes `getScore` declarative and easy to read."
4.  **Testing**: "I wrote unit tests to cover all the major scenarios and edge cases we discussed, such as winning from Deuce, points being ignored after a win, and ensuring high ties like 5-5 correctly resolve to Deuce."
5.  **Defensive Coding**: "I added `require` checks to `awardPoint` and internal helpers to fail fast if the input is invalid, which makes the class more robust."

## Notes on AI Usage

An AI assistant (Google's Gemini) was used for the following tasks in this project:
- **Initial scaffolding and refactoring**: Assisted in creating the initial class structure and applying refactors, such as consolidating helper functions.
- **Documentation**: Generated KDoc comments and the initial draft of this README based on specific outlines.
- **Git integration**: Provided the sequence of commands to initialize the repository and connect it to GitHub.

All code was reviewed, and final changes were directed by the developer to ensure alignment with the project goals.

## License

This project is unlicensed and free to use.