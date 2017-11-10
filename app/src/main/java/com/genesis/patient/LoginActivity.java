package com.genesis.patient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;
    private static final int existingUser = 0;

    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;

    SharedPreferences signInInfo;
    SharedPreferences.Editor signInEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInInfo = getApplicationContext().getSharedPreferences("MyPref", 0);


        ImageView gsignin = (ImageView) findViewById(R.id.gimage);

        gsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                       // updateUI(false);
                    }
                });
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        //updateUI(false);
                    }
                });
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading");
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.e(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + (acct != null ? acct.getDisplayName() : "NO Account Detected"));

            String personName = acct != null ? acct.getDisplayName() : "NO Account Detected";
            String personPhotoUrl = acct != null ? acct.getPhotoUrl().toString() : "NO Account Detected";
            String email = acct != null ? acct.getEmail() : "NO Account Detected";

            Toast.makeText(this, personName +"  "+email, Toast.LENGTH_LONG).show();

            Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);

            signInEditor = signInInfo.edit();
            signInEditor.clear();
            signInEditor.putString("email",email);
            signInEditor.putString("name",personName);
            signInEditor.apply();

            PatientBean pb = new PatientBean();

            showProgressDialog();
            //#######################################################################################################################################################
            //Send email to the server and get the already details entered flag
            // pb.existingUser = "1" for already existing
            // pb.existingUser = "0" for new user
            // pb.existingUser = "1" for already existing
            // pb.existingUser = "0" for new user
            // get all other details of the user.
            // update this value
            // update all values of Patient in pb
            //#######################################################################################################################################################
            hideProgressDialog();

            // Temporary  ###########################################################################################################################################
            pb.setExistingUser("0");

            pb.setName(personName);
            pb.setAddress("PYRA 34, Kaintikkara Road, Muppathadom PO, Aluva-10");
            pb.setAge("21");
            pb.setBloodGroup("B+ve");
            pb.setEmail(email);
            pb.setGender("M");
            pb.setPhn("8594014280");
            pb.setGoogleID("myid1234");
            //#######################################################################################################################################################

            if(pb.getExistingUser().equalsIgnoreCase("0")){
                Intent i = new Intent(LoginActivity.this,NewUserDetailsActivity.class);
                i.putExtra("patientbean",pb);
                startActivity(i);
            }
            else{
                Intent i = new Intent(LoginActivity.this,PatientActivity.class);

                SharedPreferences preferences = getApplicationContext().getSharedPreferences("patientbean", 0);
                SharedPreferences.Editor editor = preferences.edit();
                Gson gson = new Gson();
                String pbString = gson.toJson(pb);
                editor.putString("patientbean",pbString);
                editor.apply();

                String jsonret = preferences.getString("patientbean","");
                PatientBean pbret = gson.fromJson(jsonret,PatientBean.class);

                i.putExtra("patientbean",pb);
                startActivity(i);
            }
//            txtName.setText(personName);
//            txtEmail.setText(email);
//            Glide.with(getApplicationContext()).load(personPhotoUrl)
//                    .thumbnail(0.5f)
//                    .crossFade()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(imgProfilePic);

        } else {
            
            // Signed out, show unauthenticated UI.

        }
    }



}
