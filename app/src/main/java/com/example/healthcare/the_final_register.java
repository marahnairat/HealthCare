package com.example.healthcare;


    import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

    public class the_final_register extends AppCompatActivity {

        EditText fullName,email,password,phone;
        Button registerBtn,goToLogin;
        boolean valid = true;
        FirebaseAuth fauth;
        FirebaseFirestore fstore;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_the_final_register);
            fauth=FirebaseAuth.getInstance();
            fstore=FirebaseFirestore.getInstance();

            fullName = findViewById(R.id.registerName);
            email = findViewById(R.id.registerEmail);
            password = findViewById(R.id.registerPassword);
            phone = findViewById(R.id.registerPhone);
            registerBtn = findViewById(R.id.registerBtn);
            goToLogin = findViewById(R.id.gotoLogin);

            registerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkField(fullName);
                    checkField(email);
                    checkField(password);
                    checkField(phone);
                    if (valid){
                        fauth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                FirebaseUser user =fauth.getCurrentUser();
                                Toast.makeText(the_final_register.this,"Avvount created ",Toast.LENGTH_SHORT).show();
                                DocumentReference df =fstore.collection("Users").document(user.getUid());
                                Map<String,Object> userinfo =new HashMap<>();
                                userinfo.put("FullName",fullName.getText().toString());
                                userinfo.put("UserEmail",email.getText().toString());
                                userinfo.put("PhoneNumber",phone.getText().toString());
                                userinfo.put("isUser","1");
                                df.set(userinfo);
                                startActivity(new Intent(getApplicationContext(),the_final_login.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(the_final_register.this,"Failled to creat account ",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
            goToLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),the_final_login.class));
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