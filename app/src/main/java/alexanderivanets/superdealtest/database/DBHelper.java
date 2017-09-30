package alexanderivanets.superdealtest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alexander on 30.09.17.
 */

public class DBHelper extends SQLiteOpenHelper {
    public final static String DB_NAME = "superDealDB.db";
    public final static String TABLE_NAME_RECENT = "resentTable";
    public static int DB_VERSION = 1;
    private Context context;

    private final static String createStart = "CREATE TABLE  IF NOT EXISTS ";
    private final String createEnd = "(imagePath TEXT," +
            " orgName TEXT, orgLocation TEXT, orgBlog TEXT, reposNumb TEXT);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createStart + TABLE_NAME_RECENT + createEnd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
