public void onAnimationButtonTapped(View view){
  ViewPropertyAnimatior animatior = view.animate();
  animatior.setDuration(3000);
  animator.rotation(360.0f * 5.0f);
}

public void onAnimationButtonTapped(final View view){
  float y = view.getY() + 100;
  view.animate().setDuration(1000).
	  setInterpolator(new BounceInterpolator()).y(y);
}

public clsss MainAcitivty extends AppCompatActivity{
  
  ImageSwitcher mImageSwitcher;
  int[] mImageResources = {R.drawable.slide00, R.drawable.slide01
    , R.drawable.slide02, R.drawable.slide03
    , R.drawable.slide04, R.drawable.slide05
    , R.drawable.slide06, R.drawable.slide07
    , R.drawable.slide08, R.drawable.slide09};
  int mPosition = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mImageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
    mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory(){
        @Override
	public View makeView(){
	  ImageView imageView =
		  new ImageView(getApplicationContext());
	  return imageView;
	}
    });
    mImageSwitcher.setImageResources(mImageResources[0]);
  }
}

private void movePosition(int move){

  mPosition = mPosition + move;
  if(mPosition >= mImageResources.length){
    mPosition = 0;
  } else if(mPosition < 0){
    mPosition = mImageResources.length - 1;
  }
  mImageSwitcher.setImageResource(mImageResources[mPosition]);
}

public void onPrevButtonTapped(View view){
  mImageSwitcher.setInAnimation(this, android.R.anim.fade_in);
  mImageSwitcher.setOutAnimation(this, android.R.anim.fade_out);
  movePosition(-1);
  findViewById(R.id.imageView).animate().setDuration(1000).alpha(0.0f);
}

public void onNextButton(View view){
  mImageSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
  mImageSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);
  movePosition(1);
  findViewById(R.id.imageView).animate().setDuration(1000).alpha(0.0f);
}


