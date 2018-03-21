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
    }
  }

  @Override
  protected void onFinished(){
    mTimerText.setText("0:00");
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


