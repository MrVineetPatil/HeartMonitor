package com.example.admin.heartmonitor;

import android.app.ProgressDialog;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DisplayMainContent extends AppCompatActivity{


    public TextView bloodPressureValueJava;
    public TextView heartBeatRateValueJava;
    public ProgressDialog progressDialog;
    public ImageView sampleImageView;
    public Button signoutButton;
    public int i=0;
    public int[] heartbeat;
    public int[] bloodPressure;
    private FirebaseAuth firebaseAuths;
    private DatabaseReference databaseReference;
    public String dataName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_main_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        heartbeat= new int[]{88, 78, 90, 73, 88, 90, 77, 97, 77, 87, 89, 76, 81, 71, 85,74,79,83,94,87,81,85,};
        bloodPressure=new int[]{91,93,96,101,104,108,89,99,100,107,108,103,99,94,97,93,90,87,93,95,98,105};


        firebaseAuths=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        progressDialog=new ProgressDialog(this);
        bloodPressureValueJava=(TextView) findViewById(R.id.bloodPressureValue);
        heartBeatRateValueJava=(TextView) findViewById(R.id.heartBeatValue);
        sampleImageView=(ImageView) findViewById(R.id.emotionImage);
        signoutButton=(Button) findViewById(R.id.signoutButton);

        dataName=getIntent().getExtras().getString("Value");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setMessage("Data has been fetched.. Processing HBP..");
                progressDialog.show();
                sampleFunction();
                heartBeatRateValueJava.setText(heartbeat[i]+"");
                bloodPressureValueJava.setText(bloodPressure[i]+"");
                if(i==20){
                    i=0;
                }
                i = i + 1;
                if(i%4==0 || i!=0){
                    sampleImageView.setImageResource(R.drawable.ic_teeth_face);
                }
                if(i%3==0){
                    sampleImageView.setImageResource(R.drawable.ic_sample_smily);
                }

                Thread thread=new Thread(){
                    public void run(){
                        super.run();
                        try {
                            sleep(3000);
                            progressDialog.dismiss();
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();



            }
        });

        signoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuths.signOut();
                Toast.makeText(DisplayMainContent.this,"You have Successfully Signed out..",Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    public void sampleFunction(){
        int hb= heartbeat[i];
        int bp= bloodPressure[i];

        UserInformation userInformation=new UserInformation(hb,bp);

        FirebaseUser sample=firebaseAuths.getCurrentUser();

        databaseReference.child(dataName).child(sample.getUid()).setValue(userInformation);

        Toast.makeText(DisplayMainContent.this,"Successfully saved in Firebase",Toast.LENGTH_SHORT).show();

    }

    /*public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item1:
                firebaseAuths.signOut();
                Toast.makeText(DisplayMainContent.this,"You have Successfully Signed out..",Toast.LENGTH_LONG).show();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/


}
