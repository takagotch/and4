public class MainActivity extends AppCompatActivity
    implements SensorEventListener{
  SensorManager mSensorManager;
  Sensor mAccSensor;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mSensorManager =
	    (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    mAccSensor = mSensorManager.
	    getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
  }

  @Override
  protected void onResume(){
    super.onResume();
    mSensorManager.registerListener(this, mAccSensor,
	SensorManager.SENSOR_DELAY_GAME);
  }

  @Override
  protected void onPause(){
    super.onPause();
    mSensorManager.unregisterListener(this);
  }

  @Override
  public void onSensorChanged(SensorEvent event){
    if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
      Log.d("MainActivity",
	"x=" + String.valueOf(event.valuesOf[0]) +
	"y=" + String.valudOf(event.valudeOf[1]) +
	"z=" + String.valudOf(event.valudesOf[2]));
    }
  }
}

