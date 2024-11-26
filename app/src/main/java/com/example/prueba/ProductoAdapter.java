package com.example.prueba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

// Clase Producto para representar los datos
class Producto {
    private int id; // ID único del producto
    private int sku;
    private String nombre;

    public Producto(int id, int sku, String nombre) {
        this.id = id;
        this.sku = sku;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public int getSku() {
        return sku;
    }

    public String getNombre() {
        return nombre;
    }
}

// Adaptador personalizado para el ListView
public class ProductoAdapter extends BaseAdapter {

    private Context context;
    private List<Producto> productos;
    private LayoutInflater inflater;

    public ProductoAdapter(Context context, List<Producto> productos) {
        this.context = context;
        this.productos = productos;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int position) {
        return productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return productos.get(position).getId(); // Retorna el ID único del producto
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_producto, parent, false);
            holder = new ViewHolder();
            holder.tvSku = convertView.findViewById(R.id.tvSku);
            holder.tvNombre = convertView.findViewById(R.id.tvNombre);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Producto producto = productos.get(position);

        holder.tvSku.setText("SKU: " + producto.getSku());
        holder.tvNombre.setText("Nombre: " + producto.getNombre());

        return convertView;
    }

    static class ViewHolder {
        TextView tvSku;
        TextView tvNombre;
    }
}
