package ua.kpi.comsys.io8303.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.example.lab6.R;
import ua.kpi.comsys.io8303.model.PicItem;

import java.util.List;

public class PicsAdapter extends ArrayAdapter<PicItem> {

    private final LayoutInflater layoutInflater;

    public PicsAdapter(Context context, List<PicItem> items) {
        super(context, 0, items);
        layoutInflater = LayoutInflater.from(context);
    }

    public PicsAdapter(Context context) {
        super(context, 0);
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PicItem item = getItem(position);
        View v = layoutInflater.inflate( R.layout.pics_item, parent, false);
        ImageView image = v.findViewById(R.id.galleryImage);
        System.out.println(item.getUrl());

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(getContext());
        circularProgressDrawable.setStyle(CircularProgressDrawable.DEFAULT);

        if (item.getUrl() != null) {
            Glide.with(getContext()).
                    load(item.getUrl()).
                    placeholder(circularProgressDrawable).
                    into(image);
            circularProgressDrawable.start();
        }
        else Glide.with(getContext()).load(item.getUri()).into(image);
        return v;
    }

    @Override public int getViewTypeCount() {
        return 2;
    }

    @Override public int getItemViewType(int position) {
        return position % 2 == 0 ? 1 : 0;
    }

    public void appendItems(List<PicItem> newItems) {
        addAll(newItems);
        notifyDataSetChanged();
    }

    public void setItems(List<PicItem> moreItems) {
        clear();
        appendItems(moreItems);
    }
}
