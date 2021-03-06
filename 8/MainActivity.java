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

    mListView.setOnItemClickListener(
	new AdapterView.OnItemClickListener(){
	  @Override
	  public void onItemClick(AdapterView<?> parent, View view,
		int position, long id){
	    Schedule schedule =
		    (Schedule) parent.getItemAtPosition(position);
	    startActivity(new Intent(MainActivity.this,
			   ScheduleEditActivity.class)
			   .putExtra("schedule_id", schedule.getId()));
	  }
    });


  }

  @Override
  public void onDestroy(){
    super.onDestroy();
    mRealm.clone();
  }
}

