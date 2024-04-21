package com.example.eshop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // 用户名和密码，这里简单地写在代码里
    private static final String CORRECT_USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "1234";
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 初始化视图
        usernameEditText = findViewById(R.id.username_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        loginButton = findViewById(R.id.login_button);
        // 获取 SharedPreferences 对象
        sharedPreferences = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        // 尝试自动登录
        String savedUsername = sharedPreferences.getString("admin", "");
        String savedPassword = sharedPreferences.getString("password", "");

        if (!savedUsername.isEmpty() && !savedPassword.isEmpty()) {
            // 自动填充用户名和密码
            usernameEditText.setText(savedUsername);
            passwordEditText.setText(savedPassword);
            // 执行登录逻辑
            attemptLogin(savedUsername, savedPassword);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户输入的用户名和密码
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // 检查用户名和密码是否正确
                if (username.equals(CORRECT_USERNAME) && password.equals(CORRECT_PASSWORD)) {
                    attemptLogin(username,password);
                } else {
                    // 登录失败，显示错误信息
                    Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    // 登录逻辑
    private void attemptLogin(String username, String password) {
        // 在登录成功后保存用户名和密码到 SharedPreferences 中
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
        // 登录成功，跳转到商品列表界面
        Intent intent = new Intent(MainActivity.this, GoodsActivity.class);
        startActivity(intent);
        finish(); // 结束当前Activity
    }
}
