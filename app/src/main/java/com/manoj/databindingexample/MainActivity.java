package com.manoj.databindingexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.manoj.databindingexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private User user;
    private MyClickHandlers clickHandlers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        user = new User();
        user.setName("manoj");
        user.setEmail("manoj@gmail.com");

        binding.content.setUser(user);

        clickHandlers = new MyClickHandlers(this);
        binding.setUser(user);
        binding.setHandlers(clickHandlers);
    }

    public class MyClickHandlers {
        Context context;

        public MyClickHandlers(Context context) {
            this.context=context;
        }

        public void onFabClicked(View view,User user) {
            Toast.makeText(context, user.email, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,RecyclerActivity.class);
            startActivity(intent);
        }
    }
}