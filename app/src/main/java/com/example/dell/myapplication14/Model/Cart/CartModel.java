package com.example.dell.myapplication14.Model.Cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.myapplication14.Model.Object.Product;


import java.util.ArrayList;
import java.util.List;

public class CartModel {
    SQLiteDatabase sqLiteDatabase;

    public void opnenConnectSQL(Context context) {
        DBProduct dbProduct = new DBProduct(context);
        sqLiteDatabase = dbProduct.getWritableDatabase();
    }

    public boolean addCart(Product product) {
        ContentValues value = new ContentValues();
        value.put(DBProduct.TB_CART_ID, product.getProduct_id());
        value.put(DBProduct.TB_CART_NAME, product.getProduct_name());
        value.put(DBProduct.TB_CART_PRICE, product.getPrice());
        value.put(DBProduct.TB_CART_IMAGE, product.getImage());
        value.put(DBProduct.TB_CART_QUANTITY, product.getQuantity());
        value.put(DBProduct.TB_CART_INVENTORYNUMBER,product.getINVENTORYNUMBER());
        long id = sqLiteDatabase.insert(DBProduct.TB_CART, null, value);
        if (id > 0) {
            return true;
        } else {

            return false;
        }
    }
    public boolean updateCart(int product_id,int quarity)
    {
        ContentValues values=new ContentValues();
        values.put(DBProduct.TB_CART_QUANTITY,quarity);
        int check=sqLiteDatabase.update(DBProduct.TB_CART,values,DBProduct.TB_CART_ID + " = " + product_id,null);
        if(check>0)
        {
            return true;

        }else{
            return false;
        }
    }
    public boolean deleteCart(int product_id) {
        int check = sqLiteDatabase.delete(DBProduct.TB_CART, DBProduct.TB_CART_ID + " = " + product_id, null);
        if (check > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Product> getListProductFromCart() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM " + DBProduct.TB_CART;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int product_id = cursor.getInt(cursor.getColumnIndex(DBProduct.TB_CART_ID));
            String name = cursor.getString(cursor.getColumnIndex(DBProduct.TB_CART_NAME));
            int price = cursor.getInt(cursor.getColumnIndex(DBProduct.TB_CART_PRICE));
            byte[] image = cursor.getBlob(cursor.getColumnIndex(DBProduct.TB_CART_IMAGE));
            int quarity = cursor.getInt(cursor.getColumnIndex(DBProduct.TB_CART_QUANTITY));
            int INVENTORYNUMBER=cursor.getInt(cursor.getColumnIndex(DBProduct.TB_CART_INVENTORYNUMBER));
            Product product = new Product();
            product.setProduct_id(product_id);
            product.setProduct_name(name);
            product.setPrice(price);
            product.setImage(image);
            product.setQuantity(quarity);
            product.setINVENTORYNUMBER(INVENTORYNUMBER);
            cursor.moveToNext();
            productList.add(product);
        }
        return productList;
    }
}
