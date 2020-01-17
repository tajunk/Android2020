package ca.nait.tawde1.chatter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public class ReceiveActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        getFromServer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
//        works as MenuInflater inflater = getMenuInflater();
//        but using 'this' for self called classes enables intellisense
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu_item_view_home:
            {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_item_view_text_view:
            {
                Intent intent = new Intent(this, ReceiveActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_item_view_system_list:
            {
                Intent intent = new Intent(this, SystemListActivity.class);
                startActivity(intent);
                break;
            }
        }
        return true;
    }

    private void getFromServer()
    {
        BufferedReader in = null;
        TextView textBox = findViewById(R.id.text_view_chatter);

        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI("http://www.youcode.ca/JSONServlet"));
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";

            while((line = in.readLine()) != null)
            {
                textBox.append(line + "\n");
            }
            in.close();
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }
    }
}
