package com.example.adrisgs.xmlcuentos;

import android.support.annotation.NonNull;

import java.util.List;

public class Texto {

    private String libro;
    private String titulo_cuento;
    private List<String> parrafo;
    private List<String> reflexion;

    public Texto(){

    }
    public Texto(String libro, String titulo_cuento, List<String> parrafo, List<String> reflexion) {
        this.libro = libro;
        this.titulo_cuento = titulo_cuento;
        this.parrafo = parrafo;
        this.reflexion = reflexion;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getTitulo_cuento() {
        return titulo_cuento;
    }

    public void setTitulo_cuento(String titulo_cuento) {
        this.titulo_cuento = titulo_cuento;
    }

    public List<String> getParrafo() {
        return parrafo;
    }

    public void setParrafo(List<String> parrafo) {
        this.parrafo = parrafo;
    }

    public List<String> getReflexion() {
        return reflexion;
    }

    public void setReflexion(List<String> reflexion) {
        this.reflexion = reflexion;
    }

   /* @Override
    public String toString() {
        return "Texto{" +
                "libro='" + libro + '\'' +
                ", titulo_cuento='" + titulo_cuento + '\'' +
                ", parrafo=" + parrafo +
                ", reflexion=" + reflexion +
                '}';
    }
*/



    @Override
    public String toString() {
        return " nombre_libro="+ libro +
                ", titulo_cuento=" + titulo_cuento;
    }
}
