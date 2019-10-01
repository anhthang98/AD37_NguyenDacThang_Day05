package com.example.exercise_day05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        edtUsername.setText("admin");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                password = edtPassword.getText().toString();

                if (password.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Username and password invalid!", Toast.LENGTH_LONG).show();
                } else if (!isValidPassword(password)) {
                    Toast.makeText(getBaseContext(), "Password must contain mix of upper and lower case letters as well as digits and one special charecter(4-20)", Toast.LENGTH_LONG).show();
                } else {
                 startActivity(new Intent(getBaseContext(), CreateNoteActivity.class));
            }
            }
        });


    }

    public static boolean isValidPassword(String password) {
        Matcher matcher = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!*]).{6,20})").matcher(password);
        return matcher.matches();
    }
}
