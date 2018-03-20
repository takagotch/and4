public class DiaryListFragment extends Fragment {
  private OnFragmentInteractionListener mListener;

  public DiaryListFragment() {}

  public static DiaryListFragment newInstance() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	Bundle savedInstanceState){
    View v = inflater.inflate(R.layout.fragment.diary_list, container, false);
    RecyclerView  recyclerView = (RecyclerView) v.findViewById(R.id.recycler);

    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
    llm.setOrientation(LinearLayoutManager.VERTICAL);

    recyclerView.setLayoutManager(llm);

    return v;
  }

  @Override
  public void onAttach(Context context){
    super.onAttach(context);
    if(context instanceof OnFragmentInteractionListener){
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
	+ " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach(){
    super.onDetach();
    mListener = null;
  }

  public interface OnFragmentInteractionListener {
  }
}


