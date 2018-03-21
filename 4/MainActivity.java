public class MainActivity extends AppCompatActivity {
 
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void onJankenButtonTapped(View view){
    Intent intent = new Intent(this, ResultActivity.class);
    startActivity(intent);
  }
}

public void onJankenButtonTapped(View view){
  Intent intent = new Intent(this, ResultActivity.class);
  intent.putExtra("MY_HAND", view.getId());
  startActivity(intent);
}


