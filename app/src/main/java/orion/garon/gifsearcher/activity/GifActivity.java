package orion.garon.gifsearcher.activity;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import orion.garon.gifsearcher.R;
import orion.garon.gifsearcher.rest.data.Gif;

/**
 * Created by VKI on 01.04.2017.
 */

public class GifActivity extends BaseActivity {

    @Override
    protected Fragment createFragment() {

        Gif gif = getIntent().getParcelableExtra(GifFragment.GIF_PARSED);
        GifFragment gifFragment = GifFragment.newInstance(gif);
        return gifFragment;
    }

    public static class GifFragment extends Fragment{

        public static final String GIF_PARSED = "GIF";

        private Gif gif;

        @BindView(R.id.gif_username)
        TextView gifUsername;

        @BindView(R.id.gif_date)
        TextView gifDate;

        @BindView(R.id.gif_rating)
        TextView gifRating;

        @BindView(R.id.gif_image)
        ImageView gifImage;

        @BindView(R.id.progress_bar_gif)
        ProgressBar gifProgressBar;

        public static GifFragment newInstance(Gif gif) {

            Bundle bundle = new Bundle();
            bundle.putParcelable(GIF_PARSED, gif);
            GifFragment gifFragment = new GifFragment();
            gifFragment.setArguments(bundle);

            return gifFragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_gif, container, false);
            ButterKnife.bind(this, view);

            gif = getArguments().getParcelable(GIF_PARSED);

            if(gif.getUsername().equals("")) {
                gifUsername.setVisibility(View.GONE);
            }
            gifUsername.setText(getString(R.string.user) + " " + gif.getUsername());
            gifDate.setText(getString(R.string.date) + " " + gif.getImportDatetime());
            gifRating.setText(getString(R.string.rating) + " " + gif.getRating());

            Ion.with(getActivity()).load(gif.getImages().getOriginal().getUrl()).progressBar(gifProgressBar).
                    intoImageView(gifImage).
            setCallback(new FutureCallback<ImageView>() {
                @Override
                public void onCompleted(Exception e, ImageView result) {
                    gifProgressBar.setVisibility(View.GONE);
                }
            });

            return view;
        }
    }

}
