package sa.gov.mohe.mtokhais.testapp.MazinDBholder;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by mazoo_000 on 04/02/2016.
 */
public class GiftItem implements Serializable {

    private String title;
    private String description;
    private String path;
    private String occasion;
    private String reminder;
    private long datetimeLong;
    private String Date;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public GiftItem(String title, String description, String path,
                   long datetimeLong ,String occasion, String reminder) {
        this.title = title;
        this.description = description;
        this.path = path;
        this.datetimeLong = datetimeLong;
        this.setOccasion(occasion);
        this.setReminder(reminder);
    }

    public GiftItem() {
    }

    /**
     * Gets title.
     *
     * @return Value of title.
     */
    public String getTitle() { return title; }

    /**
     * Gets datetime.
     *
     * @return Value of datetime.
     */
    public Calendar getDatetime() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(datetimeLong);
        return cal;
    }

    /**
     * Sets new datetimeLong.
     *
     * @param datetimeLong New value of datetimeLong.
     */
    public void setDatetime(long datetimeLong) {
        this.datetimeLong = datetimeLong;
    }

    /**
     * Sets new datetime.
     *
     * @param datetime New value of datetime.
     */
    public void setDatetime(Calendar datetime) {
        this.datetimeLong = datetime.getTimeInMillis();
    }

    /**
     * Gets description.
     *
     * @return Value of description.
     */
    public String getDescription() { return description; }

    /**
     * Sets new title.
     *
     * @param title New value of title.
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * Gets datetimeLong.
     *
     * @return Value of datetimeLong.
     */
    public long getDatetimeLong() { return datetimeLong; }

    /**
     * Sets new description.
     *
     * @param description New value of description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets new path.
     *
     * @param path New value of path.
     */
    public void setPath(String path) { this.path = path; }

    /**
     * Gets path.
     *
     * @return Value of path.
     */
    public String getPath() { return path; }

    @Override public String toString() {
        return "Title:" + title + "   " + df.format(getDatetime().getTime()) +
                "\nDescription:" + description;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}