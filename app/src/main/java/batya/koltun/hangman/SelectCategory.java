package batya.koltun.hangman;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

// interface for selecting a category of words to guess
// after a category is selected, and the user pressed the start button,
// displaying and selecting a category is implemented with a spinner,
// that filled from the resources string array of categories
// the system will randomly select a word from the selected category
// from the resources string array
public class SelectCategory extends AppCompatActivity {
    Context context;
    Spinner sCategories;
    Button bCategory;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_category);
        initComponents();

        bCategory.setOnClickListener(v -> {
            String selectedCategory = sCategories.getSelectedItem().toString();


            String word = getRandomWordFromCategory(selectedCategory);

            Intent go = new Intent(context, MainActivity.class);
            go.putExtra("word", word);
            go.putExtra("name",name );
            startActivity(go);
        });

    }

    protected void initComponents() {
        context = this;
        Intent intent = getIntent();
        // Retrieve the string data passed from the first activity
        name = intent.getStringExtra("name");

        // Find the TextView in the layout
        TextView tvTitle = findViewById(R.id.tvTitle);
        String title = tvTitle.getText().toString();
        // Set the retrieved name to the TextView
        tvTitle.setText(name + ", " + title);
        bCategory = findViewById(R.id.bCategory);
        sCategories = findViewById(R.id.sCategories);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        sCategories.setAdapter(adapter);
    }

    // the method randomly selects a word from the selected category
    private String getRandomWordFromCategory(String category) {
        int arrayId = getResources().getIdentifier(category, "array", getPackageName());
        String[] words = getResources().getStringArray(arrayId);

        Random random = new Random();
        String word = words[random.nextInt(words.length)];

        return word;

    }
}