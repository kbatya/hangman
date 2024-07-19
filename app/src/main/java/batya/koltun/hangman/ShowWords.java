package batya.koltun.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class ShowWords extends AppCompatActivity {

    Button bShow,bAdd;
    ListView lvWords;
    ArrayList<String> alWords;
    ArrayAdapter<String> adapter;
    SQLiteDatabase db;
    HelperDB helperDB;
    Context context;
    Spinner sCategories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_words);

        initComponents();


        bShow.setOnClickListener(v-> {
                String selection=null;
                String[] selectionArgs = null;

                String category=sCategories.getSelectedItem().toString();
                if(!category.equals("all")) {
                    selection = HelperDB.WORD_CATEGORY + "=?";
                    selectionArgs = new String[]{category};

                }
                db=helperDB.getReadableDatabase();
                alWords = new ArrayList<>();
                //===================================
                Cursor cursor= db.query(HelperDB.WORD_TABLE,
                        null,selection,selectionArgs,
                        null,null,HelperDB.WORD_WORD);
                //=================================
                cursor.moveToFirst();
                while (!cursor.isAfterLast())  {
                    String stCode=cursor.getString(0);
                    String stCategory=cursor.getString(1);
                    String stLevel=cursor.getString(2);
                    String stWord=cursor.getString(3);
                    alWords.add(stWord+"("+stCode+") from "+stCategory+" level="+stLevel);
                    cursor.moveToNext();
                }
                cursor.close();
                db.close();
                adapter=new ArrayAdapter<>(context,
                        android.R.layout.simple_list_item_1,
                        alWords);
                lvWords.setAdapter(adapter);
        });

        bAdd.setOnClickListener(v -> {
            Intent go = new Intent(context, WordsActivity.class);
            startActivity(go);
        });
    }

    private void initComponents() {
        context=this;
        helperDB=new HelperDB(context);
        bShow= (Button) findViewById(R.id.bShow);
        bAdd = (Button) findViewById(R.id.bAdd);
        sCategories = findViewById(R.id.sCategories);
        lvWords= (ListView) findViewById(R.id.lvWords);

        alWords=new ArrayList<>();
        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayList<CharSequence> categoriesList = new ArrayList<>();
        categoriesList.add("all");
        categoriesList.addAll(Arrays.asList(getResources().getStringArray(R.array.categories)));

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item,
                categoriesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCategories.setAdapter(adapter);
    }
}