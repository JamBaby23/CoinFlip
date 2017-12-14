package edu.sdmesa.coinflip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FeedbackActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText subject = (EditText) findViewById(R.id.subject);
        final EditText emailText = (EditText) findViewById(R.id.emailText);

        Button sendEmail = (Button) findViewById(R.id.sendEmail);
        sendEmail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String getEmail = email.getText().toString();
                String getSubject = subject.getText().toString();
                String getText = emailText.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{getEmail});
                email.putExtra(Intent.EXTRA_SUBJECT, getSubject);
                email.putExtra(Intent.EXTRA_TEXT,getText);

                email.setType("jammal.loiseau@yahoo.com");
                startActivity(Intent.createChooser(email,"Select an App"));

            }
        });

    }
}
