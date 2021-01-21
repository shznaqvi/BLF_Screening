package edu.aku.hassannaqvi.blf_screening.contracts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class EpisodesContract {

    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.mi_covid";

    public static abstract class EpisodesTable implements BaseColumns {

        public static final String TABLE_NAME = "episodes";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

        public static final String ID = "_id";
        public static final String COLUMN_UID = "uid";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_DUID = "duid";
        public static final String COLUMN_MINUTES = "minutes";
        public static final String COLUMN_HOURS = "hours";
        public static final String COLUMN_DAYS = "days";
        public static final String COLUMN_SECONDS = "seconds";
        public static final String COLUMN_SYSDATE = "sysdate";
        public static final String COLUMN_DEVICE_ID = "deviceid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";

        public static String PATH = "episodes";
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