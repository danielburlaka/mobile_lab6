package ua.kpi.comsys.io8303.ui_fragments.pics;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.lab6.R;
import ua.kpi.comsys.io8303.adapters.PicsAdapter;
import ua.kpi.comsys.io8303.model.PicItem;
import ua.kpi.comsys.io8303.model.PicItemJson;
import ua.kpi.comsys.io8303.model.PicSearchJson;
import ua.kpi.comsys.io8303.threads.DisplayPicsBGThread;
import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PicsFragment extends Fragment {

    private static final int RESULT_LOAD_IMG = 1;
    private ListAdapter adapter;
    private AsymmetricGridView listView;
    final List<PicItem> items = new ArrayList<>();
    private static String picsJSON = "";

    private int index = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pics, container, false);

        listView = root.findViewById(R.id.picsList);
        Button addButton = root.findViewById(R.id.addPicButton);

        Gson gson = new Gson();
        Type listOfMoviesItemsType = new TypeToken<PicSearchJson>() {}.getType();
        DisplayPicsBGThread g = new DisplayPicsBGThread();
        Thread t = new Thread(g, "Background Thread");
        t.start();//we start the thread
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PicSearchJson searchItem = gson.fromJson(picsJSON, listOfMoviesItemsType);
        ArrayList<Integer> Span = new ArrayList<>();
        fillSpan(Span);
        for (PicItemJson pic : searchItem.getPics()) {
            items.add(new PicItem(Span.get(index % 8), Span.get(index % 8), 0, pic.getImage(), null, null));
            index++;
            refresh();
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });


        listView.setRequestedColumnWidth(Utils.dpToPx(this.getContext(), 80));
        adapter = new PicsAdapter(getContext(), items);
        listView.setAdapter(new AsymmetricGridViewAdapter(getContext(), listView, adapter));

        return root;
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        System.out.println("Result Code: " + reqCode);
        if (reqCode == RESULT_LOAD_IMG) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContext().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                ArrayList<Integer> Span = new ArrayList<>();
                fillSpan(Span);
                items.add(new PicItem(Span.get(index % 8), Span.get(index % 8), 0, null, selectedImage, imageUri));
                index++;
                refresh();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void fillSpan(ArrayList<Integer> columnSpan) {
        columnSpan.add(1);
        columnSpan.add(3);
        columnSpan.add(1);
        columnSpan.add(1);
        columnSpan.add(1);
        columnSpan.add(1);
        columnSpan.add(1);
        columnSpan.add(1);
    }

    private void refresh() {
        listView.setRequestedColumnWidth(Utils.dpToPx(this.getContext(), 80));
        adapter = new PicsAdapter(getContext(), items);
        listView.setAdapter(new AsymmetricGridViewAdapter(getContext(), listView, adapter));
    }

    public static void getUrlResponse(String search) throws IOException {
        picsJSON = search;
    }
}
