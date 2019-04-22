package com.comrench.fastmathsforkids;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void startGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
//        intent.putExtra(nameKey, nameStr);
//            intent.putExtra("VALUE1", 79);
//            intent.putExtra("VALUE2", true);
//            intent.putExtra("VALUE3", 2.5F);
        startActivity(intent);

    }
}
