package com.aiden.yolopersonff;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aiden.yolopersonff.Fragment.Bulletin_Fragment;
import com.aiden.yolopersonff.Fragment.Map_Fragment;
import com.aiden.yolopersonff.Fragment.Movie_Fragment;

public class MainActivity extends AppCompatActivity{

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    int Current_Fragment_Index = 1;
    int Select_Fragment_Index;

    Button Map_Btn, Bulletin_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// fragment를 불러오는 소스코드.
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, new Map_Fragment());
        fragmentTransaction.commit();

        Map_Btn = (Button)findViewById(R.id.Map_btn);
        Bulletin_Btn = (Button)findViewById(R.id.Bulletin_Btn);

        Map_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Select_Fragment_Index = 1;
                Fragment_Change(new Map_Fragment());
            }
        });
        Bulletin_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Select_Fragment_Index = 2;
                Fragment_Change(new Bulletin_Fragment());
            }
        });
    }

    private void Fragment_Change(Fragment changeFragment){
        fragmentTransaction = fragmentManager.beginTransaction();

        if(Current_Fragment_Index > Select_Fragment_Index){
            fragmentTransaction.setCustomAnimations(R.anim.fromleft, R.anim.toright);
        }else if(Current_Fragment_Index < Select_Fragment_Index){
            fragmentTransaction.setCustomAnimations(R.anim.fromright, R.anim.toleft);
        }
        Current_Fragment_Index = Select_Fragment_Index;
        fragmentTransaction.replace(R.id.content, changeFragment);
        fragmentTransaction.commit();
    }
}
