package com.example.simmiotp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

//import com.google.firebase.auth.FirebaseAuth;

public class SimmiRegistration extends AppCompatActivity {
    ImageView simmilogo;
EditText name,mobilenumber;
Button generateOTPbutton;
ProgressBar regprogressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simmi_registration);
        getSupportActionBar().hide();
        generateOTPbutton=findViewById(R.id.generateOTPButton);

        regprogressbar=findViewById(R.id.registerprogressbar);
        regprogressbar.setVisibility(View.INVISIBLE);

        name=findViewById(R.id.name);
        simmilogo=findViewById(R.id.simmilogoid);
        mobilenumber=findViewById(R.id.mobilenumber);

        generateOTPbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mobilenumber.getText().toString().trim().isEmpty() || !name.getText().toString().trim().isEmpty()){
                    if(name.getText().toString().trim().isEmpty()){
                        Toast.makeText(SimmiRegistration.this,"Enter Name",Toast.LENGTH_LONG).show();
                    }
                    if(mobilenumber.getText().toString().trim().isEmpty()){
                        Toast.makeText(SimmiRegistration.this,"Enter Mobile Number",Toast.LENGTH_LONG).show();
                    }
                    if(mobilenumber.getText().toString().trim().length()==10 && !name.getText().toString().trim().isEmpty()){


                        regprogressbar.setVisibility(View.VISIBLE);
                        generateOTPbutton.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + mobilenumber.getText().toString(), 60, TimeUnit.SECONDS, SimmiRegistration.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                        regprogressbar.setVisibility(View.GONE);
                                        generateOTPbutton.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                        regprogressbar.setVisibility(View.GONE);
                                        generateOTPbutton.setVisibility(View.VISIBLE);
                                        Toast.makeText(SimmiRegistration.this,e.getMessage(),Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        regprogressbar.setVisibility(View.GONE);
                                        generateOTPbutton.setVisibility(View.VISIBLE);
                                        Intent i=new Intent(getApplicationContext(),VerifyOTP.class);
                                        i.putExtra("Mobile Number",mobilenumber.getText().toString().trim());
                                        i.putExtra("User Name",name.getText().toString().trim());
                                        i.putExtra("backendOTP",backendOTP);
                                        startActivity(i);

                                    }
                                }
                        );


                    } else{
                        Toast.makeText(SimmiRegistration.this,"Enter 10 digits",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(SimmiRegistration.this,"Enter Correct Mobile Number and Name",Toast.LENGTH_LONG).show();
                }
            }
        });

}
}