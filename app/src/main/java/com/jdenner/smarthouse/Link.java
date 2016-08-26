package com.jdenner.smarthouse;

import java.io.Serializable;

/**
 * Created by Juliano on 26/08/2016.
 */
public class Link implements Serializable {

    private int codigo;
    private String nome;
    private String urlOn;
    private String urlOff;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlOn() {
        return urlOn;
    }

    public void setUrlOn(String urlOn) {
        this.urlOn = urlOn;
    }

    public String getUrlOff() {
        return urlOff;
    }

    public void setUrlOff(String urlOff) {
        this.urlOff = urlOff;
    }

    @Override
    public String toString() {
        return getNome();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Link) {
            return ((Link) o).getCodigo() == getCodigo();
        }
        return false;
    }
}
