package hanlonglin.com.aop_tools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import hanlonglin.com.aop_tools.annotation.Async;
import hanlonglin.com.aop_tools.annotation.Logger;
import hanlonglin.com.aop_tools.annotation.Main;
import hanlonglin.com.aop_tools.annotation.UserLogin;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn_main);
        btn2 = (Button) findViewById(R.id.btn_async);
        btn3 = (Button) findViewById(R.id.btn_log);
        btn4 = (Button) findViewById(R.id.btn_login);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doMain();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAsync();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dolog("开始吧", 125);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMainPage();
            }
        });
    }


    @Main
    private void doMain() {
        Log.e("TAG", "doMain(),线程：" + Thread.currentThread().getName());
    }

    @Async
    private void doAsync() {
        Log.e("TAG", "doAsync(),线程：" + Thread.currentThread().getName());
    }

    @Logger(Log.ERROR)
    private String dolog(String msg, int count) {
        Log.e("TAG", "dolog()");
        return "结束";
    }

    /**
     * 进入主页面
     * 进入之前需要进行登录验证
     */
    @UserLogin(userName = "hanlonglin",password = "123")
    private void gotoMainPage() {
        Log.e("MainActivity","成功进入主页面！！！");
    }

}
