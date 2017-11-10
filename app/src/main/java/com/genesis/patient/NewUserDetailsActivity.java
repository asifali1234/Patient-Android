package com.genesis.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewUserDetailsActivity extends AppCompatActivity {

    PatientBean pb;

    TextView name, email;
    CustomEditText phn, address, bloodGroup, gender, age;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_details);
        pb = (PatientBean) getIntent().getSerializableExtra("patientbean");

        name = (TextView) findViewById(R.id.patient_name);
        email = (TextView) findViewById(R.id.patient_email);

        name.setText(pb.getName());
        email.setText(pb.getName());

        phn = (CustomEditText) findViewById(R.id.patient_phone);
        address = (CustomEditText) findViewById(R.id.patient_address);
        bloodGroup = (CustomEditText) findViewById(R.id.patient_blood);
        gender = (CustomEditText) findViewById(R.id.patient_gender);
        age = (CustomEditText) findViewById(R.id.patient_age);

        submit = (Button) findViewById(R.id.patient_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setPhn(phn.getText().toString());
                pb.setAddress(address.getText().toString());
                pb.setBloodGroup(bloodGroup.getText().toString());
                pb.setGender(gender.getText().toString());
                pb.setAge(age.getText().toString());

                Intent i = new Intent(NewUserDetailsActivity.this,OTPVerifyActivity.class);
                i.putExtra("patientbean",pb);
                startActivity(i);
            }
        });

    }
}
