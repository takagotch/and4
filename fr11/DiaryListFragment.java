public class DiaryListFragment extends Fragment {
  private OnFragmentInteractionListener mListener;
  private Realm mRealm;

  public DiaryListFragment() {}

  public static DiaryListFragment newInstance() {
    DiaryListFragment fragment = new DiaryListFragment();
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    mRealm = Realm.getDefaultInstace();
  }

  @Override
  public void onDestroy(){
    super.onDestroy();
    mrealm.close();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	Bundle savedInstanceState){
    View v = inflater.inflate(R.layout.fragment.diary_list, container, false);
    RecyclerView  recyclerView = (RecyclerView) v.findViewById(R.id.recycler);

    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
    llm.setOrientation(LinearLayoutManager.VERTICAL);

    recyclerView.setLayoutManager(llm);

    RealmResults<Diary> diaries = mRealm.where(Diary.class).findAll();
    DiaryRealmAdapter adapter =
	    new DiaryRealmAdapter(getActivity(), diaries, true);

    recyclerView.setAdapter(adapter);
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
    void onAddDiarySelected();
  }
}


