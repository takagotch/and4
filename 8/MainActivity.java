public class MainActivity extends AppCompatActivity{
  private Realm mRealm;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    fab.setOnClickListener(new View.OnClickListener(){
    });
    mRealm = Realm.getDefaultInstance();
  }

  @Override
  public void onDestroy(){
    super.onDestroy();
    mRealm.clone();
  }
}

