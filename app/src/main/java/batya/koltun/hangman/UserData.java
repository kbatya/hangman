package batya.koltun.hangman;


import android.content.Intent;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;



public class UserData extends AppCompatActivity {
    Button bGo;
    EditText eName,ePassword;;
    CheckBox cbAdmin;
    User user;
    Context context;
    private static final String PREFS_NAME = "shPrefCredentials";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ADMIN_USERNAME = "admin_username";
    private static final String KEY_ADMIN_PASSWORD = "admin_password";

    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);


        initComponents();
        bGo.setOnClickListener(v -> {


            is_Login(user);
         });
    }

    private void insertIntoSharedPreferences    (User user)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String username_key, password_key;
        if (user.getIsAdmin()) {
            username_key = KEY_ADMIN_USERNAME;
            password_key = KEY_ADMIN_PASSWORD;
        }

        else {
            username_key = KEY_USERNAME;
            password_key = KEY_PASSWORD;
        }
        editor.putString(username_key, user.getUserName());
        editor.putString(password_key, user.getUserPassword());
        editor.apply();
    }

    private User RetrieveFromSharedPreferences(User user)
    {
        String username_key, password_key;
        if (user.getIsAdmin()) {
            username_key = KEY_ADMIN_USERNAME;
            password_key = KEY_ADMIN_PASSWORD;

        }

        else {
            username_key = KEY_USERNAME;
            password_key = KEY_PASSWORD;
        }
        User fspUser = new User(sharedPreferences.getString(username_key, ""),sharedPreferences.getString(password_key, ""),user.getIsAdmin());
        if (fspUser.getUserName().isEmpty()) {
            insertIntoSharedPreferences(user);
            return user;
        }

        return fspUser;
    }
    // method to check if the user entered username and password
    // method to check if the user exists in shared preferences or not
    // if not then insert the user data into shared preferences
    // if exists then checks the password
    // if correct then gos to next activity with username as a parameter
    private void is_Login(User user) {
        user = new User(eName.getText().toString().trim(), ePassword.getText().toString().trim(), cbAdmin.isChecked());
        if (user.getUserName().isEmpty() ) {
            eName.setError("Enter your name");
            return;
        }
        if (user.getUserPassword().isEmpty()) {
            ePassword.setError("Enter your password");
            return;
        }
        User fspUser = RetrieveFromSharedPreferences(user);

        if (!user.equals(fspUser)) {
            Toast.makeText(context, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            Intent go;
            if (fspUser.getIsAdmin())
                go = new Intent(context, ShowWords.class);
            else {
                 go = new Intent(context, SelectCategory.class);
                 go.putExtra("name", user.getUserName());
            }
            startActivity(go);
        }
    }

    // method to initialize the components
    private void initComponents()
    {
        context=this;
        eName=findViewById(R.id.eName);
        ePassword=findViewById(R.id.ePassword);
        cbAdmin=findViewById(R.id.cbAdmin);
        bGo=findViewById(R.id.bGo);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    }

}