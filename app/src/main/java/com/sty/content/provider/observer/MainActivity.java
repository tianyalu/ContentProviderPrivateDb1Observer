package com.sty.content.provider.observer;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRegistObserverDb1();
    }

    private void  myRegistObserverDb1(){
        //1.注册内容观察者
        Uri uri = Uri.parse("content://com.sty.provider/");
        //第二个参数若为false: 则uri为全路径严格匹配(content://com.sty.provider/query)
        //若为true: 则uri前缀路径匹配即可(content://com.sty.provider/)
        getContentResolver().registerContentObserver(uri, true, new MyContentObserver(new Handler()));
    }

    //定义内容观察者
    private class MyContentObserver extends ContentObserver {

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MyContentObserver(Handler handler) {
            super(handler);
        }

        //当我们观察的uri发生改变的时候调用
        @Override
        public void onChange(boolean selfChange) {
            Log.i("Tag", "来自ContentProviderPrivateDb1Observer: 数据库1的内容改变了！！！");
            super.onChange(selfChange);
        }
    }
}
