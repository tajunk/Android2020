package ca.nait.tawde1.threaddemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "in onCreate()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        Log.d(TAG, "in onCreateOptionsMenu()");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu_item_start_service:
            {
                Log.d(TAG, "Menu item start service");
                startService(new Intent(this, ChatService.class));
                break;
            }
            case R.id.menu_item_stop_service:
            {
                Log.d(TAG, "Menu item stop service");
                stopService(new Intent(this, ChatService.class));
                break;
            }
        }
        return true;
    }
}
