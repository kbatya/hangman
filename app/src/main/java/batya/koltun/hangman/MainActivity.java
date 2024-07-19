package batya.koltun.hangman;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

// The game activity
// This activity handles the game logic and user interactions.
// It initializes the game state and updates the UI accordingly.
// the user can enter a letter only and guess it.
// The system checks the user's guess and displays all guessed letters till now
// if the user guesses a letter correctly, the letter is displayed in the word.
// if the user guesses a letter incorrectly, the user loses an attempt.
// and will be drawn another part of the hangman. on the hangman
// The game ends when the user guesses the word or runs out of attempts (6).
// The user can restart the game by clicking the "New game" button.
//After pressing the "New game" button, the user is redirected to the SelectCategory activity.
public class MainActivity extends AppCompatActivity {

    private TextView wordTextView;
    private TextView guessedLettersTextView;
    private EditText letterInputEditText;
    private Button guessButton;
    private TextView resultTextView;
    private HangmanView hangmanView;
    private Context context;
    private String wordToGuess ;
    private char[] displayedWord;
    private Set<Character> guessedLetters = new HashSet<>();
    private int attemptsLeft = 6;
    private boolean gameIsOver = false;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitComponents();


        initializeGame();

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameIsOver) {
                    Intent go = new Intent(context, SelectCategory.class);
                    go.putExtra("name", name);
                    startActivity(go);
                }

                String input = letterInputEditText.getText().toString().toUpperCase();
                if (Character.isLetter(input.charAt(0))) {
                    char guessedLetter = input.charAt(0);
                    handleGuess(guessedLetter);

                } else {

                    Toast.makeText(context, "Please, enter one letter only", Toast.LENGTH_SHORT).show();
                }
                letterInputEditText.setText("");
            }
        });
    }

    private  void InitComponents()
    {
        context = this;
        Intent intent = getIntent();
        // Retrieve the string data passed from the first activity
        name = intent.getStringExtra("name");
        wordToGuess = intent.getStringExtra("word").toUpperCase();
        //Toast.makeText(context, wordToGuess, Toast.LENGTH_SHORT).show();
        wordTextView = findViewById(R.id.wordTextView);
        guessedLettersTextView = findViewById(R.id.guessedLettersTextView);
        letterInputEditText = findViewById(R.id.letterInputEditText);
        guessButton = findViewById(R.id.guessButton);
        resultTextView = findViewById(R.id.resultTextView);
        hangmanView = findViewById(R.id.hangmanView);
    }

    private void initializeGame() {
        gameIsOver = false;
        displayedWord = new char[wordToGuess.length()];
        for (int i = 0; i < wordToGuess.length(); i++) {
            displayedWord[i] = '_';
        }
        updateWordDisplay();
        guessedLettersTextView.setText("Guessed Letters: ");
        resultTextView.setText("");
        guessButton.setText("Guess");
        letterInputEditText.setEnabled(true);
        hangmanView.setAttemptsLeft(attemptsLeft);
    }

    private void handleGuess(char guessedLetter) {
        if (guessedLetters.contains(guessedLetter)) {
            Toast.makeText(this, "You already guessed that letter", Toast.LENGTH_SHORT).show();
            return;
        }

        guessedLetters.add(guessedLetter);
        guessedLettersTextView.setText("Guessed Letters: " + guessedLetters.toString());

        boolean correctGuess = false;
        for (int i = 0; i < wordToGuess.length(); i++) {

            if (wordToGuess.charAt(i) == guessedLetter) {

                displayedWord[i] = guessedLetter;
                correctGuess = true;
            }
        }

        if (correctGuess) {
            updateWordDisplay();
            if (new String(displayedWord).equals(wordToGuess)) {
                letterInputEditText.setEnabled(false);
                resultTextView.setText("You win!");
                guessButton.setText("New game");
                gameIsOver = true;
            }
        } else {
            attemptsLeft--;
            hangmanView.setAttemptsLeft(attemptsLeft);
            if (attemptsLeft == 0) {
                letterInputEditText.setEnabled(false);
                guessButton.setText("New game");
                resultTextView.setText("You lose! The word was " + wordToGuess);
                gameIsOver = true;
            }
        }
    }

    private void updateWordDisplay() {
        StringBuilder display = new StringBuilder("Word: ");
        for (char c : displayedWord) {
            display.append(c).append(' ');
        }
        wordTextView.setText(display.toString());
    }
}
