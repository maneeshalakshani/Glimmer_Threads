package com.example.glimmerthreads;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

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



    //Get all Images
    public ArrayList<ImageModel> getAllImagesData(){
        try {
            SQLiteDatabase DB = this.getReadableDatabase(); //Because going to read data
            ArrayList<ImageModel> objectImageModelClassList = new ArrayList<>();

            //Get values back
            Cursor cursorObject = DB.rawQuery("select * from imageInfo", null);
            if(cursorObject.getCount() != 0){
                while (cursorObject.moveToNext()){
                    String nameOfImage = cursorObject.getString(0);
                    byte[] imageBytes = cursorObject.getBlob(1);

                    //Convert bytes into Bitmap
                    Bitmap bitmapObject = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);

                    //Add this into ImageModel class
                    objectImageModelClassList.add(new ImageModel(nameOfImage,bitmapObject));
                }
                return objectImageModelClassList;
            }else{
                Toast.makeText(context, "No values in Database", Toast.LENGTH_SHORT).show();
                return null;
            }

        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }

}
