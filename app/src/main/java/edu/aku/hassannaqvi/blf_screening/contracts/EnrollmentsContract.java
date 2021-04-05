package edu.aku.hassannaqvi.blf_screening.contracts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class EnrollmentsContract {

    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.mi_covid";

    public static abstract class enrollmentsTable implements BaseColumns {

        public static final String TABLE_NAME = "enrollments";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

        public static final String COLUMN_ID = "_id"; // Auto Increament
        public static final String COLUMN_S1Q2 = "s1q2";

        public static String PATH = "enrollments";
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