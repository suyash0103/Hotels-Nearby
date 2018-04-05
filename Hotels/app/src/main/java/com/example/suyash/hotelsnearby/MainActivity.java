package com.example.suyash.hotelsnearby;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN_1 = 1;
    private int RC_SIGN_IN_2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI(account);

        findViewById(R.id.sign_in_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(1);
            }
        });

        findViewById(R.id.sign_in_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(2);
            }
        });


    }

    private void signIn(int a) {
        if(a == 1)
        {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN_1);
        }
        else
        {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN_2);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN_1) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task, 1);
        }
        else if(requestCode == RC_SIGN_IN_2)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task, 2);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask, int a) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            updateUI(account, a);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google Sign In", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null, 0);
        }
    }

    private void updateUI(GoogleSignInAccount account, int a) {
        if (account != null && a == 1) {
            Toast.makeText(MainActivity.this, "Logged In ", Toast.LENGTH_SHORT).show();
            Intent AfterLoginIntent = new Intent(MainActivity.this, Owner.class);
            startActivity(AfterLoginIntent);
            finish();
        }
        else if(account != null && a == 2)
        {
            Toast.makeText(MainActivity.this, "Logged In ", Toast.LENGTH_SHORT).show();
            Intent AfterLoginIntent = new Intent(MainActivity.this, User.class);
            startActivity(AfterLoginIntent);
            finish();
        }
    }

}
