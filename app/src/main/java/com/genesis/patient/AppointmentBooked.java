package com.genesis.patient;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.Toast;

public class AppointmentBooked extends AppCompatActivity  {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_booked);



        AppointmentBean ab = (AppointmentBean) getIntent().getSerializableExtra("appointmentbean");

        PatientBean pb = ab.getPb();
        String doctorid = ab.getDoctorid();
        String dateDay = ab.getDateDay();
        String dateMonth = ab.getDateMonth();
        String dateYear = ab.getDateYear();

        Toast.makeText(this,"doctoid",Toast.LENGTH_SHORT).show();












    }






}
