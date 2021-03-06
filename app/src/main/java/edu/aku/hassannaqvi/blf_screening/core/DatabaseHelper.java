package edu.aku.hassannaqvi.blf_screening.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import edu.aku.hassannaqvi.blf_screening.contracts.DiseasesContract;
import edu.aku.hassannaqvi.blf_screening.contracts.EnrollmentsContract;
import edu.aku.hassannaqvi.blf_screening.contracts.EpisodesContract;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsENContract.FormsS3Table;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsSESContract;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsSFContract.FormsSFTable;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsSLContract;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsSLContract.FormsSLTable;
import edu.aku.hassannaqvi.blf_screening.contracts.FormsWFContract.FormsWFTable;
import edu.aku.hassannaqvi.blf_screening.contracts.SitesContract;
import edu.aku.hassannaqvi.blf_screening.contracts.UsersContract.UsersTable;
import edu.aku.hassannaqvi.blf_screening.contracts.VersionAppContract;
import edu.aku.hassannaqvi.blf_screening.contracts.VersionAppContract.VersionAppTable;
import edu.aku.hassannaqvi.blf_screening.contracts.WFB108Contract;
import edu.aku.hassannaqvi.blf_screening.contracts.childFollowupContract;
import edu.aku.hassannaqvi.blf_screening.models.Diseases;
import edu.aku.hassannaqvi.blf_screening.models.Enrollments;
import edu.aku.hassannaqvi.blf_screening.models.Episodes;
import edu.aku.hassannaqvi.blf_screening.models.FollowUps;
import edu.aku.hassannaqvi.blf_screening.models.FormsEN;
import edu.aku.hassannaqvi.blf_screening.models.FormsSES;
import edu.aku.hassannaqvi.blf_screening.models.FormsSF;
import edu.aku.hassannaqvi.blf_screening.models.FormsSL;
import edu.aku.hassannaqvi.blf_screening.models.FormsWF;
import edu.aku.hassannaqvi.blf_screening.models.MWFB108;
import edu.aku.hassannaqvi.blf_screening.models.Sites;
import edu.aku.hassannaqvi.blf_screening.models.Users;
import edu.aku.hassannaqvi.blf_screening.models.VersionApp;
import edu.aku.hassannaqvi.blf_screening.models.WFA303Model;
import edu.aku.hassannaqvi.blf_screening.models.WFB108;

import static edu.aku.hassannaqvi.blf_screening.utils.CreateTable.DATABASE_NAME;
import static edu.aku.hassannaqvi.blf_screening.utils.CreateTable.DATABASE_VERSION;
import static edu.aku.hassannaqvi.blf_screening.utils.CreateTable.SQL_CREATE_BL_RANDOM;
import static edu.aku.hassannaqvi.blf_screening.utils.CreateTable.SQL_CREATE_CHILD_FOLLOWUP;
import static edu.aku.hassannaqvi.blf_screening.utils.CreateTable.SQL_CREATE_DISEASES;
import static edu.aku.hassannaqvi.blf_screening.utils.CreateTable.SQL_CREATE_DISTRICTS;
import static edu.aku.hassannaqvi.blf_screening.utils.CreateTable.SQL_CREATE_EPISODES;
import static edu.aku.hassannaqvi.blf_screening.utils.CreateTable.SQL_CREATE_FORMSEN;
import static edu.aku.hassannaqvi.blf_screening.utils.CreateTable.SQL_CREATE_FORMSSF;
import static edu.aku.hassannaqvi.blf_screening.utils.CreateTable.SQL_CREATE_FORMSSL;
import static edu.aku.hassannaqvi.blf_screening.utils.CreateTable.SQL_CREATE_FORMSWF;
import static edu.aku.hassannaqvi.blf_screening.utils.CreateTable.SQL_CREATE_USERS;
import static edu.aku.hassannaqvi.blf_screening.utils.CreateTable.SQL_CREATE_VERSIONAPP;
import static edu.aku.hassannaqvi.blf_screening.utils.CreateTable.SQL_CREATE_WFB108;
import static edu.aku.hassannaqvi.blf_screening.utils.CreateTable.SQL_CREATE_SES;
import static edu.aku.hassannaqvi.blf_screening.utils.CreateTable.SQL_CREATE_ENROLLMENT;
import static edu.aku.hassannaqvi.blf_screening.utils.CreateTable.SQL_CREATE_SITES;


/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private final String TAG = "DatabaseHelper";
    private final Context mContext;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_FORMSSL);
        db.execSQL(SQL_CREATE_FORMSSF);
        db.execSQL(SQL_CREATE_FORMSWF);
        db.execSQL(SQL_CREATE_FORMSEN);
        db.execSQL(SQL_CREATE_BL_RANDOM);
        db.execSQL(SQL_CREATE_DISTRICTS);
        db.execSQL(SQL_CREATE_VERSIONAPP);
        db.execSQL(SQL_CREATE_DISEASES);
        db.execSQL(SQL_CREATE_EPISODES);
        db.execSQL(SQL_CREATE_CHILD_FOLLOWUP);
        db.execSQL(SQL_CREATE_WFB108);
        db.execSQL(SQL_CREATE_SES);
        db.execSQL(SQL_CREATE_ENROLLMENT);
        db.execSQL(SQL_CREATE_SITES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(mContext, "Upgrading Database to version " + newVersion + "...", Toast.LENGTH_SHORT).show();
        Toast.makeText(mContext, "Upgrading Database to version " + newVersion + "...", Toast.LENGTH_SHORT).show();

        switch (oldVersion) {
            case 1:
                db.execSQL(SQL_CREATE_FORMSWF);
                Toast.makeText(mContext, "Follow-up Table Created!", Toast.LENGTH_SHORT).show();
            case 2:
//                db.execSQL(SQL_ALTER_FORMS_A05CODE);
                //               db.execSQL(SQL_ALTER_FORMS_A08);
        }
    }

    public Integer syncVersionApp(JSONObject VersionList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VersionAppContract.VersionAppTable.TABLE_NAME, null, null);
        long count = 0;
        try {
            JSONObject jsonObjectCC = ((JSONArray) VersionList.get(VersionAppContract.VersionAppTable.COLUMN_VERSION_PATH)).getJSONObject(0);
            VersionApp Vc = new VersionApp();
            Vc.Sync(jsonObjectCC);

            ContentValues values = new ContentValues();

            values.put(VersionAppContract.VersionAppTable.COLUMN_PATH_NAME, Vc.getPathname());
            values.put(VersionAppContract.VersionAppTable.COLUMN_VERSION_CODE, Vc.getVersioncode());
            values.put(VersionAppContract.VersionAppTable.COLUMN_VERSION_NAME, Vc.getVersionname());

            count = db.insert(VersionAppContract.VersionAppTable.TABLE_NAME, null, values);
            if (count > 0) count = 1;

        } catch (Exception ignored) {
        } finally {
            db.close();
        }

        return (int) count;
    }

    public VersionApp getVersionApp() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                VersionAppTable._ID,
                VersionAppTable.COLUMN_VERSION_CODE,
                VersionAppTable.COLUMN_VERSION_NAME,
                VersionAppTable.COLUMN_PATH_NAME
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = null;

        VersionApp allVC = new VersionApp();
        try {
            c = db.query(
                    VersionAppTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allVC.hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allVC;
    }

    public int syncUser(JSONArray userList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UsersTable.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            for (int i = 0; i < userList.length(); i++) {

                JSONObject jsonObjectUser = userList.getJSONObject(i);

                Users user = new Users();
                user.Sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(UsersTable.COLUMN_USERNAME, user.getUserName());
                values.put(UsersTable.COLUMN_PASSWORD, user.getPassword());
                values.put(UsersTable.COLUMN_FULLNAME, user.getFullname());
                long rowID = db.insert(UsersTable.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;
            }

        } catch (Exception e) {
            Log.d(TAG, "syncUser(e): " + e);
            db.close();
        } finally {
            db.close();
        }
        return insertCount;
    }


    public boolean Login(String username, String password) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCursor = db.rawQuery("SELECT * FROM " + UsersTable.TABLE_NAME + " WHERE " + UsersTable.COLUMN_USERNAME + "=? AND " + UsersTable.COLUMN_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }

    public boolean checkUsers() throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCursor = db.rawQuery("SELECT * FROM " + UsersTable.TABLE_NAME, null, null);
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }


    public Long addFormSL(FormsSL formsl) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FormsSLContract.FormsSLTable.COLUMN_PROJECT_NAME, formsl.getProjectName());
        values.put(FormsSLTable.COLUMN_UID, formsl.get_UID());
        values.put(FormsSLTable.COLUMN_SYSDATE, formsl.getSysdate());
        //     values.put(FormsSLTable.COLUMN_ISTATUS, formsl.getIstatus());
        //    values.put(FormsSLTable.COLUMN_ISTATUS96x, formsl.getIstatus96x());
        /*values.put(FormsSLTable.COLUMN_ENDINGDATETIME, formsl.getEndingdatetime());*/
        values.put(FormsSLTable.COLUMN_GPSLAT, formsl.getGpsLat());
        values.put(FormsSLTable.COLUMN_GPSLNG, formsl.getGpsLng());
        values.put(FormsSLTable.COLUMN_GPSDATE, formsl.getGpsDT());
        values.put(FormsSLTable.COLUMN_GPSACC, formsl.getGpsAcc());
        values.put(FormsSLTable.COLUMN_DEVICETAGID, formsl.getDevicetagID());
        values.put(FormsSLTable.COLUMN_DEVICEID, formsl.getDeviceID());
        values.put(FormsSLTable.COLUMN_APPVERSION, formsl.getAppversion());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormsSLTable.TABLE_NAME,
                FormsSLTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public Long addFormSF(FormsSF formSF) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FormsSFTable.COLUMN_PROJECT_NAME, formSF.getProjectName());
        values.put(FormsSFTable.COLUMN_UID, formSF.get_UID());
        values.put(FormsSFTable.COLUMN_SYSDATE, formSF.getSysdate());
    /*    values.put(FormsSFTable.COLUMN_ISTATUS, formSF.getIstatus());
        values.put(FormsSFTable.COLUMN_ISTATUS96x, formSF.getIstatus96x());*/
