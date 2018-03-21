public class MainActivity extends AppCompatActivity {

  private static final String NECK = "NECK";
  private static final String SLEEVE = "SLEEVE";

  @Override
  protected void onCreate(savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    SharedPreferences pref =
	    PreferenceManager.getDefaultSharedPreferences(this);

    String neck = pref.getString(NECK, "");
    String sleeve = pref.getString(SLEEVE, "");
    String waist = pref.getString(WAIST, "");
    String inseam = pref.getString(INSEAM, "");
    editNeck = (EditText) findViewById(R.id.neck);
    editSleeve = (EditText) findViewById(R.id.sleeve);
    editWaist = (EditText) findViewById(R.id.waist);
    editinseam = (EditText) findViewById(R.id.inseam);

    editNeck.setText(neck);
    editSleeve.setText(sleeve);
    editWaist.setText(waist);
    editInseam.setText(inseam);
  }

  public void onSaveTapped(View view){
    SharedPreferences pref =
	    PrefrenceManager.getDefaultSharedPreferences(this);
    SharedPreferences.Editor editor = pref.edit();
    editor.putString(NECK, editorNeck.getText().toString());
    editor.putString(SLEVE, editSleeve.getText().toString());
    editor.putString(WAIST, editWaist.getText().toString());
    editor.putString(INSEAM, editInseam.getText().toString());
    editor.commit();
  }
}


