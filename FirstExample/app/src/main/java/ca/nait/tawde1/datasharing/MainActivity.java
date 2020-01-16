package ca.nait.tawde1.datasharing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendButton = findViewById(R.id.button_send_data);
        sendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        EditText textBox = findViewById(R.id.edit_text_data);
        String data = textBox.getText().toString();

        //Creating a message and sending it to the ReceiveActivity
        Intent intent = new Intent(this, ReceiveActivity.class);

        // Package multiple strings in to one variable to send to other Activities
        Bundle theBundle = new Bundle();
        theBundle.putString("PREFIX", "From main activity: ");
        theBundle.putString("DATA", data);

        intent.putExtras(theBundle);

        startActivity(intent);

        // Text popup displaying text typed in EditText
        //Toast.makeText(this, "You typed: " + data, Toast.LENGTH_LONG).show();
    }
}
