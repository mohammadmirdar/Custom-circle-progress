package com.mmdev.customcontrol;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class CustomSpinner extends LinearLayout {

    private float maxValue = 10;
    private float minValue = -10;
    EditText edtValue;
    private float defaultValue = 0;
    public CustomSpinner(Context context) {
        super(context);
        initialize(context);
    }

    public CustomSpinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public CustomSpinner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context);
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
        if (defaultValue > maxValue) {
            defaultValue = maxValue;
            edtValue.setText("" + defaultValue);
        }

    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
        if (defaultValue < minValue) {
            defaultValue = minValue;
            edtValue.setText("" + minValue);
        }
    }

    public void setDefaultValue(float defaultValue) {
        this.defaultValue = defaultValue;
        if (defaultValue > maxValue) {
            this.defaultValue = maxValue;
        }

        if (defaultValue < minValue) {
            this.defaultValue = minValue;
        }
        edtValue.setText("" + this.defaultValue);

    }

    private void initialize(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.custom_spinner,this,true);

        edtValue = view.findViewById(R.id.edtValue);
        Button btnUp = view.findViewById(R.id.btnUp);
        Button btnDown = view.findViewById(R.id.btnDown);

        btnUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultValue = getCurrentValue();
                if (defaultValue < maxValue){
                    float newValue = defaultValue + 1;
                    edtValue.setText("" + newValue);
                }
            }
        });

        btnDown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultValue = getCurrentValue();
                if (defaultValue > minValue) {
                    float newValue = defaultValue - 1;
                    edtValue.setText("" + newValue);
                }
            }
        });
    }

    private float getCurrentValue(){
        return Float.parseFloat(edtValue.getText().toString());
    }
}
