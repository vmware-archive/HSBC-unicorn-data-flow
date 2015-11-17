package pivotal.io.cardsnotification;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

public class OfferDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        setContentView(R.layout.activity_offer_details);

        ImageView imageView = (ImageView)this.findViewById(R.id.imageView);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getY() > 2650) {
                    new AcceptOfferTask().execute("http://hsbc-offers.corpdemo.fe.pivotal.io/messages");
                    Intent intent = new Intent(OfferDetails.this,OfferAcceptedActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
    }

    private class AcceptOfferTask extends AsyncTask<String,Integer,Long>{
        @Override
        protected Long doInBackground(String... params) {
            String payload = NotificationHolder.getInstance().popNotification();
            DefaultHttpClient client = new DefaultHttpClient();
            if(payload != null) {

                HttpPost post = new HttpPost(params[0]);
                try {
                    Log.i("MyLog", "Posting data back to server");
                    StringEntity entity = new StringEntity(payload);
                    entity.setContentType("application/json");
                    post.setEntity(entity);
                    HttpResponse response = client.execute(post);
                    Log.i("MyLog","Response: " + response.getStatusLine().getStatusCode());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    client.getConnectionManager().shutdown();
                }
            }
            return 1L;
        }
    }
}
