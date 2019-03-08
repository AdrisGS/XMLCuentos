package com.example.adrisgs.xmlcuentos;

public class Version {

    private String titulo;
    private String parrafo;
    private TemasRelacionados temasRelacionados;

    public Version(String titulo, String parrafo, TemasRelacionados temasRelacionados) {
        this.titulo = titulo;
        this.parrafo = parrafo;
        this.temasRelacionados = temasRelacionados;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getParrafo() {
        return parrafo;
    }

    public void setParrafo(String parrafo) {
        this.parrafo = parrafo;
    }

    public TemasRelacionados getTemasRelacionados() {
        return temasRelacionados;
    }

    public void setTemasRelacionados(TemasRelacionados temasRelacionados) {
        this.temasRelacionados = temasRelacionados;
    }
}
