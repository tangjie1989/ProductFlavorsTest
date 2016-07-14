package com.tj.productflavorstest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    /**
     * 测试调用位于src/main中的ThirdActivity
     */
    public void startThirdActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(SecondActivity.this, ThirdActivity.class);
        startActivity(intent);
    }
}
