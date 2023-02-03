package com.example.miniproj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    //EXTRA VARIABLES
    public static final String AT_CLS        = "attended";
    public static final String AT_HELD          = "held";
    public static final String AT_PERCENT          = "percent";




    // Table Name
    public static final String TABLE_NAME  = "STUDENTS";
    public static final String TABLE_NAME1 = "TEACHERS";
    public static final String TABLE_NAME2 = "ADMIN";
    public static final String TABLE_NAME3 = "SUBJECT";
    public static final String TABLE_NAME4 = "ATTENDENCE";
    public static final String TABLE_NAME5 = "TOTALATTENDENCE";
    public static final String TABLE_NAME6 = "SUBJECTWISEATT";



    // Student Table columns

    public static final String S_ID       = "_id";
    public static final String S_NAME     = "name";
    public static final String S_REGNO    = "regNo";
    public static final String S_COURSE   = "course";
    public static final String S_SEMESTER = "semester";
    public static final String S_SECTION  =  "section";

    // Teacher Table columns
    public static final String T_ID       = "_id";
    public static final String T_NAME     = "name";
    public static final String T_EMAIL    = "email";
    public static final String T_CONTACT  = "contactNo";
    public static final String T_USERID   = "uid";
    public static final String T_PASSWORD = "password";

    // Admin Table Columns
    public static final String A_ID     = "_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    //Subject Table Columns
    public static final String SUB_ID     = "_id";
    public static final String SUB_NAME   = "sub_name";
    public static final String SUB_T_ID   = "t_id";
    public static final String SUB_CODE   = "code";
    public static final String SUB_COURSE = "course";
    public static final String SUB_SEM    = "semester";
    public static final String SUB_SECTION    = "section";
    public static final String SUB_TCLASSES    = "tclasses";



    //Attendence Table Columns

    public static final String AT_ID            = "_id";
    public static final String AT_STUDENT_NAME  = "sname";
    public static final String AT_STUDENT_CLASS = "sclass";
    public static final String AT_STUDENT_SECTION    = "section";
    public static final String AT_SUBJECT       = "subject";
    public static final String AT_STUDENT_SEM   = "ssem";
    public static final String AT_STUDENT_REGNO = "sregno";
    public static final String AT_STATUS        = "status";
    public static final String AT_DATE          = "date";


    // TOTAL Attendance Table Columns
    public static final String TAT_ID            = "_id";
    public static final String TAT_ATTENDED  = "sname";
    public static final String TAT_HELD = "sclass";
    public static final String TAT_PERCENT    = "section";

    // TOTAL Attendance Table Columns
    public static final String TAT_SUBJECT           = "_id";
    public static final String TAT_ATTENDED_SUBJECT  = "sname";
    public static final String TAT_HELD_SUBJECT = "sclass";
    public static final String TAT_PERCENT_SUBJECT    = "section";

    // Database Information
    static final String DB_NAME = "NewAttendance1.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query

    //STUDENT
    private  String CREATE_TABLE = "create table " + TABLE_NAME + "(" + S_ID
            + " INTEGER, " + S_NAME + " TEXT, "
            + S_REGNO +" TEXT PRIMARY KEY, " + S_COURSE + " TEXT, "+ S_SEMESTER +" TEXT, " + S_SECTION +" TEXT NOT NULL );";
    //TEACHER
    private static final String CREATE_TABLE1 = "create table " + TABLE_NAME1 + "(" + T_ID
            +" INTEGER PRIMARY KEY AUTOINCREMENT, " + T_NAME + " TEXT NOT NULL, " + T_EMAIL + " TEXT, "
            + T_CONTACT + " INTEGER, " + T_USERID + " TEXT NOT NULL, " + T_PASSWORD + " TEXT NOT NULL);";

    //ADMIN
    private static final  String CREATE_TABLE2 = "create table " + TABLE_NAME2 + "(" + A_ID
            +" INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT, " + PASSWORD + " TEXT );";

    //SUBJECT
    private static final String CREATE_TABLE3 = "create table " + TABLE_NAME3 + "(" + SUB_ID
            +" INTEGER PRIMARY KEY AUTOINCREMENT, " + SUB_NAME + " TEXT NOT NULL, " + SUB_CODE + " INTEGER, "
            + SUB_T_ID + " TEXT, " + SUB_COURSE + " TEXT NOT NULL, " + SUB_SEM + " TEXT," + SUB_SECTION +" TEXT);";


    //ATTENDENCE
    private static final String CREATE_TABLE4 ="create table "+ TABLE_NAME4 + "(" + AT_ID
            +" INTEGER PRIMARY KEY AUTOINCREMENT, " + AT_STUDENT_NAME + " TEXT NOT NULL, " + AT_STUDENT_CLASS  + " TEXT, "+ AT_SUBJECT
            +" TEXT, " +AT_STUDENT_SEM + " TEXT, " + AT_STUDENT_REGNO + " TEXT, " + AT_STATUS + " TEXT NOT NULL, " + AT_DATE+
            " TEXT," + AT_STUDENT_SECTION +"TEXT );";

    //TOTAL ATTENDANCE
    private static final String CREATE_TABLE5 = "create table " + TABLE_NAME5 + "(" + TAT_ID
            +" STRING PRIMARY KEY, " + TAT_ATTENDED + " Float NOT NULL, " + TAT_HELD + " Float, "
            + TAT_PERCENT + " Float NOT NULL );";

    //TOTAL ATTENDANCE SUBJECT WISE
    private static final String CREATE_TABLE6 = "create table " + TABLE_NAME6 + "(" + TAT_SUBJECT
            +" STRING PRIMARY KEY, " + TAT_ATTENDED_SUBJECT + " Float NOT NULL, " + TAT_HELD_SUBJECT + " Float, "
            + TAT_PERCENT_SUBJECT + " Float NOT NULL );";

    public static String currentDBPath = "";
    public DBhelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        currentDBPath = context.getDatabasePath(DB_NAME).getPath();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE1);
        db.execSQL(CREATE_TABLE2);
        db.execSQL(CREATE_TABLE3);
        db.execSQL(CREATE_TABLE4);
        db.execSQL(CREATE_TABLE5);
        db.execSQL(CREATE_TABLE6);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME4);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME5);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME6);

        onCreate(db);

    }
}
