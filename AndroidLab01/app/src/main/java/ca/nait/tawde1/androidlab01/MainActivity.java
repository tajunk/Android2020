package ca.nait.tawde1.androidlab01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        SharedPreferences.OnSharedPreferenceChangeListener
{
    SharedPreferences prefs;
    View mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy ourPolicy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(ourPolicy);
        }

        Button sendButton = (Button)findViewById(R.id.button_send);
        sendButton.setOnClickListener(this);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);

        mainView = findViewById(R.id.main_page_layout);
        String bgColor = prefs.getString(getResources().getString(R.string.preference_key_main_bg_color), "#FCF8CF");
        mainView.setBackgroundColor(Color.parseColor(bgColor));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu_item_home:
            {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_item_reviews:
            {
                Intent intent = new Intent(this,ReceiveActivity.class);
                this.startActivity(intent);
                break;
            }
            case R.id.menu_item_preferences:
            {
                Intent intent = new Intent(this, PrefsActivity.class);
                startActivity(intent);
                break;
            }
        }
        return true;
    }

    public void onClick(View view)
    {
        EditText text = (EditText) findViewById(R.id.text_box_nominee);
        String nomineeText = text.getText().toString();

        EditText text2 = (EditText) findViewById(R.id.text_box_review);
        String reviewText = text2.getText().toString();

        RadioGroup rg = (RadioGroup) findViewById(R.id.radio_group_categories);
        int rbid = rg.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton) findViewById(rbid);
        String category = (String)rb.getTag();

        postToWeb(reviewText, nomineeText, category);
        text.setText("");
        text2.setText("");
    }

    private void postToWeb(String reviewText, String nomineeText, String category)
    {
        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(prefs.getString("url", "http://www.youcode.ca/Lab01Servlet"));
            List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("REVIEW", reviewText));
            postParameters.add(new BasicNameValuePair("REVIEWER", prefs.getString("reviewer", "")));
            postParameters.add(new BasicNameValuePair("NOMINEE", nomineeText));
            postParameters.add(new BasicNameValuePair("CATEGORY", category));
            postParameters.add(new BasicNameValuePair("PASSWORD", prefs.getString("password", "oscar275")));
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
            request.setEntity(formEntity);
            HttpResponse response = client.execute(request);

        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        String bgColor = prefs.getString(getResources().getString(R.string.preference_key_main_bg_color), "#990000");
        mainView.setBackgroundColor(Color.parseColor(bgColor));
    }
}
