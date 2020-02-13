package ca.nait.tawde1.androidlab01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

public class ReceiveActivity extends AppCompatActivity
{
    ArrayList<HashMap<String,String>> outlet = new ArrayList<HashMap<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        displayOutlet();
        String barColor = "#383836";
    }

    public void displayOutlet()
    {
        String[] fields = new String[]{"Date","Reviewer","Category","Nominee","Review"};
        int [] ids = new int[]{R.id.time,R.id.reviewer,R.id.category,R.id.nominee,R.id.review};

        SimpleAdapter adapter = new SimpleAdapter(this,outlet,R.layout.custom_row,fields,ids);
        ListView listView = (ListView)findViewById(R.id.list_view_custom);
        populateList();
        listView.setAdapter(adapter);
    }
    public void populateList()
    {
        BufferedReader in = null;
        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI("http://www.youcode.ca/Lab01Servlet"));
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while((line = in.readLine()) != null)
            {
                HashMap<String,String> temp = new HashMap<String, String>();
                temp.put("Date",line);

                line = in.readLine();
                temp.put("Reviewer",line);

                line = in.readLine();
                temp.put("Category",line);

                line = in.readLine();
                temp.put("Nominee",line);

                line = in.readLine();
                temp.put("Review",line);

                outlet.add(temp);
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }
    }
}
