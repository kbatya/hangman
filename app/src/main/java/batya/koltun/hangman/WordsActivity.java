package batya.koltun.hangman;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


public class WordsActivity extends AppCompatActivity {
    Context context;
    HelperDB helperDB;
    SQLiteDatabase db;
    String wCategory, wLevel, wWord, wHint;
    Spinner sCategories;
    EditText etWord, etHint;
    RadioGroup rgLevel;
    Button bAdd, bBack,bShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        initComponents();

        bAdd.setOnClickListener(v -> {

                ContentValues cv = new ContentValues();

                cv.put(HelperDB.WORD_CATEGORY, sCategories.getSelectedItem().toString());
                cv.put(HelperDB.WORD_LEVEL, rbLevelSelectedText());
                cv.put(HelperDB.WORD_WORD, etWord.getText().toString());
                cv.put(HelperDB.WORD_HINT, etHint.getText().toString());


                db = helperDB.getWritableDatabase();

                db.insert(HelperDB.WORD_TABLE, null, cv);

                db.close();
                resetValues();
        });
        bShow.setOnClickListener(v -> {
            Intent go = new Intent(context, ShowWords.class);
            startActivity(go);
        });

        bBack.setOnClickListener(v ->  {
                 finish();
        });

    }

    private String rbLevelSelectedText() {
        int selectedId = rgLevel.getCheckedRadioButtonId();
        if (selectedId == -1) return "";
        // Check if any radio button is selected
            // Find the radio button by its ID
        RadioButton selectedRadioButton = findViewById(selectedId);

        // Get the text of the selected radio button
        return selectedRadioButton.getText().toString();


    }

    protected void initComponents() {
        context = this;
        helperDB = new HelperDB(context);


        sCategories = findViewById(R.id.sCategories);
        rgLevel = findViewById(R.id.rgLevel);
        etWord = findViewById(R.id.etWord);
        etHint = findViewById(R.id.etHint);
        bAdd = findViewById(R.id.bAdd);
        bBack = findViewById(R.id.bBack);
        bShow = findViewById(R.id.bShow);

         //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCategories.setAdapter(adapter);
    }
    protected void resetValues()
    {
        sCategories.setSelection(0);
        etWord.setText("");
        etHint.setText("");
        rgLevel.clearCheck();


    }
}

