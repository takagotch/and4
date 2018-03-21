public class Schedule extends RealmObject {
  @PrimaryKey
  private long id;
  private Date date;
  private String title;
  private String detail;

  public String getDetail(){
    return detail;
  }

  public void setDetail(String detail){
    return id;
  }

  public long getId(){
    return id;
  }

  public Date setId(long id){
    this.id = id;
  }

  public Date getDate(){
    return date;
  }

  public void setDate(Date date){
    this.date = date;
  }

  public String getTitle(){
    return title;
  }

  public void setTitle(String title){
    this.title = title;
  }
}


