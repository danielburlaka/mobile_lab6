package ua.kpi.comsys.io8303.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;

import org.jetbrains.annotations.NotNull;

public class PicItem implements AsymmetricItem {
    private int columnSpan;
    private int rowSpan;
    private int position;
    private String imageUrl;
    private Bitmap image;
    private Uri uri;
    public PicItem() {
        this(1, 1, 0,"", null, null);
    }

    public PicItem(int columnSpan, int rowSpan, int position, String imageUrl, Bitmap image, Uri uri) {
        this.columnSpan = columnSpan;
        this.rowSpan = rowSpan;
        this.position = position;
        this.imageUrl=imageUrl;
        this.image=image;
        this.uri=uri;
    }

    public PicItem(Parcel in) {
        readFromParcel(in);
    }

    @Override public int getColumnSpan() {
        return columnSpan;
    }

    @Override public int getRowSpan() {
        return rowSpan;
    }

    public Bitmap getImage(){
        return image;
    }

    public Uri getUri() {
        return uri;
    }

    public String getUrl() {
        return imageUrl;
    }

    public int getPosition() {
        return position;
    }

    @NotNull
    @Override public String toString() {
        return String.format("%s: %sx%s", position, rowSpan, columnSpan);
    }

    @Override public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in) {
        columnSpan = in.readInt();
        rowSpan = in.readInt();
        position = in.readInt();
    }

    @Override public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(columnSpan);
        dest.writeInt(rowSpan);
        dest.writeInt(position);
    }

    /* Parcelable interface implementation */
    public static final Parcelable.Creator<PicItem> CREATOR = new Parcelable.Creator<PicItem>() {

        @Override public PicItem createFromParcel(@NonNull Parcel in) {
            return new PicItem(in);
        }

        @Override @NonNull
        public PicItem[] newArray(int size) {
            return new PicItem[size];
        }
    };
}
