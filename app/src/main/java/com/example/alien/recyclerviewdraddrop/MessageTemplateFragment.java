package com.example.alien.recyclerviewdraddrop;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.alien.recyclerviewdraddrop.common.CustomLayoutManager;
import com.example.alien.recyclerviewdraddrop.helper.SimpleItemTouchHelperCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageTemplateFragment extends Fragment implements MessageTemplateListAdapter.IOnEditItemListener {

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
        if (savedInstanceState == null) {
            mAdapter = new MessageTemplateListAdapter();
            mAdapter.setIOnEditItemListener(this);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new CustomLayoutManager());

            ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
            mItemTouchHelper = new ItemTouchHelper(callback);
            mItemTouchHelper.attachToRecyclerView(mRecyclerView);

            setHasOptionsMenu(true);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
         inflater.inflate(R.menu.add_menu, menu);
//        for (int i = 0; i < mAdapter.getTypesCount(); i++) {
//            menu.add(Menu.NONE, i, Menu.NONE, mAdapter.getTypesItem(i));
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.itmText) {
            return mAdapter.addItem(MessageTemplateListAdapter.ITEM_TYPE_TEXT);
        }
        return mAdapter.addItem(MessageTemplateListAdapter.ITEM_TYPE_PARAMETER);
    }

    @Override
    public void onEditTextItem(final int pos) {
        String text = "";
        if (pos != MessageTemplateListAdapter.NEW_ITEM) {
            text = mAdapter.getItemText(pos);
        }

        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Введите текст")
                .setView(R.layout.dialog_text)
                .setPositiveButton("Сохранить", null)
                .setNegativeButton("Отмена", null)
                .create();
        alertDialog.show();

        final EditText editText = alertDialog.findViewById(R.id.etText);
        editText.setText(text);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String text = editText.getText().toString();
                if (pos == MessageTemplateListAdapter.NEW_ITEM) {
                    mAdapter.addTextItem(text);
                } else {
                    mAdapter.updateTextItem(pos, text);
                }
            }
        });
    }


    @Override
    public void onEditParameterItem(final int pos) {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Выберите тип параметра")
                .setNegativeButton("Отмена", null)
                .setItems(mAdapter.getParametersTypes(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int type) {
                        if (pos == MessageTemplateListAdapter.NEW_ITEM) {
                            mAdapter.addParameterItem(type);
                        } else {
                            mAdapter.updateParameterItem(pos, type);
                        }
                        dialogInterface.dismiss();
                    }
                })
                .create();
        alertDialog.show();
    }
}
