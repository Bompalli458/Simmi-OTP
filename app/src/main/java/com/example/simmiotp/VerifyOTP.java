package com.example.simmiotp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOTP extends AppCompatActivity {
EditText edttxt1,edttxt2,edttxt3,edttxt4,edttxt5,edttxt6;
Button verifybtn;
ProgressBar verifyProgressbar;
TextView otptxt,usernameandnumber;
ImageView otpimg;
String getOTPBackend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        getSupportActionBar().hide();
        usernameandnumber=findViewById(R.id.usercredentials);
       String localname;
        localname="Dear "+getIntent().getStringExtra("User Name")+" \n Mobile Number "+getIntent().getStringExtra("Mobile Number");
           usernameandnumber.setText(String.format(localname));

           getOTPBackend=getIntent().getStringExtra("backendOTP");

        verifyProgressbar=findViewById(R.id.verifyProgressbar);
        verifyProgressbar.setVisibility(View.INVISIBLE);

           edttxt1=findViewById(R.id.edt1);
        edttxt2=findViewById(R.id.edt2);
        edttxt3=findViewById(R.id.edt3);
        edttxt4=findViewById(R.id.edt4);
        edttxt5=findViewById(R.id.edt5);
        edttxt6=findViewById(R.id.edt6);
        verifybtn=findViewById(R.id.verifyid);
        otptxt=findViewById(R.id.otptxt);
        otpimg=findViewById(R.id.otpimgid);
        verifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(     !edttxt1.getText().toString().trim().isEmpty() &&
                        !edttxt2.getText().toString().trim().isEmpty() &&
                        !edttxt3.getText().toString().trim().isEmpty() &&
                        !edttxt4.getText().toString().trim().isEmpty() &&
                        !edttxt5.getText().toString().trim().isEmpty() &&
                        !edttxt6.getText().toString().trim().isEmpty()
                ) {
                    String enterCodeOTP = edttxt1.getText().toString() + edttxt2.getText().toString() + edttxt3.getText().toString() + edttxt4.getText().toString();
                    if (getOTPBackend != null) {
                        verifyProgressbar.setVisibility(View.VISIBLE);
                        verifybtn.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(getOTPBackend, enterCodeOTP);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                verifyProgressbar.setVisibility(View.GONE);
                                verifybtn.setVisibility(View.VISIBLE);
                                if (task.isSuccessful()) {
                                    Intent i = new Intent(getApplicationContext(), Confirmation.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                } else {
                                    Toast.makeText(VerifyOTP.this, "Enter The Correct OTP", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        Toast.makeText(VerifyOTP.this, "OTP Verified", Toast.LENGTH_SHORT).show();
                    }
                    } else {
                        Toast.makeText(VerifyOTP.this, "Please enter all numbers", Toast.LENGTH_SHORT).show();
                    }

                    Intent i = new Intent(getApplicationContext(), Confirmation.class);
                    startActivity(i);

            }
        });
        numberotpmove();

        TextView resendlabel=findViewById(R.id.textresendOTP);
        resendlabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("Mobile Number"), 60, TimeUnit.SECONDS, VerifyOTP.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(VerifyOTP.this,e.getMessage(),Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                getOTPBackend=newbackendOTP;
                                Toast.makeText(VerifyOTP.this, "OTP sent Succesfully", Toast.LENGTH_SHORT).show();

                            }
                        }
                );
            }
        });
    }
    private void numberotpmove(){
        edttxt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty()){
                    edttxt2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edttxt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty()){
                    edttxt3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edttxt3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty()){
                    edttxt4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edttxt4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty()){
                    edttxt5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edttxt5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty()){
                    edttxt6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}