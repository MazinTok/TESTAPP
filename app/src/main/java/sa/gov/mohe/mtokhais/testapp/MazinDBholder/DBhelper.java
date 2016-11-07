package sa.gov.mohe.mtokhais.testapp.MazinDBholder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mazoo_000 on 04/02/2016.
 */
public class DBhelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "sqlitegifts.db";
    public static final int DB_VERSION = 1;

    public static final String COMMA_SEP = ",";
    public static final String TEXT_TYPE = " TEXT";
    public static final String NUMERIC_TYPE = " NUMERIC";
    public static final String INTEGER_TYPE = " INTEGER";

    public static final String TABLE_NAME = "gift";


    public static final String COLUMN_ID = "id";

    public static final String COLUMN_PATH = "path";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DATETIME = "datetime";
    public static final String COLUMN_DATETIME_LONG = "datetimelong";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_OCCASTION = "occasion";
    public static final String COLUMN_REMINDER = "reminder";

//    public static final String PRIMARY_KEY = "PRIMARY KEY (" + COLUMN_TITLE + "," + COLUMN_DATETIME + ")";

    public static final String PRIMARY_KEY = COLUMN_ID +" integer primary key autoincrement not null";

    private static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            PRIMARY_KEY +COMMA_SEP +
            COLUMN_PATH + TEXT_TYPE + COMMA_SEP +
            COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
            COLUMN_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
            COLUMN_OCCASTION + TEXT_TYPE + COMMA_SEP +
            COLUMN_DATETIME + TEXT_TYPE + COMMA_SEP +
            COLUMN_REMINDER + TEXT_TYPE +COMMA_SEP +
            COLUMN_DATETIME_LONG + INTEGER_TYPE +
            " )";

    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }
}