public class MySchedulerApplication extends Application {
  @Override
  public void onCreate(){
    super.onCreate();
    Realm.init(this);
    RealmConfiguration realmConfig = new RealmConfiguration.Builder().build();
    Realm.setDefaultConfiguration(realmConfig);
  }
}

