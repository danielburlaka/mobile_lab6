package ua.kpi.comsys.io8303.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lab6.R;
import ua.kpi.comsys.io8303.model.BookDescritionItem;
import ua.kpi.comsys.io8303.threads.DisplayBookBGThread;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class DisplayBookActivity extends Activity {
    private static final String BOOK = "movie";
    private static String bookInfoJSON = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaybook);
        Intent intent = getIntent();
        String book = intent.getStringExtra(BOOK);

        Gson gson = new Gson();
        DisplayBookBGThread g = new DisplayBookBGThread(book);
        Thread t = new Thread(g, "Background Thread");
        t.start();//we start the thread
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BookDescritionItem description = new BookDescritionItem();
        Type BookDescritionItemType = new TypeToken<BookDescritionItem>() {}.getType();
        System.out.println(bookInfoJSON);
        description = gson.fromJson(bookInfoJSON, BookDescritionItemType);

        TextView tv = (TextView) findViewById(R.id.desciption);
        tv.setText(description.toString());

        if (description.getImage() != null){
            ImageView poster = findViewById(R.id.poster_display);
            Glide.with(this).load(description.getImage()).into(poster);
        }

    }

    public String ReadTextFile(String name) throws IOException {
        StringBuilder string = new StringBuilder();
        String line = "";
        InputStream is = this.getAssets().open(name);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            string.append(line);
        }
        is.close();
        return string.toString();
    }

    public static void getUrlResponse(String search) throws IOException {
        bookInfoJSON = search;
    }
}