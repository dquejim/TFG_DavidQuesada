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

    //Login variables
    private String tbLoginName = "tb_login";
    private String tbLogin_userColumn = "user";
    private String tbLogin_passwordColumn = "password";
    private String tbLogin_numberColumn = "number";
    private String tbLogin_adressColumn = "adress";

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
                tbLogin_userColumn +" TEXT," +
                tbLogin_passwordColumn +" TEXT," +
                tbLogin_numberColumn +" TEXT," +
                tbLogin_adressColumn + " TEXT)";

        sqLiteDatabase.execSQL(CREATE_TABLE);
        //Confirmacion de creado de tabla
        System.out.println("Tabla login creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long login_insert(String user, String password,String number,String adress){

        SQLiteDatabase db = this.getReadableDatabase();
        long query_result = -1;

        ContentValues values = new ContentValues();

        //Valores a insertar en la tabla
        values.put(tbLogin_userColumn,user);
        values.put(tbLogin_passwordColumn, password);
        values.put(tbLogin_numberColumn,number);
        values.put(tbLogin_adressColumn, adress);

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

    public String checkUser(String myUser, String myPassword, int option){
        String result = null;
        //Nos conectamos a la base de datos
        SQLiteDatabase db = this.getReadableDatabase();
        //Indicamos las columnas que queremos obtener en nuetsra consulta
        String[] cols = new String[]{ tbLogin_userColumn,tbLogin_passwordColumn };
        //Creamos el cursor con los datos de la consulta, en este caso solo nos devolverá un registro
        //Esto se debe a que buscamos los datos del usuario por su nombre, y al ser clave primaria no habrá dos usuarios con el mismo nombre
        Cursor c = db.query(tbLoginName,cols,tbLogin_userColumn+"=?", new String[]{myUser},null,null,null);

        //Si el cursor recoge datos...
        if(c.moveToFirst()) {
            String searchedUser = c.getString(0);
            String searchedPassword = c.getString(1);

            switch (option) {
                //Para el boton login
                case 1:
                    //Si el usuario introducido coinicide con la contraseña, result nos devolverá el usuario
                    if (searchedUser.equals(myUser) && searchedPassword.equals(myPassword)) {
                        result = searchedUser;
                    }
                    break;
                //Para el boton register
                case 2:
                    //Si el usuario ya existe, nos devolvera su nombre
                    if (searchedUser.equals(myUser)) {
                        result = searchedUser;
                    }
                    break;
            }
        }
        return result;
    }

    /*
    public void deleteDB(){
        cContext.deleteDatabase(DB_NAME);
    }
    */
}
