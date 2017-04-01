package orion.garon.gifsearcher.rest.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VKI on 02.04.2017.
 */

public class GifList {

    @SerializedName("data")
    @Expose
    private List<Gif> data = new ArrayList<>();

    public List<Gif> getData() {
        return data;
    }

    public void setData(List<Gif> data) {
        this.data = data;
    }

}
