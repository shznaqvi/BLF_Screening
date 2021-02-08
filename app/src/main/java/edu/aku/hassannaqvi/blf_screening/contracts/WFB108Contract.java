package edu.aku.hassannaqvi.blf_screening.contracts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class WFB108Contract {

    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.mi_covid";

    public static abstract class WFB108Table implements BaseColumns {

        public static final String TABLE_NAME = "wfb108";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

        public static final String ID                 = "_id";
        public static final String COLUMN_UID         = "uid";
        public static final String COLUMN_UUID        = "uuid";
        public static final String COLUMN_SYSDATE     = "sysdate";
        public static final String COLUMN_DEVICE_ID   = "deviceid";
        public static final String COLUMN_SYNCED      = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_DAY_NO      = "dayNo";
        public static final String COLUMN_WFB108_A    = "wfb108a";
        public static final String COLUMN_WFB108_B    = "wfb108b";
        public static final String COLUMN_WFB108_C    = "wfb108c";
        public static final String COLUMN_WFB108_D    = "wfb108d";
        public static final String COLUMN_WFB108_D5X  = "wfb108d5x";
        public static final String COLUMN_WFB108_D96X = "wfb108d96x";

        public static String PATH = "wfb108";
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