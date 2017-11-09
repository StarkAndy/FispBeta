package com.example.thelost.fisp_beta10.data;

import android.provider.BaseColumns;

/**
 * Created by Personal Pc on 10/23/2017.
 */

public class FispContract {


    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FispContract() {}

    /* Inner class that defines the table contents */
    public static class FispTableRoom implements BaseColumns {
        public static final String TABLE_NAME = "Room";
        public static final String TABLE_ROOM_IMAGE="imageRoom";
        public static final String COLUMN_SHORT_Information = "shortinformation";
        public static final String COLUMN_Location = "location";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_NEGOTIATION = "negotation";
        public static final String COLUMN_COST= "cost";
        public static final String COLUMN_AVIALABLE_ROOM= "availableroom";
        public static final String COLUMN_ROOM_TYPE= "roomtype";
        public static final String COLUMN_FACILITY= "facility";
        public static final String COLUMN_LOOKING_FOR= "lookingfor";
        public static final String COLUMN_IMAGE_FIRST="imagefirst";
        public static final String COLUMN_IMAGE_SECOND="imagesecond";
        public static final String COLUMN_IMAGE_THIRD="imagethird";
        public static final String COLUMN_IMAGE_FOURTH="imagefourth";
    }

}
