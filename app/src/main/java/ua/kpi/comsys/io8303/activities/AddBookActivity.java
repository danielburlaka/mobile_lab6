package ua.kpi.comsys.io8303.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab6.R;

public class AddBookActivity extends Activity {
    private static final String BOOK = "movie";
    private static final String RESULT = "result";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        Intent intent = getIntent();
        String filetext = intent.getStringExtra(BOOK);
        TextView title = (TextView) findViewById(R.id.titleText);
        TextView type = (TextView) findViewById(R.id.typeText);
        TextView year = (TextView) findViewById(R.id.yearText);

        Button button = findViewById(R.id.addBookButton);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String newBook = "";
                newBook = filetext.replace("]", "");
                newBook += ", {\"Title\":\"" + title.getText().toString() + "\"," +
                        "\"Year\":\"" + year.getText().toString() + "\"," +
                        "\"imdbID\":\"\"," +
                        "\"Type\":\"" + type.getText().toString() + "\"," +
                        "\"Poster\":\"\"} ]";

                Intent resultIntent = new Intent();
                resultIntent.putExtra(RESULT, newBook);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}