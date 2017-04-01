package orion.garon.gifsearcher.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by VKI on 02.04.2017.
 */

public class RecyclerViewItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener onItemClickListener;

    GestureDetector gestureDetector;

    public RecyclerViewItemClickListener(Context context, final RecyclerView recyclerView,
                                         OnItemClickListener onClickListener) {

        onItemClickListener = onClickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {

                View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (view != null && onItemClickListener != null) {

                    onItemClickListener.onLongItemClick(view, recyclerView.getChildAdapterPosition(view));
                }
            }
        });
    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View view = rv.findChildViewUnder(e.getX(), e.getY());
        if (view != null && onItemClickListener != null && gestureDetector.onTouchEvent(e)) {

            onItemClickListener.onItemClick(view, rv.getChildAdapterPosition(view));
            return true;
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {}

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}

    public interface OnItemClickListener {

        public void onItemClick(View view, int position);
        public void onLongItemClick(View view, int position);
    }
}
