package com.example.earthviewmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText editTextFullName,editTextEmailAddress,editTextMobileNumber,editTextPassword;
    private TextView login;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        editTextFullName = findViewById(R.id.inputfullname);
        editTextEmailAddress = findViewById(R.id.editText);
        editTextMobileNumber = findViewById(R.id.editText2);
        editTextPassword = findViewById(R.id.editText3);
        progressBar = findViewById(R.id.progressBar);
        signup = findViewById(R.id.sigup);

                            }

    public void home(View view) {
        String email =editTextEmailAddress.getText().toString().trim();
        String fullName =editTextFullName.getText().toString().trim();
        String phone =editTextMobileNumber.getText().toString().trim();
        String password =editTextPassword.getText().toString().trim();
        if(fullName.isEmpty()){
            editTextFullName.setError("fullNameis required!");
            editTextFullName.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editTextEmailAddress.setError("Email is required!!");
            editTextEmailAddress.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmailAddress.setError("Provide a valid email Address!!");
            editTextEmailAddress.requestFocus();
            return;

        }
        if(phone.isEmpty()){
            editTextMobileNumber.setError("mobile number is required ");
            editTextMobileNumber.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("password is required");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6){
            editTextPassword.setError("Min password length is 6 characters");
            editTextPassword.requestFocus();
            return;
    }
        progressBar.setVisibility(view.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
              .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {

                      if (task.isSuccessful()){
                          User user = new User(fullName,email,phone,password);

                          FirebaseDatabase.getInstance().getReference("Users")
                                  .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                  .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                      @Override
                                      public void onComplete(@NonNull Task<Void> task) {
                                          if (task.isSuccessful()){
                                              startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                              Toast.makeText(MainActivity.this,"User has been registered successfully!",Toast.LENGTH_SHORT).show();
                                          }else {
                                              Toast.makeText(MainActivity.this,"failed to register user please check internet connection and try again",Toast.LENGTH_SHORT).show();

                                          }
                                          progressBar.setVisibility(View.GONE);
                                      }
                                  });
                      }else {
                          Toast.makeText(MainActivity.this,"failed to register user check internet connection and details",Toast.LENGTH_SHORT).show();
                          progressBar.setVisibility(View.GONE);

                      }
                  }
              });
    }

    public void login(View view) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }
    }

