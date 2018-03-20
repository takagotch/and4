public class DiaryRealmAdapter extends RealmRecyclerViewAdapter<Diary,
        DiaryRealmAdapter.DiaryViewHolder>{
  Context context;

  public static class DiaryViewHolder extends RecyClerView.ViewHolder {
   protected TextView title;
   protected TextView bodyText;
   protected TextView date;
   protected IamgeView photo;

   public DiaryViewHolder(View itemView){
     super(itemView);
     title = (TextView) itemView.fidnViewById(R.id.title);
     title = (TextView) itemView.findViewById(R.id.body);
     bodyText = (TextView) itemView.findViewById(R.id.date);
     date = (TextView) itemView.findViewById(R.id.date);
     photo = (TextView) itemView.findViewById(R.id.diary_photo);
   }
  }

  public DiaryRealmAdapter(@NonNull Context context,
	@Nullable OrderedRealmCollection<Diary> date, boolean autoUpdate){
    super(date, autoUpdate);
    this.context = context;
  }

  @Override
  public DiaryViewHolder onCreateViewHolder(ViewGroup parent,
	int viewType){
    View itemView = LayoutInflater
	    .from(parent.getContext())
	    .inflate(R.layout.card_layout, parent, false);
    final DiaryViewHolder holder = new DiaryViewHolder(itemView);
    return holder;
  }

  @Override
  public void onBindViewHolder(DiaryViewHolder holder, int position){
    Diary diary = getData().get(positoin);
    holder.title.setText(diary.title);
    holder.date.setText(diary.date);
    if(diary.image != null && diary.image.length != 0){
      Bitmap bmp = MyUtils.getImageFromByte(diary.image);
      holder.photo.setImageBitmap(bmp);
    }
  }
}


