package com.example.tlin7877.assignment_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tlin7877.assignment_1.EmailPersister;
import com.example.tlin7877.assignment_1.R;
import com.example.tlin7877.assignment_1.database.AppDatabase;
import com.example.tlin7877.assignment_1.entity.User;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    private AppDatabase db;
    // UI references.
    private EditText mEmail;
    private EditText mPassword;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        db = AppDatabase.getAppDatabase(this);

        // Set up the login form.
        mEmail = (EditText) findViewById(R.id.txtEmail_sign_in);
        mPassword = (EditText) findViewById(R.id.txtPassword_sign_in);

        mLoginFormView = findViewById(R.id.frm_sign_in);
        mProgressView = findViewById(R.id.login_progress);

        Button mEmailSignInButton = (Button) findViewById(R.id.btnSignIn_sign_in);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = db.userDao().findByEmail(mEmail.getText().toString());
                if (mPassword.getText().toString().equals(user.getPassword())){
                    final EmailPersister ep =  new EmailPersister(LoginActivity.this);
                    ep.storeUser(user.getEmail());
                    Toast.makeText(LoginActivity.this,"Hello", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this,"Username and password is NOT correct", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

