package com.example.prueba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Constantes para la base de datos
    public static final String DATABASE_NAME = "prodbd.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "productos";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SKU = "sku";
    public static final String COLUMN_PROD = "prod";

    // Constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Crear la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SKU + " INTEGER, " +
                COLUMN_PROD + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    // Actualizar la base de datos si hay cambios en la versión
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //  Verificar SKU
    public boolean verificarSkuExistente(int sku) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_SKU + " = ?", new String[]{String.valueOf(sku)}, null, null, null);
        boolean existe = cursor.moveToFirst();
        cursor.close();
        return existe;
    }

    //  insertarProducto
    public void insertarProducto(int sku, String prod) {
        // Verificamos si el SKU ya existe
        if (verificarSkuExistente(sku)) {
            return;  // No insertamos el producto si el SKU ya existe
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SKU, sku);
        values.put(COLUMN_PROD, prod);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Editar un producto por SKU
    public void editarProductoPorSku(int sku, String nuevoProducto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PROD, nuevoProducto);
        db.update(TABLE_NAME, values, COLUMN_SKU + " = ?", new String[]{String.valueOf(sku)});
        db.close();
    }

    // Eliminar un producto por SKU
    public void eliminarProductoPorSku(int sku) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_SKU + " = ?", new String[]{String.valueOf(sku)});
        db.close();
    }

    // Obtener todos los productos
    public Cursor obtenerProductos() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // Obtener productos ordenados de Z a A por el nombre
    public Cursor obtenerProductosOrdenadosZA() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_PROD + " DESC", null);
    }

    // Buscar producto(s) por nombre
    public Cursor buscarProductoPorNombre(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, COLUMN_PROD + " LIKE ?", new String[]{"%" + nombre + "%"}, null, null, null);
    }
}
