package sa.gov.mohe.mtokhais.testapp.MazinListView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import sa.gov.mohe.mtokhais.testapp.MazinDBholder.GiftItem;
import sa.gov.mohe.mtokhais.testapp.R;

/**
 * Created by mazoo_000 on 29/12/2015.
 */

public class LazyAdapter extends BaseAdapter {

    private Activity activity;
    private  List<GiftItem> data;
    private static LayoutInflater inflater=null;
    // public ImageLoader imageLoader;

    public LazyAdapter(Activity a,  List<GiftItem> _data) {
        activity = a;
        data= _data;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //imageLoader=new ImageLoader(activity.getApplicationContext());
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
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image

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