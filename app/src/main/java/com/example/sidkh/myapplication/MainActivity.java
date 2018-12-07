package com.example.sidkh.myapplication;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements LogIn, View.OnClickListener {
    EditText Email;
    EditText password;
    Button LogIn;
    Button SignUp;
    Presenter presenter;

    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_First").build();
        Email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.Password);
        LogIn=(Button) findViewById(R.id.login);
        SignUp=(Button) findViewById(R.id.signUp);
        SignUp.setOnClickListener(this);
        LogIn.setOnClickListener(this);
        presenter = new Presenter(this, new Interactor(this.getApplicationContext()));

    }


    @Override
    public void InvalidLogin() {
        Toast.makeText(getApplicationContext(), "either username or password is incorrect !", Toast.LENGTH_LONG).show();
    }

        @Override
    public void AlreadyAccount() {
        Toast.makeText(getApplicationContext(), "account already available with this userName", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLogin() {
        Intent i =new Intent(this, Successfull.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(), "successful login", Toast.LENGTH_LONG).show();

    }

    @Override
    public void noAccount() {
        Toast.makeText(getApplicationContext(), "no account is available with this userName", Toast.LENGTH_LONG).show();
    }

    @Override
    public void AccountCreated() {
        Toast.makeText(getApplicationContext(), "account created successfully !", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                //Toast.makeText(getApplicationContext(), "invalid username or password", Toast.LENGTH_LONG).show();
                presenter.onLoginClicked(Email.getText().toString(), password.getText().toString());
                break;
            case R.id.signUp:
                presenter.onSignUp(Email.getText().toString(), password.getText().toString());
        }
    }
}
