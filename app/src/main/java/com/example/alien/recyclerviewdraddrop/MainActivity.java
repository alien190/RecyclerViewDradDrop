package com.example.alien.recyclerviewdraddrop;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alien.recyclerviewdraddrop.common.SingleFragmentActivity;

public class MainActivity extends SingleFragmentActivity {
    @Override
    protected Fragment getFragment() {
        return RecyclerListFragment.newInstance();
    }
}
