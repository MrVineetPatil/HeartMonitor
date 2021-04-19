package com.example.admin.heartmonitor;

import android.app.ProgressDialog;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    
    public EditText nameUser;
    public EditText emailUser;
    public EditText passwordUser;
    public Button registerButton;
    public String sampleuser;
    private FirebaseAuth firebaseauthu;
    private ProgressDialog progressDialog;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog=new ProgressDialog(this);
        firebaseauthu=FirebaseAuth.getInstance();
        nameUser=(EditText) findViewById(R.id.name);
        emailUser=(EditText) findViewById(R.id.emailHeart);
        passwordUser=(EditText) findViewById(R.id.passwordHeart);
        registerButton=(Button) findViewById(R.id.saveButton);





        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String email=emailUser.getText().toString().trim();
                String pass=passwordUser.getText().toString().trim();
                sampleuser= nameUser.getText().toString().trim();

                progressDialog.setMessage("Registering..");
                progressDialog.show();

                firebaseauthu.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent i=new Intent(MainActivity.this,DisplayMainContent.class);
                                    i.putExtra("Value",sampleuser);
                                    startActivity(i);
                                    Toast.makeText(MainActivity.this, "Hey, "+sampleuser+"\nRegistered Successfully.. " ,Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }else{
                                    Toast.makeText(MainActivity.this, "Registration Failed.." ,Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                        });


            }
        });

        
    }
    
    
}
