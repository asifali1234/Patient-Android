package com.genesis.patient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

public class OTPVerifyActivity extends AppCompatActivity {

    Button verify;

    CustomEditText otp;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverify);

        verify = (Button) findViewById(R.id.patient_verify);
        otp = (CustomEditText) findViewById(R.id.patient_otp);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String otp_entered = otp.getText().toString();
                showProgressDialog("Verifying");

                // #########################################################################################################
                // Verify otp
                //##########################################################################################################

                if(otp_entered.equalsIgnoreCase("1234")){

                    hideProgressDialog();
                    showProgressDialog("Registering");
                    PatientBean pb = (PatientBean) getIntent().getSerializableExtra("patientbean");

                    // #########################################################################################################
                    // Register user details.
                    // send details to server
                    //##########################################################################################################

                    SharedPreferences preferences = getApplicationContext().getSharedPreferences("patientbean", 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    Gson gson = new Gson();
                    String pbString = gson.toJson(pb);
                    editor.putString("patientbean",pbString);
                    editor.apply();

                    String jsonret = preferences.getString("patientbean","");
                    PatientBean pbret = gson.fromJson(jsonret,PatientBean.class);
                    hideProgressDialog();

                    Intent i = new Intent(OTPVerifyActivity.this,PatientActivity.class);
                    i.putExtra("patientbean",pb);
                    startActivity(i);

                }
                else {
                    hideProgressDialog();
                    Toast.makeText(OTPVerifyActivity.this,"OTP is wrong",Toast.LENGTH_LONG).show();
                }


            }
        });


    }

    private void showProgressDialog(String str) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(str);
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }
}
