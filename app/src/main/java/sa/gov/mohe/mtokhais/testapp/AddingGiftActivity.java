package sa.gov.mohe.mtokhais.testapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.joooonho.SelectableRoundedImageView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import sa.gov.mohe.mtokhais.testapp.MazinDBholder.DAInterfacedb;
import sa.gov.mohe.mtokhais.testapp.MazinDBholder.GiftItem;

//import android.app.DatePickerDialog;


public class AddingGiftActivity extends ActionBarActivity implements DatePickerDialog.OnDateSetListener {
//    final LayoutInflater factory = getLayoutInflater();

    //final View textEntryView = factory.inflate(R.layout.tool_bar, null);

    public static final String IMAGE_TYPE = "image/*";
    private static final int SELECT_PICTURE_GALARY = 101;
    private static final int SELECT_PICTURE_CAMERA = 201;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_gift);

        InitToolBar();
        InitView();

        circularButton1.setIndeterminateProgressMode(true);
        circularButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton1.getProgress() == 0) {
                    circularButton1.setProgress(50);
                    AddingGift();
                } else if (circularButton1.getProgress() == 100) {
                    circularButton1.setProgress(0);
                } else {
                    circularButton1.setProgress(100);
                }
//                if (circularButton1.getProgress() == 0) {
//                    circularButton1.setProgress(50);
//                } else if (circularButton1.getProgress() == -1) {
//                    circularButton1.setProgress(0);
//                } else {
//                    circularButton1.setProgress(-1);
//                }
            }
        });
        edTxData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        AddingGiftActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog" );
            }
        });

        edTxReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        AddingGiftActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "ReminderPickerdialog");
                //circularButton1.setProgress(100);
            }
        });

        // Of course, you can set round radius in code.

        selectedImagePreview.setImageDrawable(getResources().getDrawable(R.drawable.gift));
        ((SelectableRoundedImageView)selectedImagePreview).setCornerRadiiDP(4, 4, 0, 0);

        findViewById(R.id.btn_pick_multiple_images).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();
            }
        });

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
    edTxData = (TextView)findViewById(R.id.et_Date);
    edTxReminder = (TextView)findViewById(R.id.etReminder);
    edTxName = (EditText)findViewById(R.id.etName);;
    edTxDescraption = (EditText)findViewById(R.id.etDescraption);;
    edTxOccastion =(EditText)findViewById(R.id.etOccasion);;


    circularButton1 = (CircularProgressButton) findViewById(R.id.btnWithText);
    selectedImagePreview = (SelectableRoundedImageView) findViewById(R.id.image3);
}
    private void selectImage() {

        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(AddingGiftActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo"))

                {

                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, SELECT_PICTURE_CAMERA);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, SELECT_PICTURE_GALARY);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }

            }

        });

        builder.show();

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE_GALARY) {

                Uri selectedImageUri = data.getData();

                try {
                    selectedImagePreview.setScaleType(ImageView.ScaleType.FIT_XY);

                    selectedImagePreview.setImageBitmap(new UserPicture(selectedImageUri, getContentResolver()).getBitmap());

                } catch (IOException e) {
                    Log.e(MainActivity.class.getSimpleName(), "Failed to load image", e);
                }

            }
            else if (requestCode == SELECT_PICTURE_CAMERA) {

                File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                //Bitmap input1 = BitmapFactory.decodeFile(f.getName());
                //Bitmap bitmap = Bitmap.createBitmap(Uri.fromFile(f),100,100,1,1);
                //Bitmap bitmap = BitmapFactory.decodeFile(Uri.fromFile(f));
                //Bitmap bitmap = Bitmap.createScaledBitmap(input1, 350, 240, true);

               // Uri imageUri = data.getData();
                Bitmap bitmap = null;
                ExifInterface ei = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.fromFile(f));
                    ei = new ExifInterface(f.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

                switch(orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        bitmap=   rotateImage(bitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        bitmap=  rotateImage(bitmap, 180);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        bitmap=  rotateImage(bitmap, 270);
                        break;
                    // etc.
                }
                selectedImagePreview.setScaleType(ImageView.ScaleType.FIT_XY);
//                selectedImagePreview.getAdjustViewBounds(true);
               // selectedImagePreview.setImageURI(Uri.fromFile(f));
                selectedImagePreview.setImageBitmap(bitmap);
            }


        } else {
            // report failure
            Toast.makeText(getApplicationContext(), R.string.msg_failed_to_get_intent_data, Toast.LENGTH_LONG).show();
            Log.d(MainActivity.class.getSimpleName(), "Failed to get intent data, result code is " + resultCode);
        }
    }
    public static Bitmap rotateImage(Bitmap source, float angle) {
        Bitmap retVal;

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        retVal = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);

        return retVal;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adding_gift, menu);
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
    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int i, int i1, int i2) {

        if (datePickerDialog.getTag() == "Datepickerdialog")
        {
            String date =  +i+"/"+(++i1)+"/"+i2;
            edTxData.setText(date);
        }
        else
        {
            String date =  +i+"/"+(++i1)+"/"+i2;
            edTxReminder.setText(date);
        }

    }
    protected void setActionBarIcon(int iconRes) {
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("ffff");
        toolbar.setNavigationIcon(iconRes);
    }
    private void AddingGift()
    {
        Db = new DAInterfacedb(this);


        GiftItem Gift = new GiftItem();
        Gift.setTitle(edTxName.getText().toString());
        Gift.setDescription(edTxDescraption.getText().toString());
        Gift.setDatetime(System.currentTimeMillis());
        Gift.setPath("pathTest");
        Gift.setOccasion(edTxOccastion.getText().toString());
        Gift.setReminder(edTxReminder.getText().toString());
        //images.add(image);
        Db.addGiftItem(Gift);
        Db.close();
        circularButton1.setProgress(100);
//        .addImage(image);
    }
}