//        values.put(FormsSFTable.COLUMN_ENDINGDATETIME, formSF.getEndingdatetime());
        values.put(FormsSFTable.COLUMN_GPSLAT, formSF.getGpsLat());
        values.put(FormsSFTable.COLUMN_GPSLNG, formSF.getGpsLng());
        values.put(FormsSFTable.COLUMN_GPSDATE, formSF.getGpsDT());
        values.put(FormsSFTable.COLUMN_GPSACC, formSF.getGpsAcc());
        values.put(FormsSFTable.COLUMN_DEVICETAGID, formSF.getDevicetagID());
        values.put(FormsSFTable.COLUMN_DEVICEID, formSF.getDeviceID());
        values.put(FormsSFTable.COLUMN_APPVERSION, formSF.getAppversion());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormsSFTable.TABLE_NAME,
                FormsSFTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public Long addFormS3(FormsEN formS3) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FormsS3Table.COLUMN_PROJECT_NAME, formS3.getProjectName());
        values.put(FormsS3Table.COLUMN_UID, formS3.get_UID());
        values.put(FormsS3Table.COLUMN_SYSDATE, formS3.getSysdate());
    /*    values.put( FormsS3Table.COLUMN_ISTATUS, formSF.getIstatus());
        values.put( FormsS3Table.COLUMN_ISTATUS96x, formSF.getIstatus96x());*/
//        values.put( FormsS3Table.COLUMN_ENDINGDATETIME, formSF.getEndingdatetime());
        values.put(FormsS3Table.COLUMN_GPSLAT, formS3.getGpsLat());
        values.put(FormsS3Table.COLUMN_GPSLNG, formS3.getGpsLng());
        values.put(FormsS3Table.COLUMN_GPSDATE, formS3.getGpsDT());
        values.put(FormsS3Table.COLUMN_GPSACC, formS3.getGpsAcc());
        values.put(FormsS3Table.COLUMN_DEVICETAGID, formS3.getDevicetagID());
        values.put(FormsS3Table.COLUMN_DEVICEID, formS3.getDeviceID());
        values.put(FormsS3Table.COLUMN_APPVERSION, formS3.getAppversion());
        values.put(FormsS3Table.COLUMN_EN, formS3.getSecEN());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormsS3Table.TABLE_NAME,
                FormsS3Table.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public Long addFormWF(FormsWF formWF) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FormsWFTable.COLUMN_PROJECT_NAME, formWF.getProjectName());
        values.put(FormsWFTable.COLUMN_UID, formWF.get_UID());
        values.put(FormsWFTable.COLUMN_SYSDATE, formWF.getSysdate());
    /*    values.put(FormsWFTable.COLUMN_ISTATUS, formSF.getIstatus());
        values.put(FormsWFTable.COLUMN_ISTATUS96x, formSF.getIstatus96x());*/
//        values.put(FormsWFTable.COLUMN_ENDINGDATETIME, formSF.getEndingdatetime());
        values.put(FormsWFTable.COLUMN_GPSLAT, formWF.getGpsLat());
        values.put(FormsWFTable.COLUMN_GPSLNG, formWF.getGpsLng());
        values.put(FormsWFTable.COLUMN_GPSDATE, formWF.getGpsDT());
        values.put(FormsWFTable.COLUMN_GPSACC, formWF.getGpsAcc());
        values.put(FormsWFTable.COLUMN_DEVICETAGID, formWF.getDevicetagID());
        values.put(FormsWFTable.COLUMN_DEVICEID, formWF.getDeviceID());
        values.put(FormsWFTable.COLUMN_APPVERSION, formWF.getAppversion());
        values.put(FormsWFTable.COLUMN_USERNAME, formWF.getUsername());
        values.put(FormsWFTable.COLUMN_SWFA01, formWF.getsWFA01());
        values.put(FormsWFTable.COLUMN_SWFA02, formWF.getsWFA02());
        values.put(FormsWFTable.COLUMN_SWFA03, formWF.getsWFA03());
        values.put(FormsWFTable.COLUMN_SWFA04, formWF.getsWFA04());
        values.put(FormsWFTable.COLUMN_SWFA05, formWF.getsWFA05());
        values.put(FormsWFTable.COLUMN_SWFB01, formWF.getsWFB01());
        values.put(FormsWFTable.COLUMN_SWFB02, formWF.getsWFB02());
        values.put(FormsWFTable.COLUMN_SWFC, formWF.getsWFC());
        values.put(FormsWFTable.COLUMN_SWFD, formWF.getsWFD());
        values.put(FormsWFTable.COLUMN_SWFE, formWF.getsWFE());
        values.put(FormsWFTable.COLUMN_SWFF, formWF.getsWFF());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormsWFTable.TABLE_NAME,
                FormsWFTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }


    public int updateFormSLID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsSLTable.COLUMN_UID, MainApp.formsSL.get_UID());

// Which row to update, based on the ID
        String selection = FormsSLTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.formsSL.get_ID())};

        int count = db.update(FormsSLTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateFormSFID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsSLTable.COLUMN_UID, MainApp.formsSF.get_UID());

