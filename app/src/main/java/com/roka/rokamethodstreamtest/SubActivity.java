package com.roka.rokamethodstreamtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.roka.rokamethodstream.RokaMethodStream;


public class SubActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtn;
    private TextView mDataTv;
    private EditText mEditTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        _InitUi();
        RokaMethodStream.init().attach(mUpdateData, "subViewUpdate");
    }

    private void _InitUi() {
        mBtn = (Button)findViewById(R.id.activity_sub_btn);
        mBtn.setOnClickListener(this);
        mDataTv = (TextView)findViewById(R.id.activity_sub_tv);
        mEditTv = (EditText)findViewById(R.id.activity_sub_et);
    }

    RokaMethodStream.Procedure mUpdateData = () -> {
        mDataTv.setText(mEditTv.getText().toString());

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // TODO : 메서드 해제를 필요없을 경우에는 꼭 해야 함
        RokaMethodStream.init().detach("subViewUpdate");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_sub_btn:
                RokaMethodStream.init().run(null, "subViewUpdate").run(mEditTv.getText().toString(), "mainViewUpdate").run(mEditTv.getText().toString(), "testLog");
                Toast.makeText(this, "1번뷰와 2번뷰가 변경되었습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
