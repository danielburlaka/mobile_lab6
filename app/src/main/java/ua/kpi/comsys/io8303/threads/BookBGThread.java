package ua.kpi.comsys.io8303.threads;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import ua.kpi.comsys.io8303.ui_fragments.book_list.BookListFragment;

public class BookBGThread implements  Runnable {

    private String search;

    public BookBGThread(String search) { this.search = search; }

    @Override
    public void run() {
        try {
            BookListFragment.getUrlResponse(getSearch());
        } catch (IOException ignored) {
        }
    }

    public String getSearch() {
        String URL_ENDPOINT_SERVER = String.format("https://api.itbook.store/1.0/search/%s", search);
        try {
            URL url = new URL(URL_ENDPOINT_SERVER);
            Log.i("NewsDataLoader", url.toString());
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            // Open the response stream and get a reader for it.
            InputStream responseStream = connection.getInputStream();

            //checking if input stream is available
            if (responseStream.available() < 0)
                Log.i("NewsDataLoader", "istream is available");
            else Log.i("NewsDataLoader", "istream is not available");
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            //just in case there is more than one line
            while ((line = reader.readLine()) != null) {
                Log.i("NewsDataLoader", "Reading line");
                stringBuilder.append(line);
            }
            connection.disconnect();
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
