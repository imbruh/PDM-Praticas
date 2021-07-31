package com.example.pratica2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class MainActivity extends AppCompatActivity {

    private EditText user;
    private EditText senha;
    private Button botaoLogin;
    private Switch admin;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.user = findViewById(R.id.user);
        this.senha = findViewById(R.id.senha);
        this.botaoLogin = findViewById(R.id.login);
        this.admin = findViewById(R.id.admin);
        this.info = findViewById(R.id.info);

        this.botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(admin.isChecked()) {
                    verificaAdmin(v);
                }
                else{
                    verificarUser(v);
                }
            }
        });

    }

    private void verificaAdmin(View view) {
        String user = this.user.getText().toString();
        String senha = this.senha.getText().toString();

        if (user.equals("admin") && senha.equals("admin")) {
            info.setText("Login realizado!");
        }
        else{
            info.setText("User ou senha incorretos.");
        }
    }

    private void verificarUser(View view) {
        String user = this.user.getText().toString();
        String senha = this.senha.getText().toString();

        if (user.equals("bruna") && senha.equals("123")) {
            info.setText("Login realizado!");
        }
        else{
            info.setText("User ou senha incorretos.");
        }
    }


}