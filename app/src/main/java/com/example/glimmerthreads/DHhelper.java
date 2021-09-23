package com.example.glimmerthreads;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DHhelper extends SQLiteOpenHelper {
    public DHhelper(Context context) {
        super(context, "DiscountDetails.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        /*DB.execSQL("create Table DiscountDetails(Discount_Code NUMBER primary key autoincrement,item_code NUMBER, discount TEXT,name TEXT)"); */  //-------
        DB.execSQL("create Table DiscountDetails(Discount_Code NUMBER primary key ,discount TEXT,name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists DiscountDetails");
    }


    public Boolean insertData(String name,int discount_code,int discount){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("discount",discount);
        contentValues.put("Discount_Code",discount_code);
        /*contentValues.put("item_code",discount_code);*/   //-------
        long result = DB.insert("DiscountDetails",null,contentValues);
        if(result == -1){
            return false;
        } else{
            return true;
        }
    }


    public Boolean updateData(String name,int discount_code,int discount){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("discount",discount);
//        contentValues.put("item_code",discount_code);   //-------
        contentValues.put("Discount_Code",discount_code);

        //select a row
        //discount_code matches with the given discount code
        Cursor cursor = DB.rawQuery("select * from DiscountDetails where Discount_Code = ?", new String[] {String.valueOf(discount_code)});

        if(cursor.getCount() > 0) {
            long result = DB.update("DiscountDetails", contentValues, "Discount_Code=?", new String[]{String.valueOf(discount_code)});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }



    public Boolean deleteDiscount(int discount_code){
        SQLiteDatabase DB = this.getWritableDatabase();

        //select a row
        //discount_code matches with the given discount code
        Cursor cursor = DB.rawQuery("select * from DiscountDetails where Discount_Code = ?", new String[] {String.valueOf(discount_code)});

        if(cursor.getCount() > 0) {
            long result = DB.delete("DiscountDetails", "Discount_Code=?", new String[]{String.valueOf(discount_code)});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }



    //Get all discounts
    public Cursor getDiscount(){
        SQLiteDatabase DB = this.getWritableDatabase();

        //select a row
        //discount_code matches with the given discount code
        Cursor cursor = DB.rawQuery("select * from DiscountDetails",null);

        return cursor;
    }

    //Get a discount
    public Cursor getADiscout(int discountCOde){
        SQLiteDatabase DB = this.getWritableDatabase();

        //select a row
        //discount_code matches with the given discount code

        Cursor cursor = DB.rawQuery("select * from DiscountDetails where discount_code =" +discountCOde,null);
        cursor.moveToFirst();
        return cursor;
    }




}

