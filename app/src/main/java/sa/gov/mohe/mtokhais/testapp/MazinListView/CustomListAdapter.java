package sa.gov.mohe.mtokhais.testapp.MazinListView;

/**
 * Created by mazoo_000 on 12/04/2015.
 */
/*
public class CustomListAdapter extends ArrayAdapter<Gifts> {

    Context context;
    int layoutResourceId;
    List<Gifts> data = null;
    //String data[] = null;

    public CustomListAdapter(Context context, int layoutResourceId, List<Gifts> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        WeatherHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new WeatherHolder();
          //  holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.newslistitem);
            holder.txtIn = (TextView)row.findViewById(R.id.InTV);
            holder.txtOut = (TextView)row.findViewById(R.id.OutTV);
            holder.txtday = (TextView)row.findViewById(R.id.dayTxt);

            //holder.WView = (WebView) row.findViewById(R.id.webView);

            row.setTag(holder);
        }
        else
        {
            holder = (WeatherHolder)row.getTag();
        }


        String day = data.get(position).getDay();
        String month = data.get(position).getMonth();
        String year = data.get(position).getYear();
        String txtday="" ;
        Calendar calendar = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day)); // Note that Month value is 0-based. e.g., 0 for January.
        int reslut = calendar.get(Calendar.DAY_OF_WEEK);
        switch (reslut) {
            case Calendar.SUNDAY:
                txtday = "Sun";
                break;
            case Calendar.MONDAY:
                txtday = "Mon";
                break;
            case Calendar.TUESDAY:
                txtday = "Tue";
                break;
            case Calendar.WEDNESDAY:
                txtday = "Wen";
                break;
            case Calendar.THURSDAY:
                txtday = "Thu";
                break;
            case Calendar.FRIDAY:
                txtday = "Fri";
                break;
            case Calendar.SATURDAY:
                txtday = "Sat";
                break;
        }

        String weather = Showdate(day,month,year);
        holder.txtTitle.setText( weather);
        holder.txtday.setText(txtday);
        holder.txtIn.setText(data.get(position).getTimeIN());
        holder.txtOut.setText(data.get(position).getTimeOut());
        //weather.setContent(weather.getTxt() +weather.getContent()());
       // holder.WView.loadDataWithBaseURL("", weather.getContent(), "text/html", "UTF-8", "");
       // holder.imgIcon.setImageResource(weather.icon);

        return row;
    }

    static class WeatherHolder
    {
        WebView WView;
        //ImageView imgIcon;
        TextView txtTitle;
        TextView txtIn;
        TextView txtOut;
        TextView txtday;
    }
    public String Showdate(String h, String m,String s)
    {
        //String curTime = day + "/" + month + "/" + year;
        String curTime = h + "/" + m + "/" + s;
        return curTime;
    }
}

*/
