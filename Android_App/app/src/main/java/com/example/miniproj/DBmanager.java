package com.example.miniproj;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

public class DBmanager {
    private DBhelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBmanager(Context c) {
        context = c;
    }

    public DBmanager open() throws SQLException {
        dbHelper = new DBhelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Inserting Into Student Table
    public void insert(String name, String regno, String cource, String semester,String sec) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBhelper.S_NAME, name);
        contentValue.put(DBhelper.S_REGNO, regno);
        contentValue.put(DBhelper.S_COURSE, cource);
        contentValue.put(DBhelper.S_SEMESTER, semester);
        contentValue.put(DBhelper.S_SECTION, sec);
        database.insert(DBhelper.TABLE_NAME, null, contentValue);
    }

    //Inserting Into TeacherTable
    public void insert_into_teacher(String name, String email, Integer contact, String uid, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.T_NAME, name);
        contentValues.put(DBhelper.T_EMAIL, email);
        contentValues.put(DBhelper.T_CONTACT, contact);
        contentValues.put(DBhelper.T_USERID, uid);
        contentValues.put(DBhelper.T_PASSWORD, password);
        database.insert(DBhelper.TABLE_NAME1, null, contentValues);
    }

    //Inserting into Admin
    public long insert_into_admin(String username, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.USERNAME, username);
        contentValues.put(DBhelper.PASSWORD, password);
        long i = database.insert(DBhelper.TABLE_NAME2, null, contentValues);
        return i;

    }

    // Inserting into Subject

    public long insert_into_subject(String S_name, String T_id, int code, String course, String sem,String sec) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.SUB_NAME, S_name);
        contentValues.put(DBhelper.SUB_T_ID, T_id);
        contentValues.put(DBhelper.SUB_CODE, code);
        contentValues.put(DBhelper.SUB_COURSE, course);
        contentValues.put(DBhelper.SUB_SEM, sem);
        contentValues.put(DBhelper.SUB_SECTION, sec);
        long l = database.insert(DBhelper.TABLE_NAME3, null, contentValues);
        return l;
    }

    //Total Classes of a Subject


    //Insert Into Attendence
    public long insert_into_attendence(String s_name, String s_class, String subject, String semester
            , String regno, String status, String date) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.AT_STUDENT_NAME, s_name);
        contentValues.put(DBhelper.AT_STUDENT_CLASS, s_class);
        contentValues.put(DBhelper.AT_SUBJECT, subject);
        contentValues.put(DBhelper.AT_STUDENT_SEM, semester);
        contentValues.put(DBhelper.AT_STUDENT_REGNO, regno);
        contentValues.put(DBhelper.AT_STATUS, status);
        contentValues.put(DBhelper.AT_DATE, date);
        long i = database.insert(DBhelper.TABLE_NAME4, null, contentValues);
        return i;
    }

    //INSERT INTO TOTAL ATTENDANCE
    public void insert_into_tatattendence(String id, Float attended, Float held, Float percent) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.TAT_ID, id);
        contentValues.put(DBhelper.TAT_ATTENDED, attended);
        contentValues.put(DBhelper.TAT_HELD, held);
        contentValues.put(DBhelper.TAT_PERCENT, percent);
        database.insert(DBhelper.TABLE_NAME5, null, contentValues);


    }

    public void insert_into_subwise(String name,Float attended, Float held, Float percent){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.TAT_SUBJECT, name);
        contentValues.put(DBhelper.TAT_ATTENDED_SUBJECT, attended);
        contentValues.put(DBhelper.TAT_HELD_SUBJECT, held);
        contentValues.put(DBhelper.TAT_PERCENT_SUBJECT, percent);
        database.insert(DBhelper.TABLE_NAME6, null, contentValues);
    }

    //Fetching From Students Table
    public Cursor fetch() {
        String[] columns = new String[]{DBhelper.S_ID, DBhelper.S_NAME, DBhelper.S_REGNO,
                DBhelper.S_COURSE, DBhelper.S_SEMESTER};
        Cursor cursor = database.query(DBhelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Fetching From Teacher Table
    public Cursor fetch_from_teacher() {
        String[] columns = new String[]{DBhelper.T_ID, DBhelper.T_NAME, DBhelper.T_EMAIL,
                DBhelper.T_CONTACT, DBhelper.T_USERID, DBhelper.T_PASSWORD};
        Cursor cursor = database.query(DBhelper.TABLE_NAME1, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    //get student rollnums
    public Cursor get_roll_nums(String c,String sem,String sec){
        Cursor cursor = database.rawQuery("SELECT regNo FROM " + DBhelper.TABLE_NAME +
                " WHERE course=? AND semester=?  AND section=?", new String[]{c, sem,sec});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;

    }
    // get student attended
    public Cursor get_attended(String r){
        Cursor j;

        j=database.rawQuery("SELECT sregNo FROM " + DBhelper.TABLE_NAME4 +
                " WHERE sregNo=? AND status=? ", new String[]{r,"P"});

        if (j != null) {
            j.moveToFirst();
        }
        return j;
    }

    // get student attended subject
    public Cursor get_attended_subject(String r,String s){
        Cursor j;

        j=database.rawQuery("SELECT sregNo FROM " + DBhelper.TABLE_NAME4 +
                " WHERE sregNo=? AND status=? AND subject=? ", new String[]{r,"P",s});

        if (j != null) {
            j.moveToFirst();
        }
        return j;
    }
    // get student total periods
    public Cursor get_held(String r){
        Cursor j;

        j=database.rawQuery("SELECT sregNo FROM " + DBhelper.TABLE_NAME4 +
                " WHERE sregNo=?", new String[]{r});

        if (j != null) {
            j.moveToFirst();
        }
        return j;
    }


    // get student total subject periods

    public Cursor get_held_subject(String r,String s){
        Cursor j;

        j=database.rawQuery("SELECT sregNo FROM " + DBhelper.TABLE_NAME4 +
                " WHERE sregNo=? and subject=?", new String[]{r,s});

        if (j != null) {
            j.moveToFirst();
        }
        return j;
    }
    //get YEAR SECTION AND SEM
    public Cursor get_year_sem_sec(String r){
        Cursor cursor = database.rawQuery("SELECT course , semester , section FROM " + DBhelper.TABLE_NAME +
                " WHERE regNo=? ", new String[]{r});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //get Subjects_Year_Course_Section
    public Cursor get_subjects_year(String y,String sem,String sec){
        Cursor cursor = database.rawQuery("SELECT sub_name FROM " + DBhelper.TABLE_NAME3 +
                " WHERE course=? AND semester=? AND section=?  ", new String[]{y,sem,sec});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


//Total attendance
public Cursor tat() {
    String[] col = new String[]{DBhelper.TAT_ID, DBhelper.TAT_ATTENDED,DBhelper.TAT_HELD,DBhelper.TAT_PERCENT};
    Cursor cursor = database.query(DBhelper.TABLE_NAME5, col, null, null, null, null, null);
    if (cursor != null) {
        cursor.moveToFirst();
    }
    return cursor;
}
public void  del(){
    database.execSQL("DROP TABLE IF EXISTS "+ DBhelper.TABLE_NAME5);
    database.execSQL("create table " + DBhelper.TABLE_NAME5 + "(" + DBhelper.TAT_ID
            +" STRING PRIMARY KEY, " + DBhelper.TAT_ATTENDED + " Float NOT NULL, " + DBhelper.TAT_HELD + " Float, "
            + DBhelper.TAT_PERCENT + " Float NOT NULL );");

}


    //Total attendance
    public Cursor tat_sub() {
        String[] col = new String[]{DBhelper.TAT_SUBJECT, DBhelper.TAT_ATTENDED_SUBJECT,DBhelper.TAT_HELD_SUBJECT,DBhelper.TAT_PERCENT_SUBJECT};
        Cursor cursor = database.query(DBhelper.TABLE_NAME6, col, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public void  del_sub(){
        database.execSQL("DROP TABLE IF EXISTS "+ DBhelper.TABLE_NAME6);
        database.execSQL("create table " + DBhelper.TABLE_NAME6 + "(" + DBhelper.TAT_SUBJECT
                +" STRING PRIMARY KEY, " + DBhelper.TAT_ATTENDED_SUBJECT + " Float NOT NULL, " + DBhelper.TAT_HELD_SUBJECT + " Float, "
                + DBhelper.TAT_PERCENT_SUBJECT + " Float NOT NULL );");

    }

    public void  del_sub1(){
        database.execSQL("DROP TABLE IF EXISTS "+ DBhelper.TABLE_NAME3);
        database.execSQL("create table " + DBhelper.TABLE_NAME3 + "(" + DBhelper.SUB_ID
                +" INTEGER PRIMARY KEY AUTOINCREMENT, " + DBhelper.SUB_NAME + " TEXT NOT NULL, " + DBhelper.SUB_CODE + " INTEGER, "
                + DBhelper.SUB_T_ID + " TEXT, " + DBhelper.SUB_COURSE + " TEXT NOT NULL, " + DBhelper.SUB_SEM + " TEXT," + DBhelper.SUB_SECTION +" TEXT);");
    }
//    //Fetching From Admin
//    public Cursor fetch_from_admin(){
//        String[] columns = new String[]{DatabaseHelper.A_ID,DatabaseHelper.USERNAME,DatabaseHelper.PASSWORD};
//        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME2,columns,null,null,null,null,null);
//        if(cursor!=null){
//            cursor.moveToFirst();
//        }
//        return cursor;
//    }
    //FETCHING TOTAL ATTENDANCE
    //public Cursor fetch_total_attendance(String Course, String Sem,String Section){
   //     Cursor cursor = database.rawQuery("SELECT * FROM " + DBhelper.TABLE_NAME +
   //             " WHERE course=? AND semester=?", new String[]{c, s});
   //     if (cursor != null) {
   //         cursor.moveToFirst();
    //    }
    //    return cursor;

   // }

    //Fetching from Subject
    public Cursor fetch_from_Subject() {
        String[] columns = new String[]{DBhelper.SUB_ID, DBhelper.SUB_NAME, DBhelper.SUB_CODE, DBhelper.SUB_T_ID
                , DBhelper.SUB_COURSE, DBhelper.SUB_SEM};
        Cursor cursor = database.query(DBhelper.TABLE_NAME3, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Login System
    public boolean login(String username, String password) {
        Cursor cursor = database.rawQuery("Select * FROM " + DBhelper.TABLE_NAME2 +
                " WHERE username=? AND password=?", new String[]{username, password});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                return true;
            }
        }
        return false;
    }

    //Teacher Login
    public Boolean checkusername(String username) {

        Cursor cursor = database.rawQuery("SELECT uid,password FROM " + DBhelper.TABLE_NAME1 +
                " WHERE uid=?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public boolean Teacher_login(String teacherId, String password) {
        Cursor cursor = database.rawQuery("SELECT uid,password FROM " + DBhelper.TABLE_NAME1 +
                " WHERE uid=? AND password=?", new String[]{teacherId, password});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                return true;
            }
        }
        return false;
    }

//    //Auto FIll TextView
//    public Cursor autofill_fetch(){
//        String[] columns = new String[]{DatabaseHelper.T_ID,DatabaseHelper.T_USERID};
//        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME1,columns,null,null,null,null,null);
//        if (cursor!=null){
//            cursor.moveToFirst();
//        }
//        return cursor;
//    }

    //fetch teacher subject
    public Cursor teacher_attendence_subjects() {

        String[] col = new String[]{DBhelper.SUB_ID, DBhelper.SUB_NAME};
        Cursor cursor = database.query(DBhelper.TABLE_NAME3, col, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                @SuppressLint("Range") String sub = cursor.getString(cursor.getColumnIndex(DBhelper.SUB_NAME));
            }
        }
        return cursor;
    }

    //get subjects for studentPortal
    public Cursor getSubjects() {
        String[] col = new String[]{DBhelper.SUB_ID, DBhelper.SUB_NAME};
        Cursor cursor = database.query(DBhelper.TABLE_NAME3, col, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // get Attendence List
    public Cursor get_Attendence_List(String c, String s,String sec) {

        Cursor cursor = database.rawQuery("SELECT * FROM " + DBhelper.TABLE_NAME +
                " WHERE course=? AND semester=? AND section=?", new String[]{c, s,sec});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }



    //View Attendence database

    public Cursor get_Attendece_data() {

        String[] column = new String[]{DBhelper.AT_ID, DBhelper.AT_STUDENT_NAME, DBhelper.AT_STUDENT_REGNO
                , DBhelper.AT_STUDENT_CLASS, DBhelper.AT_STUDENT_SEM, DBhelper.AT_SUBJECT,
                DBhelper.AT_DATE, DBhelper.AT_STATUS};
        Cursor cursor = database.query(DBhelper.TABLE_NAME4, column, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // View Attendence of Student

    public Cursor View_attendence_by_student(String regno, String course, String sem, String sub) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + DBhelper.TABLE_NAME4 +
                " WHERE sregno=? AND sclass=? AND subject=? AND ssem=?", new String[]{regno, course, sub, sem});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    // View Attendence of Class

    public  Cursor View_attendence_of_class(String course, String sem, String sub, String date) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + DBhelper.TABLE_NAME4 +
                " WHERE sclass=? AND subject=? AND ssem=? AND date=?", new String[]{course, sub, sem, date});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Forgot Password

    public Cursor forgot_pass(String email){

        Cursor cursor = database.rawQuery("SELECT uid,password FROM "+DBhelper.TABLE_NAME1+
                " WHERE  email=?",new String[]{email});
        if (cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public boolean register_email_exixts(String email){

        Cursor cursor = database.rawQuery("SELECT email FROM "+DBhelper.TABLE_NAME1+
                " WHERE  email=?",new String[]{email});
        if (cursor!=null){
            if (cursor.getCount() > 0) {
                return true;
            }
        }return false ;
    }

    //Update Student Details
    public int update(long _id, String name, String regno, String cource, String semester) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.S_NAME, name);
        contentValues.put(DBhelper.S_REGNO, regno);
        contentValues.put(DBhelper.S_COURSE, cource);
        contentValues.put(DBhelper.S_SEMESTER, semester);
        int i = database.update(DBhelper.TABLE_NAME, contentValues, DBhelper.S_ID + " = " + _id, null);
        return i;
    }


    //Update Teacher Details
    public int update_teacher(long _id, String name, String email, Integer contact, String uid, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.T_NAME, name);
        contentValues.put(DBhelper.T_EMAIL, email);
        contentValues.put(DBhelper.T_CONTACT, contact);
        contentValues.put(DBhelper.T_USERID, uid);
        contentValues.put(DBhelper.T_PASSWORD, password);
        int T_i = database.update(DBhelper.TABLE_NAME1, contentValues, DBhelper.T_ID + " = " + _id, null);
        return T_i;
    }

    // update Admin
    public int update_admin(long _id, String Username, String Password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.USERNAME, Username);
        contentValues.put(DBhelper.PASSWORD, Password);
        int A_i = database.update(DBhelper.TABLE_NAME2, contentValues, DBhelper.A_ID + " = " + _id, null);
        return A_i;
    }

    //Update Subject
    public int update_Subject(long _id, String S_name, String T_id, int code, String cource, String sem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.SUB_NAME, S_name);
        contentValues.put(DBhelper.SUB_T_ID, T_id);
        contentValues.put(DBhelper.SUB_CODE, code);
        contentValues.put(DBhelper.SUB_COURSE, cource);
        contentValues.put(DBhelper.SUB_SEM, sem);
        int Sub = database.update(DBhelper.TABLE_NAME3, contentValues, DBhelper.SUB_ID + " = " + _id, null);
        return Sub;
    }

    public int update_attendence(long _id, String status) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", status);
        int i = database.update(DBhelper.TABLE_NAME4, contentValues, DBhelper.AT_ID + " = " + _id, null);
        return i;
    }

    public void delete(String S_REGNO) {
        database.rawQuery("DELETE  FROM " + DBhelper.TABLE_NAME +
                " WHERE  regNo=?", new String[]{S_REGNO});
        database.rawQuery("DELETE  FROM " + DBhelper.TABLE_NAME4 +
                " WHERE  sregno=?", new String[]{S_REGNO});
    }

    public void delete_teacher(String _id) {
        database.rawQuery("DELETE  FROM " + DBhelper.TABLE_NAME1 +
                " WHERE  uid=?", new String[]{_id});
        //database.delete(DBhelper.TABLE_NAME1, DBhelper.T_USERID + "=" + _id, null);
    }

    public void delete_admin(long _id) {
        database.delete(DBhelper.TABLE_NAME2, DBhelper.A_ID + "=" + _id, null);
    }

    public void delete_subject(long _id) {
        database.delete(DBhelper.TABLE_NAME3, DBhelper.SUB_ID + "=" + _id, null);
    }

    public void delete_attendence_record(long _id) {
        database.delete(DBhelper.TABLE_NAME4, DBhelper.AT_ID + " = " + _id, null);
    }

    public int delete_attendence_Class_record(String course, String sem, String subject, String date) {

        int i =database.delete(DBhelper.TABLE_NAME4,DBhelper.AT_STUDENT_CLASS +"=? AND "+
                        DBhelper.AT_STUDENT_SEM+"=? AND "+DBhelper.AT_SUBJECT+"=? AND "+DBhelper.AT_DATE+"=?",
                new String[]{course,sem,subject,date});

        return i;
    }




}
