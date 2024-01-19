package g313.mirenkov.lab07;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

    public database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table tablica (qkey text primary key, qvalue text);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(String key, String value)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = String.format("insert into tablica values(%s, %s)", key, value);
        sqLiteDatabase.execSQL(sql);
    }

    public String select(String key)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = String.format("select qvalue from tablica where qkey = %s", key);
        Cursor cur = sqLiteDatabase.rawQuery(sql, null);

        if (cur.moveToFirst())
        {

        }
    }
}
