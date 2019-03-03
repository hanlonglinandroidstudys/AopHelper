package hanlonglin.com.aop_study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import hanlonglin.com.aop_tools.annotation.Async;
import hanlonglin.com.aop_tools.annotation.Logger;
import hanlonglin.com.aop_tools.annotation.Main;


public class MainActivity extends AppCompatActivity {

    Button btn_start;

    String value="empty";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               readFile();
            }
        });
    }

    private void log(String text) {
        Log.e(getClass().getName(), text);
    }


    @Logger(Log.ERROR)
    public String testLog(String msg,int count){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "finish";
    }



    @Async
    private void readFile() {
        log("读文件开始----thread：" + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("读文件完成----thread：" + Thread.currentThread().getName());
        writeFile();
    }

    @Async
    private void writeFile() {
        log("写文件开始----thread：" + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("写文件完成----thread：" + Thread.currentThread().getName());
        showView();
    }


    @Main
    private void showView() {
        log("更新ui----thread：" + Thread.currentThread().getName());
        testLog("测试消息",108);
    }


}
