package util;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Gustavo on 4/12/16.
 */

public class VolleyBlockingRequestActivity extends Activity {

    public static final String REQUEST_TAG = "VolleyBlockingRequestActivity";
    private TextView mTextView;
    private Button mButton;
    private RequestQueue mQueue;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_volley_blocking_request);
//
//        mTextView = (TextView) findViewById(R.id.textView);
//        mButton = (Button) findViewById(R.id.button);
//    }

    @Override
    protected void onStart() {
//        super.onStart();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startParsingTask();
            }
        });
    }

    @Override
    protected void onStop() {
//        super.onStop();
        if (mQueue != null) {
            mQueue.cancelAll(REQUEST_TAG);
        }
    }

    public void startParsingTask() {
        Thread threadA = new Thread() {
            public void run() {
                ThreadB threadB = new ThreadB(getApplicationContext());
                JSONObject jsonObject = null;
                try {
                    jsonObject = threadB.execute().get(10, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
                final JSONObject receivedJSONObject = jsonObject;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText("Response is: " + receivedJSONObject);
                        if (receivedJSONObject != null) {
                            try {
                                mTextView.setText(mTextView.getText() + "\n\n" +
                                        receivedJSONObject.getString("name"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        };
        threadA.start();
    }

    private class ThreadB extends AsyncTask<Void, Void, JSONObject> {
        private Context mContext;

        public ThreadB(Context ctx) {
            mContext = ctx;
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            final RequestFuture<JSONObject> futureRequest = RequestFuture.newFuture();
            mQueue = CustomVolleyRequestQueue.getInstance(mContext.getApplicationContext())
                    .getRequestQueue();
            String url = "http://api.openweathermap.org/data/2.5/weather?q=London,uk";
            final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method
                    .GET, url,
                    new JSONObject(), futureRequest, futureRequest);
            jsonRequest.setTag(REQUEST_TAG);
            mQueue.add(jsonRequest);
            try {
                return futureRequest.get(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
