package pivotal.io.cardsnotification;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import io.pivotal.android.push.Push;
import io.pivotal.android.push.registration.RegistrationListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerPush();


        ImageView imageView = (ImageView)this.findViewById(R.id.imageView);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if((event.getY() > 1400 && event.getY() < 1560) && event.getX() < 800){
                    Intent intent = new Intent(MainActivity.this,OfferActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });


    }

    private void registerPush(){
        try {
            // RegistrationListener is optional and may be `null`.
            Set<String> tags = new HashSet<>();
            tags.add("hsbc");
            Push.getInstance(this).startRegistration("dev1", tags, false, new RegistrationListener() {

                @Override
                public void onRegistrationComplete() {
                    Log.i("MyLogTag", "Registration with PCF Push successful.");
                }

                @Override
                public void onRegistrationFailed(String reason) {
                    Log.e("MyLogTag", "Registration with PCF Push failed: " + reason);
                }
            });
        } catch (Exception e) {
            Log.e("MyLogTag", "Registration with PCF Push failed: " + e);
        }
    }




}