// Which row to update, based on the ID
        String selection = FormsSLTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.formsSF.get_ID())};

        int count = db.update(FormsSLTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public Collection<FormsSL> getAllFormsSL() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsSLTable._ID,
                FormsSLTable.COLUMN_UID,
                FormsSLTable.COLUMN_SYSDATE,
/*
                FormsSLTable.COLUMN_ISTATUS,
*/
                FormsSLTable.COLUMN_SL,
                FormsSLTable.COLUMN_GPSLAT,
                FormsSLTable.COLUMN_GPSLNG,
                FormsSLTable.COLUMN_GPSDATE,
                FormsSLTable.COLUMN_GPSACC,
                FormsSLTable.COLUMN_DEVICETAGID,
                FormsSLTable.COLUMN_DEVICEID,
                FormsSLTable.COLUMN_APPVERSION,

        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsSLTable.COLUMN_ID + " ASC";

        Collection<FormsSL> allFormsSL = new ArrayList<FormsSL>();
        try {
            c = db.query(
                    FormsSLTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsSL formsSL = new FormsSL();
                allFormsSL.add(formsSL.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFormsSL;
    }

    public Collection<FormsSF> getAllFormsSF() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsSFTable._ID,
                FormsSFTable.COLUMN_UID,
                FormsSFTable.COLUMN_SYSDATE,
/*
                FormsSFTable.COLUMN_ISTATUS,
*/
                FormsSFTable.COLUMN_SF,
                FormsSFTable.COLUMN_GPSLAT,
                FormsSFTable.COLUMN_GPSLNG,
                FormsSFTable.COLUMN_GPSDATE,
                FormsSFTable.COLUMN_GPSACC,
                FormsSFTable.COLUMN_DEVICETAGID,
                FormsSFTable.COLUMN_DEVICEID,
                FormsSFTable.COLUMN_APPVERSION,

        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsSFTable.COLUMN_ID + " ASC";

        Collection<FormsSF> allFormsSF = new ArrayList<FormsSF>();
        try {
            c = db.query(
                    FormsSFTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsSF formsSF = new FormsSF();
                allFormsSF.add(formsSF.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFormsSF;
    }

    public Collection<FormsEN> getAllFormsS3() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsS3Table._ID,
                FormsS3Table.COLUMN_UID,
                FormsS3Table.COLUMN_SYSDATE,
/*
                 FormsS3Table.COLUMN_ISTATUS,
*/
                FormsS3Table.COLUMN_EN,
                FormsS3Table.COLUMN_GPSLAT,
                FormsS3Table.COLUMN_GPSLNG,
                FormsS3Table.COLUMN_GPSDATE,
                FormsS3Table.COLUMN_GPSACC,
                FormsS3Table.COLUMN_DEVICETAGID,
                FormsS3Table.COLUMN_DEVICEID,
                FormsS3Table.COLUMN_APPVERSION,

        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsS3Table.COLUMN_ID + " ASC";

        Collection<FormsEN> allFormsEN = new ArrayList<FormsEN>();
        try {
            c = db.query(
                    FormsS3Table.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsEN formsEN = new FormsEN();
                allFormsEN.add(formsEN.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFormsEN;
    }

    public Collection<FormsWF> getAllFormsWF() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsWFTable._ID,
                FormsWFTable.COLUMN_UID,
                FormsWFTable.COLUMN_SYSDATE,
/*
                FormsWFTable.COLUMN_ISTATUS,
*/
                FormsWFTable.COLUMN_SWFA01,
                FormsWFTable.COLUMN_SWFA02,
                FormsWFTable.COLUMN_SWFA03,
                FormsWFTable.COLUMN_SWFA04,
                FormsWFTable.COLUMN_SWFA05,
                FormsWFTable.COLUMN_SWFB01,
                FormsWFTable.COLUMN_SWFB02,
                FormsWFTable.COLUMN_SWFC,
                FormsWFTable.COLUMN_SWFD,
                FormsWFTable.COLUMN_SWFE,
                FormsWFTable.COLUMN_SWFF,
                FormsWFTable.COLUMN_GPSLAT,
                FormsWFTable.COLUMN_GPSLNG,
                FormsWFTable.COLUMN_GPSDATE,
                FormsWFTable.COLUMN_GPSACC,
                FormsWFTable.COLUMN_DEVICETAGID,
                FormsWFTable.COLUMN_DEVICEID,
                FormsWFTable.COLUMN_APPVERSION,

        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsWFTable.COLUMN_ID + " ASC";

        Collection<FormsWF> allFormsWF = new ArrayList<FormsWF>();
        try {
            c = db.query(
                    FormsWFTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsWF formsWF = new FormsWF();
                allFormsWF.add(formsWF.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFormsWF;
    }


    public Collection<FormsSF> checkFormsSFExist() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsSFTable._ID,
                FormsSFTable.COLUMN_UID,
                FormsSFTable.COLUMN_SYSDATE,
//                FormsSFTable.COLUMN_ISTATUS,
                FormsSFTable.COLUMN_SF,
                FormsSFTable.COLUMN_GPSLAT,
                FormsSFTable.COLUMN_GPSLNG,
                FormsSFTable.COLUMN_GPSDATE,
                FormsSFTable.COLUMN_GPSACC,
                FormsSFTable.COLUMN_DEVICETAGID,
                FormsSFTable.COLUMN_DEVICEID,
                FormsSFTable.COLUMN_APPVERSION,

        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsSFTable.COLUMN_ID + " ASC";

        Collection<FormsSF> allFormsSF = new ArrayList<FormsSF>();
        try {
            c = db.query(
                    FormsSFTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsSF formsSF = new FormsSF();
                allFormsSF.add(formsSF.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFormsSF;
    }

    public Collection<FormsEN> checkFormsS3Exist() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsS3Table._ID,
                FormsS3Table.COLUMN_UID,
                FormsS3Table.COLUMN_SYSDATE,
//                 FormsS3Table.COLUMN_ISTATUS,
                FormsS3Table.COLUMN_EN,
                FormsS3Table.COLUMN_GPSLAT,
                FormsS3Table.COLUMN_GPSLNG,
                FormsS3Table.COLUMN_GPSDATE,
                FormsS3Table.COLUMN_GPSACC,
                FormsS3Table.COLUMN_DEVICETAGID,
                FormsS3Table.COLUMN_DEVICEID,
                FormsS3Table.COLUMN_APPVERSION,

        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsS3Table.COLUMN_ID + " ASC";

        Collection<FormsEN> allFormsEN = new ArrayList<FormsEN>();
        try {
            c = db.query(
                    FormsS3Table.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsEN formsEN = new FormsEN();
                allFormsEN.add(formsEN.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFormsEN;
    }

    public Collection<FormsWF> checkFormsWFExist() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsWFTable._ID,
                FormsWFTable.COLUMN_UID,
                FormsWFTable.COLUMN_SYSDATE,
//              FormsWFTable.COLUMN_ISTATUS,
                FormsWFTable.COLUMN_SWFA01,
                FormsWFTable.COLUMN_SWFA02,
                FormsWFTable.COLUMN_SWFA03,
                FormsWFTable.COLUMN_SWFA04,
                FormsWFTable.COLUMN_SWFA05,
                FormsWFTable.COLUMN_SWFB01,
                FormsWFTable.COLUMN_SWFB02,
                FormsWFTable.COLUMN_SWFC,
                FormsWFTable.COLUMN_SWFD,
                FormsWFTable.COLUMN_SWFE,
                FormsWFTable.COLUMN_SWFF,
                FormsWFTable.COLUMN_GPSLAT,
                FormsWFTable.COLUMN_GPSLNG,
                FormsWFTable.COLUMN_GPSDATE,
                FormsWFTable.COLUMN_GPSACC,
                FormsWFTable.COLUMN_DEVICETAGID,
                FormsWFTable.COLUMN_DEVICEID,
                FormsWFTable.COLUMN_APPVERSION,

        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsWFTable.COLUMN_ID + " ASC";

        Collection<FormsWF> allFormsWF = new ArrayList<FormsWF>();
        try {
            c = db.query(
                    FormsWFTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsWF formsWF = new FormsWF();
                allFormsWF.add(formsWF.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFormsWF;
    }


    public Collection<FormsSL> getUnsyncedFormsSL() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsSLTable._ID,
                FormsSLTable.COLUMN_UID,
                FormsSLTable.COLUMN_SYSDATE,
                /*               FormsSLTable.COLUMN_ISTATUS,
                               FormsSLTable.COLUMN_ISTATUS96x,
                             FormsSLTable.COLUMN_ENDINGDATETIME,  */
                FormsSLTable.COLUMN_SL,
                FormsSLTable.COLUMN_GPSLAT,
                FormsSLTable.COLUMN_GPSLNG,
                FormsSLTable.COLUMN_GPSDATE,
                FormsSLTable.COLUMN_GPSACC,
                FormsSLTable.COLUMN_DEVICETAGID,
                FormsSLTable.COLUMN_DEVICEID,
                FormsSLTable.COLUMN_APPVERSION,
        };

        String whereClause = FormsSLTable.COLUMN_SYNCED + " is null OR " + FormsSLTable.COLUMN_SYNCED + " = '' ";
        // String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = FormsSLTable.COLUMN_ID + " ASC";

        Collection<FormsSL> allFormsSL = new ArrayList<>();
        try {
            c = db.query(
                    FormsSLTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Log.d(TAG, "getUnsyncedForms: " + c.getCount());
                FormsSL formsSL = new FormsSL();
                allFormsSL.add(formsSL.Hydrate(c));
            }
        } catch (SQLiteException e) {

            Toast.makeText(mContext, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFormsSL;
    }

    public Collection<FormsSF> getUnsyncedFormsSF() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsSFTable._ID,
                FormsSFTable.COLUMN_UID,
                FormsSFTable.COLUMN_SYSDATE,
//                FormsSFTable.COLUMN_ISTATUS,
                //               FormsSFTable.COLUMN_ISTATUS96x,
//                FormsSFTable.COLUMN_ENDINGDATETIME,
                FormsSFTable.COLUMN_SF,
                FormsSFTable.COLUMN_GPSLAT,
                FormsSFTable.COLUMN_GPSLNG,
                FormsSFTable.COLUMN_GPSDATE,
                FormsSFTable.COLUMN_GPSACC,
                FormsSFTable.COLUMN_DEVICETAGID,
                FormsSFTable.COLUMN_DEVICEID,
                FormsSFTable.COLUMN_APPVERSION,
        };

        String whereClause = FormsSFTable.COLUMN_SYNCED + " is null OR " + FormsSFTable.COLUMN_SYNCED + " = '' ";
        // String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = FormsSFTable.COLUMN_ID + " ASC";

        Collection<FormsSF> allFormsSF = new ArrayList<>();
        try {
            c = db.query(
                    FormsSFTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Log.d(TAG, "getUnsyncedForms: " + c.getCount());
                FormsSF formsSF = new FormsSF();
                allFormsSF.add(formsSF.Hydrate(c));
            }
        } catch (SQLiteException e) {

            Toast.makeText(mContext, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFormsSF;
    }

    public Collection<FormsEN> getUnsyncedFormsS3() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsS3Table._ID,
                FormsS3Table.COLUMN_UID,
                FormsS3Table.COLUMN_SYSDATE,
//                 FormsS3Table.COLUMN_ISTATUS,
                //                FormsS3Table.COLUMN_ISTATUS96x,
//                 FormsS3Table.COLUMN_ENDINGDATETIME,
                FormsS3Table.COLUMN_EN,
                FormsS3Table.COLUMN_GPSLAT,
                FormsS3Table.COLUMN_GPSLNG,
                FormsS3Table.COLUMN_GPSDATE,
                FormsS3Table.COLUMN_GPSACC,
                FormsS3Table.COLUMN_DEVICETAGID,
                FormsS3Table.COLUMN_DEVICEID,
                FormsS3Table.COLUMN_APPVERSION,
        };

        String whereClause = FormsS3Table.COLUMN_SYNCED + " is null OR " + FormsS3Table.COLUMN_SYNCED + " = '' ";
        // String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = FormsS3Table.COLUMN_ID + " ASC";

        Collection<FormsEN> allFormsEN = new ArrayList<>();
        try {
            c = db.query(
                    FormsS3Table.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Log.d(TAG, "getUnsyncedForms: " + c.getCount());
                FormsEN formsEN = new FormsEN();
                allFormsEN.add(formsEN.Hydrate(c));
            }
        } catch (SQLiteException e) {

            Toast.makeText(mContext, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFormsEN;
    }


    public Collection<FormsSL> getTodayFormsSL(String sysdate) {

        // String sysdate =  spDateT.substring(0, 8).trim()
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsSLTable._ID,
                FormsSLTable.COLUMN_UID,
                FormsSLTable.COLUMN_SYSDATE,
//                FormsSLTable.COLUMN_ISTATUS,
                FormsSLTable.COLUMN_SYNCED,

        };
        String whereClause = FormsSLTable.COLUMN_SYSDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + sysdate + " %"};
//        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsSLTable.COLUMN_ID + " ASC";

        Collection<FormsSL> allFormsSL = new ArrayList<>();
        try {
            c = db.query(
                    FormsSLTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsSL formsSL = new FormsSL();
                formsSL.set_ID(c.getString(c.getColumnIndex(FormsSLTable.COLUMN_ID)));
                formsSL.set_UID(c.getString(c.getColumnIndex(FormsSLTable.COLUMN_UID)));
                formsSL.setSysdate(c.getString(c.getColumnIndex(FormsSLTable.COLUMN_SYSDATE)));
//                formsSL.setIstatus(c.getString(c.getColumnIndex(FormsSLTable.COLUMN_ISTATUS)));
                formsSL.setSynced(c.getString(c.getColumnIndex(FormsSLTable.COLUMN_SYNCED)));
                allFormsSL.add(formsSL);
            }
        } catch (SQLiteException e) {


        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFormsSL;
    }

    public Collection<FormsSF> getTodayFormsSF(String sysdate) {

        // String sysdate =  spDateT.substring(0, 8).trim()
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsSFTable._ID,
                FormsSFTable.COLUMN_UID,
                FormsSFTable.COLUMN_SYSDATE,
//                FormsSFTable.COLUMN_ISTATUS,
                FormsSFTable.COLUMN_SYNCED,

        };
        String whereClause = FormsSFTable.COLUMN_SYSDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + sysdate + " %"};
//        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsSFTable.COLUMN_ID + " ASC";

        Collection<FormsSF> allFormsSF = new ArrayList<>();
        try {
            c = db.query(
                    FormsSFTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsSF formsSF = new FormsSF();
                formsSF.set_ID(c.getString(c.getColumnIndex(FormsSFTable.COLUMN_ID)));
                formsSF.set_UID(c.getString(c.getColumnIndex(FormsSFTable.COLUMN_UID)));
                formsSF.setSysdate(c.getString(c.getColumnIndex(FormsSFTable.COLUMN_SYSDATE)));
                //               formsSF.setIstatus(c.getString(c.getColumnIndex(FormsSFTable.COLUMN_ISTATUS)));
                formsSF.setSynced(c.getString(c.getColumnIndex(FormsSFTable.COLUMN_SYNCED)));
                allFormsSF.add(formsSF);
            }
        } catch (SQLiteException e) {

            //      db.rawQuery(SQL_ALTER_FORMS_A05CODE, null);

        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFormsSF;
    }

    public Collection<FormsEN> getTodayFormsS3(String sysdate) {

        // String sysdate =  spDateT.substring(0, 8).trim()
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsS3Table._ID,
                FormsS3Table.COLUMN_UID,
                FormsS3Table.COLUMN_SYSDATE,
//                 FormsS3Table.COLUMN_ISTATUS,
                FormsS3Table.COLUMN_SYNCED,

        };
        String whereClause = FormsS3Table.COLUMN_SYSDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + sysdate + " %"};
//        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsS3Table.COLUMN_ID + " ASC";

        Collection<FormsEN> allFormsEN = new ArrayList<>();
        try {
            c = db.query(
                    FormsS3Table.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsEN formsEN = new FormsEN();
                formsEN.set_ID(c.getString(c.getColumnIndex(FormsS3Table.COLUMN_ID)));
                formsEN.set_UID(c.getString(c.getColumnIndex(FormsS3Table.COLUMN_UID)));
                formsEN.setSysdate(c.getString(c.getColumnIndex(FormsS3Table.COLUMN_SYSDATE)));
                //               formsSF.setIstatus(c.getString(c.getColumnIndex( FormsS3Table.COLUMN_ISTATUS)));
                formsEN.setSynced(c.getString(c.getColumnIndex(FormsS3Table.COLUMN_SYNCED)));
                allFormsEN.add(formsEN);
            }
        } catch (SQLiteException e) {

            //      db.rawQuery(SQL_ALTER_FORMS_A05CODE, null);

        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFormsEN;
    }

    public Collection<FormsWF> getTodayFormsWF(String sysdate) {

        // String sysdate =  spDateT.substring(0, 8).trim()
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsWFTable._ID,
                FormsWFTable.COLUMN_UID,
                FormsWFTable.COLUMN_SYSDATE,
//                FormsWFTable.COLUMN_ISTATUS,
                FormsWFTable.COLUMN_SYNCED,

        };
        String whereClause = FormsWFTable.COLUMN_SYSDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + sysdate + " %"};
//        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsWFTable.COLUMN_ID + " ASC";

        Collection<FormsWF> allFormsWF = new ArrayList<>();
        try {
            c = db.query(
                    FormsWFTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsWF formsWF = new FormsWF();
                formsWF.set_ID(c.getString(c.getColumnIndex(FormsWFTable.COLUMN_ID)));
                formsWF.set_UID(c.getString(c.getColumnIndex(FormsWFTable.COLUMN_UID)));
                formsWF.setSysdate(c.getString(c.getColumnIndex(FormsWFTable.COLUMN_SYSDATE)));
                //               formsWF.setIstatus(c.getString(c.getColumnIndex(FormsWFTable.COLUMN_ISTATUS)));
                formsWF.setSynced(c.getString(c.getColumnIndex(FormsWFTable.COLUMN_SYNCED)));
                allFormsWF.add(formsWF);
            }
        } catch (SQLiteException e) {

            //      db.rawQuery(SQL_ALTER_FORMS_A05CODE, null);

        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFormsWF;
    }
    
    /*public Collection<FormsSL> getFormsByCluster(String cluster) {

        // String sysdate =  spDateT.substring(0, 8).trim()
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SYNCED,

        };
       // String whereClause = FormsTable.COLUMN_REFNO + " = ? ";
        String whereClause = null;
        //String[] whereArgs = new String[]{cluster};
        String[] whereArgs =null;
//        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<FormsSL> allForms = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsSL form = new FormsSL();
                form.set_ID(c.getString(c.getColumnIndex(FormsTable.COLUMN_ID)));
                form.set_UID(c.getString(c.getColumnIndex(FormsTable.COLUMN_UID)));
                form.setSysdate(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYSDATE)));
                form.setIstatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
                form.setSynced(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYNCED)));
                allForms.add(form);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }

    public ArrayList<FormsSL> getUnclosedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SYNCED,
        };
        String whereClause = FormsTable.COLUMN_ISTATUS + " = ''";
        String[] whereArgs = null;
//        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;
        String orderBy = FormsTable.COLUMN_ID + " ASC";
        ArrayList<FormsSL> allForms = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsSL form = new FormsSL();
                form.set_ID(c.getString(c.getColumnIndex(FormsTable.COLUMN_ID)));
                form.set_UID(c.getString(c.getColumnIndex(FormsTable.COLUMN_UID)));
                form.setSysdate(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYSDATE)));
                form.setIstatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
                form.setSynced(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYNCED)));
                allForms.add(form);
            }
        } catch (SQLiteException e) {


        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }*/

    public int updateEndingSL() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        /*values.put(FormsSLTable.COLUMN_ISTATUS, MainApp.formsSL.getIstatus());
        values.put(FormsSLTable.COLUMN_ISTATUS96x, MainApp.formsSL.getIstatus96x());
        values.put(FormsSLTable.COLUMN_ENDINGDATETIME, MainApp.formsSL.getEndingdatetime());*/

        // Which row to update, based on the ID
        String selection = FormsSLTable.COLUMN_ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.formsSL.get_ID())};

        return db.update(FormsSLTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public int updateEndingSF() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
/*        values.put(FormsSFTable.COLUMN_ISTATUS, MainApp.formsSF.getIstatus());
        values.put(FormsSFTable.COLUMN_ISTATUS96x, MainApp.formsSF.getIstatus96x());*/
//        values.put(FormsSFTable.COLUMN_ENDINGDATETIME, MainApp.formsSF.getEndingdatetime());

        // Which row to update, based on the ID
        String selection = FormsSFTable.COLUMN_ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.formsSF.get_ID())};

        return db.update(FormsSFTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public int updateEndingS3() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
/*        values.put(FormsSFTable.COLUMN_ISTATUS, MainApp.formsSF.getIstatus());
        values.put(FormsSFTable.COLUMN_ISTATUS96x, MainApp.formsSF.getIstatus96x());*/
//        values.put(FormsSFTable.COLUMN_ENDINGDATETIME, MainApp.formsSF.getEndingdatetime());

        // Which row to update, based on the ID
        String selection = FormsS3Table.COLUMN_ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.formsEN.get_ID())};

        return db.update(FormsS3Table.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public int updateEndingWF() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsSFTable.COLUMN_ISTATUS, MainApp.formsWF.getIstatus());
        values.put(FormsSFTable.COLUMN_ISTATUS96x, MainApp.formsWF.getIstatus96x());
        values.put(FormsSFTable.COLUMN_ENDINGDATETIME, MainApp.formsWF.getEndingdatetime());

        // Which row to update, based on the ID
        String selection = FormsWFTable.COLUMN_ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.formsWF.get_ID())};

        return db.update(FormsWFTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


  /*  //Get FormsSL already exist
    public FormsSL getFilledFormSL(String district, String refno) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS96x,
                FormsTable.COLUMN_ENDINGDATETIME,
                FormsTable.COLUMN_SF,
                FormsTable.COLUMN_SL,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_APPVERSION
        };

//        String whereClause = "(" + FormsTable.COLUMN_ISTATUS + " is null OR " + FormsTable.COLUMN_ISTATUS + "='') AND " + FormsTable.COLUMN_CLUSTERCODE + "=? AND " + FormsTable.COLUMN_HHNO + "=?";
        //String whereClause = FormsTable.COLUMN_A05 + "=? AND " + FormsTable.COLUMN_REFNO + "=?";
        //String[] whereArgs = {district, refno};
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = FormsTable._ID + " ASC";
        FormsSL allForms = null;
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allForms = new FormsSL().Hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }*/

    //Generic update FormColumn
    public int updatesFormsSLColumn(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = FormsSLTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.formsSL.get_ID())};

        return db.update(FormsSLTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    //Generic update FormColumn
    public int updatesFormsSFColumn(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = FormsSFTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.formsSF.get_ID())};

        return db.update(FormsSFTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    //Generic update FormColumn
    public int updatesFormsS3Column(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = FormsS3Table._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.formsEN.get_ID())};

        return db.update(FormsS3Table.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    //Generic update FormColumn
    public int updatesFormsWFColumn(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = FormsWFTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.formsWF.get_ID())};

        return db.update(FormsWFTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    // ANDROID DATABASE MANAGER
    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }

    //Generic Un-Synced Forms
    public void updateSyncedFormsSL(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsSLTable.COLUMN_SYNCED, true);
        values.put(FormsSLTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormsSLTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsSLTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    //Generic Un-Synced Forms
    public void updateSyncedFormsSF(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsSFTable.COLUMN_SYNCED, true);
        values.put(FormsSFTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormsSFTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsSFTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    //Generic Un-Synced Forms
    public void updateSyncedFormsEN(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsS3Table.COLUMN_SYNCED, true);
        values.put(FormsS3Table.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormsS3Table.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsS3Table.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    //Generic Un-Synced Forms
    public void updateSyncedFormsWF(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsWFTable.COLUMN_SYNCED, true);
        values.put(FormsWFTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormsWFTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsWFTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    //Generic Un-Synced Forms
    public void updateSyncedFormsDisease(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(DiseasesContract.DiseasesTable.COLUMN_SYNCED, true);
        values.put(DiseasesContract.DiseasesTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = DiseasesContract.DiseasesTable.ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                DiseasesContract.DiseasesTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    //Generic Un-Synced Forms
    public void updateSyncedFormsEpisode(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(EpisodesContract.EpisodesTable.COLUMN_SYNCED, true);
        values.put(EpisodesContract.EpisodesTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = EpisodesContract.EpisodesTable.ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                EpisodesContract.EpisodesTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public long insertDisease(String disease) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        //values.put(DiseasesContract.DiseasesTable.COLUMN_UID, MainApp.formsWF.get_ID());
        values.put(DiseasesContract.DiseasesTable.COLUMN_Q_NO, disease);
        values.put(DiseasesContract.DiseasesTable.COLUMN_UUID, MainApp.formsWF.get_UID());
        values.put(DiseasesContract.DiseasesTable.COLUMN_SYSDATE, MainApp.formsWF.getSysdate());
        values.put(DiseasesContract.DiseasesTable.COLUMN_DEVICE_ID, MainApp.formsWF.getDeviceID());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                DiseasesContract.DiseasesTable.TABLE_NAME,
                DiseasesContract.DiseasesTable.COLUMN_NAME_NULLABLE,
                values);

        if (newRowId > 0) {
            String uid = MainApp.formsWF.getDeviceID() + newRowId;
            long index = updatesFormsWFDiseases(DiseasesContract.DiseasesTable.COLUMN_UID, uid, newRowId);
            if (index < 0)
                newRowId = 0;
        }

        return newRowId;
    }

    public long insertWFB108(WFB108 wfb108, int day) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        //values.put(DiseasesContract.DiseasesTable.COLUMN_Q_NO, wfb108);
        values.put(WFB108Contract.WFB108Table.COLUMN_UUID, MainApp.formsWF.get_UID());
        values.put(WFB108Contract.WFB108Table.COLUMN_SYSDATE, MainApp.formsWF.getSysdate());
        values.put(WFB108Contract.WFB108Table.COLUMN_DEVICE_ID, MainApp.formsWF.getDeviceID());
        values.put(WFB108Contract.WFB108Table.COLUMN_WFB108_A, wfb108.getWfb1081a());
        values.put(WFB108Contract.WFB108Table.COLUMN_WFB108_A2, wfb108.getWfb1081a2());
        values.put(WFB108Contract.WFB108Table.COLUMN_WFB108_A296X, wfb108.getWfb1081a296());
        values.put(WFB108Contract.WFB108Table.COLUMN_WFB108_B, wfb108.getWfb1081b());
        values.put(WFB108Contract.WFB108Table.COLUMN_WFB108_C, wfb108.getWfb1081c());
        values.put(WFB108Contract.WFB108Table.COLUMN_WFB108_D, wfb108.getWfb1081d());
        values.put(WFB108Contract.WFB108Table.COLUMN_WFB108_D5X, wfb108.getWfb1081d5());
        values.put(WFB108Contract.WFB108Table.COLUMN_WFB108_D96X, wfb108.getWfb1081d96());
        values.put(WFB108Contract.WFB108Table.COLUMN_DAY_NO, day);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                WFB108Contract.WFB108Table.TABLE_NAME,
                WFB108Contract.WFB108Table.COLUMN_NAME_NULLABLE,
                values);

        if (newRowId > 0) {
            String uid = MainApp.formsWF.getDeviceID() + newRowId;
            long index = updateWFB108(WFB108Contract.WFB108Table.COLUMN_UID, uid, newRowId);
            if (index < 0)
                newRowId = 0;
        }

        return newRowId;
    }

    //Generic update FormColumn
    public int updateWFB108(String column, String value, long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = WFB108Contract.WFB108Table.ID + " =? ";
        String[] selectionArgs = {String.valueOf(id)};

        return db.update(WFB108Contract.WFB108Table.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    //Generic update FormColumn
    public int updatesFormsWFDiseases(String column, String value, long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = DiseasesContract.DiseasesTable.ID + " =? ";
        String[] selectionArgs = {String.valueOf(id)};

        return db.update(DiseasesContract.DiseasesTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public String getDiseaseUID(String tableName, long _id) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select uid from " + tableName + " where _id = '" + _id + "'", null);
        res.moveToFirst();
        String formUID = res.getString(res.getColumnIndex("uid"));
        return formUID;

    }

    public long insertEpisode(WFA303Model wfa303Model, String duid) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        //values.put(DiseasesContract.DiseasesTable.COLUMN_UID, MainApp.formsWF.get_ID());
        values.put(EpisodesContract.EpisodesTable.COLUMN_MINUTES, wfa303Model.getWfa30301());
        values.put(EpisodesContract.EpisodesTable.COLUMN_HOURS, wfa303Model.getWfa30302());
        values.put(EpisodesContract.EpisodesTable.COLUMN_DAYS, wfa303Model.getWfa30303());
        values.put(EpisodesContract.EpisodesTable.COLUMN_SECONDS, wfa303Model.getWfa30304());
        values.put(EpisodesContract.EpisodesTable.COLUMN_DUID, duid);
        values.put(EpisodesContract.EpisodesTable.COLUMN_UUID, MainApp.formsWF.get_UID());
        values.put(EpisodesContract.EpisodesTable.COLUMN_SYSDATE, MainApp.formsWF.getSysdate());
        values.put(EpisodesContract.EpisodesTable.COLUMN_DEVICE_ID, MainApp.formsWF.getDeviceID());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                EpisodesContract.EpisodesTable.TABLE_NAME,
                EpisodesContract.EpisodesTable.COLUMN_NAME_NULLABLE,
                values);

        if (newRowId > 0) {
            String uid = MainApp.formsWF.getDeviceID() + newRowId;
            long index = updatesFormsWFEpisode(EpisodesContract.EpisodesTable.COLUMN_UID, uid, newRowId);
            if (index < 0)
                newRowId = 0;
        }

        return newRowId;
    }

    //Generic update FormColumn
    public int updatesFormsWFEpisode(String column, String value, long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = EpisodesContract.EpisodesTable.ID + " =? ";
        String[] selectionArgs = {String.valueOf(id)};

        return db.update(EpisodesContract.EpisodesTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public int syncFollowups(JSONArray followupsList) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(childFollowupContract.childFollowupTable.TABLE_NAME, null, null);

        int insertCount = 0;

        Toast.makeText(mContext, "followups length: " + followupsList.length(), Toast.LENGTH_LONG).show();

        try {
            for (int i = 0; i < followupsList.length(); i++) {

                JSONObject jsonObjectFollowup = followupsList.getJSONObject(i);

                FollowUps followups = new FollowUps();
                followups.Sync(jsonObjectFollowup);

                /*if (checkFollowup(followups.getMrno().trim(), followups.getFupdt().trim(), followups.getFupweek().trim())) {
                    continue;
                }*/

                ContentValues values = new ContentValues();
                values.put(childFollowupContract.childFollowupTable.COLUMN_MRNO, followups.getMrno().trim());
                values.put(childFollowupContract.childFollowupTable.COLUMN_STUDYID, followups.getStudyid().trim());
                values.put(childFollowupContract.childFollowupTable.COLUMN_FUPDT, followups.getFupdt().trim());
                values.put(childFollowupContract.childFollowupTable.COLUMN_FUPWEEK, followups.getFupweek().trim());
                values.put(childFollowupContract.childFollowupTable.COLUMN_CHNAME, followups.getChName().trim());
                values.put(childFollowupContract.childFollowupTable.COLUMN_MNAME, followups.getmName().trim());
                values.put(childFollowupContract.childFollowupTable.COLUMN_MRNO_M, followups.getMrno_m().trim());
                values.put(childFollowupContract.childFollowupTable.COLUMN_S1Q501, followups.getS1q501().trim());
                values.put(childFollowupContract.childFollowupTable.COLUMN_P_FOLLOWUP_DATE, followups.getpFollowUpDate().trim());
                values.put(childFollowupContract.childFollowupTable.COLUMN_STUDY_SITE, followups.getStudySite().trim());
                values.put(childFollowupContract.childFollowupTable.COLUMN_STATUS, 0);


                long rowID = db.insert(childFollowupContract.childFollowupTable.TABLE_NAME, null, values);


                if (rowID != -1) insertCount++;
            }

        } catch (Exception e) {
            Log.d(TAG, "syncFollowups(e): " + e);
            db.close();
        } finally {
            db.close();
        }
        return insertCount;
    }

    public boolean checkFollowup (String mrno, String date, String week) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from childFollowup where sf5 = '"+ mrno +"' and curfupdt = '" + date + "' and curfupweek = '"+ week +"'", null);

        return result.getCount() > 0;
    }

    public Cursor getFollowups(String mrno) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from childFollowup where sf5 = '" + mrno + "' and status = 0 order by id asc limit 1", null);
        return result;
    }

    public Cursor getFollowup(String mrno) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from formsWf where mrno = '" + mrno + "' order by _id desc limit 1", null);
        return result;
    }

    public Collection<FormsWF> getUnsyncedFormsWF() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = null;

        String whereClause = FormsWFTable.COLUMN_SYNCED + " is null OR " + FormsWFTable.COLUMN_SYNCED + " = '' ";
        // String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = FormsWFTable.COLUMN_ID + " ASC";

        Collection<FormsWF> allFormsWF = new ArrayList<>();
        try {
            c = db.query(
                    FormsWFTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Log.d(TAG, "getUnsyncedForms: " + c.getCount());
                FormsWF formsWF = new FormsWF();
                allFormsWF.add(formsWF.Hydrate(c));
            }
        } catch (SQLiteException e) {

            Toast.makeText(mContext, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFormsWF;
    }

    public Collection<Diseases> getUnsyncedDiseasesWF() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                DiseasesContract.DiseasesTable.ID,
                DiseasesContract.DiseasesTable.COLUMN_UID,
                DiseasesContract.DiseasesTable.COLUMN_UUID,
                DiseasesContract.DiseasesTable.COLUMN_SYSDATE,
                DiseasesContract.DiseasesTable.COLUMN_Q_NO,
                DiseasesContract.DiseasesTable.COLUMN_DEVICE_ID,
                //DiseasesContract.DiseasesTable.COLUMN_MR_NO,
                //DiseasesContract.DiseasesTable.COLUMN_STUDYID,
        };

        String whereClause = DiseasesContract.DiseasesTable.COLUMN_SYNCED + " is null OR " + DiseasesContract.DiseasesTable.COLUMN_SYNCED + " = '' ";
        // String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = DiseasesContract.DiseasesTable.ID + " ASC";

        Collection<Diseases> allDiseases = new ArrayList<>();
        try {
            c = db.query(
                    DiseasesContract.DiseasesTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            c.moveToFirst();
            while (c.moveToNext()) {
                Log.d(TAG, "getUnsyncedForms: " + c.getCount());
                Diseases DiseasesWF = new Diseases();
                allDiseases.add(DiseasesWF.Hydrate(c));
            }
        } catch (SQLiteException e) {

            Toast.makeText(mContext, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allDiseases;
    }

    public Collection<Episodes> getUnsyncedEpisodesWF() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {

                EpisodesContract.EpisodesTable.ID,
                EpisodesContract.EpisodesTable.COLUMN_UID,
                EpisodesContract.EpisodesTable.COLUMN_UUID,
                EpisodesContract.EpisodesTable.COLUMN_DUID,
                EpisodesContract.EpisodesTable.COLUMN_SYSDATE,
                EpisodesContract.EpisodesTable.COLUMN_MINUTES,
                EpisodesContract.EpisodesTable.COLUMN_HOURS,
                EpisodesContract.EpisodesTable.COLUMN_DAYS,
                EpisodesContract.EpisodesTable.COLUMN_SECONDS,
                EpisodesContract.EpisodesTable.COLUMN_DEVICE_ID,
        };

        String whereClause = EpisodesContract.EpisodesTable.COLUMN_SYNCED + " is null OR " + EpisodesContract.EpisodesTable.COLUMN_SYNCED + " = '' ";
        // String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = EpisodesContract.EpisodesTable.ID + " ASC";

        Collection<Episodes> allEpisodes = new ArrayList<>();
        try {
            c = db.query(
                    EpisodesContract.EpisodesTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Log.d(TAG, "getUnsyncedForms: " + c.getCount());
                Episodes EpisodesWF = new Episodes();
                allEpisodes.add(EpisodesWF.Hydrate(c));
            }
        } catch (SQLiteException e) {

            Toast.makeText(mContext, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allEpisodes;
    }

    public void updateChildFollowup(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(childFollowupContract.childFollowupTable.COLUMN_STATUS, 1);
        String where = childFollowupContract.childFollowupTable.ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};
        int count = db.update(
                childFollowupContract.childFollowupTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }


    //Generic Un-Synced WFB108
    public void updateSyncedFormsWFB108(String id) {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(WFB108Contract.WFB108Table.COLUMN_SYNCED, true);
        values.put(WFB108Contract.WFB108Table.COLUMN_SYNCED_DATE, new Date().toString());

        String where = WFB108Contract.WFB108Table.ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                WFB108Contract.WFB108Table.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public Collection<MWFB108> getUnsyncedWFB108() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                WFB108Contract.WFB108Table.ID,
                WFB108Contract.WFB108Table.COLUMN_UID,
                WFB108Contract.WFB108Table.COLUMN_UUID,
                WFB108Contract.WFB108Table.COLUMN_SYSDATE,
                WFB108Contract.WFB108Table.COLUMN_DEVICE_ID,
                WFB108Contract.WFB108Table.COLUMN_WFB108_A,
                WFB108Contract.WFB108Table.COLUMN_WFB108_A2,
                WFB108Contract.WFB108Table.COLUMN_WFB108_A296X,
                WFB108Contract.WFB108Table.COLUMN_WFB108_B,
                WFB108Contract.WFB108Table.COLUMN_WFB108_C,
                WFB108Contract.WFB108Table.COLUMN_WFB108_D,
                WFB108Contract.WFB108Table.COLUMN_WFB108_D5X,
                WFB108Contract.WFB108Table.COLUMN_WFB108_D96X,
                WFB108Contract.WFB108Table.COLUMN_DAY_NO,
        };

        String whereClause = WFB108Contract.WFB108Table.COLUMN_SYNCED + " is null OR " + WFB108Contract.WFB108Table.COLUMN_SYNCED + " = '' ";
        // String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = WFB108Contract.WFB108Table.ID + " ASC";

        Collection<MWFB108> allWFB108 = new ArrayList<>();
        try {
            c = db.query(
                    WFB108Contract.WFB108Table.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );


            //c.moveToFirst();

            while (c.moveToNext()) {
                Log.d(TAG, "WFB108 Records: " + c.getCount());
                MWFB108 mwfb108 = new MWFB108();
                allWFB108.add(mwfb108.Hydrate(c));
            }
            // stuff
            /*do {
                Log.d(TAG, "WFB108 Records: " + c.getCount());
                MWFB108 mwfb108 = new MWFB108();
                allWFB108.add(mwfb108.Hydrate(c));

            } while (c.moveToNext());*/


        } catch (SQLiteException e) {

            Toast.makeText(mContext, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allWFB108;
    }


    ////  FORM SES
    public Long addFormSES(FormsSES formSES) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FormsSESContract.FormsSESTable.COLUMN_PROJECT_NAME, formSES.getProjectName());
        values.put(FormsSESContract.FormsSESTable.COLUMN_UID, formSES.get_UID());
        values.put(FormsSESContract.FormsSESTable.COLUMN_SYSDATE, formSES.getSysdate());
        values.put(FormsSESContract.FormsSESTable.COLUMN_DEVICETAGID, formSES.getDevicetagID());
        values.put(FormsSESContract.FormsSESTable.COLUMN_DEVICEID, formSES.getDeviceID());
        values.put(FormsSESContract.FormsSESTable.COLUMN_APPVERSION, formSES.getAppversion());
        values.put(FormsSESContract.FormsSESTable.COLUMN_USERNAME, formSES.getUsername());
        values.put(FormsSESContract.FormsSESTable.COLUMN_S1, formSES.getS1());
        values.put(FormsSESContract.FormsSESTable.COLUMN_S2, formSES.getS2());
        values.put(FormsSESContract.FormsSESTable.COLUMN_S3, formSES.getS3());
        values.put(FormsSESContract.FormsSESTable.COLUMN_S4, formSES.getS4());

        long newRowId;
        newRowId = db.insert(
                FormsSESContract.FormsSESTable.TABLE_NAME,
                FormsSESContract.FormsSESTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    //Generic update FormColumn
    public int updatesFormsSES(String column, String value) {

        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = FormsSESContract.FormsSESTable.COLUMN_ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.formsSES.get_ID())};

        return db.update(FormsSESContract.FormsSESTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    //Generic Un-Synced Form SES
    public void updateSyncedFormsSES(String id) {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(FormsSESContract.FormsSESTable.COLUMN_SYNCED, true);
        values.put(FormsSESContract.FormsSESTable.COLUMN_SYNCED_DATE, new Date().toString());

        String where = FormsSESContract.FormsSESTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsSESContract.FormsSESTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public Collection<FormsSES> getUnsyncedFormsSES() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = null;

        String whereClause = FormsSESContract.FormsSESTable.COLUMN_SYNCED + " is null OR " + FormsSESContract.FormsSESTable.COLUMN_SYNCED + " = ''";
        // String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = FormsSESContract.FormsSESTable.COLUMN_ID + " ASC";

        Collection<FormsSES> allFromsSES = new ArrayList<>();
        try {
            c = db.query(
                    FormsSESContract.FormsSESTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                Log.d(TAG, "getUnsyncedFormsSES: " + c.getCount());
                FormsSES FromsSES = new FormsSES();
                allFromsSES.add(FromsSES.Hydrate(c));
                c.moveToNext();
            }
        } catch (SQLiteException e) {

            Toast.makeText(mContext, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFromsSES;
    }

    public int updateEndingSES() {

        SQLiteDatabase db = this.getReadableDatabase();
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsSESContract.FormsSESTable.COLUMN_ISTATUS, MainApp.formsSES.getIstatus());
        values.put(FormsSESContract.FormsSESTable.COLUMN_ISTATUS96x, MainApp.formsSES.getIstatus96x());
        values.put(FormsSESContract.FormsSESTable.COLUMN_ENDINGDATETIME, MainApp.formsSES.getEndingdatetime());

        // Which row to update, based on the ID
        String selection = FormsSESContract.FormsSESTable.COLUMN_ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.formsSES.get_ID())};

        return db.update(FormsSESContract.FormsSESTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public Cursor checkMRNo(String mrno) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from enrollments where s1q2 = '" + mrno + "' order by _id asc limit 1", null);
        return result;
    }

    public int syncEnrollments(JSONArray enrollmentsList) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EnrollmentsContract.enrollmentsTable.TABLE_NAME, null, null);

        int insertCount = 0;

        Toast.makeText(mContext, "Enrollments length: " + enrollmentsList.length(), Toast.LENGTH_LONG).show();

        if (enrollmentsList.length() > 0) {

            try {
                for (int i = 0; i < enrollmentsList.length(); i++) {

                    JSONObject jsonObjectEnrollments = enrollmentsList.getJSONObject(i);

                    Enrollments enrollments = new Enrollments();
                    enrollments.Sync(jsonObjectEnrollments);

                    ContentValues values = new ContentValues();
                    values.put(EnrollmentsContract.enrollmentsTable.COLUMN_S1Q2, enrollments.getMrno().trim());

                    long rowID = db.insert(EnrollmentsContract.enrollmentsTable.TABLE_NAME, null, values);

                    if (rowID != -1) insertCount++;
                }

            } catch (Exception e) {
                Log.d(TAG, "syncEnrollments(e): " + e);
                db.close();
            } finally {
                db.close();
            }
        }
        return insertCount;
    }

    public int syncSites(JSONArray sitesList) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SitesContract.sitesTable.TABLE_NAME, null, null);

        int insertCount = 0;

        Toast.makeText(mContext, "Sites length: " + sitesList.length(), Toast.LENGTH_LONG).show();

        if (sitesList.length() > 0) {

            try {
                for (int i = 0; i < sitesList.length(); i++) {

                    JSONObject jsonObjectSites = sitesList.getJSONObject(i);

                    Sites sites = new Sites();
                    sites.Sync(jsonObjectSites);

                    ContentValues values = new ContentValues();
                    values.put(SitesContract.sitesTable.COLUMN_SITENAME, sites.getSiteName().trim());

                    long rowID = db.insert(SitesContract.sitesTable.TABLE_NAME, null, values);

                    if (rowID != -1) insertCount++;
                }

            } catch (Exception e) {
                Log.d(TAG, "syncSites(e): " + e);
                db.close();
            } finally {
                db.close();
            }
        }
        return insertCount;
    }

    public Collection<Sites> getSites() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                SitesContract.sitesTable.COLUMN_SITENAME
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = SitesContract.sitesTable.COLUMN_ID + " ASC";

        Collection<Sites> allsites = new ArrayList<>();
        try {
            c = db.query(
                    SitesContract.sitesTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allsites.add(new Sites().Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allsites;
    }
}