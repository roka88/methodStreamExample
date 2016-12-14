package com.roka.rokamethodstreamtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.roka.rokamethodstream.RokaMethodStream;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button mBtn;
    private TextView mDataTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _InitUi();
        RokaMethodStream.init().attach(mUpdateData, "mainViewUpdate").attach(mTestLog, "testLog");
    }

    private void _InitUi() {
        mBtn = (Button)findViewById(R.id.activity_main_btn);
        mBtn.setOnClickListener(this);
        mDataTv = (TextView)findViewById(R.id.activity_main_tv);
    }

    RokaMethodStream.Func mUpdateData = (Object obj) -> {
        if (obj instanceof String) {
            mDataTv.setText((String)obj);
        }
    };

    RokaMethodStream.Func mTestLog = (Object obj) -> {
        Toast.makeText(this, (String)obj, Toast.LENGTH_LONG).show();
        Log.e("테스트", "테스트");
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_btn:
                startActivity(new Intent(this, SubActivity.class));
                break;
        }
    }


}
