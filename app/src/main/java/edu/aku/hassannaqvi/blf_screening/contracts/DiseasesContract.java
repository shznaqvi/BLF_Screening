package edu.aku.hassannaqvi.blf_screening.contracts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class DiseasesContract {

    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.mi_covid";

    public static abstract class DiseasesTable implements BaseColumns {

        public static final String TABLE_NAME = "diseases";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

        public static final String ID = "_id";
        public static final String COLUMN_UID = "uid";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_Q_NO = "q_no";
        public static final String COLUMN_SYSDATE = "sysdate";
        public static final String COLUMN_DEVICE_ID = "deviceid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";

        public static String PATH = "diseases";
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY)
                .buildUpon().appendPath(PATH).build();

        public static String getMovieKeyFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static Uri buildUriWithId(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}