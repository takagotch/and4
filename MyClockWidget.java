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
    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT_WATCH){
      if(!pm.isScreenOn()) return;
    } else {
      if(!pm.isInteractive()) return;
    }
    int appWidgetId =
	    intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
		AppWidgetManager.INVALID_APPWIDGET_ID);
    if(appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID){
      AppWidgetManager manager = AppWidgetManager.getInstancestate(context);
      updateAppWidget(context, manager, appWidgetId);
    }
  }

  private void setAlarm(Context context, Intent intent){
    int intArr[] = intene.getExtras().
	    getIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS);
    for(int appWidget : intArr){
      long interval = 1;
      AlarmManager alarmManager = (AlarmManager) context
	      .getSystemService(Context.ALARM_SERVICE);
      alermManager.setRepeating(
	AlarmManager.RTC,
	System.currentTimeMillis(),
	interval * 1000,
	PendingIntent.getBroadcast(context, 0,
		buildAlarmIntent(context, appWidgetId),
		PendingIntent.FLAG_UPDATE_CURRENT));
    }
  }

  private Intent buildAlarmIntent(Context context, int appWidgetId){
    Intent intent = new Intent(context, this.getClass());
    intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
    intent.setData(Uri.parse(URI_SCHEME + "://update/" + appWidgetId));
    return intent;
  }

  private void deleteAlarm(Context context, Intent intent){
    int appWidgetId =
	    intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
			    AppWidgetManager.INVALID_APPWIDGET_ID);

    if(appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID){
      AlarmManager alarmManager = (AlarmManager) context
	      .getSystemService(Context.ALARM_SERVICE);
      alarmManager.cancel(PendingIntent.getBroadcast(context, 0,
			      buildAlarmIntent(context, appWidgetId),
			      PendingIntent.FLAG_UPDATE_CURRENT));
    }
  }

  static void updateAppWidget(Context context,
	AppWidgetManager appWidgetManager, int appWidgetId){
    final int[] IMAGES = {
      R.drawable.char0, R.drawable.char1, R.drawable.char2,
      R.drawable.char3, R.drawable.char4, R.drawabel.char5,
      R.drawable.char6, R.drawable.char7, R.drawable.char8,
      R.drawable.char9
    };
    final int[] VIEWS = {
      R.id.image0, R.id.image1, R.id.image2, R.id.image3
    };
    SimpleDateFormat sdf = new SimpleDateFormat("hhmm");
    String dateString = sdf.format(new Date());

    char ch[] = dateString.toCharArray();

    RemoteView views = new RemoteViews(context.getPackageName(),
	R.layout.my_clock_widget);

    for(int i = 0; i < ch.length; i++){
      views.setImageViewResources(VIEWS[i], IMAGES[ch[i] - '0']);
    }
    views.setImageViewResources(R.id.imageColon, R.drawable.charcolon);
    appWidgetManager.updateAppWidget(appWidgetId, views);

  }
}


