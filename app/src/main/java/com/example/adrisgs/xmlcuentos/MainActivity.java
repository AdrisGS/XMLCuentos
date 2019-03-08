package com.example.adrisgs.xmlcuentos;


import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {


    private ArrayList<Cuento> cuentos;
    private ListView listaView;
    private CuentosAdapter adapter;
    private TextView tvNombre,tvNumCelda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cuentos = new ArrayList<Cuento>();


        parsearXmlDeUrl();



        //los text de la vista
        tvNombre = (TextView) findViewById(R.id.contenido);
        tvNumCelda = (TextView) findViewById(R.id.idCelda);


        adapter  = new CuentosAdapter(this,cuentos);

        listaView = findViewById(R.id.lista);
        listaView.setAdapter(adapter);

        listaView.setOnItemClickListener(this);
       // printPlayers(cuentos);
    }

     void parsearXmlDeUrl(){

        InputStream stream ;
        XmlPullParserFactory parserFactory;
        ParserXML parserXml = new ParserXML();


         try {

            parserFactory=XmlPullParserFactory.newInstance();
            XmlPullParser parser=parserFactory.newPullParser();
            stream= getAssets().open("cuentos.xml");


             parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(stream,null);
            cuentos= (parserXml.parsear(stream));





        } catch (XmlPullParserException e) {
             Log.e("ESTAMOS EN EL parser 4 ",":OOOOO");

             e.printStackTrace();
        } catch (IOException e) {
             Log.e("ESTAMOS EN EL parser 5 ",":OOOOO");


         }


    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Al hacer click sobre uno de los items del ListView mostramos los
        // datos en los TextView.
        Cuento cuento = cuentos.get(position);
        tvNombre.setText( cuento.toString2());
        tvNumCelda.setText(cuento.toString3());
    }
}
