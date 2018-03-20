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

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
		  Bundle savedInstanceState){
    View v = inflater.inflate(R.layout.fragment_input_diary,
	container, false);
    mTitleEdit = () v.findViewById();
    mBodyEdit = () v.findViewById();
    mDiaryImage = () v.findViewById();

    mDiaryImage.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view){
        requestReadStorage(view);
      }
    });

    mTitleEdit.addTextChangeListener(new TextWatcher(){
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count,
		      int after){}

      @Override
      public void onTextChanged(CharSequence s, int start, int before,
		      int count){}

      @Override
      public void afterChanged(final Editable s){
        mRealm.executeTransactionAsync(new Realm.Transaction(){
	  @Override
	  public void execute(Realm realm){
	    Diary diary = realm.where(Diary.class).equalTo("id",
		mDiaryId).findFirst();
	    diary.title = s.toString();
	  }
	});
      }
    });

    mBodyEdit.addTextChangedListener(new TextWatcher(){
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count,
	int after){}

      @Override
      publci void onTextChanged(CharSequence s, int start, int before,
	int count){}

      @Override
      public void afterTextChanged(final Editable s){
        mRealm.executeTransactionAsync(new Realm.Transaction(){
	  @Override
	  public void execute(Realm realm){
	    Diary diary = realm.where(Diary.class).equalTo("id",
			    mDiary).findFirst();
	    diary.bodyText=s.toString();
	  }
	});
      }
    });
    return v;
  }

  private void requestReadStorage(View view){
    if(ContextCompat.checkSelfPermission(getActivity(),
	Manifest.permission.READ_EXTERNAL_STORAGE)
	!= PackageManager.PERMISSION_GRANTED){
      if(shouldShowRequestPermissionRationable(
	Manifest.permission.READ_EXTERNAL_STORAGE)){
	  Snackbar.make(view,R.string.rationable,
		Snackbar.LENGTH_LONG).show();
      }

      requestPermissions(new String[]{
        Manifest.permission.READ_EXTERNAL_STORAGE
      }, PERMISSON_REQUEST_CODE);

    } else {
      pickImage();
    }
  }

  private void pickImage(){
    Intent intent = new Intent(Intent.ACTION_PICK,
	MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    intent.setType("image/*");
    startActivityForResult(
	Intent.createChooser(
		intent,
		getString(R.string.pick_image)
	),
	REQUEST_CODE);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data){
    super.onActivityResult(requestCode, resultCode, data);
    if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
      Uri uri = (data == null) ? null : data.getData();
      if(uri != null){
        Bitmap img = MyUtils.getImageFormStream(
	  getActivity().getContentResolver(), uri);
	mDiaryImage.setImageBitmap(img);
      } catch (IOException e){
        e.printStackTrace();
      }
      mRealm.executeTransactionAsync(new Realm.Transaction(){
        @Override
	public void execute(Realm realm){
	  Diary diary = realm.where(Diary.class)
		  .equalTo("id", mDiaryId)
		  .findFirst();
	  BitmapDrawable bitmap =
		  (BitmapDrawable) mDiaryImage.getDrawable();
	  byte[] bytes = MyUtils.getByteFromImage(bitmap.getBitmap());
	  if(bytes != null && bytes.length > 0){
	    diary.image=bytes;
	  }
	}
      });
    }
  }

  @Override
  public void onRequestPermissionResult(int requestCode,
		  @NonNull String[] permission,
		  @NonNull int[] grantResults){
    if(requestCode == PERMISSION_REQUEST_CODE){
      if(grantResults.length != 1 ||
		      grangResults[0] != PackageManager.PERMISSION_GRANTED){
	Snackbar.make(mDiaryImage, R.string.permission_deny,
		      Snackbar.LENGTH_LONG).show();
	} else {
	  pickImage();
	}
      }
    }
}


