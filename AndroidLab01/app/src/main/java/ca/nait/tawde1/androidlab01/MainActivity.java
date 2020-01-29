package ca.nait.tawde1.androidlab01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRadioButtonClicked(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId())
        {
            case R.id.radio_category_picture:
                if (checked)
                    break;
            case R.id.radio_category_actor:
                if (checked)
                    break;
            case R.id.radio_category_actress:
                if (checked)
                    break;
            case R.id.radio_category_editing:
                if (checked)
                    break;
            case R.id.radio_category_effects:
                if (checked)
                    break;
        }
    }
}
