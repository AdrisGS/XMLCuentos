package com.example.adrisgs.xmlcuentos;

public class Frase {

    private Integer dia;
    private Version version;

    public Frase(Integer dia, Version version) {
        this.dia = dia;
        this.version = version;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }
}
