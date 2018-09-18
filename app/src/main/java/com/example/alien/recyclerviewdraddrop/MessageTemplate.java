package com.example.alien.recyclerviewdraddrop;

import java.util.ArrayList;
import java.util.List;

public class MessageTemplate {
    private List<Object> mItems = new ArrayList<>();

    public MessageTemplate() {
    }

    public void addTextItem(String text) {
        mItems.add(new TextTemplateItem(text));
    }

    public void addParameterItem(String text) {
        mItems.add(new ParameterTemplateItem(text));
    }

    public CommonTemplateItem getItem(int pos) {
        if (pos >= 0 && pos < mItems.size()) {
            return (CommonTemplateItem) mItems.get(pos);
        } else return null;
    }

    public Object removeItem(int pos) {
        Object ret = null;
        if (pos >= 0 && pos < mItems.size()) {
            ret = mItems.get(pos);
            mItems.remove(pos);
        }
        return ret;
    }

    public boolean moveItem(int fromPosition, int toPosition) {
        boolean ret = false;
        Object prev = removeItem(fromPosition);
        if (prev != null) {
            //mItems.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
            mItems.add(toPosition, prev);
            ret = true;
        }
        return ret;
    }

    public int getItemCount() {
        return mItems.size();
    }

    public class CommonTemplateItem {
        private String mText;

        public CommonTemplateItem(String text) {
            mText = text;
        }

        public String getText() {
            return mText;
        }

        public void setText(String text) {
            mText = text;
        }
    }

    public class TextTemplateItem extends CommonTemplateItem {
        public TextTemplateItem(String text) {
            super(text);
        }
    }

    public class ParameterTemplateItem extends CommonTemplateItem {
        public ParameterTemplateItem(String text) {
            super(text);
        }
    }
}
