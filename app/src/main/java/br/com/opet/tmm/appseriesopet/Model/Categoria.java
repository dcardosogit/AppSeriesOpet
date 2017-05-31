package br.com.opet.tmm.appseriesopet.Model;

/**
 * Created by Diego on 31/05/2017.
 */

public class Categoria {
    private int _ID;
    private String categoria;

    public Categoria(String categoria) {
        this.categoria = categoria;
    }

    public Categoria() {
    }

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
