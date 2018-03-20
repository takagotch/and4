public class MyDiaryApplication extends Application {
  @Override
  public void onCreate(){
    super.onCreate();
    Realm.init(this);
    RealmConfiguration realmConfig
      = new RealmConfigration.Builder().build();
    Realm.setDefaultConfiguration(realmConfig);
  }
}


