public class ScheduleAdapter extends RealmBaseAdapter<Schedule>{
  
  private static class ViewHolder{
    TextView data;
    TextView title;
  }

  public ScheduleAdapter(@Nullable OrderedRealmCollection<Schedule> data){
    super(data);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent){
    ViewHolder viewHolder;

    if(convertView == null){
      convertView = LayoutInflater.from(parent.getContext()).inflate(
	android.R.layout.simple_list_item_2, parent, false);
      viewHolder = new ViewHolder();
      viewHolder.date = 
	      (TextView) convertView.findViewById(android.R.id.text1);
      viewHolder.date = 
	      (TextView) convertView.findViewById(android.R.id.text2);
      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder)convertView.getTag();
    }

    Schedule schedule = adapterData.get(position);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/ddd");
    String formatDate = sdf.format(schedule.getDate());
    viewHolder.date.setText(formatDate);
    viewHolder.title.setText(schedule.getTitle());
    return convertView;
  }
}


