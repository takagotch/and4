public class MainActivity extends AppCompatActivity
    implements SensorEventListener, SurfaceHolder.Callback {
  SensorManager mSensorManager;
  Sensor mAccSensor;
  SurfaceHolder mHolder;
  int mSurfaceWidth;
  int mSurfaceHeight;

  static final float RADIUS = 50.0f;
  static final float COEF = 1000.0f;

  float mBallX;
  float mBallY;
  float mVX;
  float mVY;

  long mForm;
  long mTo;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mSensorManager =
	    (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    mAccSensor = mSensorManager.
	    getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    SurfaceView surfaceView =
	    (SurfaceView) findViewById(R.id.surfaceView);
    mHolder = surfaceView.getHolder();
    mHolder.addCallback(this);
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

  @Override
  public void surfaceCreated(SurfaceHolder holder){
    mSensorManager.registerListener(this, mAccSensor,
	SensorManager.SENSOR_DELAY_GAME);
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format,
	int width, int height){
    mSurfaceWidth = width;
    mSurfaceHeight = height;
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder){
    mSensorManager.unregisterListener(this);
  }

}

