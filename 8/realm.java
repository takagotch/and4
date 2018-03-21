public class Dog extends RealmObject {
  private String name;
  private int age;
  @ignore private int dontPersistMe;
}

//
Realm realm = Realm.getInstance(this.getContext());
realm.beginTransaction();
Dog dog = realm.createObject(Dog.class);
dog.setName("Rex");
dog.setAge(3);
realm.comitTransaction();

//
realm.executeTransaction(new Realm.Transaction(){
  @Override
  public void execute(Realm realm){
    Dog dog = realm.createObject(Dog.class);
    dog.setName("Rex");
    dog.setAge(3);
  }
});

//
RealmResults<Dog> query = realm.where(Dog.class)
	.greaterThan("age", 8)
	.findAll();

RealmResult<Dog> allRex = query.where()
	.contains("name", "Rex")
	.findAll();


//DELETE
realm.beginTransaction();
query.deleteFirstFromRealm();
query.deletelastFromRealm();
realm.commitTransaction()


