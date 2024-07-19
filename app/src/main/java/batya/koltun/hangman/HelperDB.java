package batya.koltun.hangman;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class HelperDB extends SQLiteOpenHelper
{
public static final String DB_FILE="words.db";

public static final String WORD_TABLE="Words";
public static final String WORD_CODE="wCode";
public static final String WORD_WORD="wWord";
public static final String WORD_LEVEL="wLevel";
public static final String WORD_HINT="wHint";
public static final String WORD_CATEGORY="wCategory";


public static final String USER_TABLE="Users";


public HelperDB(@Nullable Context context) {
        super(context, DB_FILE, null, 1);
}

@Override
public void onCreate(SQLiteDatabase db) {
        String st="CREATE TABLE IF NOT EXISTS "+WORD_TABLE;
        st+=" ( "+WORD_CODE+" INTEGER PRIMARY KEY AUTOINCREMENT, "+WORD_CATEGORY +" TEXT NOT NULL, ";
        st+= WORD_LEVEL+" TEXT, "+WORD_WORD +" TEXT NOT NULL, ";
        st+= WORD_HINT+" TEXT);";
        db.execSQL(st);

}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

}
}