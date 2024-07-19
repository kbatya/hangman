# 🎮 Hangman Game

Welcome to our exciting Hangman game! Test your word-guessing skills and expand your vocabulary while having fun.

## 📋 Game Overview

Hangman is a classic word guessing game. The game randomly selects a word, and the player tries to guess it by suggesting letters. The game is won by guessing the word before a stick figure is completely drawn.

## 🕹️ How to Play

1. Start a new game by selecting a category.
2. A hidden word will be displayed as underscores (e.g., "_ _ _ _ _").
3. Enter a single letter as your guess.
4. If the letter is in the word, it will be revealed in its correct position(s).
5. If the letter is not in the word, you lose an attempt, and part of the hangman is drawn.
6. Keep guessing letters until you either:
   - Correctly guess the entire word (You win! 🎉)
   - Run out of attempts (Game over 😢)

## 🚀 Features

- **Category Selection**: Choose from various word categories for a tailored experience.
- **Letter Tracking**: The game displays all guessed letters, helping you keep track of your progress.
- **Visual Feedback**: Watch the hangman drawing progress with each incorrect guess.
- **Attempt Counter**: Keep an eye on your remaining attempts (you have 6 in total).
- **New Game Option**: Easily start a fresh game at any time.

## 👨‍💻 Technical Details

- The game is implemented in the `GameActivity` class.
- It handles all game logic and user interactions.
- The game state is initialized at the start of each round.
- User input is restricted to single letters only.
- The UI updates in real-time to reflect the game state.

## 🔄 Game Flow

1. Select a category in the `SelectCategory` activity.
2. `GameActivity` initializes with a random word from the chosen category.
3. Enter letter guesses one at a time.
4. The game ends when the word is guessed or attempts run out.
5. Press "New Game" to return to category selection and start over.

## 🎨 UI Elements

- Word display (with correctly guessed letters revealed)
- Hangman drawing (updated with each incorrect guess)
- Letter input field
- "Guess" button
- Display of all guessed letters
- Remaining attempts counter
- "New Game" button

Enjoy playing Hangman! May your vocabulary be vast and your guesses be lucky! 🍀
