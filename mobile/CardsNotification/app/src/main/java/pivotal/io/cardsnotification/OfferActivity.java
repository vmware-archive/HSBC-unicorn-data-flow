package pivotal.io.cardsnotification;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class OfferActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        setContentView(R.layout.activity_offer);

        ImageView imageView = (ImageView)this.findViewById(R.id.imageView);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("MyLog",event.getX()+":"+event.getY());
                if ((event.getY() > 270 && event.getY() < 470)) {
                    Intent intent = new Intent(OfferActivity.this, OfferDetails.class);
                    startActivity(intent);
                    return false;
                }
                return false;
            }
        });

    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

}
