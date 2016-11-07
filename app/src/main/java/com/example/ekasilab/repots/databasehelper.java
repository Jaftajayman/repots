        package com.example.ekasilab.repots;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        public class databasehelper extends SQLiteOpenHelper {

            private static final int DATABASE_VERSION = 1;
            private static final String DATABSE_NAME = "contact.db";
            private static final String TABLE_NAME = "contacts";
            private static final String COLUMN_ID = "id";
            private static final String COLUMN_NAME = "name";
            private static final String COLUMN_EMAIL = "email";
            private static final String COLUMN_UNAME = "uname";

            private static final String COLUMN_PASS = "pass";

            SQLiteDatabase db;
            private static final String TABLE_CREATE = "create table  contacts (id primary key not null, " +
                    "name text not null, email text not null, marks text not null,uname text not null, pass text not null, );";

            //creating constructor and it has no return type
            public databasehelper(Context context) {
                super(context, DATABSE_NAME, null, DATABASE_VERSION);

            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(TABLE_CREATE);
                this.db = db;
            }


            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                String query = "DROP TABLE IF EXISTS " + TABLE_CREATE;
                db.execSQL(query);
                this.onCreate(db);
            }

            public void insertContent(contact c) {
                db = this.getWritableDatabase();
                ContentValues values = new ContentValues();

                String query ="select * from contacts";
                Cursor cursor =db.rawQuery(query,null);
                int count =cursor.getCount();

                values.put(COLUMN_ID, count);
                values.put(COLUMN_NAME, c.getName());
                values.put(COLUMN_EMAIL, c.getEmail());
                values.put(COLUMN_UNAME, c.getUName());
                values.put(COLUMN_PASS, c.getPass());

        //this will insert data in the database
                db.insert(TABLE_NAME, null, values);

                db.close();
            }


            public String searchPass(String uname) {


                db = this.getWritableDatabase();
                String query = "select uname, pass from " + TABLE_NAME;
                Cursor cursor = db.rawQuery(query, null);
                String a, b;
                b= "not found";
                if (cursor.moveToFirst()) {
                    do {
                        a = cursor.getString(0);
                        if (a.equals(uname))
                        {
                            b= cursor.getString(1);
                            break;
                        }
                    }
                    while (cursor.moveToNext());
                }
                return b;
            }
            public Cursor getAlldata() {
                SQLiteDatabase db = this.getWritableDatabase();
                //instance of cursor
                Cursor resid = db.rawQuery("select * from " + TABLE_NAME, null);


                return resid;
            }

            public Cursor getdata(String pass) {
                SQLiteDatabase db = this.getWritableDatabase();
                //instance of cursor
                Cursor res = db.rawQuery("select * from " + TABLE_NAME+" where pass = " +pass, null);


                return res;
            }



        }