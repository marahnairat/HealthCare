package com.example.healthcare;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

    public class the_final_login extends AppCompatActivity {

        EditText email,password;
        Button loginBtn,gotoRegister;
        boolean valid = true;
        FirebaseAuth fauth;
        FirebaseFirestore fstore;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_the_final_login);

            fauth=FirebaseAuth.getInstance();
            fstore=FirebaseFirestore.getInstance();

            email = findViewById(R.id.loginEmail);
            password = findViewById(R.id.loginPassword);
            loginBtn = findViewById(R.id.loginBtn);
            gotoRegister = findViewById(R.id.gotoRegister);

            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkField(email);
                    checkField(password);
                    if (valid) {
                        fauth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(the_final_login.this,"Loggedin successfully  ",Toast.LENGTH_SHORT).show();
                                CheckUserAccessLevel(authResult.getUser().getUid());

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });


                    }
                }
            });

            gotoRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),the_final_register.class));
                }
            });



        }

        private void CheckUserAccessLevel(String uid) {
            DocumentReference df =fstore.collection("Users").document(uid);
            df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Log.d("TAG", "onSuccess:" + documentSnapshot.getData());
                    if(documentSnapshot.getLong("isDoctor")!=null){
                        String doctor_name= documentSnapshot.getString("FullName");
                        String doctor_phone= documentSnapshot.getString("PhoneNumber");
                        Intent intent = new Intent(the_final_login.this, Dr_profile_dr_side.class);
                        intent.putExtra("doctor_name", doctor_name);
                        intent.putExtra("doctor_phone", doctor_phone);
                        startActivity(intent);
//                    startActivity(new Intent(getApplicationContext(),Dr_profile_dr_side.class));
                        finish();
                    }
                    if(documentSnapshot.getString("isUser")!=null){
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();


                    }


                }
            });
        }

        public boolean checkField(EditText textField){
            if(textField.getText().toString().isEmpty()){
                textField.setError("Error");
                valid = false;
            }else {
                valid = true;
            }

            return valid;
        }

    }