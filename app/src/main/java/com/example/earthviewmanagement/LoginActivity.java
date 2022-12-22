package com.example.earthviewmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText EditTextEmailAddress,editTextPassword;
    private FirebaseAuth mAuth;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        EditTextEmailAddress = findViewById(R.id.editText);
        editTextPassword = findViewById(R.id.editText3);
    }

    public void home(View view) {
        String email =EditTextEmailAddress.getText().toString().trim();
        String password =editTextPassword.getText().toString().trim();


        if (email.isEmpty()){
            EditTextEmailAddress.setError(" email is required!!");
            EditTextEmailAddress.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EditTextEmailAddress.setError("Please provide a valid email address!");
            EditTextEmailAddress.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError(" password is required!!");
            editTextPassword.requestFocus();
            return;

    }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                    Toast.makeText(LoginActivity.this,"Logged in successfully",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this,"Failed to log in check your credentials and Internet connection",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void sign_Up(View view) {
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }

    public void password(View view) {
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }
}
