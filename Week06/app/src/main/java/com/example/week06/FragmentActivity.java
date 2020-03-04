package com.example.week06;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Button buttonTwo = findViewById(R.id.button_two);
        buttonTwo.setOnClickListener(this);
        Button buttonThree = findViewById(R.id.button_three);
        buttonThree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.button_two:
            {
                loadFragment(new FragmentTwo());
                break;
            }
            case R.id.button_three:
            {
                loadFragment(new FragmentThree());
                break;
            }
        }
    }

    private void loadFragment(Fragment fragment)
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_placeholder, fragment);
        ft.commit();
    }
}
