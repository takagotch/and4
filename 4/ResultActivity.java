public class ResultActivity extends AppCompatActivity {
  final int JANKEN_GU = 0;
  final int JANKEN_CHOKI = 1;
  final int JANKEN_PA = 2;

@Override
protected void onCreate(Bundle savedInsatenceState){
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  int myHand = 0;
  Intent intent = getIntent();
  int id = intent.getIntentExtra("MY_HAND", 0);

  ImageView myHandImageView =
	  (ImageView) findViewById(R.id.my_hand_image);
  switch (id){
    case R.id.gu:
	    myHandImageView.setImageResource(R.drawable.gu);
	    myHand = JANKEN_GU;
	    break;
    case R.id.choki:
	    myHandImageView.setImageResource(R.drawable.choki);
	    myHand = JANKEN_CHOKI;
	    break;
    case R.id.pa:
	    myHandImageView.setImageResource(R.drawable.pa);
	    myHand = JANKEN_PA;
	    break;
    default:
	    myHand = JANKEN_GU;
	    break;
  }

  int comHand = (int)(Math.random() * 3);
  ImageView comHandImageView =
	  (ImageView) findViewById(R.id.com_hand_image);
  switch (comHand){
    case JANKEN_GU:
	    comHandImageView.setImageResource(R.drawable.com_gu);
	    break;
    case JANKEN_CHOKI:
	    comHandImageView.setImageResource(R.drawable.com_choki);
	    break;
    case JANKEN_PA:
	    comHandImageView.setImageResource(R.drawable.com_pa);
	    break;
  }

  TextView resultLabel = (TextView) findViewById(R.id.result_label);
  int gameResult = (comHand - myHand + 3) % 3;
  switch (gameResult){
    case 0:
	    resultLabel.setText(R.string.result_draw);
	    break;
    case 1:
	    resultLabel.setText(R.string.result_win);
	    break;
    case 2:
	    resultLabel.setText(R.string.result_lose);
	    break;
  }
}

public void onBackButton(View view){
  finish();
}

private void saveData(int myHand, int comHand, int gameResult){
  SharedPreferences pref =
	  PreferenceManager.getDefaultSharedPreferences(this);
  SharedPreferences.Editor editor = pref.edit();

  int gameCount pref.getInt("GAME+COUNT", 0);
  int winningStreakCount = pref.getInt("WINNING_STEAK_COUNT", 0);
  int lastComHand = pref.getInt("LAST_COM_HAND", 0);
  int lastGameResult = pref.getInt("GAME_RESULT", -1);
  editor.putInt("GAME_COUNT", gameCount + 1);
  if(lastGameResult == 2 && gameResult == 2){
    editor.putInt("WINNEING_STREAK_COUNT", winningSteakCount + 1);
  } else {
    editor.putInt("WINNING_STREAK_COUNT", 0);
  }
  editor.putInt("LAST_MY_HAND", myHand);
  editor.putInt("LAST_COM_HAND", comHand);
  editor.putInt("BEFORE_LAST_COM_HAND", lastComHand);
  editor.putInt("GAME_RESULT", gameResult);

  editor.commit();
}

}
