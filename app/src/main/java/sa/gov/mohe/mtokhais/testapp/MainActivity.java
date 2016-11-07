package sa.gov.mohe.mtokhais.testapp;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.getbase.floatingactionbutton.AddFloatingActionButton;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import sa.gov.mohe.mtokhais.testapp.MazinDBholder.DAInterfacedb;
import sa.gov.mohe.mtokhais.testapp.MazinDBholder.GiftItem;
import sa.gov.mohe.mtokhais.testapp.MazinListView.LazyAdapter;

//import com.baoyz.swipemenulistview.BaseSwipListAdapter;


public class MainActivity extends ActionBarActivity {

    private SwipeMenuListView mListView;
    private AppAdapter mAdapter;
    private List<ApplicationInfo> mAppList;
    private List<GiftItem> GiftList;

    AddFloatingActionButton PlusBtn;
    ListView list ;

    DAInterfacedb Db;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.libtest);
// Create global configuration and initialize ImageLoader with this config
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
//       .build();
//        ImageLoader.getInstance().init(config);


        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
        .build();
        ImageLoader.getInstance().init(config);

        InitToolBar();
        InitView();
        RefreachView();
        //mAppList = getPackageManager().getInstalledApplications(0);



        // Click event for single list row
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view,
                                    int position, long id) {

                GiftItem gift =  GiftList.get(position);
                Intent i = new Intent(MainActivity.this, ViewGiftActivity.class);
                i.putExtra("sampleObject", gift);
                startActivity(i);

            }
        });


        PlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddingGiftActivity.class);
                startActivity(intent);
            }
        });

/*
        mAdapter = new AppAdapter();
        mListView.setAdapter(mAdapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.facebook);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        mListView.setMenuCreator(creator);

        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        break;
                    case 1:
                        // delete
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        */
    }
private void RefreachView()
{
    Db = new DAInterfacedb(this);

    GiftList = Db.getGiftItem();
//        List<String> NewsResList  = new  ArrayList();
//        HashMap<String, String> songsList = new HashMap<String, String>();
//
//
//        // looping through all song nodes &lt;song&gt;
//        for (int i = 0; i < 15; i++) {
//            // creating new HashMap
//            HashMap<String, String> map = new HashMap<String, String>();
//
//            // adding each child node to HashMap key =&gt; value
//            map.put("KEY_ID", "d");
//            map.put("KEY_TITLE", "D");
//            map.put("KEY_ARTIST", "s");
//            map.put("KEY_DURATION", "a");
//            map.put("KEY_THUMB_URL","f");
//
//            // adding HashList to ArrayList
//           // songsList.add(map);
//            NewsResList.add("s");
//        }



    // Getting adapter by passing xml data ArrayList
    LazyAdapter adapter=new LazyAdapter(this, GiftList);
    list.setAdapter(adapter);



}
    private void InitToolBar() {
        toolbar = (Toolbar) findViewById(R.id.include);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private void InitView()
    {
         PlusBtn= (AddFloatingActionButton) findViewById(R.id.normal_plus);
        mListView = (SwipeMenuListView) findViewById(R.id.listView);
        list=(ListView)findViewById(R.id.list);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onResume(){
        super.onResume();
        // put your code here...
        RefreachView();

    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
    public abstract class BaseSwipListAdapter extends BaseAdapter {

        public boolean getSwipEnableByPosition(int position){
            return true;
        }



    }
    class AppAdapter extends BaseSwipListAdapter {

        @Override
        public int getCount() {
            return mAppList.size();
        }

        @Override
        public ApplicationInfo getItem(int position) {
            return mAppList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.item_list_app, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            ApplicationInfo item = getItem(position);
            holder.iv_icon.setImageDrawable(item.loadIcon(getPackageManager()));
            holder.tv_name.setText(item.loadLabel(getPackageManager()));
            holder.iv_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "iv_icon_click", Toast.LENGTH_SHORT).show();
                }
            });
            holder.tv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "iv_icon_click", Toast.LENGTH_SHORT).show();
                }
            });
            return convertView;
        }

        class ViewHolder {
            ImageView iv_icon;
            TextView tv_name;

            public ViewHolder(View view) {
                iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
                tv_name = (TextView) view.findViewById(R.id.tv_name);
                view.setTag(this);
            }
        }

        @Override
        public boolean getSwipEnableByPosition(int position) {
            if (position % 2 == 0) {
                return false;
            }
            return true;
        }
    }
}
