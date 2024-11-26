package com.example.prueba;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtSku, edtProd;
    Button btnAgregar, btnEditar, btnEliminar, btnOrdenar;
    ListView lvProductos;
    DBHelper dbHelper;
    ProductoAdapter adapter;
    List<Producto> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSku = findViewById(R.id.edtSku);
        edtProd = findViewById(R.id.edtProd);
        btnAgregar = findViewById(R.id.btnInsertar);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnOrdenar = findViewById(R.id.btnOrdenarZA);
        lvProductos = findViewById(R.id.lvProductos);

        dbHelper = new DBHelper(this);
        listaProductos = new ArrayList<>();

        // Cargar productos
        cargarProductos();

        // Configurar adaptador
        adapter = new ProductoAdapter(this, listaProductos);
        lvProductos.setAdapter(adapter);

        // Acción al agregar producto
        btnAgregar.setOnClickListener(v -> {
            int sku = Integer.parseInt(edtSku.getText().toString());
            String prod = edtProd.getText().toString();

            if (dbHelper.verificarSkuExistente(sku)) {
                Toast.makeText(MainActivity.this, "El SKU ya existe.", Toast.LENGTH_SHORT).show();
            } else {
                dbHelper.insertarProducto(sku, prod);
                cargarProductos();
                adapter.notifyDataSetChanged();
                edtSku.setText("");
                edtProd.setText("");
            }
        });

        // Acción al editar producto
        btnEditar.setOnClickListener(v -> {
            int sku = Integer.parseInt(edtSku.getText().toString());
            String prod = edtProd.getText().toString();

            if (dbHelper.verificarSkuExistente(sku)) {
                dbHelper.editarProductoPorSku(sku, prod);
                cargarProductos();
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(MainActivity.this, "Producto no encontrado para editar.", Toast.LENGTH_SHORT).show();
            }
        });

        // Acción al eliminar producto
        btnEliminar.setOnClickListener(v -> {
            int sku = Integer.parseInt(edtSku.getText().toString());

            if (dbHelper.verificarSkuExistente(sku)) {
                dbHelper.eliminarProductoPorSku(sku);
                cargarProductos();
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(MainActivity.this, "Producto no encontrado para eliminar.", Toast.LENGTH_SHORT).show();
            }
        });

        // Ordenar productos de Z a A
        btnOrdenar.setOnClickListener(v -> {
            Cursor cursor = dbHelper.obtenerProductosOrdenadosZA();
            listaProductos.clear();
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID));
                    int sku = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_SKU));
                    String prod = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PROD));
                    listaProductos.add(new Producto(id, sku, prod));
                } while (cursor.moveToNext());
            }
            cursor.close();
            adapter.notifyDataSetChanged();
        });

        // Acción al hacer clic en un producto en la lista
        lvProductos.setOnItemClickListener((parent, view, position, id) -> {
            Producto producto = listaProductos.get(position);
            edtSku.setText(String.valueOf(producto.getSku()));
            edtProd.setText(producto.getNombre());
        });
    }

    // Cargar productos en la lista
    private void cargarProductos() {
        Cursor cursor = dbHelper.obtenerProductos();
        listaProductos.clear();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID));
                int sku = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_SKU));
                String prod = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PROD));
                listaProductos.add(new Producto(id, sku, prod));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
