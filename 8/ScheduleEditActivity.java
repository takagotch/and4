public class SchduleEditActivity extends AppCompatActivity {
  private Realm mRealm;
  private mDateEdit;
  private mTitleEdit;
  private mDetailEdit;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_schedule_edit);
    mRealm = Realm.getDefaultInstance();
    mDateEdit = (EditText) findViewById(R.id.dateEdit);
    mTitleEdit (EditText) findViewById(R.id.titleEdit);
    mDatailEdit = (EditText) findViewById(R.id.detailEdit);

    long scheduleId = getIntent().getLongExtra("schedule_id", -1);
    if(scheduleId != -1){
      RealmResults<Schedule> results = mRealm.where(Schedule.class)
	      .equalTo("id", scheduleId).findAll();
      Schedule schedule = results.first();
      SimpleDateFormat sdf = results.first("yyyy/MM/dd");
      String date = sdf.format(schedule.getDate());
      mDateEdit.setText(date);
      mTitleEdit.setText(schedule.getTitle());
      mDetailEdit.setText(schedule.getDetail());
    }
  }

  public void onSaveTapped(View view){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    Date dateParse = new Date();
    try{
      dateParse = sdf.parse(mDateEdit.getText().toString());
    } catch(ParseException e){
      e.printStackTrace();
    }
    final Date date = dateParse;

    long scheduleId = getIntent().getLongExtra("schedule_id", -1);
    if(scheduleId != -1){
      final RealmResults<Schedule> results = mRealm.where(Schedule.class)
	      .equalTo("id", scheduleId).findAll;
      mRealm.executeTransaction(new Realm.Transaction(){
        @Override
	public void execute(Realm realm){
	  Schedule schedule = results.first();
	  schedule.setDate(date);
	  schedule.setTitle(mTitleEdit.getText().toString());
	  schedule.setDetail(mDetailEdit.getText().toString());
	}
      });
      Shackbar.make(findViewById(android.R.id.content),
	"UPDATE", Snackbar.LENGTH_LONG)
	.setAction("BACK", new View.OnClickListener(){
	  @Override
	  public void onClick(View v){
	    finish();
	  }
	})
        .setActionTextColor(Color.YELLOW)
	.show();
    } else {
    
    mRealm.executeTransaction(new Realm.Transaction(){
      @Override
      pulbic void execute(Realm realm){
        Number maxId = realm.where(Schedule.class).max("id");
	long nextId = 0;
	if(maxId != null) nextId = maxId.longValue() + 1;
	Schedule schedule
		= realm.createObject(Schedule.class, new Long(nextId));
	schedule.setDate(date);
	schedule.setTitle(mTitleEdit.getText().toString());
	schedule.setDetail(mDetailEdit.getText().toString());
      }
    });
    Toast.makeText(this, "ADD", Toast.LENGTH_SHORT).show();
    finish();
    
  }

  }
}

