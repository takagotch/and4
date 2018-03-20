public class MyClockWidgetextends AppWidgetProvider {
  
  public static final String URI_SCHEME = "myclockwidget";

  public void onReceive(Context context, Intent intent){
    if(AppWidgetManager.ACTION_APPWIDGET_DELETED.
		    equals(intent.getAction())){
	deleteAlarm(context, intent);
	} else if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.
			equals(intent.getAction())){
		if(!URI_SCHEME.equals(intent.getScheme())){
		  setAlarm(context, intent);
		} else {
		  doProc(context, intent);
		}
	}
  }

  private void doProc(Context context, Intent intent){
    PowerManager pm = (PowerManager)context.
	    getSystemService();
    if(){} else {}
    int appWidgetId =
	    intent.getIntExtra();
    if(){}
  }

  private void setAlarm(){}

  private Intent buildAlarmIntent(){}

  private void deleteAlarm(){}

  static void updateAppWidget(){}



}


