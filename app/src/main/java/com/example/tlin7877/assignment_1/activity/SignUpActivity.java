package com.example.tlin7877.assignment_1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tlin7877.assignment_1.database.AppDatabase;
import com.example.tlin7877.assignment_1.R;
import com.example.tlin7877.assignment_1.entity.User;

/**
 * A login screen that offers login via email/password.
 */
public class SignUpActivity extends AppCompatActivity{

    private AppDatabase db;
    // UI references.
    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtAddress;
    private EditText txtCity;
    private EditText txtState;
    private EditText txtZipCode;
    private EditText txtEmail;
    private EditText txtPassword;

    private RadioButton radioDigitalCard;
    private RadioButton radioUseCard;
    private RadioButton radioNotJoin;

    private Spinner spinnerMonth;
    private Spinner spinnerDay;

    private CheckBox chboxReceiveEmail;
    private CheckBox chboxUseFingerPrint;
    private CheckBox chboxTermOfUse;


    private View mProgressView;
    private View mSignUpFormView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        // Set up the login form.
        txtFirstName = (EditText) findViewById(R.id.txtFirstName_sign_up);
        txtLastName = (EditText) findViewById(R.id.txtLastName_sign_up);
        txtAddress = (EditText) findViewById(R.id.txtAddress1_sign_up);
        txtCity = (EditText) findViewById(R.id.txtCity_sign_up);
        txtState = (EditText) findViewById(R.id.txtState_sign_up);
        txtZipCode = (EditText) findViewById(R.id.txtZipCode_sign_up);
        txtEmail = (EditText) findViewById(R.id.txtEmail_sign_up);
        txtPassword = (EditText) findViewById(R.id.txtPassword_sign_up);

        radioDigitalCard = (RadioButton) findViewById(R.id.radio_digital_card);
        radioUseCard = (RadioButton) findViewById(R.id.radio_use_card);
        radioNotJoin = (RadioButton) findViewById(R.id.radio_not_join);

        chboxReceiveEmail = (CheckBox) findViewById(R.id.chbox_receive_email);
        chboxUseFingerPrint = (CheckBox) findViewById(R.id.chbox_use_fingerprint);
        chboxTermOfUse = (CheckBox) findViewById(R.id.chbox_term_of_use);


       spinnerMonth = (Spinner) findViewById(R.id.spinner_month);
        ArrayAdapter<CharSequence> adapterMonth = ArrayAdapter.createFromResource(this,
                R.array.spinner_month, android.R.layout.simple_spinner_item);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(adapterMonth);

        spinnerDay = (Spinner) findViewById(R.id.spinner_day);
        ArrayAdapter<CharSequence> adapterDay = ArrayAdapter.createFromResource(this,
                R.array.spinner_day, android.R.layout.simple_spinner_item);
        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDay.setAdapter(adapterDay);

        Button mJoinRewardsButton = (Button) findViewById(R.id.btnJoinRewards_sign_up);
    }

    // Handles radio button check
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_digital_card:
                if (checked)
                    break;
            case R.id.radio_use_card:
                if (checked)
                    break;
            case R.id.radio_not_join:
                if (checked)
                    break;
        }
    }

    public void addData(View view){
        if (txtFirstName.getText().toString() != "" &&
                txtLastName.getText().toString() != "" &&
                txtAddress.getText().toString() != "" &&
                txtCity.getText().toString() != "" &&
                txtState.getText().toString() != "" &&
                txtZipCode.getText().toString() != "" &&
                txtEmail.getText().toString() != "" &&
                txtPassword.getText().toString() != "" &&
                (radioDigitalCard.isChecked() ||
                        radioUseCard.isChecked() ||
                        radioNotJoin.isChecked()) &&
                chboxTermOfUse.isChecked()
                ){
            String dob =  spinnerMonth.getSelectedItem().toString()
                    + spinnerDay.getSelectedItem().toString();
            int receiveEmail = 1;
            if (!chboxReceiveEmail.isChecked()){
                receiveEmail = 0;
            }

            // Inserting User
            db = AppDatabase.getAppDatabase(this);
            db.userDao().insert(new User(txtEmail.getText().toString(),
                    txtPassword.getText().toString(),
                    txtFirstName.getText().toString(),
                    txtLastName.getText().toString(),
                    txtAddress.getText().toString(),
                    txtCity.getText().toString(),
                    txtState.getText().toString(),
                    txtZipCode.getText().toString(),
                    dob,receiveEmail));
            Toast.makeText(SignUpActivity.this,"Please sign in", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(SignUpActivity.this,
                    "Please fill in all the required fields with *.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}

