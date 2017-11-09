package com.example.thelost.fisp_beta10.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.thelost.fisp_beta10.R;
import com.example.thelost.fisp_beta10.adapter.RoomAdapter;
import com.example.thelost.fisp_beta10.bean.RoomBean;
import com.example.thelost.fisp_beta10.data.FispDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,RoomAdapter.OnViewClickListener {

    List<RoomBean> roomLists = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RoomAdapter mRoomAdapter;

    private static final String TAG = "Error";

    private static final int SELECT_PICTURE = 100;

    FispDatabase fispDatabase;

    AppCompatImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initializing recyclerview
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recyclerView);
        //initializing ImageView


        fispDatabase = new FispDatabase(this);

        fispDatabase.open();

        roomLists= getTableData();

        for(int i=0;i<10;i++) {
            fispDatabase.insertIntFispRoomText("Description", "Second Description", "Third Description", "Fourth Description", "Fifth Description", "Sixth Description", "Seventh Description", "Eighth Description", "Nineth Description");
            fispDatabase.insertIntoFispRoomImageDatabase(convertToByte(), convertToByte(), convertToByte(), convertToByte());

        }
        //initializing room adapter
        mRoomAdapter = new RoomAdapter(MainActivity.this, roomLists,fispDatabase.getTwoRemainingImage(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mRoomAdapter);

        mRoomAdapter.notifyDataSetChanged();


    }

    public byte[] convertToByte(){
        byte[] bitmapdata=null;
        Drawable d = getResources().getDrawable(getResources()
                .getIdentifier("first", "drawable", getPackageName()));
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
         bitmapdata = stream.toByteArray();

        return bitmapdata;
    }

    //function get data from the datatabase
    public List<RoomBean> getTableData() {

        try {

           List<RoomBean> roomBeen= fispDatabase.retriveTableRoomData();

            return fispDatabase.retriveTableRoomData();


        }catch (Exception ex){
            ex.printStackTrace();
            return  null;
        }

    }


    /*
    public void testingGallery(View view){
        openImageChooser();

    }
    public void getTableData() {

        FispDatabase fispDatabase = new FispDatabase(MainActivity.this);


        fispDatabase.open();

        Cursor cursor = fispDatabase.retriveTableRoomData();


        if (cursor.moveToFirst()) {
            do {
                String shortInformation = cursor.getString(cursor.getColumnIndex(FispContract.FispTableRoom.COLUMN_AVIALABLE_ROOM));
                String location = cursor.getString(cursor.getColumnIndex(FispContract.FispTableRoom.COLUMN_Location));
                String description = cursor.getString(cursor.getColumnIndex(FispContract.FispTableRoom.COLUMN_DESCRIPTION));
                String negotation = cursor.getString(cursor.getColumnIndex(FispContract.FispTableRoom.COLUMN_NEGOTIATION));
                String cost = cursor.getString(cursor.getColumnIndex(FispContract.FispTableRoom.COLUMN_COST));
                String availableRoom = cursor.getString(cursor.getColumnIndex(FispContract.FispTableRoom.COLUMN_AVIALABLE_ROOM));
                String roomType = cursor.getString(cursor.getColumnIndex(FispContract.FispTableRoom.COLUMN_ROOM_TYPE));
                String facility = cursor.getString(cursor.getColumnIndex(FispContract.FispTableRoom.COLUMN_FACILITY));
                String lookingFor = cursor.getString(cursor.getColumnIndex(FispContract.FispTableRoom.COLUMN_LOOKING_FOR));
                String columnImageFirst = cursor.getString(cursor.getColumnIndex(FispContract.FispTableRoom.COLUMN_IMAGE_FIRST));
                String imageSecond = cursor.getString(cursor.getColumnIndex(FispContract.FispTableRoom.COLUMN_IMAGE_SECOND));
                String imageThird = cursor.getString(cursor.getColumnIndex(FispContract.FispTableRoom.COLUMN_IMAGE_THIRD));
                String imageFourth = cursor.getString(cursor.getColumnIndex(FispContract.FispTableRoom.COLUMN_IMAGE_FOURTH));

                RoomBean roomBean = new RoomBean(shortInformation,
                        location,
                        description,
                        negotation,
                        cost,
                        roomType,
                        facility,
                        lookingFor,
                        columnImageFirst,
                        availableRoom,
                        imageSecond,
                        imageThird,
                        imageFourth);

                roomLists.add(roomBean);

            } while (cursor.moveToNext());
        }
        cursor.close();


    }

    // Choose an image from Gallery
    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image*//*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {

                Uri selectedImageUri = data.getData();

                if (null != selectedImageUri) {

                    // Saving to Database...
                    if (saveImageInDB(selectedImageUri)) {
                        Toast.makeText(MainActivity.this, "Saving image to database", Toast.LENGTH_LONG).show();

                        imgView.setImageURI(selectedImageUri);
                    }

                    // Reading from Database after 3 seconds just to show the message
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           *//* if (loadImageFromDB()) {
                                Toast.makeText(MainActivity.this, "Loading Image to Dababase", Toast.LENGTH_LONG).show();
                            }*//*
                        }
                    }, 3000);
                }

            }
        }
    }


    // Save the
    Boolean saveImageInDB(Uri selectedImageUri) {

        FispDatabase fispDatabase=new FispDatabase(MainActivity.this);
        try {

            fispDatabase.open();

            InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
            byte[] inputData = ImageUtils.getBytes(inputStream);

            fispDatabase.insertIntoFispRoomDatbase(
                    "",
                    "harry",
                    "wilson",
                    "princess",
                    "joe",
                    "further",
                    "personal",
                    "fame",
                    "love",
                    inputData,
                    inputData,
                    inputData,
                    inputData);

            fispDatabase.close();

        } catch (Exception ex) {

            fispDatabase.close();
            return false;

        }


         *//*   dbHelper.open();
            InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
            byte[] inputData = Utils.getBytes(iStream);
            dbHelper.insertImage(inputData);
            dbHelper.close();
            return true;
        } catch (IOException ioe) {
            Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
            dbHelper.close();
            return false;
        }*//*


       *//* Boolean loadImageFromDB(){
       *//**//* try {
            dbHelper.open();
            byte[] bytes = dbHelper.retreiveImageFromDB();
            dbHelper.close();
            // Show Image from DB in ImageView
            imgView.setImageBitmap(Utils.getImage(bytes));
            return true;
        } catch (Exception e) {
            Log.e(TAG, "<loadImageFromDB> Error : " + e.getLocalizedMessage());
            dbHelper.close();
            return false;
        }*//**//*
        }*//*

        return true;
    }

    @Override
    public void onViewClicked() {

    }*/






    @Override
    public void onClick(View v) {

        openImageChooser();

    }

    // Choose an image from Gallery
    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {

                Uri selectedImageUri = data.getData();

                if (null != selectedImageUri) {

                    // Saving to Database...
                    if (saveImageInDB(selectedImageUri)) {
                        Toast.makeText(MainActivity.this, "Image Saving", Toast.LENGTH_SHORT).show();

                    }

                    // Reading from Database after 3 seconds just to show the message
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (loadImageFromDB()) {
                                Toast.makeText(MainActivity.this, "Loading image from databsae", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, 3000);
                }

            }
        }
    }


    // Save the
    Boolean saveImageInDB(Uri selectedImageUri) {

        try {

            InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
           // fispDatabase.insertIntoFispRoomDatbase("", "", "", "", "", "", "", "", "", inputData, inputData, inputData, inputData);
            return true;
        } catch (IOException ioe) {
            Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());

            return false;
        }

    }

    Boolean loadImageFromDB() {
        try {
            byte[] blob = fispDatabase.retreiveImageFromDB();

            // Show Image from DB in ImageView

            return true;
        } catch (Exception e) {
            fispDatabase.close();
            Toast.makeText(MainActivity.this, "Error while getting image", Toast.LENGTH_LONG).show();
            Log.e(TAG, "<loadImageFromDB> Error : " + e.getLocalizedMessage());
            fispDatabase.close();
            return false;
        }
    }

    public void getImage(View view) {
        loadImageFromDB();
    }

    @Override
    public void onViewClicked() {

    }
}
