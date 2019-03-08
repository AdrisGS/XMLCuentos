package com.example.adrisgs.xmlcuentos;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.xmlpull.v1.XmlPullParser.END_DOCUMENT;
import static org.xmlpull.v1.XmlPullParser.START_TAG;

public class ParserXML {

    private static final String ns= null;

    //constantes del archivo
    private static final String ETIQUETA_CUENTOS="cuentos";
    private static final String ETIQUETA_CUENTO="cuento";
    private static final String ETIQUETA_AUTOR="autor";
    private static final String ETIQUETA_NOMBRE="nombre";
    private static final String ETIQUETA_APELLIDO="apellido";
    private static final String ETIQUETA_TEXTO="texto";
    private static final String ATRIBUTO_ES="es";
    private static final String ATRIBUTO_EN="en";
    private static final String ETIQUETA_LIBRO="libro";
    private static final String ETIQUETA_TITULO_CUENTO="titulo_cuento";
    private static final String ETIQUETA_PARRAFO="parrafo";
    private static final String ETIQUETA_REFLEXION="reflexion";

    public ArrayList<Cuento> parsear(InputStream inputStream) throws XmlPullParserException,IOException{
        try {
            XmlPullParser  parser =Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,true);
            parser.setInput(inputStream,null);
            parser.nextTag();
            return leerCuentos(parser);
        }finally {
            inputStream.close();
        }
    }


    public ArrayList<Cuento> leerCuentos(XmlPullParser parser) throws XmlPullParserException,IOException{
        ArrayList<Cuento> listaCuentos = new ArrayList<Cuento>();

        parser.require(START_TAG,ns,ETIQUETA_CUENTOS);

        while (parser.next() != END_DOCUMENT){
            if (parser.getEventType() != START_TAG){
                continue;
            }

            //se obtiene nombre de la etiqueta
            String nombreEtiqueta=parser.getName();
            Log.e("NOMBRE DE LA ETIQUETA",nombreEtiqueta);

            //buscar etiqueta cuento
            if (nombreEtiqueta.equals(ETIQUETA_CUENTO)){
                listaCuentos=leerCuento(parser);
            }else {
                saltarEtiqueta(parser);
            }
        }


        return listaCuentos;
    }

    public ArrayList<Cuento> leerCuento(XmlPullParser parser) throws IOException,XmlPullParserException{


        parser.require(START_TAG, ns, ETIQUETA_CUENTO);


        //declaracion de los atributos de un cuento
        Autor autor=null;
        String nombre ="";
        String apellido="";

        List<Texto> texto=new ArrayList<>();

        Cuento cuento = null;

        //dos textos porque algunos cuentos tienen el texto
        //en español y otro en ingles
        Texto texto1 =null;
        Texto texto2 =null;

        String libro = "";
        String titulo_cuento = "";
        ArrayList<String> parrafo = new ArrayList<>();
        ArrayList<Cuento> cuentosLista = new ArrayList<>();

        List<String> reflexion =new ArrayList<>();
        int eventType=parser.getEventType();

        String name= null;

        name=parser.getName();
        String atibuto="";

        if (name.equals(ETIQUETA_CUENTO)){
            parser.nextTag();
            cuento = new Cuento();
        }



        while (eventType!= XmlPullParser.END_DOCUMENT){

            switch (eventType){



                case XmlPullParser.START_TAG:
                //     Log.i("event case", String.valueOf(eventType));
                    name=parser.getName();
                  //  Log.i(" name event", name);

                    if (cuento!=null) {
                        if (ETIQUETA_AUTOR.equals(name)) {
                            autor = new Autor();
                            parser.next();
                        } else if (autor != null) {
                            if (ETIQUETA_NOMBRE.equals(name)) {

                                nombre = leerNombre(parser);
                                autor.setNombre(nombre);
                                parser.next();
                              // Log.d("ATRIBUTO-------NAME: ", autor.getNombre());

                            } else if (ETIQUETA_APELLIDO.equals(name)) {
                                apellido= leerApellido(parser);
                                autor.setApellido(apellido);
                             //  Log.d("ATRIBUTO-------APELL.: ", autor.getApellido());
                                parser.next();
                            } else if (ETIQUETA_TEXTO.equals(name)) {
                                 atibuto = parser.getAttributeValue(0);
                             //   Log.d("ATRIBUTO-----------10: ", atibuto);
                                if (atibuto.equals(ATRIBUTO_ES)) {
                                    texto1 = new Texto();
                                 //   Log.d("*********------texto1: ", name);

                                } else if (atibuto.equals(ATRIBUTO_EN)) {
                              //      Log.d("TEXTO EN--------: ", name);
                                    texto2=new Texto();
                                }

                            } else if (texto1 != null && atibuto.equals(ATRIBUTO_ES)) {
                                if (ETIQUETA_LIBRO.equals(name)) {
                                    libro = leerLibro(parser);
                                    texto1.setLibro(libro);
                                    //Log.d("ATRIBUTO-------libro ", texto1.getLibro());

                                } else if (ETIQUETA_TITULO_CUENTO.equals(name)) {
                                    titulo_cuento= leerTituloCuento(parser);
                                    texto1.setTitulo_cuento(titulo_cuento);

                                 //   Log.d("ATRIBUTO-------titulo: ", texto1.getTitulo_cuento());

                                } else if (ETIQUETA_PARRAFO.equals(name)) {
                                   // Log.d("********---parrafo: ", name);
                                    parrafo.add(leerParrafo(parser));

                                } else if (ETIQUETA_REFLEXION.equals(name)) {
                                  //  Log.d("*********------text: ", name);
                                    reflexion.add(leerReflexion(parser));

                                }
                            }else  if (texto2 != null && atibuto.equals(ATRIBUTO_EN) ) {

                                if (ETIQUETA_LIBRO.equals(name)) {
                                    libro = leerLibro(parser);
                                    texto2.setLibro(libro);
                                   // Log.d("ATRIBUTO-------libro ", texto1.getLibro());

                                } else if (ETIQUETA_TITULO_CUENTO.equals(name)) {
                                    titulo_cuento= leerTituloCuento(parser);
                                    texto2.setTitulo_cuento(titulo_cuento);

                                    //Log.d("ATRIBUTO-------titulo: ", texto1.getTitulo_cuento());

                                } else if (ETIQUETA_PARRAFO.equals(name)) {
                                   // Log.d("********---parrafo: ", name);
                                    parrafo.add(leerParrafo(parser));

                                } else if (ETIQUETA_REFLEXION.equals(name)) {
                                   // Log.d("*********------text: ", name);
                                    reflexion.add(leerReflexion(parser));

                                }
                            }
                        }
                    }
            }

            eventType = parser.next();

          //  Log.e("antes del OTRO CUENTO", name+ "::"+eventType);

            if (name.equals(ETIQUETA_CUENTO) || eventType==1){
            //    Log.d("otro cuento :O ", name);

                if (texto1!=null){
                    texto1.setParrafo(parrafo);
                    texto1.setReflexion(reflexion);
                  //  Log.d("otro cuento :O ", texto1.toString());

                    texto.add(texto1);
                }

                if (texto2!=null){
                    texto2.setParrafo(parrafo);
                    texto2.setReflexion(reflexion);
                    texto.add(texto2);
                }

                if (texto!=null){
                    cuento.setTexto(texto);

                }

                if (autor!=null){
                    cuento.setAutor(autor);
                    cuento.setTexto(texto);
                }


                cuentosLista.add(cuento);

                texto1=null;
                texto2=null;
                autor=null;
                texto = new ArrayList<>();
                reflexion = new ArrayList<>();
                parrafo = new ArrayList<>();
               // Log.d("CUENTO EN EL ARREGLO: ", cuento.toString());

                cuento = new Cuento();
                eventType = parser.next();
            }

        }

        return cuentosLista;

    }

    // Procesa las etiqueta <nombre> del Autor
    private String leerNombre(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, ETIQUETA_NOMBRE);
        String nombre = obtenerTexto(parser);
        parser.require(XmlPullParser.END_TAG, ns, ETIQUETA_NOMBRE);
        return nombre;
    }

    // Procesa las etiqueta <apellido> del Autor
    private String leerApellido(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, ETIQUETA_APELLIDO);
        String apellido = obtenerTexto(parser);
        parser.require(XmlPullParser.END_TAG, ns, ETIQUETA_APELLIDO);
        return apellido;
    }

    //procesa la etiqueta de <libro>
    private String leerLibro(XmlPullParser parser) throws IOException,XmlPullParserException{
        parser.require(XmlPullParser.START_TAG, ns, ETIQUETA_LIBRO);
        String libro = obtenerTexto(parser);
        parser.require(XmlPullParser.END_TAG, ns, ETIQUETA_LIBRO);
        return libro;
    }

    //procesa la etiqueta de <titulo_cuento>
    private String leerTituloCuento(XmlPullParser parser) throws IOException,XmlPullParserException{
        parser.require(XmlPullParser.START_TAG, ns, ETIQUETA_TITULO_CUENTO);
        String titulo_cuento = obtenerTexto(parser);
        parser.require(XmlPullParser.END_TAG, ns, ETIQUETA_TITULO_CUENTO);
        return titulo_cuento;
    }

    //procesa la etiqueta de <parrafo>
    private String leerParrafo(XmlPullParser parser) throws IOException,XmlPullParserException{
        parser.require(XmlPullParser.START_TAG, ns, ETIQUETA_PARRAFO);
        String parrafo = obtenerTexto(parser);
       // Log.e("parraFOOOO",parrafo);
        parser.require(XmlPullParser.END_TAG, ns, ETIQUETA_PARRAFO);
        return parrafo;
    }

    //procesa la etiqueta de <reflexion>
    private String leerReflexion(XmlPullParser parser) throws IOException,XmlPullParserException{
        parser.require(XmlPullParser.START_TAG, ns, ETIQUETA_REFLEXION);
        String reflexion = obtenerTexto(parser);
        parser.require(XmlPullParser.END_TAG, ns, ETIQUETA_REFLEXION);
        return reflexion;
    }
    // Obtiene el texto de los atributos
    private String obtenerTexto(XmlPullParser parser) throws IOException, XmlPullParserException {
        String resultado = "";
        if (parser.next() == XmlPullParser.TEXT) {
            resultado = parser.getText();
            parser.nextTag();
        }
        return resultado;
    }

    // Salta aquellos objeteos que no interesen en la jerarquía XML.
    private void saltarEtiqueta(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
