package orion.garon.gifsearcher.adapter;

import android.content.Context;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import orion.garon.gifsearcher.R;
import orion.garon.gifsearcher.activity.OnBackPressedListener;
import orion.garon.gifsearcher.rest.data.Gif;

/**
 * Created by VKI on 02.04.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PersonViewHolder>  {

    private Context context;
    private List<Gif> gifs;

    public RecyclerViewAdapter (Context context, List<Gif> gifs) {

        this.context = context;
        this.gifs = gifs;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gif_list_item, parent, false);
        PersonViewHolder personViewHolder = new PersonViewHolder(view);
        return personViewHolder;
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder holder, int position) {

        Gif gif = gifs.get(position);

        Ion.with(context).load(gif.getImages().getOriginal().getUrl()).progressBar(holder.gifProgressBarList).
                intoImageView(holder.gifImageList).setCallback(new FutureCallback<ImageView>() {
            @Override
            public void onCompleted(Exception e, ImageView result) {
                holder.gifProgressBarList.setVisibility(View.GONE);
            }
        });
        holder.gifUsernameList.setText(gif.getUsername());
        holder.gifDateTimeList.setText(gif.getImportDatetime());
    }

    @Override
    public int getItemCount() {
        return gifs.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.gif_image_list)
        ImageView gifImageList;

        @BindView(R.id.progress_bar_gif_list)
        ProgressBar gifProgressBarList;

        @BindView(R.id.date_and_time_list)
        TextView gifDateTimeList;

        @BindView(R.id.username_list)
        TextView gifUsernameList;

        public PersonViewHolder(View item) {

            super(item);
            ButterKnife.bind(this, item);
        }

    }
}
