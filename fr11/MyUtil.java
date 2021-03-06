public class MyUtils {
  public static Bitmap getImageFromStream(CotentResolver resolver, Uri uri)
	  throws IOException {
    InputStream in;
    BitmapFactory.Options opt = new BitmapFactory.Options();
    opt.inJustDecodeBounds = true;
    in = resolver.openInputStream(uri);
    BitmapFactory.decodeStream(in, null, opt);
    in.close();
    int bitmapSize = 1;
    if((opt.outHeight * opt.outWidth ) > 500000){
      double outSize = (double) (opt.outHeight * opt.outWidth) / 500000;
      bitmapSize = (int)(Math.sqrt(outSize) + 1);
    }

    opt.inJustDecodeBounds = false;
    opt.inSampleSize = bitmapSize;
    in = resolver.openInputStream(uri);
    Bitmap bmp = BitmapFactory.decodeStream(in, null, opt);
    in.close();
    return bmp;
  }
}


