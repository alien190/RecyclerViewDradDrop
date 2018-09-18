package com.example.alien.recyclerviewdraddrop;

import android.support.v4.app.Fragment;

import com.example.alien.recyclerviewdraddrop.common.SingleFragmentActivity;

public class MainActivity extends SingleFragmentActivity {
    @Override
    protected Fragment getFragment() {
        return MessageTemplateFragment.newInstance();
    }
}
