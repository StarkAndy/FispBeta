package com.example.thelost.fisp_beta10.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.thelost.fisp_beta10.bean.RoomBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Personal Pc on 10/23/2017.
 */

public class FispDatabase {

    private FispDatbabaseHelper mfispDatbabaseHelper;

    private Context mContext;

    private SQLiteDatabase sqLiteDatabase;

    private String DATABSE_NAME="fispData101";

    private int VERSION_N0=1;

    public static final String[] ALL_KEYS = new String[] {FispContract.FispTableRoom.COLUMN_SHORT_Information,FispContract.FispTableRoom.COLUMN_Location,FispContract.FispTableRoom.COLUMN_DESCRIPTION,FispContract.FispTableRoom.COLUMN_NEGOTIATION,FispContract.FispTableRoom.COLUMN_COST,FispContract.FispTableRoom.COLUMN_AVIALABLE_ROOM,FispContract.FispTableRoom.COLUMN_ROOM_TYPE,FispContract.FispTableRoom.COLUMN_FACILITY,FispContract.FispTableRoom.COLUMN_LOOKING_FOR,FispContract.FispTableRoom.COLUMN_IMAGE_FIRST,FispContract.FispTableRoom.COLUMN_IMAGE_SECOND};

    public static final String[] TWO_IMAGE_KEY=new String[]{FispContract.FispTableRoom.COLUMN_IMAGE_THIRD,FispContract.FispTableRoom.COLUMN_IMAGE_FOURTH};
    private static final String SQL_CREATE_FISP_ROOM_ENTRIES =
            "CREATE TABLE " + FispContract.FispTableRoom.TABLE_NAME + " (" +
                    FispContract.FispTableRoom._ID + " INTEGER PRIMARY KEY," +
                    FispContract.FispTableRoom.COLUMN_SHORT_Information + " TEXT," +
                    FispContract.FispTableRoom.COLUMN_Location + " TEXT," +
                    FispContract.FispTableRoom.COLUMN_DESCRIPTION + " TEXT," +
                    FispContract.FispTableRoom.COLUMN_NEGOTIATION + " TEXT," +
                    FispContract.FispTableRoom.COLUMN_COST + " TEXT," +
                    FispContract.FispTableRoom.COLUMN_AVIALABLE_ROOM + " TEXT," +
                    FispContract.FispTableRoom.COLUMN_ROOM_TYPE + " TEXT," +
                    FispContract.FispTableRoom.COLUMN_FACILITY + " TEXT," +
                    FispContract.FispTableRoom.COLUMN_LOOKING_FOR + " TEXT," +
                    FispContract.FispTableRoom.COLUMN_IMAGE_FIRST + " BLOB," +
                    FispContract.FispTableRoom.COLUMN_IMAGE_SECOND + " BLOB," +
                    FispContract.FispTableRoom.COLUMN_IMAGE_THIRD + " BLOB," +
                    FispContract.FispTableRoom.COLUMN_IMAGE_FOURTH + " BLOB);";

    private static final String SQL_CREATE_FISP_ROOM_TEXT_INFORMATION =
            "CREATE TABLE " + FispContract.FispTableRoom.TABLE_NAME + " (" +
                    FispContract.FispTableRoom._ID + " INTEGER PRIMARY KEY," +
                    FispContract.FispTableRoom.COLUMN_SHORT_Information + " TEXT," +
                    FispContract.FispTableRoom.COLUMN_Location + " TEXT," +
                    FispContract.FispTableRoom.COLUMN_DESCRIPTION + " TEXT," +
                    FispContract.FispTableRoom.COLUMN_NEGOTIATION + " TEXT," +
                    FispContract.FispTableRoom.COLUMN_COST + " TEXT," +
                    FispContract.FispTableRoom.COLUMN_AVIALABLE_ROOM + " TEXT," +
                    FispContract.FispTableRoom.COLUMN_ROOM_TYPE + " TEXT," +
                    FispContract.FispTableRoom.COLUMN_FACILITY + " TEXT," +
                    FispContract.FispTableRoom.COLUMN_LOOKING_FOR + " TEXT);";

