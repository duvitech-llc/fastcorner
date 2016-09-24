/*==============================================================================
            Copyright (c) 2010-2011 Qualcomm Technologies Incorporated.
            All Rights Reserved.
            Qualcomm Technologies Confidential and Proprietary
==============================================================================*/

package com.qualcomm.fastcorner;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

/** The splash screen activity for FastCV sample application */
public class SplashScreen extends Activity 
{
    private static final String TAG = "FASTCV_EX";
    private WindowManager               mWindowManager;
    private SplashScreenView            mHomeView;
    public static Display               sDisplay;

    /**
     * Id to identify a camera permission request.
     */
    private static final int REQUEST_CAMERA = 0;
    private static final int REQUEST_AUDIO = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        sDisplay = mWindowManager.getDefaultDisplay();
      
        // Initialize UI
        mHomeView = new SplashScreenView(this);
        setContentView( mHomeView );


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            requestCameraPermission();

        }else{

            Log.i(TAG,
                    "CAMERA permission has already been granted.");
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            requestAudioPermission();

        }else{

            Log.i(TAG,
                    "RECORD_AUDIO permission has already been granted.");
        }

    }

    private void requestCameraPermission() {
        Log.i(TAG, "CAMERA permission has NOT been granted. Requesting permission.");


        // Camera permission has not been granted yet. Request it directly.
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                REQUEST_CAMERA);

    }

    private void requestAudioPermission() {
        Log.i(TAG, "RECORD_AUDIO permission has NOT been granted. Requesting permission.");


        // Camera permission has not been granted yet. Request it directly.
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                REQUEST_AUDIO);

    }

    @Override
    protected void onPause() 
    {
       super.onPause(); 
    }
    /** Called when the option menu is created. */
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) 
    {
       MenuInflater inflater = getMenuInflater();
       inflater.inflate( R.menu.splashmenu, menu );
       return true;
    }
    
     /** User Option selection menu
     */
    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
       // Handle item selection
       switch( item.getItemId() ) 
       {
       case R.id.main_start:
           Intent startActivity = new Intent(getBaseContext(), FastCVSample.class);
           startActivity( startActivity );

           return true;
          
       case R.id.settings:
           Intent settingsActivity = new Intent( getBaseContext(), Preferences.class );
           startActivity( settingsActivity );

           return true;

       case R.id.about:
          Intent aboutActivity = new Intent( getBaseContext(), About.class );
          startActivity( aboutActivity );

          return true;

       default:
          return super.onOptionsItemSelected(item);
       }
    }
}