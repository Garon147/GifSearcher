package orion.garon.gifsearcher.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import orion.garon.gifsearcher.R;
import orion.garon.gifsearcher.adapter.RecyclerViewAdapter;
import orion.garon.gifsearcher.adapter.RecyclerViewItemClickListener;
import orion.garon.gifsearcher.rest.data.GifList;

/**
 * Created by VKI on 04.04.2017.
 */

public class RatingActivity extends AppCompatActivity {

    @BindView(R.id.recyclerViewRating)
    RecyclerView recyclerViewRating;

    RecyclerViewAdapter recyclerViewAdapterRating;

    private Intent intent;
    private GifList ratingList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_activity_layout);
        ButterKnife.bind(this);

        intent = getIntent();
        setTitle(intent.getStringExtra("title"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewRating.setLayoutManager(linearLayoutManager);

        Bundle data = intent.getExtras();
        ratingList = (GifList) data.getParcelable("ratingList");

        RecyclerViewAdapter recyclerViewAdapterRating = new RecyclerViewAdapter(this, ratingList.getData());
        recyclerViewRating.setAdapter(recyclerViewAdapterRating);
        recyclerViewRating.addOnItemTouchListener(new RecyclerViewItemClickListener(this, recyclerViewRating,
                new RecyclerViewItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(getApplicationContext(), GifActivity.class);
                        intent.putExtra(GifActivity.GifFragment.GIF_PARSED, ratingList.getData().get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {}
                }));

    }
}