    private static final String SQL_CREATE_FISP_ROOM_IMAGE=   "CREATE TABLE " + FispContract.FispTableRoom.TABLE_ROOM_IMAGE + " (" +
            FispContract.FispTableRoom._ID + " INTEGER PRIMARY KEY," +
            FispContract.FispTableRoom.COLUMN_IMAGE_FIRST + " BLOB," +
            FispContract.FispTableRoom.COLUMN_IMAGE_SECOND + " BLOB," +
            FispContract.FispTableRoom.COLUMN_IMAGE_THIRD + " BLOB," +
            FispContract.FispTableRoom.COLUMN_IMAGE_FOURTH + " BLOB);";

    private static final String SQL_DELETE_ENTRIES_TABLE_ROOM =
            "DROP TABLE IF EXISTS " + FispContract.FispTableRoom.TABLE_NAME;


    /*Constructor which accept context parameter
    initialize FispDatabaseHelper
     */
    public FispDatabase(Context context){
        this.mContext=context;
        mfispDatbabaseHelper=new FispDatbabaseHelper(mContext,DATABSE_NAME,null,VERSION_N0);

    }

    //Help to link sqlit datbase to
    public void open() {
        //Initializing mSqLiteDatabase
        sqLiteDatabase=mfispDatbabaseHelper.getWritableDatabase();

    }


   /* public void insertIntoFispRoomDatbase(String shortInformation,
                                          String location,
                                          String description,
                                          String negotiation,
                                          String cost,
                                          String availableRoom,
                                          String roomType,
                                          String facility,
                                          String lookingFor,
                                          String columnImageFirst,
                                          String imageSecond,
                                          String imageThird,
                                          String imageFourth) {
        SQLiteDatabase mSqLiteDatabase = mfispDatbabaseHelper.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put(FispContract.FispTableRoom.COLUMN_SHORT_Information, shortInformation);
        values.put(FispContract.FispTableRoom.COLUMN_Location,location);
        values.put(FispContract.FispTableRoom.COLUMN_DESCRIPTION,description);
        values.put(FispContract.FispTableRoom.COLUMN_NEGOTIATION,negotiation);
        values.put(FispContract.FispTableRoom.COLUMN_COST,cost);
        values.put(FispContract.FispTableRoom.COLUMN_AVIALABLE_ROOM,availableRoom);
        values.put(FispContract.FispTableRoom.COLUMN_ROOM_TYPE,roomType);
        values.put(FispContract.FispTableRoom.COLUMN_FACILITY,facility);
        values.put(FispContract.FispTableRoom.COLUMN_LOOKING_FOR,lookingFor);
        values.put(FispContract.FispTableRoom.COLUMN_IMAGE_FIRST,columnImageFirst);
        values.put(FispContract.FispTableRoom.COLUMN_IMAGE_SECOND,imageSecond);
        values.put(FispContract.FispTableRoom.COLUMN_IMAGE_THIRD,imageThird);
        values.put(FispContract.FispTableRoom.COLUMN_IMAGE_FOURTH,imageFourth);


        try {

            mSqLiteDatabase.insert(FispContract.FispTableRoom.TABLE_NAME, null, values);
            Toast.makeText(mContext,"Database Added Succesfully",Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(mContext,"Database Failure",Toast.LENGTH_LONG).show();

        }
    }*/



