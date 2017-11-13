package com.example.ratha.notificationdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnOneChanel)
    public void pushNotificationOneChanel(View view){
       /* Intent intent=new Intent(this,PushOneChanelActivity.class);
        startActivity(intent);*/
       postNitification();
    }

    private void postNitification(){
        final String CHANEL_ID  ="my_chanel_01";

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,CHANEL_ID);
        builder.setSmallIcon(R.drawable.ic_teddy_bear_24dp);
        String text="You posted in your timeline";
        builder.setContentTitle("A Im Posted");
        builder.setContentText(text);

        Intent mRestultIntent=new Intent(this,ResultActivity.class);
        mRestultIntent.putExtra("DATA",text);


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

        //builder.setLargeIcon(BitmapFactory.decodeResource(getResources() ,R.drawable.ic_bear_460dp));
        builder.setSubText("I'm also post some text to!");

        //setBoxstyle
        NotificationCompat.InboxStyle inboxStyle=new NotificationCompat.InboxStyle();
        String[] events={   "1. Message for A Im",
                            "2. Big View notificatio",
                            "3. From HRD"};

        for(String s : events){
            inboxStyle.addLine(s);
        }
        //builder.setStyle(inboxStyle);

        NotificationCompat.BigPictureStyle bigPictureStyle=new NotificationCompat.BigPictureStyle();

        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.ic_bear_460dp);
        bigPictureStyle.setBigContentTitle("Big content title");
        bigPictureStyle.setSummaryText(getString(R.string.notification_big_pic_view_summary));
        bigPictureStyle.bigPicture(bitmap);
        //bigPictureStyle.bigLargeIcon(bitmap);
        builder.setStyle(bigPictureStyle);

        builder.setTicker("some posted");
        builder.setContentIntent(mResultPendingIntent);
        NotificationManager mNotificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int mNotificationId=(int) System.currentTimeMillis()/1000;
        mNotificationManager.notify(mNotificationId,builder.build());

    }
}
