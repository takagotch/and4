public class MainActivity extends AppCompatActivity
	implements DiaryListFragment.OnFragmentInteracttionListener{

  private Realm mRealm;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    mRealm = Realm.getDefaultInstance();

    createTestData();
    showDiaryList();
  }

  @Override
  protected void onDestroy(){
    super.onDestroy();
    mRealm.close();
  }

  private void createTestData(){
    mRealm.executeTransaction(new Realm.Transaction(){
      @Override
      public void execute(Realm realm){
        Number maxId = mRealm.where(Diary.class).max("id");
	long nextId = 0;
	if (maxId != null) nextId = maxId.longValue() + 1;
	Diary diary = realm.createObject(Diary.class, new Long(nextId));
	diary.title="TEST TITLE";
	diary.bodyText = "TEST";
	diary.date="Feb 22";
      }
    });
  }

  private void showDiaryList(){
    FragmentManager manager = getSupportFragmentManager();
    Fragment fragment = manager.findFragmetnByTag("DiaryListFragment");
    if(fragment == null){
      fragment = new DiaryListFragment();
      FragmentTransaction transaction = manager.beginTransaction();
      transaction.add(R.id.content, fragment, "DiaryListFragment");
      transaction.commit();
    }
  }
  
  @Override
  public void onAddDiarySelected(){
    //...
  }

}

