package com.example.igclone3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.igclone3.controller.UserController;
import com.example.igclone3.model.User;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        onClick();
    }

    EditText etUserName, etUserEmail, etUserPassword;
    Button btnRegister;
    DataHelper dataHelper;
    private void init(){
        etUserName = findViewById(R.id.etUserName);
        etUserEmail = findViewById(R.id.etUserEmail);
        etUserPassword = findViewById(R.id.etUserPassword);
        dataHelper = new DataHelper(getApplicationContext());
        btnRegister = findViewById(R.id.btnRegister);
    }

    private void onClick(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = etUserName.getText().toString();
                String userEmail = etUserEmail.getText().toString();
                String userPassword = etUserPassword.getText().toString();

                User user = UserController.createUser(userName, userEmail, userPassword);

                System.out.println(dataHelper.registerUser(user));

                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });
    }
}