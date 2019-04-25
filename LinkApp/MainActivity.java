package com.example.administrator.dnf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String PREFS_NAME = "MyPrefs01";
    EditText name;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = (EditText) findViewById(R.id.editID);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == loginBtn) {
            SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            String pref_name = sharedPref.getString("name", "");

            if (name.getText().toString().equals("")) {
                Toast.makeText(this, "이름이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
            } else {
                if (pref_name.equals("")) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    pref_name = name.getText().toString();
                    editor.putString("name", pref_name);
                    editor.commit();
                    Toast.makeText(this, "이름을 등록하였습니다.", Toast.LENGTH_SHORT).show();
                }
            }
            if (name.getText().toString().equals(pref_name)) {
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

