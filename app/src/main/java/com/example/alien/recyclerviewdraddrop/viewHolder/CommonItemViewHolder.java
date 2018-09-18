package com.example.alien.recyclerviewdraddrop.viewHolder;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.alien.recyclerviewdraddrop.MessageTemplate;
import com.example.alien.recyclerviewdraddrop.R;
import com.example.alien.recyclerviewdraddrop.helper.ItemTouchHelperViewHolder;

public abstract class CommonItemViewHolder extends RecyclerView.ViewHolder implements
        ItemTouchHelperViewHolder {

    protected final TextView mTextView;
    protected final CardView mCardView;
    private ColorStateList mColorStateList;
    private int mPos;
    private IOnItemClickListener mIOnItemClickListener;

    public CommonItemViewHolder(final View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.tvItem);
        mCardView = itemView.findViewById(R.id.cardView);
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIOnItemClickListener != null) {
                    mIOnItemClickListener.onItemClick(mPos);
                }
            }
        });
    }

    @Override
    public void onItemSelected() {
        mColorStateList = mCardView.getCardBackgroundColor();
        mCardView.setCardBackgroundColor(Color.GREEN);
    }

    @Override
    public void onItemClear() {
        mCardView.setCardBackgroundColor(mColorStateList);
    }

    public void onBind(MessageTemplate.CommonTemplateItem item, int pos) {
        if (item != null) {
            mTextView.setText(item.getText());
            mPos = pos;
        }
    }

    static int getTypesCount() {
        return 0;
    }

    static String getTypesItem(int pos) {
        return "";
    }

    public void setIOnItemClickListener(IOnItemClickListener IOnItemClickListener) {
        mIOnItemClickListener = IOnItemClickListener;
    }

    public interface IOnItemClickListener {
        void onItemClick(int pos);
    }
}
