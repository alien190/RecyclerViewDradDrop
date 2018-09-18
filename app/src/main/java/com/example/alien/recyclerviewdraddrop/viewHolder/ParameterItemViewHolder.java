package com.example.alien.recyclerviewdraddrop.viewHolder;

import android.graphics.Color;
import android.view.View;

public class ParameterItemViewHolder extends CommonItemViewHolder {

    public ParameterItemViewHolder(View itemView) {
        super(itemView);
        mCardView.setCardBackgroundColor(Color.YELLOW);
    }

    public static int getTypesCount(){
        return 3;
    }
    public static String getTypesItem(int pos) {
        return "Добавить параметр " + pos;
    }
}

