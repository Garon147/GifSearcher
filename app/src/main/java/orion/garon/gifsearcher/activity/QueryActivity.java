package orion.garon.gifsearcher.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import orion.garon.gifsearcher.R;
import orion.garon.gifsearcher.adapter.RecyclerViewAdapter;
import orion.garon.gifsearcher.adapter.RecyclerViewItemClickListener;
import orion.garon.gifsearcher.rest.data.Gif;
import orion.garon.gifsearcher.rest.data.GifList;

/**
 * Created by VKI on 03.04.2017.
 */

public class QueryActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.recyclerViewQuery)
    RecyclerView recyclerViewQuery;

    @BindView(R.id.checkbox_rating)
    CheckBox checkBoxRating;

    RecyclerViewAdapter recyclerViewAdapterQuery;

    private Intent intent;

    private GifList gifList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);
        ButterKnife.bind(this);

        intent = getIntent();
        setTitle(intent.getStringExtra("query"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewQuery.setLayoutManager(linearLayoutManager);

        Bundle data = intent.getExtras();
        gifList = (GifList) data.getParcelable("gifList");

        recyclerViewAdapterQuery = new RecyclerViewAdapter(this, gifList.getData());
        recyclerViewQuery.setAdapter(recyclerViewAdapterQuery);

        checkBoxRating.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        String rating;
        final List<Gif> gifs = new ArrayList<>();
        Gif element;

        if(checkBoxRating.isChecked()){

            for(int i=0;i<gifList.getData().size();i++) {
                rating = gifList.getData().get(i).getRating();
                element = gifList.getData().get(i);
                if(rating.equals("pg") /*|| rating.equals("g") || rating.equals("y")*/){
                    gifs.add(element);
                }
            }

            recyclerViewAdapterQuery = new RecyclerViewAdapter(this, gifs);

            recyclerViewQuery.setAdapter(recyclerViewAdapterQuery);
            setClickListener(recyclerViewQuery, gifs);

        } else {

            recyclerViewAdapterQuery = new RecyclerViewAdapter(this, gifList.getData());
            recyclerViewQuery.setAdapter(recyclerViewAdapterQuery);
            setClickListener(recyclerViewQuery, gifList.getData());
        }

    }

    public void setClickListener(RecyclerView recyclerView, final List<Gif> sortedList){
            recyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(this, recyclerViewQuery,
                    new RecyclerViewItemClickListener.OnItemClickListener() {

                        @Override
                        public void onItemClick(View view, int position) {

                            Intent intent = new Intent(getApplicationContext(), GifActivity.class);
                            intent.putExtra(GifActivity.GifFragment.GIF_PARSED, sortedList.get(position));
                            startActivity(intent);
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {}
                    }));
    }
}
