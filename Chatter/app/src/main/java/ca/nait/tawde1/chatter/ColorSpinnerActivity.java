package ca.nait.tawde1.chatter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.*;
import android.widget.Spinner;

public class ColorSpinnerActivity extends AppCompatActivity
{
    public static final String TAG = "ColorSpinnerActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_spinner);
        Spinner colorSpinner = findViewById(R.id.color_spinner_change);

        ////////////////////////////////////
        // following applies an implicit instantiation of a class
        colorSpinner.setOnItemSelectedListener(new MyColorSpinnerListener());

        ////////////////////////////////////
        // following applies an explicit instantiation
        MyColorSpinnerListener listener = new MyColorSpinnerListener();
        colorSpinner.setOnItemSelectedListener(listener);
    }
}
class MyColorSpinnerListener implements OnItemSelectedListener
{
    @Override
    public void onItemSelected(AdapterView<?> spinner, View view, int position, long id)
    {
        View linearLayout = (View)spinner.getParent();
        String bgColor = spinner.getResources().getStringArray(R.array.color_values)[position];
        linearLayout.setBackgroundColor(Color.parseColor(bgColor));
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
