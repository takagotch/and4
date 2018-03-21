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


