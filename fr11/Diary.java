public class Diary extends RealmObject {
  @PrimaryKey
  public long id;
  public String title;
  public String bodyText;
  public String date;
  public byte[] image;
}

