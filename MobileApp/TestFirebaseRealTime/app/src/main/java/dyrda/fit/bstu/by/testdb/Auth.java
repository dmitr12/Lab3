package dyrda.fit.bstu.by.testdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Auth extends AppCompatActivity implements View.OnClickListener{

    private EditText mEmailField;
    private EditText mPasswordField;
    private TextView infoUser;
    private Button signInBtn;
    private Button createAccountBtn;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        mEmailField=findViewById(R.id.field_email);
        infoUser=findViewById(R.id.infoUser);
        mPasswordField=findViewById(R.id.field_password);
        signInBtn=findViewById(R.id.email_sign_in_button);
        createAccountBtn=findViewById(R.id.email_create_account_button);

        signInBtn.setOnClickListener(this);
        createAccountBtn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null)
                    Toast.makeText(getApplicationContext(),"onAuthStateChanged:sign_in", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"onAuthStateChanged:sign_out", Toast.LENGTH_LONG).show();
                updateUI(user);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
            mAuth.signOut();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void createAccount(String email, String password){
        if(!validateForm())
            return;
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mEmailField.setText("");
                        mPasswordField.setText("");
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"Create account successful", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            intent.putExtra("uid", mAuth.getCurrentUser().getUid());
                            startActivity(intent);
                        }
                        if(!task.isSuccessful())
                            Toast.makeText(getApplicationContext(),"Create account failed", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void signIn(String email, String password){
        if(!validateForm())
            return;
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    intent.putExtra("uid", mAuth.getCurrentUser().getUid());
                    startActivity(intent);
                }
                if(!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Sig in failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void updateUI(FirebaseUser user){
        if(user!=null){
            infoUser.setText("Current user:"+user.getEmail());
        }
        else {
            infoUser.setText("");
        }
    }

    private boolean validateForm(){
        boolean valid=true;
        String email=mEmailField.getText().toString();
        if(TextUtils.isEmpty(email)){
            mEmailField.setError("Required");
            valid=false;
        }else{
            mEmailField.setError(null);
        }
        String password=mPasswordField.getText().toString();
        if(TextUtils.isEmpty(password)){
            mPasswordField.setError("Required");
            valid=false;
        }else{
            mPasswordField.setError(null);
        }
        return valid;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.email_create_account_button:
                createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
                break;
            case R.id.email_sign_in_button:
                signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
                break;
        }
    }
}