package com.example.glimmerthreads;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DHhelper extends SQLiteOpenHelper {

    /*public DHhelper(Context context) {
        super(context, "DiscountDetails.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
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
*/





    //===============================================================================


    public DHhelper(Context context) {
        super(context, "DiscountDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Discount_Table(discountID INTEGER PRIMARY KEY AUTOINCREMENT, discountTitle TEXT, itemCode TEXT, amount TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Discount_Table");
        onCreate(sqLiteDatabase);
    }


    public long addDiscount(Discount dis) {
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("discountTitle",dis.getDiscountTitle());
        cv.put("itemCode",dis.getiCode());
        cv.put("amount",dis.getAmount());

        return DB.insert("Discount_Table",null,cv);
    }





    public ArrayList<Discount> getAllDiscount(){
        ArrayList<Discount> discountList = new ArrayList<>();
        SQLiteDatabase DB = getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Discount_Table",null);
        if(cursor.moveToFirst()){
            do{
                int discountID = cursor.getInt(0);
                String discountTitle = cursor.getString(1);
                String itemCode = cursor.getString(2);
                String amount = cursor.getString(3);

                Discount discount = new Discount(discountID,discountTitle,Integer.parseInt(itemCode),Integer.parseInt(amount));
                discountList.add(discount);
            }while (cursor.moveToNext());
        }

        return discountList;
    }



    //To show in a popup/Alert
    //Get all discounts
    public Cursor getDiscount(){
        SQLiteDatabase DB = this.getWritableDatabase();

        //select a row
        //discount_code matches with the given discount code
        Cursor cursor = DB.rawQuery("select * from Discount_Table",null);

        return cursor;
    }


    public int updateDiscount(Discount d) {
        SQLiteDatabase DB = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("discountTitle",d.getDiscountTitle());
        contentValues.put("itemCode",d.getiCode());
        contentValues.put("amount",d.getAmount());
        return DB.update("Discount_Table", contentValues,"discountID = ?" ,new String[]{String.valueOf(d.getDiscountID())});
    }
}

