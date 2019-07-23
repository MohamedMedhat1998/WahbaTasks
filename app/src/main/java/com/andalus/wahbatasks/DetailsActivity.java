package com.andalus.wahbatasks;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    List<Inventory> list;
    ImageView imageView;
    TextView name;
    TextView size;
    private static final String TAG=DetailsActivity.class.getName();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView=(ImageView)findViewById(R.id.image_detail);
        name=(TextView) findViewById(R.id.name_detail);
        size=(TextView)findViewById(R.id.size_detail);
        list=new ArrayList<>();

        Intent intent = getIntent();
        //Inventory x=list.get(1);


//        if(intent.hasExtra("image"))
//        {
//            Integer y= Integer.parseInt(intent.getStringExtra("image"));
//            imageView.setImageDrawable(this.getResources().getDrawable(y));
//        }

        if(intent.hasExtra("name"))
        {
            String y=intent.getStringExtra("name");
            //name.setText(x.getName());
            Log.d(TAG,y);
            name.setText(y);
        }
        else if(intent.hasExtra("size"))
        {
            String y=intent.getStringExtra("size");
            //name.setText(x.getSize());
            name.setText(y);
        }


    }
}
