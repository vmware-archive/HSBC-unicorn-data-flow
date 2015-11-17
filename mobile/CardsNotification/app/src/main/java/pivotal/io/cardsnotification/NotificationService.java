package pivotal.io.cardsnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import io.pivotal.android.push.service.GcmService;

public class NotificationService extends GcmService {
    public static final int NOTIFICATION_ID = 1;
    private static final int NOTIFICATION_LIGHTS_COLOR = 0xff008981;
    private static final int NOTIFICATION_LIGHTS_ON_MS = 500;
    private static final int NOTIFICATION_LIGHTS_OFF_MS = 1000;
    public NotificationService() {
    }

    @Override
    public void onReceiveMessage(Bundle payload) {
        if (payload.containsKey("message")) {
            final String message = payload.getString("message");
            handleMessage(message);
        }
    }

    private void handleMessage(String msg) {
        final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        NotificationHolder.getInstance().addNotification(msg);

        builder.setLights(NOTIFICATION_LIGHTS_COLOR, NOTIFICATION_LIGHTS_ON_MS, NOTIFICATION_LIGHTS_OFF_MS)
                .setSmallIcon(R.drawable.hsbc_logo)
                .setContentTitle("You have a new card offer")
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setContentText("Open to check your new card offer")
                ;

        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }
}
