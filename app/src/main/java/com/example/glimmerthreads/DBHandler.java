package com.example.glimmerthreads;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class DBHandler extends SQLiteOpenHelper {

    Context context;
    private static String DATABASE_NAME = "imageDB.db";

    private static int DATABASE_VERSION = 1;
    private static String createTableQuery = "create table imageInfo (imageName TEXT" + ",image BLOB)";

    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInBytes;


    //Constructor
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    //Create table for the first time
    @Override
    public void onCreate(SQLiteDatabase DB) {
        try{
            DB.execSQL(createTableQuery);
            Toast.makeText(context, "Table created", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void storeImage(ImageModel iObj){
        try{
            SQLiteDatabase ObjSQLiteDB = this.getWritableDatabase();
            Bitmap imageToStore = iObj.getImage();

            //convert bitmap to byte
            objectByteArrayOutputStream = new ByteArrayOutputStream();
            imageToStore.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream);

            //ByteArrayOutputStream into ByteArray
            imageInBytes = objectByteArrayOutputStream.toByteArray();

            ContentValues objectContentValues = new ContentValues();
            objectContentValues.put("imageName",iObj.getImageName());
            objectContentValues.put("image",imageInBytes);

            long checkIfQueryRuns = ObjSQLiteDB.insert("imageInfo",null,objectContentValues);
            if(checkIfQueryRuns != -1){
                Toast.makeText(context, "Insert into Database", Toast.LENGTH_SHORT).show();
                ObjSQLiteDB.close();
            }else{
                Toast.makeText(context, "Fails to add data", Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
