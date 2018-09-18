package com.example.alien.recyclerviewdraddrop;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alien.recyclerviewdraddrop.helper.ItemTouchHelperAdapter;
import com.example.alien.recyclerviewdraddrop.viewHolder.CommonItemViewHolder;
import com.example.alien.recyclerviewdraddrop.viewHolder.ParameterItemViewHolder;
import com.example.alien.recyclerviewdraddrop.viewHolder.TextItemViewHolder;

import java.util.ArrayList;

public class MessageTemplateListAdapter extends RecyclerView.Adapter<CommonItemViewHolder>
        implements ItemTouchHelperAdapter, CommonItemViewHolder.IOnItemClickListener {

    private final MessageTemplate mMessageTemplate = new MessageTemplate();
    public static final int ITEM_TYPE_TEXT = 1;
    public static final int ITEM_TYPE_PARAMETER = 2;
    public static final int NEW_ITEM = -1;
    private IOnEditItemListener mIOnEditItemListener;

    public MessageTemplateListAdapter() {
        mMessageTemplate.addTextItem("text1");
        mMessageTemplate.addParameterItem(1);
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
        commonItemViewHolder.setIOnItemClickListener(this);
        return commonItemViewHolder;
    }

    @Override
    public void onBindViewHolder(CommonItemViewHolder holder, int position) {
        holder.onBind(mMessageTemplate.getItem(position), position);
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

    public int getTypesCount() {
        return TextItemViewHolder.getTypesCount() + ParameterItemViewHolder.getTypesCount();
    }

    public String getTypesItem(int pos) {
        if (pos < TextItemViewHolder.getTypesCount()) {
            return TextItemViewHolder.getTypesItem(pos);
        } else {
            return ParameterItemViewHolder.getTypesItem(pos - TextItemViewHolder.getTypesCount());
        }
    }

    public CharSequence[] getParametersTypes(){
        ArrayList<String> list = new ArrayList<>();
        for(int i=0; i<ParameterItemViewHolder.getTypesCount(); i++) {
            list.add(ParameterItemViewHolder.getTypesItem(i));
        }
        return list.toArray(new CharSequence[0]);
    }
    public boolean addItem(int type) {
        if (mIOnEditItemListener != null) {
            if (type == ITEM_TYPE_TEXT) {
                mIOnEditItemListener.onEditTextItem(NEW_ITEM);
            } else {
                mIOnEditItemListener.onEditParameterItem(NEW_ITEM);
            }
        }
        return true;
    }

    public void addTextItem(String text) {
        mMessageTemplate.addTextItem(text);
        notifyItemInserted(mMessageTemplate.getItemCount() - 1);
    }

    public void addParameterItem(int type) {
        mMessageTemplate.addParameterItem(type);
        notifyItemInserted(mMessageTemplate.getItemCount() - 1);
    }

    public String getItemText(int pos) {
        MessageTemplate.CommonTemplateItem commonTemplateItem = mMessageTemplate.getItem(pos);
        if (commonTemplateItem != null) {
            return commonTemplateItem.getText();
        } else {
            return "";
        }
    }

    public void setIOnEditItemListener(IOnEditItemListener IOnEditItemListener) {
        mIOnEditItemListener = IOnEditItemListener;
    }

    public void updateTextItem(int pos, String text) {
        MessageTemplate.CommonTemplateItem commonTemplateItem = mMessageTemplate.getItem(pos);
        if (commonTemplateItem != null) {
            commonTemplateItem.setText(text);
            notifyItemChanged(pos);
        }
    }
    public void updateParameterItem(int pos, int type) {
        MessageTemplate.CommonTemplateItem commonTemplateItem = mMessageTemplate.getItem(pos);
        if (commonTemplateItem != null && commonTemplateItem instanceof MessageTemplate.ParameterTemplateItem) {
            ((MessageTemplate.ParameterTemplateItem)commonTemplateItem).setType(type);
            notifyItemChanged(pos);
        }
    }

    @Override
    public void onItemClick(int pos) {
        if (mIOnEditItemListener != null) {
            if (getItemViewType(pos) == ITEM_TYPE_PARAMETER) {
                mIOnEditItemListener.onEditParameterItem(pos);
            } else {
                mIOnEditItemListener.onEditTextItem(pos);
            }
        }
    }

    interface IOnEditItemListener {
        void onEditTextItem(int pos);

        void onEditParameterItem(int pos);
    }
}
