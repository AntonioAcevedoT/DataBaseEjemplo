package com.example.databaseexample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * DELL
 * 18/02/2020
 * DataBaseExample
 */
public class DataManager {
    //Referencia a la BD
    private SQLiteDatabase db;

    //referencia a cada uno de las clases de la tabla a tratar
    /*las hacemos publicas para     */
    public static final String TABLE_ROW_ID="_id";
    public static final String TABLE_ROW_NAME="nombre";
    public static final String TABLE_ROW_AGE="edad";

    /*Referencia a cada tabla de la base de datos
    Las hacemos privadas porque nadie salvo el DM de la estructura de la base de datos */
    private static final String  DB_NAME="DireccionLibroDB";
    private static final Integer DB_VERSION=1;
    private static final String  TABLE_NAMES_AND_ADDRESS="NamesAndAddresses";

    public DataManager(Context context) {
        MyCustomSQLiteHelper helper= new MyCustomSQLiteHelper(context);
        db=helper.getWritableDatabase();
    }

    //las operaciones con la bd se implementa como metodos de nuestro Data Manager
    //Insertar un nuevo usuario

    public void insertar(String name,String age){
        //INSERT INTO NamesAndAddresses (name,age) VALUES('name','age');
        String query="INSERT INTO "+
                TABLE_NAMES_AND_ADDRESS+" ("+
                TABLE_ROW_NAME + ", "+
                TABLE_ROW_AGE + ") "+
                " VALUES ("+
                    "'" + name+"'"+ ", "+
                    "'"+age+"'"+
                ")";
        Log.i("insert() = ",query);
        db.execSQL(query);
    }
    //borrar un suario existente
    public void eliminar(String name)
    {
        //DELETE FROM NamesAndAddresses WHERE name = 'name';
        String query="DELETE FROM "+
                TABLE_NAMES_AND_ADDRESS +
                "WHERE "+ TABLE_ROW_NAME+
                " = '"+name+"';";
        Log.i("delete() = ",query);
        db.execSQL(query);
    }
    //obtener un usuario concreto
    public Cursor buscar(String name){
        String query= "SELECT "+
                TABLE_ROW_ID+", "+
                TABLE_ROW_NAME+", "+
                TABLE_ROW_AGE+
                " FROM " +
                TABLE_NAMES_AND_ADDRESS +
                " WHERE " +
                TABLE_ROW_NAME + " = '" +name+"';";
        Log.i("search() =",query);
        Cursor c = db.rawQuery(query,null);
        return c;
    }
    //obtener todos los usuarios de la base de datos
    public Cursor selectAll(){
        String query = "SELECT * FROM "+ TABLE_NAMES_AND_ADDRESS;
        Log.i("selectAll() = ",query);
        Cursor c= db.rawQuery(query,null);
        return  c;
    }
    /*
    Clase interna para gestionar nuestro propio helper de conexion a la base de datos de la app
     */
    private class  MyCustomSQLiteHelper extends SQLiteOpenHelper{

        public MyCustomSQLiteHelper( Context context ) {
            super(context, DB_NAME, null, DB_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
