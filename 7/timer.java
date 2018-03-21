Timer timer = new Timer();
timer.schedule(new MyTimerTask(), 5000)

class MyTimerTask extends TimerTask{
  public void run(){
  }
}

Handler handler = new Handler();
Timer timer = new Timer();
timer.schedule(new MyTimerTask(), 0, 5000)

class MyTimerTask extends TimerTask{
  
  @Override
  public void run(){
    handler.post(new Runnable){
      @Override
      Public void run(){
      }
    }
  }
}

