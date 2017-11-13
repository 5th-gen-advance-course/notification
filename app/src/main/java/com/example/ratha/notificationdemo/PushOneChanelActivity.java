package com.example.ratha.notificationdemo;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ratha.notificationdemo.entity.Song;

public class PushOneChanelActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_one_chanel);

        postNitification();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNitificationChanel(){
        NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String id="my_chanel_one";
        CharSequence name=getString(R.string.chanel_name);
        String desc=getString(R.string.chanel_desc);

        int importance=NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel=new NotificationChannel(id,name,importance);
        channel.setDescription(desc);
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        manager.createNotificationChannel(channel);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void postNitification(){
        final String CHANEL_ID  ="my_chanel_01";
        int mNotificationId=1;
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,CHANEL_ID);
        builder.setSmallIcon(R.drawable.ic_teddy_bear_24dp);
        builder.setContentTitle("A Im Posted");
        builder.setContentText("You posted in your timeline");

        Intent mRestultIntent=new Intent(this,ResultActivity.class);

        /*TaskStackBuilder taskStackBuilder=TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(ResultActivity.class);
        taskStackBuilder.addNextIntent(mRestultIntent);

        PendingIntent pendingIntent= taskStackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_CANCEL_CURRENT
        );*/

        PendingIntent mResultPendingIntent=PendingIntent.getActivity(this,
                0,
                mRestultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);


        /*Bundle bundle=new Bundle();
        Song song=new Song();
        bundle.putParcelable("SONG",song);
        Intent intent=new Intent(this,ResultActivity.class);
        intent.putExtra("BUNDLE",bundle);*/


        builder.setContentIntent(mResultPendingIntent);
        NotificationManager mNotificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(mNotificationId,builder.build());

    }

}
