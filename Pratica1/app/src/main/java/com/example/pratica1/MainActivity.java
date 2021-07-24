package com.example.pratica1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView info1;
    private TextView info2;
    private Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.info1 = findViewById(R.id.info1);
        this.info1.setText(android.os.Build.MODEL);

        this.info2 = findViewById(R.id.info2);

        this.botao = findViewById(R.id.botao);
        this.botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verInfos(v);
            }
        });
    }

    public void verInfos(View view) {
       String texto = "Versão do SO: " + System.getProperty("os.version")
                    + "\nHardware: " + android.os.Build.HARDWARE
                    + "\nDevice: " + android.os.Build.DEVICE
                    + "\nProduct: " + android.os.Build.PRODUCT;
       this.info2.setText(texto);
    }
}