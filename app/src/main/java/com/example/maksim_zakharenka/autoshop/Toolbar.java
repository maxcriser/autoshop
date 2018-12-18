package com.example.maksim_zakharenka.autoshop;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Toolbar extends RelativeLayout {

    private TextView mTitle;
    private View mBackView;

    public Toolbar(final Context context) {
        super(context);
        init();
    }

    public Toolbar(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Toolbar(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private boolean containsFlag(final int flagSet, final int flag) {
        final int result = flagSet | flag;

        return result == flagSet;
    }

    private void inflate() {
        inflate(getContext(), R.layout.view_toolbar, this);

        mTitle = findViewById(R.id.toolbar_view_title);
        mBackView = findViewById(R.id.toolbar_view_back);
    }

    private void init() {
        inflate();
    }

    public void setTitle(final String pTitle) {
        mTitle.setText(pTitle);
    }

    public void showBackView() {
        mBackView.setVisibility(View.VISIBLE);
        mBackView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                ((Activity) v.getContext()).onBackPressed();
            }
        });
    }
}