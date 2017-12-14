package edu.sdmesa.coinflip;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

public class CoinActivity extends AppCompatActivity
{

    private SoundPool soundPool;
    int sound = -1;

    Button flip;
    ImageView coin;
    Random random;
    int coinSide;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);

        flip = (Button) findViewById(R.id.flip);

        coin = (ImageView) findViewById(R.id.coin);

        random = new Random();

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC,0);

        startService(new Intent(this, BackgroundMusic.class));

        try
        {
            //create objects of the 2 required classes
            AssetManager assetsManager = getAssets();
            AssetFileDescriptor descriptor;

            //create our three fx in memory ready for use
            descriptor = assetsManager.openFd("coin.wav");
            sound = soundPool.load(descriptor, 0);
        }
        catch (IOException e)
        {

            Context context = getApplicationContext();
            CharSequence text = "BUTTON NOT FOUND!";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        //flip animation
        flip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                coinSide = random.nextInt(2);

                if(coinSide == 0)
                {
                    coin.setImageResource(R.drawable.heads);
                    Toast.makeText(CoinActivity.this, "Heads!", Toast.LENGTH_SHORT).show();
                }
                else if(coinSide == 1)
                {
                    coin.setImageResource(R.drawable.tails);
                    Toast.makeText(CoinActivity.this, "Tails!", Toast.LENGTH_SHORT).show();
                }

                RotateAnimation rotate = new RotateAnimation(0, 360,
                        RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);

                rotate.setDuration(2000);
                coin.startAnimation(rotate);
                //flip noise
                switch (view.getId())
                {
                    case R.id.flip:  //when the first button is pressed
                        //play sample 1
                        soundPool.play(sound, 1, 1, 0, 0, 1);
                        break;
                }
            }
        });
    }

    Intent f;

    @Override
    public void onBackPressed ()
    {
        AlertDialog.Builder feedBack = new AlertDialog.Builder(this);
        feedBack.setMessage("Please Provide us with Feedback");
        feedBack.setCancelable(true);

        f = new Intent(this,FeedbackActivity.class);

        feedBack.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
               startActivity(f);
            }
        });

        feedBack.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                finish();
            }
        });

        feedBack.setNeutralButton("Later", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                finish();
            }
        });

        AlertDialog dialog = feedBack.create();
        dialog.show();
    }
}
