public class MainActivity extends AppCompatActivity{
  
  private Realm mRealm;
  private ListView mListView;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    fab.setOnClickListener(new View.OnClickListener(){
    });
    mRealm = Realm.getDefaultInstance();

    mListView = (ListView) findViewById(R.id.listView);
    RealmResults<Schedule> schedules
	    = mRealm.where(Schedule.class).findAll();
    ScheduleAdapter adapter = new ScheduleAdapter(schedules);
    mListView.setAdapter(adapter);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view){
        startActivity(new Intent(MainActivity.this,
		ScheduleEditActivity.class));
      }
    });
  }

  @Override
  public void onDestroy(){
    super.onDestroy();
    mRealm.clone();
  }
}

