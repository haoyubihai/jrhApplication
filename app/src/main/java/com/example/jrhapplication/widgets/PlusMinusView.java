package com.example.jrhapplication.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.jrhapplication.R;

public class PlusMinusView extends LinearLayout {

    private TextView tvLabel;
    private Button btnMinus, btnPlus;
    private EditText etText;

    private int currentIndex = 1;

    public PlusMinusView(Context context) {
        super(context);

        init(context);
    }

    public PlusMinusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlusMinusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public PlusMinusView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {

        inflate(context, R.layout.plus_minus_view, this);

        tvLabel = findViewById(R.id.tvLabel);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        etText = findViewById(R.id.etText);

        etText.setText(currentIndex);

        btnPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = getCurrentIndex();
                increase();
                refreshEtText(currentIndex);
            }
        });

        btnMinus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = getCurrentIndex();
                deIncrease();
                refreshEtText(currentIndex);
            }
        });


    }

    private void deIncrease() {
        if (currentIndex<=0){
            return;
        }
        currentIndex--;
    }

    private void increase() {
        if (currentIndex>=maxValue){
            return;
        }
        currentIndex++;
    }

    private int maxValue = 1;

    public void initMaxValue(int max) {
        this.maxValue = max;
    }


    public int getCurrentIndex() {

        int indexStr = Integer.parseInt(etText.getText().toString());
        return indexStr;
    }

    private void refreshEtText(int index) {
        etText.setText(index);
    }


}
