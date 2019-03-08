package com.example.adrisgs.xmlcuentos;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CuentosAdapter extends ArrayAdapter<Cuento> {

    private Context context;
    private ArrayList<Cuento> datos;

    public CuentosAdapter(Context context, ArrayList<Cuento> datos) {
        super(context, R.layout.item_listview, datos);
        this.context = context;
        this.datos = datos;
      //  Log.e("tam de datos", String.valueOf(datos.size()));

       //Cuento nombre= datos.get(0);
       //Log.e("nombreeee CA",nombre.toString());
    }

    @Override
    public View getView(int posicion, View convertirVista, ViewGroup parent){

       // Log.i("tam datos ", String.valueOf(datos.size()));

        Log.e("posicion NNNNN", String.valueOf(posicion));
        // En primer lugar "inflamos" una nueva vista, que será la que se
        // mostrará en la celda del ListView. Para ello primero creamos el
        // inflater, y después inflamos la vista.
        View item = LayoutInflater.from(context).inflate(
                R.layout.item_listview, null);

        // A partir de la vista, recogeremos los controles que contiene para
        // poder manipularlos.
        // Recogemos el ImageView y le asignamos una foto.


        // Recogemos el TextView para mostrar el nombre y establecemos el
        // nombre.
        Cuento cuento= datos.get(posicion);

//       String autorN= cuento.getAutor().getNombre();
        TextView nombre = (TextView) item.findViewById(R.id.tvContent);
        nombre.setText(cuento.toString2());

        // Recogemos el TextView para mostrar el número de celda y lo
        // establecemos.
       TextView numCelda = (TextView) item.findViewById(R.id.tvField);
        numCelda.setText(cuento.toString3());

        // Devolvemos la vista para que se muestre en el ListView.
        return item;

    }


}
