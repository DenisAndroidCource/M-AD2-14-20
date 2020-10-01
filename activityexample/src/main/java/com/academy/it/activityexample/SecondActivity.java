package com.academy.it.activityexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private int result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final Intent intent = getIntent();
        if (intent != null) {
            int a = intent.getIntExtra("KEY_NUMBER1", 0);
            int b = intent.getIntExtra("KEY_NUMBER2", 0);
            result = a * b;
        }

        findViewById(R.id.finishActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("RESULT", result);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
