package com.example.adrisgs.xmlcuentos;

import java.util.ArrayList;
import java.util.List;

public class Cuento {

    private Autor autor;
    private List<Texto> texto;



    public Cuento(){

    }

    public Cuento(Autor autor, List<Texto> texto) {
        this.autor = autor;
        this.texto = texto;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<Texto> getTexto() {
        return texto;
    }

    public void setTexto(List<Texto> texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Cuento{" +
                "autor=" + autor +
                ", texto=" + texto +
                '}';
    }

    public String toString2() {
        return "Cuento{" +
                "AUTOR: " + autor +

                '}';
    }

    public String toString3() {
        return "Cuento{" +
                "texto=" + texto +

                '}';
    }
}
