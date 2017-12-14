package edu.sdmesa.coinflip;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Jammal on 12/13/2017.
 */

public class BackgroundMusic extends Service
{
    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        //BackGround Music
        player = MediaPlayer.create(this,R.raw.horizon);
        player.setLooping(true);
        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        player.stop();
    }
}
