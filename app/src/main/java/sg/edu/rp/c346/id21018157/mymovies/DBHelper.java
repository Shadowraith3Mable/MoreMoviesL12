package sg.edu.rp.c346.id21018157.mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mymovies.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_MOVIES = "movies";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATING = "rating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        @Override
        public void onCreate (SQLiteDatabase db){
            String createNoteTableSql = "CREATE TABLE " + TABLE_MOVIES + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " TEXT ," + COLUMN_GENRE + " TEXT ,"
                    + COLUMN_YEAR + " INTEGER ," + COLUMN_RATING + " INTEGER ) ";
            db.execSQL(createNoteTableSql);
            Log.i("info", "created tables");
        }

        @Override
        public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){
            db.execSQL("ALTER TABLE " + TABLE_MOVIES + " ADD COLUMN  module_name TEXT ");
        }

        public long insertMovie (String title, String genre,int year, String rating){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, title);
            values.put(COLUMN_GENRE, genre);
            values.put(COLUMN_YEAR, year);
            values.put(COLUMN_RATING, rating);
            long result = db.insert(TABLE_MOVIES, null, values);
            Log.d("DBHelper", "Insert failed");
            db.close();
            //id returned, shouldn’t be -1
            return result;
        }

        //perform records retrieval
        public ArrayList<Movie> getAllMovie() {
            ArrayList<Movie> songs = new ArrayList<Movie>();

            SQLiteDatabase db = this.getReadableDatabase();

            String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
            Cursor cursor = db.query(TABLE_MOVIES, columns, null, null,
                    null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String title = cursor.getString(1);
                    String genre = cursor.getString(2);
                    int year = cursor.getInt(3);
                    String rating = cursor.getString(4);
                    Movie song = new Movie(id, title, genre, year, rating);

                    songs.add(song);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return Movie;


            public int updateMovie (Movie data){
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(COLUMN_TITLE, data.getTitle());
                values.put(COLUMN_GENRE, data.getSingers());
                values.put(COLUMN_YEAR, data.getYear());
                values.put(COLUMN_RATING, data.getStars());
                String condition = COLUMN_ID + "= ?";
                String[] args = {String.valueOf(data.get_id())};
                int result = db.update(TABLE_MOVIES, values, condition, args);
                if (result < 1) {
                    Log.d("DBHelper", "Update failed");
                }
                db.close();
                return result;
            }

            public int deleteSong ( int _id){
                SQLiteDatabase db = this.getWritableDatabase();
                String condition = COLUMN_ID + "= ?";
                String[] args = {String.valueOf(id)};
                int result = db.delete(TABLE_MOVIES, condition, args);

                db.close();
                return result;
            }

            public ArrayList<Movie> getPG () {
                ArrayList<Movie> movies = new ArrayList<Song>();

                SQLiteDatabase db = this.getReadableDatabase();
                String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
                String condition = COLUMN_RATING + " = ? ";
                String[] args = {"pg-13"};
                Cursor cursor = db.query(TABLE_MOVIES, columns, condition, args,
                        null, null, null, null);

                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(0);
                        String title = cursor.getString(1);
                        String singers = cursor.getString(2);
                        int year = cursor.getInt(3);
                        String rating = cursor.getString(4);
                        Movie movie = new Movie(id, title, genre, year, rating);
                        movies.add(movie);
                    } while (cursor.moveToNext());
                }
                cursor.close();
                db.close();
                return movies;
            }

        }
    }
//new
