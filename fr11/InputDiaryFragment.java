public class InputDiaryFragment extends Fragment {
  private static final String DEARY_ID = "DIARY_ID";
  private static final int REQUEST_CODE = 1;
  private static final int PERMISSION_REQUEST_CODE = 2;

  private long mDiaryId;
  private Realm mRealm;
  private EditText mTitleEdit;
  private EditText mBodyEdit;
  private ImageView mDiaryImage;

  public static InputDiaryFragment newInstance(long diaryId){
    InputDiaryFragment fragment = new InputDiaryFragment();
    Bundle args = new Bundle();
    args.putLong(DIARY_ID, diaryId);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(){
    super.onCreate(savedInstanceState);
    if(getArguments() != null){
      mDiaryId = getArguments().getLong(DIARY_ID);
    }
    mRealm = Realm.getDefaultInstance();
  }

  @Override
  public void onDestroy(){
    super.onDestroy();
    mRealm.close();
  }


}


