package com.example.tfg_davidquesada;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DB_Management extends SQLiteOpenHelper{
    private static final String DB_NAME = "db_tfg";
    private static final int CURRENT_VERSION = 1;
    private String CREATE_TABLE = "";

    private String tbLoginName = "tb_login";
    private String tbLogin_userColumn = "user";
    private String tbLogin_passwordColumn = "password";

    private Context cContext;

    //Constructor de la base de datos que la creará o se conectará
    public DB_Management(Context context){
        super(context,DB_NAME,null,CURRENT_VERSION);

        cContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Sentencia para crear nuestra primera tabla
        CREATE_TABLE = "CREATE TABLE " + tbLoginName + "(" +
                tbLogin_userColumn +"TEXT," +
                tbLogin_passwordColumn + "TEXT)";

        sqLiteDatabase.execSQL(CREATE_TABLE);
        //Confirmacion de creado de tabla
        System.out.println("Tabla login creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long login_insert(String user, String password){

        SQLiteDatabase db = this.getReadableDatabase();
        long query_result = -1;

        ContentValues values = new ContentValues();

        //Valores a insertar en la tabla
        values.put(tbLogin_userColumn,user);
        values.put(tbLogin_passwordColumn, password);

        //Instruccion para insertar en la tabla, indicando los valores y el nombre de la misma
        query_result = db.insert(tbLoginName,null,values);

        db.close();

        return query_result;
    }

    public ArrayList<String> getAllData(String searchColumn,String tableName,int columnIndex){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> results = new ArrayList<>();
        String[] columns = new String[]{ searchColumn };

        //Abrimos cursor con todos los resultados de la consulta
        Cursor cursor = db.query(tableName,columns,null,null,null,null,null);

        //Si hay datos en nuestro cursor, obtenemos todos los datos de la columna y tabla indicadas
        if(cursor.moveToFirst()){
            do{
                String column = cursor.getString(columnIndex);
                results.add(column);
            }while(cursor.moveToNext());
        }

        //Devolvemos los resultados
        return results;
    }
}
