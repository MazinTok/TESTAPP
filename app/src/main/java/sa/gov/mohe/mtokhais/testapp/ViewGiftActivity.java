package sa.gov.mohe.mtokhais.testapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.CircularProgressButton;
import com.joooonho.SelectableRoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.squareup.picasso.Picasso;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import sa.gov.mohe.mtokhais.testapp.MazinDBholder.DAInterfacedb;
import sa.gov.mohe.mtokhais.testapp.MazinDBholder.GiftItem;

import static sa.gov.mohe.mtokhais.testapp.R.color.primary_dark;
import static sa.gov.mohe.mtokhais.testapp.R.id.etReminderViewGift;

public class ViewGiftActivity extends ActionBarActivity {

    //view
    TextView edTxData;
    TextView edTxReminder;
    EditText edTxName;
    EditText edTxDescraption;
    EditText edTxOccastion;

    SelectableRoundedImageView selectedImagePreview;

    DatePickerDialog dpd;
    Toolbar toolbar;
    CircularProgressButton circularButton1;
    //Db
    DAInterfacedb Db ;

    Uri selectedImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_gift);
        InitToolBar();
        InitView();
        Intent i = getIntent();
        GiftItem mGift = (GiftItem)i.getSerializableExtra("sampleObject");
        DateBinding( mGift);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_gift, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void InitToolBar() {
        toolbar = (Toolbar) findViewById(R.id.include1);
        // toolbar = (Toolbar) textEntryView.findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // setActionBarIcon(R.drawable.facebook);
        }
    }

    private void InitView()
    {

        edTxReminder = (TextView)findViewById(etReminderViewGift);
        edTxName = (EditText)findViewById(R.id.etNameViewGift);;
        edTxDescraption = (EditText)findViewById(R.id.etDescraptionViewGift);
        edTxOccastion =(EditText)findViewById(R.id.etOccasionViewGift);

        edTxData = (TextView)findViewById(R.id.et_DateViewGift);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        String currentDateandTime = sdf.format(new Date());
//        edTxData.setText(currentDateandTime);
        circularButton1 = (CircularProgressButton) findViewById(R.id.btnWithText);
        selectedImagePreview = (SelectableRoundedImageView) findViewById(R.id.imageViewGift);
    }
    private void DateBinding(GiftItem mGift)
    {

        edTxReminder.setText(mGift.getReminder());
        edTxName.setText(mGift.getTitle());
        edTxDescraption.setText(mGift.getDescription());
        edTxOccastion.setText(mGift.getOccasion());

        edTxData.setText(mGift.getDate());
        if (mGift.getPath()!= null) {
            String imageInSD = mGift.getPath();
            final Bitmap bitmap2 = BitmapFactory.decodeFile(imageInSD);
            selectedImagePreview.setImageBitmap(bitmap2);
            selectedImagePreview.setBackgroundColor(getResources().getColor(primary_dark));
            Log.d("image Path", mGift.getPath());
           selectedImagePreview.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
// Load image, decode it to Bitmap and display Bitmap in ImageView (or any other view
//  which implements ImageAware interface)
            ImageSize targetSize = new ImageSize(40, 40);
            //  imageLoader.displayImage(_gift.getPath(), thumb_image,targetSize);


            Picasso.with(getApplicationContext()).load(mGift.getPath())
                    .fit()

                    .into(selectedImagePreview);
        }
//        else{
//
//            Picasso.with(getApplicationContext()).load(R.drawable.gift)
//                    .resize(40, 40)
//                    .into(selectedImagePreview);
//        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        String currentDateandTime = sdf.format(new Date());
//        edTxData.setText(currentDateandTime);
//        circularButton1 = (CircularProgressButton) findViewById(R.id.btnWithText);
//        selectedImagePreview = (SelectableRoundedImageView) findViewById(R.id.imageViewGift);
    }
}
