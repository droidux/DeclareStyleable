package com.droidux.tutorials.declarestyleable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;

public class HeroUnit extends RelativeLayout {
    private static final int PADDING = 16; //dp
    private static final int PROFILE_SIZE = 64; //dp

    private ImageView mAvatar;
    private TextView mName;

    public HeroUnit(Context context) {
        this(context, null);
    }

    public HeroUnit(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeroUnit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        int padding = dpToPx(getResources(), PADDING);
        setPadding(padding, padding, padding, padding);
        initLayout();
        initAttributes(context, attrs, defStyleAttr);
    }

    private void initLayout() {

        // avatar
        mAvatar = new ImageView(getContext());
        int size = dpToPx(getResources(), PROFILE_SIZE);
        RelativeLayout.LayoutParams lp = new LayoutParams(size, size);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mAvatar.setId(android.R.id.icon);
        mAvatar.setLayoutParams(lp);
        mAvatar.setScaleType(ScaleType.CENTER_CROP);
        addView(mAvatar);

        // name
        mName = new TextView(getContext());
        lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(ALIGN_PARENT_BOTTOM);
        lp.addRule(RIGHT_OF, android.R.id.icon);
        mName.setLayoutParams(lp);
        int padding = dpToPx(getResources(), PADDING);
        mName.setPadding(padding, padding, padding, padding);
        addView(mName);
    }

    private void initAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        // declare styleable here
        final int[] styleable = new int[] {
                android.R.attr.src,
                android.R.attr.textAppearance,
                android.R.attr.text,
                android.R.attr.textSize
        };
        // This is important. The styleable must be sorted. Otherwise you'll get unexpected result.
        Arrays.sort(styleable);

        TypedArray a = context.obtainStyledAttributes(attrs, styleable, defStyleAttr,0);
        for (int attrIndex = 0; attrIndex < styleable.length; attrIndex++) {
            int attribute = styleable[attrIndex];
            switch (attribute) {
                case android.R.attr.src:
                    mAvatar.setImageDrawable(a.getDrawable(attrIndex));
                    break;
                case android.R.attr.textAppearance:
                    mName.setTextAppearance(getContext(), a.getResourceId(attrIndex, 0));
                    break;
                case android.R.attr.text:
                    mName.setText(a.getText(attrIndex));
                    break;
                case android.R.attr.textSize:
                    mName.setTextSize(a.getDimensionPixelSize(attrIndex, 10));
                    break;
            }
        }
        a.recycle();
    }
    private static int dpToPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }
}
