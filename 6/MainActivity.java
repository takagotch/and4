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

      float x = -event.values[0];
      float y = event.values[1];
      float z = event.values[2];

      mTo = System.currentTimeMillis();
      float t = (float)(mTo - mFrom);
      t = t / 1000.0f;

      float dx = mVX * t + x * t * t / 2.0f;
      float dy = mVY * t + y * t * t / 2.0f;
      mBallX = mBallX + dx * COEF;
      mBallY = mBallY + dy * COEF;
      mVX = mVX + x * t;
      mVY = mVY + y * t;

      if(mBallX - RADIUS < 0 && mVX < 0){
        mVX = -mVX / 1.5f;
	mBallX = RADIUS;
      } else if(mBallX + RADIUS > mSurfaceWidth && mVX > 0){
        mVX = -mVX / 1.5f;
	mBallX = mSurfaceWidth - RADIUS;
      }

      if(mBallY - RADIUS < 0 && mVY < 0){
        mVY = -mVY / 1.5f;
	mBallY = RADIUS;
      } else if(mBallY + RADIUS > mSurfaceHeight && mVY > 0){
        mVY = -mVY / 1.5fl
	mBallY = mSurfaceHeight - RADIUS;
      }

      mFrom = System.currentTimeMillis();
      drawCanvas();
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

