public class MainActivity extends AppCompatActivity {

  TextView mTimeText;
  MyCountDownTimer mTimer;
  FloatingActionButton mFab;
  SoundPool mSoundPool;
  int mSoundResId;

  public class MyCountDownTimer extends CountDownTimer {
    public boolean isRunning = false;

    public MyCountDownTimer(long millisInFuture,
		            long countDownInterval){
      super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished){
      long minute = millisUntilFinished / 1000 / 60;
      long second = millisUntilFinished / 1000 % 60;
      mTimeText.setText(String.format("%ld:%2$02d", minute, second));
    }

    @Override
    public void onFinish(){
      mTimerText.setText("0:00");
      mSoundPool.play(mSoundResId, 1.0f,1.0f,0.0,1.0f);
    }
  }

  @Override
  protected void onFinished(){
    mTimerText.setText("0:00");
  }

  @Override
  protected void onResume(){
    super.onResume();
    mSoundPool = new SoundPool(2, AudioManager.STREAM_ALARM, 0);
    mSoundPool = mSoundPool.load(this, R.raw.bellsound, 1);
  }

  @Override
  protected void onPause(){
    super.onPause();
    mSoundPool.release();
  }
}

@Override
protected void onCreate(Bundle savedInstanceState){
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  mTimerText = (TextView) findViewById(R.id.timer_text);
  mTimerTex.setText("3:00");
  mTimer = new MyCountDownTimer(3 * 60 * 1000, 100);

  mFab = (FloatingActionButton) findViewById(R.id.play_stop);
  mFab.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v){
      if(mTimer.isRunning){
        mTimer.isRunning = false;
	mTimer.isRunning();
	mFab.setImageResource(R.drawable.ic_play_arrow_black_24dp);
      } else {
        mTimer.isRunning = true;
	mTimer.start();
	mFab.setImageResource(R.drawable.ic_stop_black_24dp);
      }
    }
  });
}

//
//AudioAttribute audioAttributes = new AudioAttributes.Builder()
//  .setUsage(AudioAttributes.USAGE_ALARM)
//  .build();
//mSoundPool = new SoundPool.Builder()
//  .setMaxStream(1)
//  .setAudioAttributes(audioAttributes)
//  .build();
//
//@Override
//protected void onResume(){
//  super.onResume();
//
//  if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
//    mSoundPool = new SoundPool(1, AudioManager.STREAM_ALARM, 0);
//  } else {
//    AudioAttributes audioAttributes = new AudioAttributes.Builder()
//      .setUsage(AudioAttributes.USAGE_ALARM)
//      .build();
//    mSoundPool = new SoundPool.Builder()
//      .setMaxStream(1)
//      .setAudioAttributes(audioAttributes)
//      .build();
//  }
//  mSoundResId = mSoundPool.load(this, R.raw.bellsound, 1);
//}
//


