package sa.gov.mohe.mtokhais.testapp.MazinDBholder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mazoo_000 on 04/02/2016.
 */
public class DAInterfacedb {

    private SQLiteDatabase database;
    private DBhelper dbHelper;

    public DAInterfacedb(Context context) {
        dbHelper = new DBhelper(context);
        database = dbHelper.getWritableDatabase();
    }

    /**
     * close any database object
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * insert a text report item to the location database table
     *
     * @param Gift
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long addGiftItem(GiftItem Gift) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.COLUMN_PATH, Gift.getPath());
        cv.put(DBhelper.COLUMN_TITLE, Gift.getTitle());
        cv.put(DBhelper.COLUMN_DESCRIPTION, Gift.getDescription());
        cv.put(DBhelper.COLUMN_DATETIME,Gift.getDate());
        cv.put(DBhelper.COLUMN_DATETIME_LONG,Gift.getDatetimeLong());

        cv.put(DBhelper.COLUMN_OCCASTION, Gift.getOccasion());
        cv.put(DBhelper.COLUMN_REMINDER, Gift.getReminder());
        return database.insert(DBhelper.TABLE_NAME, null, cv);
    }

    /**
     * delete the given Gift from database
     *
     * @param Gift
     */
    public void deleteGiftItem(GiftItem Gift) {
        String whereClause =
                DBhelper.COLUMN_TITLE + "=? AND " + DBhelper.COLUMN_DATETIME +
                        "=?";
        String[] whereArgs = new String[]{Gift.getTitle(),
                String.valueOf(Gift.getDatetimeLong())};
        database.delete(DBhelper.TABLE_NAME, whereClause, whereArgs);
    }

    /**
     * @return all Gift as a List
     */
    public List<GiftItem> getGiftItem() {
        List<GiftItem> MyGiftItems = new ArrayList<>();
        Cursor cursor =
                database.query(DBhelper.TABLE_NAME, null, null, null, null,
                        null, DBhelper.COLUMN_DATETIME_LONG+" DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            GiftItem MyGiftItem = cursorToMyGiftItem(cursor);
            MyGiftItems.add(MyGiftItem);
            cursor.moveToNext();
        }
        cursor.close();
        return MyGiftItems;
    }

    /**
     * read the cursor row and convert the row to a MyGiftItem object
     *
     * @param cursor
     * @return MyGiftItem object
     */
    private GiftItem cursorToMyGiftItem(Cursor cursor) {
        GiftItem Gift = new GiftItem();
        Gift.setPath(
                cursor.getString(cursor.getColumnIndex(DBhelper.COLUMN_PATH)));
        Gift.setTitle(
                cursor.getString(cursor.getColumnIndex(DBhelper.COLUMN_TITLE)));
        Gift.setDatetime(cursor.getLong(
                cursor.getColumnIndex(DBhelper.COLUMN_DATETIME_LONG)));
        Gift.setDate(cursor.getString(
                cursor.getColumnIndex(DBhelper.COLUMN_DATETIME)));
        Gift.setDescription(cursor.getString(
                cursor.getColumnIndex(DBhelper.COLUMN_DESCRIPTION)));
        Gift.setOccasion(cursor.getString(
                cursor.getColumnIndex(DBhelper.COLUMN_OCCASTION)));
        Gift.setReminder(cursor.getString(
                cursor.getColumnIndex(DBhelper.COLUMN_REMINDER)));
        return Gift;
    }
}
