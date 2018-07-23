package com.example.dell.myapplication14.Model.Cart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBProduct extends SQLiteOpenHelper {
    public static String TB_CART = "CART";
    public static String TB_CART_ID = "PRODUCT_ID";
    public static String TB_CART_NAME = "PRODUCT_NAME";
    public static String TB_CART_PRICE = "PRICE";
    public static String TB_CART_IMAGE = "IMAGE";
    public static String TB_CART_QUANTITY = "QUANTITY";
    public static String TB_CART_INVENTORYNUMBER = "INVENTORYNUMBER";

    public DBProduct(Context context) {
        super(context, "SQLPRODUCT", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      /*  String tbCart="CREATE TABLE " + TB_CART + " (" + TB_CART_ID + " INTEGER PRIMARY KEY , "
                + TB_CART_NAME + " TEXT, " + TB_CART_PRICE + " REAL, " +TB_CART_IMAGE + "  BLOB," + TB_CART_QUANTITY
                + " INTEGER); ";*/

        String tbCart="CREATE TABLE " + TB_CART + " (" + TB_CART_ID + " INTEGER PRIMARY KEY , "
                + TB_CART_NAME + " TEXT, " + TB_CART_PRICE + " REAL, " +TB_CART_IMAGE + "  BLOB," + TB_CART_QUANTITY
                + " INTEGER, " +TB_CART_INVENTORYNUMBER + " INTEGER); ";
        sqLiteDatabase.execSQL(tbCart);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
           /* sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" +TB_CART);
            onCreate(sqLiteDatabase);*/
    }

}
