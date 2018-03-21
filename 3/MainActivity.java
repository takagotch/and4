package com.example.username.helloandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle saveInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button tapHere = (Button)findViewById(R.id.tapHere);
    tapHere.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view){
        TextView textView = (TextView)findViewById(R.id.textView);
	textView.setText("BUTTON TAPPED");
      }
    });

    Button tapHere = (Button)findViewById(R.id.tapHere);
    MyOnClickListener myOnClickListener = new MyOnClickListener();
    tapHere.setOnClickListener(myOnClickListener);
  }
}

public class MyOnClickListener implements View.OnClickListener{
  @Override
  public void onClick(View v){
    TextView textView = (TextView)findViewById(R.id.textView);
    textView.setText(R.string.hello_world);
  }
}



public void onClickButton(View view){
  TextView textView = (TextView)findViewById(R.id.textView);
  textView.setText("BUTTON TAPPED");
}

