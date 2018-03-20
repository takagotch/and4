@Override
  public DiaryViewHolder onCreateViewHolder(ViewGroup parent,
		  int viewType){
    View itemView = LayoutInflater
	    .from(parent.getContext())
	    .inflate(R.layout.card_layout, parent, false);
    final DiaryViewHolder holder = new DiaryViewHolder(itemView);

    holder.itemView.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view){
        int positoin = holder.getAdapterPosition();
	Diary diary = getDate().get(position);
	long diaryId = diary.id;

	Intent intent = new Intent(context, ShowDiaryActivity.class);
	intent.putExtra(ShowActivity.DIARY_ID, diaryId);
	context.startActivity(intent);
      }
    });
    return holder;
  }


