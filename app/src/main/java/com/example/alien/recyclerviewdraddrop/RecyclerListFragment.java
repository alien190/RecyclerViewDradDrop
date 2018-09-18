package com.example.alien.recyclerviewdraddrop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alien.recyclerviewdraddrop.helper.SimpleItemTouchHelperCallback;


/**
 * @author Paul Burke (ipaulpro)
 */
public class RecyclerListFragment extends Fragment {

    private ItemTouchHelper mItemTouchHelper;

    public static RecyclerListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RecyclerListFragment fragment = new RecyclerListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public RecyclerListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerListAdapter adapter = new RecyclerListAdapter();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

       // staggeredGridLayoutManager.generateLayoutParams()

        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

}
