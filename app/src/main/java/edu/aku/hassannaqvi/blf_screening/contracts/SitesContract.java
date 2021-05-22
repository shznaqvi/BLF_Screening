package edu.aku.hassannaqvi.blf_screening.contracts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class SitesContract {

    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.blf.screening";

    public static abstract class sitesTable implements BaseColumns {

        public static final String TABLE_NAME = "sites";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

        public static final String COLUMN_ID = "id"; // Auto Increament
        public static final String COLUMN_SITENAME = "siteName";

        public static String PATH = "sites";
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