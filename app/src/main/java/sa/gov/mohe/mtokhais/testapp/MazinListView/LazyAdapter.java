package sa.gov.mohe.mtokhais.testapp.MazinListView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import sa.gov.mohe.mtokhais.testapp.MazinDBholder.GiftItem;
import sa.gov.mohe.mtokhais.testapp.R;

/**
 * Created by mazoo_000 on 29/12/2015.
 */

public class LazyAdapter extends BaseAdapter {

    private Context context;
    private Activity activity;
    private  List<GiftItem> data;
    private static LayoutInflater inflater=null;
    // public ImageLoader imageLoader;

    public LazyAdapter(Activity a,  List<GiftItem> _data) {
        activity = a;
        data= _data;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //imageLoader=new ImageLoader(activity.getApplicationContext());
        this.context = a.getApplicationContext();
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.gifts_list_row, null);

        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView descreption = (TextView)vi.findViewById(R.id.tvdescreption); // artist name
        TextView occasion = (TextView)vi.findViewById(R.id.tvOccasion); // artist name
       //TextView duration = (TextView)vi.findViewById(R.id.duration); // duration

        final ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
//        imageLoader.displayImage(imageUri, imageView);
        //imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);

        TextView day = (TextView)vi.findViewById(R.id.textViewDay); // day
        TextView month = (TextView)vi.findViewById(R.id.textViewMonth); // month

        HashMap<String, String> song = new HashMap<String, String>();

//        song = data.get(position);

        GiftItem _gift = data.get(position);
        // Setting all values in listview
        title.setText(_gift.getTitle());
       // descreption.setText(_gift.getDescription());
        occasion.setText(_gift.getOccasion());
        //descreption.getText().toString()
        float text_size = getTextSize(_gift.getDescription());
        int text_view_size = descreption.getLayoutParams().width;

        // image views

       //if (_gift.getPath() != "")
//        Picasso.with(context).load(_gift.getPath()).into(thumb_image);
//        File imgFile = new  File(_gift.getPath());
//
//        Bitmap bitmap;
//        try {
//            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.fromFile(imgFile));
//            thumb_image.setImageBitmap(bitmap);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        if (_gift.getPath()!= null) {
        String imageInSD = _gift.getPath();
        final Bitmap bitmap2 = BitmapFactory.decodeFile(imageInSD);
        thumb_image.setImageBitmap(bitmap2);
        Log.d("image Path", _gift.getPath());

        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
// Load image, decode it to Bitmap and display Bitmap in ImageView (or any other view
//  which implements ImageAware interface)
        ImageSize targetSize = new ImageSize(40, 40);
      //  imageLoader.displayImage(_gift.getPath(), thumb_image,targetSize);


            Picasso.with(context).load(_gift.getPath())
                    .resize(40, 40)
                    .onlyScaleDown()
                    .into(thumb_image);
        }
        else{

            Picasso.with(context).load(R.drawable.gift)
                    .resize(40, 40)
                    .onlyScaleDown()
                    .into(thumb_image);
        }

//        imageLoader.loadImage(_gift.getPath(), new SimpleImageLoadingListener() {
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                // Do whatever you want with Bitmap
//                Bitmap resizedBitmap  = Bitmap.createScaledBitmap(
//                        loadedImage, 40, 40, false);
//                thumb_image.setImageBitmap(resizedBitmap);
//            }
//        });
//        thumb_image.setImageURI(Uri.parse(new File(_gift.getPath()).toString()));

//        if(imgFile.exists()){
//
//            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//
//
//
//            thumb_image.setImageBitmap(myBitmap);
//
//        }

       // /external/images/media/24330

        // show dates in rows
        String dateString = _gift.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date dateFR = null;
        try {
            dateFR = format.parse(dateString);
            day.setText(Integer.toString(dateFR.getDate()));
            SimpleDateFormat monthParse = new SimpleDateFormat("MM");
            SimpleDateFormat monthDisplay = new SimpleDateFormat("MMM");

            month.setText(monthDisplay.format(monthParse.parse(Integer.toString(dateFR.getMonth()+1))));
            //Integer.toString(dateFR.getMonth()))];
        } catch (ParseException e) {
            e.printStackTrace();
        }



        //Compare your text_size and text_view_size and do whatever you want here.

//        View.MeasureSpec.getSize(descreption);
        if (text_size > text_view_size) {
            //Text is truncated because text height is taller than TextView height
            descreption.setText("..." +_gift.getDescription().substring(0, text_view_size-5)+"...");

        } else {
            //Text not truncated because text height not taller than TextView height
            descreption.setText(_gift.getDescription());

        }


//        duration.setText(song.get(CustomizedListView.KEY_DURATION));
//        imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);

        return vi;
    }

    private float getTextSize(String your_text){
        Paint p = new Paint();
        //Calculate the text size in pixel
        return p.measureText(your_text);
    }
}