   /*Function insert help to insert data to the database */
    public void insertIntoFispRoomDatbase(String shortInformation,
                                          String location,
                                          String description,
                                          String negotiation,
                                          String cost,
                                          String availableRoom,
                                          String roomType,
                                          String facility,
                                          String lookingFor,
                                          byte[] columnImageFirst,
                                          byte[] imageSecond,
                                          byte[] imageThird,
                                          byte[] imageFourth){



        ContentValues values=new ContentValues();

        try {

        values.put(FispContract.FispTableRoom.COLUMN_SHORT_Information, shortInformation);
        values.put(FispContract.FispTableRoom.COLUMN_Location,location);
        values.put(FispContract.FispTableRoom.COLUMN_DESCRIPTION,description);
        values.put(FispContract.FispTableRoom.COLUMN_NEGOTIATION,negotiation);
        values.put(FispContract.FispTableRoom.COLUMN_COST,cost);
        values.put(FispContract.FispTableRoom.COLUMN_AVIALABLE_ROOM,availableRoom);
        values.put(FispContract.FispTableRoom.COLUMN_ROOM_TYPE,roomType);
        values.put(FispContract.FispTableRoom.COLUMN_FACILITY,facility);
        values.put(FispContract.FispTableRoom.COLUMN_LOOKING_FOR,lookingFor);
        values.put(FispContract.FispTableRoom.COLUMN_IMAGE_FIRST,columnImageFirst);
        values.put(FispContract.FispTableRoom.COLUMN_IMAGE_SECOND,imageSecond);
        values.put(FispContract.FispTableRoom.COLUMN_IMAGE_THIRD,imageThird);
        values.put(FispContract.FispTableRoom.COLUMN_IMAGE_FOURTH,imageFourth);


           long value= sqLiteDatabase.insert(FispContract.FispTableRoom.TABLE_NAME, null, values);


            if(value>0){
                Toast.makeText(mContext,"Data Added to databse",Toast.LENGTH_LONG).show();
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    //function insert image to first database
    public void insertIntFispRoomText(String shortInformation,
                                          String location,
                                          String description,
                                          String negotiation,
                                          String cost,
                                          String availableRoom,
                                          String roomType,
                                          String facility,
                                          String lookingFor
                                          ){



        ContentValues values=new ContentValues();

        try {

            values.put(FispContract.FispTableRoom.COLUMN_SHORT_Information, shortInformation);
            values.put(FispContract.FispTableRoom.COLUMN_Location,location);
            values.put(FispContract.FispTableRoom.COLUMN_DESCRIPTION,description);
            values.put(FispContract.FispTableRoom.COLUMN_NEGOTIATION,negotiation);
            values.put(FispContract.FispTableRoom.COLUMN_COST,cost);
            values.put(FispContract.FispTableRoom.COLUMN_AVIALABLE_ROOM,availableRoom);
            values.put(FispContract.FispTableRoom.COLUMN_ROOM_TYPE,roomType);
            values.put(FispContract.FispTableRoom.COLUMN_FACILITY,facility);
            values.put(FispContract.FispTableRoom.COLUMN_LOOKING_FOR,lookingFor);

            long value= sqLiteDatabase.insert(FispContract.FispTableRoom.TABLE_NAME, null, values);


            if(value>0){
                Toast.makeText(mContext,"Data Added to databse",Toast.LENGTH_LONG).show();
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    //Function insert immage to second imagedatbase
    public void insertIntoFispRoomImageDatabase(
                                          byte[] columnImageFirst,
                                          byte[] imageSecond,
                                          byte[] imageThird,
                                          byte[] imageFourth){



        ContentValues values=new ContentValues();

        try {
            values.put(FispContract.FispTableRoom.COLUMN_IMAGE_FIRST,columnImageFirst);
            values.put(FispContract.FispTableRoom.COLUMN_IMAGE_SECOND,imageSecond);
            values.put(FispContract.FispTableRoom.COLUMN_IMAGE_THIRD,imageThird);
            values.put(FispContract.FispTableRoom.COLUMN_IMAGE_FOURTH,imageFourth);


            long value= sqLiteDatabase.insert(FispContract.FispTableRoom.TABLE_ROOM_IMAGE, null, values);


            if(value>0){
                Toast.makeText(mContext,"Data Added to databse",Toast.LENGTH_LONG).show();
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }






    /*function help to retive data from the database*/
    public List<RoomBean> retriveTableRoomData(){


        List<RoomBean> roomInformationList=new ArrayList<>();


        Cursor tableRoomInformation=null;
        Cursor secondPartOfData;
        try {

            String where = null;
          //  firstPartOfData=  sqLiteDatabase.query(true, FispContract.FispTableRoom.TABLE_NAME, ALL_KEYS, where, null, null, null, null, null);

            String schema = "select * from table "+FispContract.FispTableRoom.TABLE_NAME;
            tableRoomInformation =sqLiteDatabase.query(
                    FispContract.FispTableRoom.TABLE_NAME,                     // The table to query
                    null,                               // The columns to return
                    null,                                // The columns for the WHERE clause
                    null,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null                                 // The sort order
            );

            int size=tableRoomInformation.getCount();
            secondPartOfData= getTwoRemainingImage();

            roomInformationList=createListFromCursor(tableRoomInformation,secondPartOfData);


        }catch (Exception ex){
            ex.printStackTrace();
        }

        return roomInformationList;

    }
    /**function retrive two remaining image from database */
    /**Reason to create seperate unable to get all image in one function */

     /*function help to retrive data from the database*/
    public byte[] retreiveImageFromDB() {

        byte[] blob=null;
        try {
            Cursor firstTillImage= sqLiteDatabase.query(true, FispContract.FispTableRoom.TABLE_NAME, new String[]{FispContract.FispTableRoom.COLUMN_IMAGE_THIRD,},
                    null, null, null, null,
                    FispContract.FispTableRoom._ID + " DESC", "2");





        }catch (Exception ex){
            ex.printStackTrace();

        }

        return blob;
    }

    public Cursor getTwoRemainingImage(){

        Cursor cur=null;

        try {

            String where = null;
           // cur=  sqLiteDatabase.query(true, FispContract.FispTableRoom.TABLE_NAME, TWO_IMAGE_KEY, where, null, null, null, null, null);
           /* cur = sqLiteDatabase.query(true, FispContract.FispTableRoom.TABLE_NAME, null,
                    null, null, null, null,


           */

            cur =sqLiteDatabase.query(
                    FispContract.FispTableRoom.TABLE_ROOM_IMAGE,                     // The table to query
                    null,                               // The columns to return
                    null,                                // The columns for the WHERE clause
                    null,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null                                 // The sort order
            );

        }catch (Exception ex){
            ex.printStackTrace();
        }


        return cur;


    }


    public List<RoomBean> createListFromCursor(Cursor first, Cursor second){

        List<RoomBean> roomDataList=new ArrayList<>();


      int a=  first.getCount();
       int b= second.getCount();
          if(first.moveToFirst() && second.moveToFirst()){

            do{


                String shortInformation=first.getString(first.getColumnIndex(FispContract.FispTableRoom.COLUMN_SHORT_Information));
                String location=first.getString(first.getColumnIndex(FispContract.FispTableRoom.COLUMN_Location));
                String description=first.getString(first.getColumnIndex(FispContract.FispTableRoom.COLUMN_DESCRIPTION));
                String negotation=first.getString(first.getColumnIndex(FispContract.FispTableRoom.COLUMN_NEGOTIATION));
                String cost=first.getString(first.getColumnIndex(FispContract.FispTableRoom.COLUMN_COST));
                String availableRoom=first.getString(first.getColumnIndex(FispContract.FispTableRoom.COLUMN_AVIALABLE_ROOM));
                String roomType=first.getString(first.getColumnIndex(FispContract.FispTableRoom.COLUMN_ROOM_TYPE));
                String facility=first.getString(first.getColumnIndex(FispContract.FispTableRoom.COLUMN_FACILITY));
                String lookingFor=first.getString(first.getColumnIndex(FispContract.FispTableRoom.COLUMN_LOOKING_FOR));
                byte[] columnImageFirst=first.getBlob(second.getColumnIndex(FispContract.FispTableRoom.COLUMN_IMAGE_FIRST));
                byte[] imageSecond=first.getBlob(second.getColumnIndex(FispContract.FispTableRoom.COLUMN_IMAGE_SECOND));
                byte[] imageThird=first.getBlob(second.getColumnIndex(FispContract.FispTableRoom.COLUMN_IMAGE_THIRD));
                byte[] imageFourth=first.getBlob(second.getColumnIndex(FispContract.FispTableRoom.COLUMN_IMAGE_FOURTH));

                RoomBean roomBean=new RoomBean(shortInformation,
                        location,
                        description,
                        negotation,
                        cost,
                        availableRoom,
                        roomType,
                        facility,
                        lookingFor,
                        columnImageFirst,
                        imageSecond,
                        imageThird,
                        imageFourth);
                roomDataList.add(roomBean);

            }while (first.moveToNext());





        }




        return roomDataList;


    }




    //CLose the database
    public void close() {
        mfispDatbabaseHelper.close();

    }

    /*
     FispdatabaseHelper class help to create table

     */

    class FispDatbabaseHelper extends SQLiteOpenHelper {

        public FispDatbabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //Creating Room table
            db.execSQL(SQL_CREATE_FISP_ROOM_TEXT_INFORMATION);
            db.execSQL(SQL_CREATE_FISP_ROOM_IMAGE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES_TABLE_ROOM);
            onCreate(db);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            super.onDowngrade(db, oldVersion, newVersion);
            onUpgrade(db,oldVersion,newVersion);
        }


    }

}
