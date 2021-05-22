package edu.aku.hassannaqvi.blf_screening.contracts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class childFollowupContract {

    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.mi_covid";

    public static abstract class childFollowupTable implements BaseColumns {

        public static final String TABLE_NAME = "childFollowup";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

        public static final String ID = "id"; // Auto Increament
        public static final String COLUMN_STUDYID = "sf20";
        public static final String COLUMN_MRNO = "sf5";
        public static final String COLUMN_FUPDT = "curfupdt";
        public static final String COLUMN_FUPWEEK = "curfupweek";
        public static final String COLUMN_CHNAME = "s1q3";
        public static final String COLUMN_MNAME = "sl5";
        public static final String COLUMN_MRNO_M = "sl4";
        public static final String COLUMN_FUPDONEDT = "fupdonedt";
        public static final String COLUMN_S1Q501 = "sf6a";
        public static final String COLUMN_P_FOLLOWUP_DATE = "pFollowUpDate";
        public static final String COLUMN_STUDY_SITE = "studySite";
        public static final String COLUMN_STATUS = "status";

        public static String PATH = "childFollowup";
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