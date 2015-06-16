package mundo;

import java.io.Serializable;

/**
 * Created by Choringa on 4/06/15.
 */
public class Tienda implements Serializable {

    //---------------------
    // Atributos
    //---------------------

    /**
     * Atributos de tienda
     */
    private int idTienda;
    private double longitud, latitud;
    private int idVeci;

    //---------------------
    // CONSTRUCTOR
    //---------------------

    /**
     * Constructor de tienda
     * @param longitud
     * @param latitud
     * @param idVeci
     */
    public Tienda(double longitud, double latitud, int idVeci) {
        this.longitud = longitud;
        this.latitud = latitud;
        this.idVeci = idVeci;
    }

    //---------------------
    // METODOS
    //---------------------

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public int getIdVeci() {
        return idVeci;
    }

    public void setIdVeci(int idVeci) {
        this.idVeci = idVeci;
    }
}
