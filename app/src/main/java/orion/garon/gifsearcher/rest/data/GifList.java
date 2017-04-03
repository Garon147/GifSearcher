package orion.garon.gifsearcher.rest.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VKI on 02.04.2017.
 */

public class GifList implements Parcelable{

//    @SerializedName("data")
//    @Expose
//    private List<Gif> data = new ArrayList<>();
//
//    public List<Gif> getData() {
//        return data;
//    }
//
//    public void setData(List<Gif> data) {
//        this.data = data;
//    }

    @SerializedName("data")
    @Expose
    private List<Gif> data = new ArrayList<>();

    public List<Gif> getData() {
        return data;
    }

    public void setData(List<Gif> data) {
        this.data = data;
    }

    public GifList() {}

    protected GifList(Parcel in) {
        if (in.readByte() == 0x01) {
            data = new ArrayList<Gif>();
            in.readList(data, Gif.class.getClassLoader());
        } else {
            data = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (data == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(data);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<GifList> CREATOR = new Parcelable.Creator<GifList>() {
        @Override
        public GifList createFromParcel(Parcel in) {
            return new GifList(in);
        }

        @Override
        public GifList[] newArray(int size) {
            return new GifList[size];
        }
    };

}
