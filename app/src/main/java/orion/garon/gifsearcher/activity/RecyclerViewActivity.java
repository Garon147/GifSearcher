package orion.garon.gifsearcher.activity;

import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.List;

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
