package com.tj.productflavorstest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 测试调用普通类(不同flavor实现不一样) 通过productFlavors
         */
        A a = new A();
        a.test();
    }

    /**
     * 测试调用activity(不同flavor实现不一样) 通过productFlavors
     *
     * SecondActivity可以在不同flavor有自己的实现 但是不能有实现的版本放在main中
     */
    public void startSecondActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
