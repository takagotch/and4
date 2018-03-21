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

