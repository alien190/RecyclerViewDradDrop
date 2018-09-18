package com.example.alien.recyclerviewdraddrop;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alien.recyclerviewdraddrop.helper.ItemTouchHelperAdapter;
import com.example.alien.recyclerviewdraddrop.helper.ItemTouchHelperViewHolder;

public class MessageTemplateListAdapter extends RecyclerView.Adapter<MessageTemplateListAdapter.CommonItemViewHolder>
        implements ItemTouchHelperAdapter {

    private final MessageTemplate mMessageTemplate = new MessageTemplate();
    private static final int ITEM_TYPE_TEXT = 1;
    private static final int ITEM_TYPE_PARAMETER = 2;

    public MessageTemplateListAdapter() {
        mMessageTemplate.addTextItem("text1");
        mMessageTemplate.addParameterItem("param1");
        mMessageTemplate.addTextItem("text2");
        mMessageTemplate.addTextItem("text3 text3 text3 text3 text3 text3 text3 text3 text3 text3 text3 text3 text3 text3 text3 text3 text3 text3 text3 text3 text3 text3 text3 text3 text3");
    }

    @Override
    public CommonItemViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        CommonItemViewHolder commonItemViewHolder;
        if (viewType == ITEM_TYPE_PARAMETER) {
            commonItemViewHolder = new ParameterItemViewHolder(view);
        } else {
            commonItemViewHolder = new TextItemViewHolder(view);
        }
        return commonItemViewHolder;
    }

    @Override
    public void onBindViewHolder(CommonItemViewHolder holder, int position) {
        holder.onBind(mMessageTemplate.getItem(position));
    }

    @Override
    public void onItemDismiss(int position) {
        mMessageTemplate.removeItem(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (mMessageTemplate.moveItem(fromPosition, toPosition)) {
            notifyItemMoved(fromPosition, toPosition);
        }
    }

    @Override
    public int getItemCount() {
        return mMessageTemplate.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        MessageTemplate.CommonTemplateItem commonTemplateItem = mMessageTemplate.getItem(position);
        if (commonTemplateItem != null && commonTemplateItem instanceof MessageTemplate.ParameterTemplateItem) {
            return ITEM_TYPE_PARAMETER;
        } else {
            return ITEM_TYPE_TEXT;
        }
    }

    public int getTypesCount(){
        return 3;
    }
    public String getTypesItem(int pos) {
        return String.valueOf(pos);
    }
    public boolean addItem(int type){
        return true;
    }

    public abstract class CommonItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {

        protected final TextView mTextView;
        protected final CardView mCardView;
        private ColorStateList mColorStateList;

        public CommonItemViewHolder(final View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tvItem);
            mCardView = itemView.findViewById(R.id.cardView);
//            mCardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(itemView.getContext(), "select", Toast.LENGTH_SHORT).show();
//                }
//            });
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

        public void onBind(MessageTemplate.CommonTemplateItem item) {
            if (item != null) {
                mTextView.setText(item.getText());
            }
        }
    }

    public class TextItemViewHolder extends CommonItemViewHolder {
        public TextItemViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ParameterItemViewHolder extends CommonItemViewHolder {
        public ParameterItemViewHolder(View itemView) {
            super(itemView);
            mCardView.setCardBackgroundColor(Color.YELLOW);
        }
    }
}
