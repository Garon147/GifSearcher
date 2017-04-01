package orion.garon.gifsearcher.activity;

import android.app.Fragment;

import orion.garon.gifsearcher.fragment.RecyclerViewFragment;

/**
 * Created by VKI on 02.04.2017.
 */

public class RecyclerViewActivity extends BaseActivity {

    @Override
    protected Fragment createFragment() {
        return new RecyclerViewFragment();
    }
}
