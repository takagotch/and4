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


