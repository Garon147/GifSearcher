package orion.garon.gifsearcher.fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import orion.garon.gifsearcher.R;
import orion.garon.gifsearcher.activity.GifActivity;
import orion.garon.gifsearcher.adapter.RecyclerViewAdapter;
import orion.garon.gifsearcher.adapter.RecyclerViewItemClickListener;
import orion.garon.gifsearcher.rest.GifMethods;
import orion.garon.gifsearcher.rest.data.Gif;
import orion.garon.gifsearcher.rest.data.GifList;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by VKI on 02.04.2017.
 */

public class RecyclerViewFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    private GifList gifList = new GifList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.main_screen, container, false);
        ButterKnife.bind(this, view);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(getActivity(), recyclerView,
                new RecyclerViewItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(getActivity(), GifActivity.class);
                        intent.putExtra(GifActivity.GifFragment.GIF_PARSED, gifList.getData().get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {}
                }));

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GifMethods gifMethods = GifMethods.retrofit.create(GifMethods.class);
        retrofit2.Call<GifList> gifListCall = gifMethods.gifs();

        gifListCall.enqueue(new Callback<GifList>() {
            @Override
            public void onResponse(retrofit2.Call<GifList> call, Response<GifList> response) {

                gifList.setData(response.body().getData());
                recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), gifList.getData());
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(retrofit2.Call<GifList> call, Throwable t) {

                Toast.makeText(getActivity(), R.string.get_error_message, Toast.LENGTH_LONG).show();
            }
        });
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.search_bar);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                newText.toLowerCase();

                final List<Gif> list = new ArrayList<>();

                for(int i = 0;i < gifList.getData().size();i++) {

                    final String text = gifList.getData().get(i).getUsername().toLowerCase();
                    if(text.contains(newText)) {

                        list.add(gifList.getData().get(i));
                    }
                }

                recyclerViewAdapter = new RecyclerViewAdapter(getContext(), list);
                recyclerView.setAdapter(recyclerViewAdapter);
                return true;
            }
        });
    }
}
