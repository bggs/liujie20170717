package com.baway.liujie.customer;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.baway.liujie.R;


/**
 * 类的作用:
 * author: 刘婕
 * date:2017/7/17
 */

public class MySelf extends LinearLayout implements View.OnClickListener, TextWatcher {
    private static final String TAG = "MySelf";
    private int amount = 1; //购买数量
    private int goods_storage = 1; //商品库存
    private EditText etAmount;
    private Button btnDecrease;
    private Button btnIncrease;
    private OnAmountChangeListener mListener;
    public MySelf(Context context) {
        this(context,null);
    }

    public MySelf(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MySelf(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_amount, this);
        etAmount = (EditText) findViewById(R.id.etAmount);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.MySelf);
        int btnWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MySelf_btnWidth,80);
        int tvWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MySelf_tvWidth, 30);
        int tvTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MySelf_tvTextSize, 0);
        int btnTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MySelf_btnTextSize, 0);
        obtainStyledAttributes.recycle();
        LayoutParams btnParams = new LayoutParams(btnWidth, LayoutParams.MATCH_PARENT);
        btnDecrease.setLayoutParams(btnParams);
        btnIncrease.setLayoutParams(btnParams);
        if (btnTextSize != 0) {
            btnDecrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
            btnIncrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
        }
        LayoutParams textParams = new LayoutParams(tvWidth, LayoutParams.MATCH_PARENT);
        etAmount.setLayoutParams(textParams);
        if (tvTextSize != 0) {
            etAmount.setTextSize(tvTextSize);
        }
    }
    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public void setGoods_storage(int goods_storage) {
        this.goods_storage = goods_storage;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnDecrease) {
            if (amount > 1) {
                amount--;
                etAmount.setText(amount + "");
            }
        } else if (i == R.id.btnIncrease) {
            if (amount < goods_storage) {
                amount++;
                etAmount.setText(amount + "");
            }
        }
        etAmount.clearFocus();

        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty())
            return;
        amount = Integer.valueOf(s.toString());
        if (amount > goods_storage) {
            etAmount.setText(goods_storage + "");
            return;
        }

        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }

    }
    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }
}
