package com.example.alien.recyclerviewdraddrop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.alien.recyclerviewdraddrop.common.CustomLayoutManager;
import com.example.alien.recyclerviewdraddrop.helper.SimpleItemTouchHelperCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageTemplateFragment extends Fragment {

    private ItemTouchHelper mItemTouchHelper;
    private MessageTemplateListAdapter mAdapter;
    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;
    private View mView;


    public static MessageTemplateFragment newInstance() {
        Bundle args = new Bundle();
        MessageTemplateFragment fragment = new MessageTemplateFragment();
        fragment.setArguments(args);
        fragment.setRetainInstance(true);
        return fragment;
    }

    public MessageTemplateFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mView = inflater.inflate(R.layout.fragment_main, container, false);
            ButterKnife.bind(this, mView);
        }
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new MessageTemplateListAdapter();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new CustomLayoutManager());

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       // inflater.inflate(R.menu.add_menu, menu);
        for(int i = 0; i< mAdapter.getTypesCount();i++){
            menu.add(Menu.NONE, i, Menu.NONE, mAdapter.getTypesItem(i));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mAdapter.addItem(item.getItemId());
    }
}
