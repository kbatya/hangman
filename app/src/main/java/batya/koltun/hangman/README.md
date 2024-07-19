# 🎮 Hangman Game

Welcome to our exciting Hangman game! Test your word-guessing skills and expand your vocabulary while having fun.

## 📋 Game Overview

Hangman is a classic word guessing game. The game randomly selects a word, and the player tries to guess it by suggesting letters. The game is won by guessing the word before a stick figure is completely drawn.

## 🔐 User Authentication

Before playing, users need to authenticate:

1. Enter a username and password.
2. The system checks if the user exists in SharedPreferences:
   - If new, the user data is stored in SharedPreferences.
   - If existing, the password is verified.
3. Upon successful authentication, the user proceeds to the game.

## 🕹️ How to Play

1. Log in or create a new account.
2. Start a new game by selecting a category.
3. A hidden word will be displayed as underscores (e.g., "_ _ _ _ _").
4. Enter a single letter as your guess.
5. If the letter is in the word, it will be revealed in its correct position(s).
6. If the letter is not in the word, you lose an attempt, and part of the hangman is drawn.
7. Keep guessing letters until you either:
   - Correctly guess the entire word (You win! 🎉)
   - Run out of attempts (Game over 😢)

## 🚀 Features

- **User Authentication**: Secure login system using SharedPreferences.
- **Category Selection**: Choose from various word categories for a tailored experience.
- **Letter Tracking**: The game displays all guessed letters, helping you keep track of your progress.
- **Visual Feedback**: Watch the hangman drawing progress with each incorrect guess.
- **Attempt Counter**: Keep an eye on your remaining attempts (you have 6 in total).
- **New Game Option**: Easily start a fresh game at any time.

## 👨‍💻 Technical Details

- User authentication is handled using SharedPreferences for data persistence.
- The game is implemented in the `GameActivity` class.
- It handles all game logic and user interactions.
- The game state is initialized at the start of each round.
- User input is restricted to single letters only.
- The UI updates in real-time to reflect the game state.

## 🔄 Game Flow

1. Authenticate or create a new user account.
2. Select a category in the `SelectCategory` activity.
3. `GameActivity` initializes with a random word from the chosen category.
4. Enter letter guesses one at a time.
5. The game ends when the word is guessed or attempts run out.
6. Press "New Game" to return to category selection and start over.

## 🎨 UI Elements

- Login/Sign Up screen
- Word display (with correctly guessed letters revealed)
- Hangman drawing (updated with each incorrect guess)
- Letter input field
- "Guess" button
- Display of all guessed letters
- Remaining attempts counter
- "New Game" button

Enjoy playing Hangman! May your vocabulary be vast and your guesses be lucky! 🍀
