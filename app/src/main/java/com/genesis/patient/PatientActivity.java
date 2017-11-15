package com.genesis.patient;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cuboid.cuboidcirclebutton.CuboidButton;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientActivity extends AppCompatActivity {

    private SpaceNavigationView spaceNavigationView;

    private CuboidButton bookAppointments, historyAppointments, scheduledAppointments, doctorSchedule;
    private TextView personName, personEmail, personBloodGroup, personPhone, personGender;
    private CircleImageView personImage;

    private PatientBean pb;
    private AppointmentBean ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        bookAppointments = (CuboidButton) findViewById(R.id.bookappointment);
        historyAppointments = (CuboidButton) findViewById(R.id.prevappointments);
        scheduledAppointments = (CuboidButton) findViewById(R.id.appointmentschedule);
        doctorSchedule = (CuboidButton) findViewById(R.id.doctorschedule);

        personBloodGroup = (TextView) findViewById(R.id.personbloodgroup);
        personEmail = (TextView) findViewById(R.id.personemail);
        personGender = (TextView) findViewById(R.id.persongender);
        personImage = (CircleImageView) findViewById(R.id.personimage);
        personName = (TextView) findViewById(R.id.personname);
        personPhone = (TextView) findViewById(R.id.personphone);

        pb = (PatientBean) getIntent().getSerializableExtra("patientbeantemp");

        personBloodGroup.setText(pb.getBloodGroup());
        personGender.setText(pb.getGender());
        personEmail.setText(pb.getEmail());
        personPhone.setText(pb.getPhn());
        personName.setText(pb.getName());

        Glide.with(getApplicationContext()).load(pb.getPhotoURL())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(personImage);

        bookAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ab = new AppointmentBean();

                ab.setPb(pb);
                ab.setDateDay("17");
                ab.setDateMonth("11");
                ab.setDateYear("2017");
                ab.setDoctorid("doctor123456");
                ab.setDateHourStart("10");
                ab.setDateMinutesEnd("30");
                ab.setDateHourEnd("12");
                ab.setDateMinutesStart("30");


                Intent i = new Intent(PatientActivity.this,AppointmentBooked.class);
                i.putExtra("appointmentbean",ab);
                startActivity(i);
            }
        });









    }
}
