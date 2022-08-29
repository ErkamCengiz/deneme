package com.example.dynamicbroadcastreceiver;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int dakika=1;

    TextView text;

    MyFirstReceiver myFirstReceiver;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFirstReceiver=new MyFirstReceiver(); //OnCreate de Receiverımızı tanımlamalıyız ki dynamic olarak kullanabilelim
        text= (TextView) findViewById(R.id.textView);

    }


    protected void onResume() {
        super.onResume();

        //Dynamic olduğu için Resumeda çalıştırmalıyız çünkü aktif uygulama çalışırken yapcağımız işlemler için

        IntentFilter intentFilter=new IntentFilter(); //Filter objesi oluştruyoz
        //intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);  //Hangi durumda işlem yapacağını belirliyoruz

        registerReceiver(myFirstReceiver, intentFilter);
        // Receiverı oluşturur
    }
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myFirstReceiver);
        // Çalışması sonlanması için
    }


    public void registerReceiver(View view) { //Başlatmak istediğimi Receiver

        IntentFilter filter=new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK); //Her dakikada işlem yapması için

        registerReceiver(myTimeTickReceiver, filter);

    }

    public void unRegisterReceiver(View view) { //Durdurmakiçin

        unregisterReceiver(myTimeTickReceiver);

    }
    private BroadcastReceiver myTimeTickReceiver=new BroadcastReceiver() {  //İstediğimiz zaman oluşacak bir broadCastReceviver
        @Override
        public void onReceive(Context context, Intent intent) { //


            Log.i(TAG, "myTimeTickReceiver receiver!!");
            Toast.makeText(context, "myTimeTickReceiver Receiver!!!", Toast.LENGTH_LONG).show();

            int dakikaDegeri=dakika;

            text.setText(dakikaDegeri+ " dakika geçti");
            dakika++;

        }
    };

}