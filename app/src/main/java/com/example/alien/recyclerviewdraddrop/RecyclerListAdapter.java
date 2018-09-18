package com.example.alien.recyclerviewdraddrop;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.example.alien.recyclerviewdraddrop.helper.ItemTouchHelperAdapter;
import com.example.alien.recyclerviewdraddrop.helper.ItemTouchHelperViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Paul Burke (ipaulpro)
 */
public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ItemViewHolder>
        implements ItemTouchHelperAdapter {

    private static final String[] STRINGS = new String[]{
            "One One One One One One One One One One One One One One One One One One One One One One One One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"
    };

    private final List<String> mItems = new ArrayList<>();

    public RecyclerListAdapter() {
        mItems.addAll(Arrays.asList(STRINGS));
    }

    @Override
    public ItemViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);

//        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                final ViewGroup.LayoutParams lp = view.getLayoutParams();
//                if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
//                    StaggeredGridLayoutManager.LayoutParams sglp =
//                            (StaggeredGridLayoutManager.LayoutParams) lp;
//                    sglp.setFullSpan(false);
//                    sglp.
//                    sglp.width = view.getWidth();
//                    sglp.height = view.getHeight();
////                            break;
////                        case TYPE_HALF:
////                            sglp.setFullSpan(false);
////                            sglp.width = itemView.getWidth() / 2;
////                            break;
////                        case TYPE_QUARTER:
////                            sglp.setFullSpan(false);
////                            sglp.width = itemView.getWidth() / 2;
////                            sglp.height = itemView.getHeight() / 2;
////                            break;
////                    }
//                    view.setLayoutParams(sglp);
//                    final StaggeredGridLayoutManager lm =
//                            (StaggeredGridLayoutManager) ((RecyclerView) parent).getLayoutManager();
//                    lm.invalidateSpanAssignments();
//                }
//                view.getViewTreeObserver().removeOnPreDrawListener(this);
//                return true;
//            }
//        });

        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.textView.setText(mItems.get(position));
    }

    @Override
    public void onItemDismiss(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        String prev = mItems.remove(fromPosition);
        mItems.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {

        public final TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;

        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
