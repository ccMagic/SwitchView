package io.github.ccmagic.switchview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ccMagic on 2018/5/4.
 * Copyright ：
 * Version ：
 * Reference ：
 * Description ：状态切换自定义View
 */
public class SwitchView extends RelativeLayout implements View.OnClickListener {

    private static final String TAG = "SwitchView";
    private Context mContext;
    /**
     * 当前选择的状态
     */
    private int mCurrentIndex;
    /**
     * 选择项选中按钮的背景
     */
    private int mIndexBackground;
    /**
     * 未选中项背景
     */
    private int mNormalBackground;
    /**
     * 选择项选中按钮的字体大小
     */
    private float mIndexTextSize;
    /**
     * 未选中项的字体大小
     */
    private float mDefaultTextSize;
    /**
     * 选择项选中按钮的字体颜色
     */
    private int mIndexTextColor;
    /**
     * 未选中项的字体颜色
     */
    private int mDefaultTextColor;

    //    /**
//     * 选中按钮
//     */
//    private TextView mIndexTv;
//
//    /**
//     * 单个状态项的高度
//     */
//    private int mSelectIndexHeight;
//    /**
//     * 单个状态项的宽度
//     */
//    private int mSelectIndexWidth;
    //
    private List<SwitchBean> mAllStatusList;
    /**
     * 上一个选中项
     */
    private TextView mPreTv;
    private OnStatusChangeListener mOnStatusChangeListener;

    public SwitchView(Context context) {
        this(context, null);
    }

    public SwitchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SwitchView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        //获取自定义的样式属性
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.SwitchView, defStyle, 0);
        try {
            mIndexBackground = typedArray.getResourceId(R.styleable.SwitchView_SwitchViewIndexBackground, mIndexBackground);
            mNormalBackground = typedArray.getResourceId(R.styleable.SwitchView_SwitchViewNormalBackground, mIndexBackground);
            //
            mIndexTextSize = typedArray.getDimension(R.styleable.SwitchView_SwitchViewIndexTextSize, mIndexTextSize);
            mDefaultTextSize = typedArray.getDimension(R.styleable.SwitchView_SwitchViewDefaultTextSize, mDefaultTextSize);
            //
            mIndexTextColor = typedArray.getColor(R.styleable.SwitchView_SwitchViewIndexTextColor, mIndexTextColor);
            mDefaultTextColor = typedArray.getColor(R.styleable.SwitchView_SwitchViewDefaultTextColor, mDefaultTextColor);
            //
        } catch (Exception e) {
            Log.e(TAG, "SwitchView: ", e);
        } finally {
            typedArray.recycle();
        }
        mContext = context;
    }

    /**
     * 设置状态切换监听
     */
    public void setOnStatusChangeListener(OnStatusChangeListener onStatusChangeListener) {
        mOnStatusChangeListener = onStatusChangeListener;
    }

    /**
     * 设置当前显示哪一项
     */
    public void setIndex(int index) {
        mCurrentIndex = index;
        if (mAllStatusList != null) {
            TextView textView = mAllStatusList.get(mCurrentIndex).getTextView();
            textView.setBackgroundResource(mIndexBackground);
            textView.setTextColor(mIndexTextColor);
            mPreTv = textView;
            mOnStatusChangeListener.statusChange(textView.getText().toString());
        }
    }

    /**
     * 设置各个选项的数据
     */
    public void setAllStatusList(List<SwitchBean> allStatusList) {
        mAllStatusList = allStatusList;
        initView();
    }

    private void initView() {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.car_easy_switch_view_layout, this, true);
        LinearLayout linearLayout = new LinearLayout(mContext);

        linearLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        //水平
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(2, 2, 2, 2);
        //
        for (SwitchBean bean : mAllStatusList) {
            TextView textView = new TextView(mContext);
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setText(bean.getName());
            textView.setTextColor(mDefaultTextColor);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mIndexTextSize);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(36, 12, 36, 12);
            textView.setOnClickListener(this);
            textView.setBackgroundResource(mNormalBackground);
            bean.setTextView(textView);
            linearLayout.addView(textView);
        }
        //
//        mSelectIndexWidth = linearLayout.getMeasuredWidth() / mIndexCount;
//        mSelectIndexHeight = linearLayout.getMeasuredHeight();
//        Log.i(TAG, "initView mSelectIndexWidth: " + mSelectIndexWidth);
//        Log.i(TAG, "initView mSelectIndexHeight: " + mSelectIndexHeight);
//        mIndexTv = new TextView(mContext);
//        mIndexTv.setBackgroundResource(mIndexBackground);
//        LayoutParams layoutParams = new LayoutParams(mSelectIndexWidth, mSelectIndexHeight);
//        mIndexTv.setLayoutParams(layoutParams);
//        addView(mIndexTv);
        addView(linearLayout);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            TextView textView = (TextView) v;
            if (mPreTv != null) {
                mPreTv.setBackgroundResource(mNormalBackground);
                mPreTv.setTextColor(mDefaultTextColor);
            }
            textView.setBackgroundResource(mIndexBackground);
            textView.setTextColor(mIndexTextColor);
            mOnStatusChangeListener.statusChange(textView.getText().toString());
            mPreTv = textView;
        }
    }

    public interface OnStatusChangeListener {
        void statusChange(String status);
    }

    public static class SwitchBean {
        private String name;
        private TextView textView;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private TextView getTextView() {
            return textView;
        }

        private void setTextView(TextView textView) {
            this.textView = textView;
        }
    }
}
