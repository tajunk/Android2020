package ca.nait.tawde1.chatter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.TextView;
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

public class CustomListActivity extends ListActivity
{
    ArrayList<HashMap<String,String>> chatter = new ArrayList<HashMap<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);
        displayChatter();
    }

    private void displayChatter()
    {
        String[] keys = new String[]{"sender", "text", "myDate"};
        int[] ids = new int[]{R.id.text_view_custom_row_sender, R.id.text_view_custom_row_message,
                R.id.text_view_custom_row_date};
        SimpleAdapter adapter = new SimpleAdapter(this, chatter, R.layout.custom_row, keys, ids);

        populateList();
        this.setListAdapter(adapter);
    }

    private void populateList()
    {
        BufferedReader in = null;

        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI("http://www.youcode.ca/JitterServlet"));
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";

            while((line = in.readLine()) != null)
            {
                HashMap<String,String> tempMap = new HashMap<String,String>();
                tempMap.put("sender",line);

                line = in.readLine();
                tempMap.put("text", line);

                line = in.readLine();
                tempMap.put("myDate", line);

                chatter.add(tempMap);
            }
            in.close();
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }
    }
}
