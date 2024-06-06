package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.Location;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class LocationService extends Service {
    // declaring object of MediaPlayer
    private MediaPlayer player;

    @Override

    // execution of service will start
    // on calling this method
    public int onStartCommand(Intent intent, int flags, int startId) {

        LocationModel locationModel = new LocationModel(getApplicationContext());
        locationModel.locationModel();



        // creating a media player which
        // will play the audio of Default
        // ringtone in android device
        player = MediaPlayer.create( this, Settings.System.DEFAULT_RINGTONE_URI );
        Log.d("onStartCommand","onStartCommand");

        // providing the boolean
        // value as true to play
        // the audio on loop
        player.setLooping( true );

        // starting the process
        player.start();



        // returns the status
        // of the program
        return START_STICKY;
    }

    @Override

    // execution of the service will
    // stop on calling this method
    public void onDestroy() {

        Log.d("onDestroy","onDestroy");
        super.onDestroy();


        // stopping the process
        player.stop();
    }



    public LocationService() {
        Log.d("LocationService2","LocationService2 running");
//        LocationModel locationModel = new LocationModel(getApplicationContext());
//        locationModel.locationModel();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}