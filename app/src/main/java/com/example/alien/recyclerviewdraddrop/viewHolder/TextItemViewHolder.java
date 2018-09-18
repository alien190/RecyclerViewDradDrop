package com.example.alien.recyclerviewdraddrop.viewHolder;

import android.view.View;

public class TextItemViewHolder extends CommonItemViewHolder {
    public TextItemViewHolder(View itemView) {
        super(itemView);
    }
    public static int getTypesCount(){
        return 1;
    }
    public static String getTypesItem(int pos) {
        return "Добавить текстовое поле";
    }
}