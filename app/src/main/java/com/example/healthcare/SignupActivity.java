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

public class SignupActivity extends AppCompatActivity {

    EditText fullName,email,password,phone;
    Button registerBtn,goToLogin;
    boolean valid = true;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_final);
        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        fullName = (EditText)findViewById(R.id.registerName);
        email = (EditText)findViewById(R.id.registerEmail);
        password =(EditText) findViewById(R.id.registerPassword);
        phone = (EditText)findViewById(R.id.registerPhone);
        registerBtn = (Button)findViewById(R.id.registerBtn);
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
                            Toast.makeText(SignupActivity.this,"Acount created ",Toast.LENGTH_SHORT).show();
                            DocumentReference df =fstore.collection("Users").document(user.getUid());
                            Map<String,Object> userinfo =new HashMap<>();
                            userinfo.put("FullName",fullName.getText().toString());
                            userinfo.put("UserEmail",email.getText().toString());
                            userinfo.put("PhoneNumber",phone.getText().toString());
                            userinfo.put("isUser","1");
                            df.set(userinfo);
                            Toast.makeText(SignupActivity.this,"data in map ",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignupActivity.this,"Failled to creat account ",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),loginFinal.class));
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