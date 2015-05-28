package mundo;

import java.io.Serializable;

/**
 * Clase que representa un usuario
 * @author Choringa
 */
public class Usuario implements Serializable {

    /**
     * Atributo ID del usuario.
     */
    private int idUsuario;

    /**
     * Atributo nombre y username del usuario
     */
    private String nombre, username;

    /**
     * El tipo al que pertenece el usuario
     */
    private int tipo;

    /**
     * Constructor de la clase
     * @param nombre nombre del usuario
     * @param username username del usuario
     * @param tipo tipo al que pertenece el usuario
     */
    public Usuario(int idUsuario, String nombre, String username, int tipo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.username = username;
        this.tipo = tipo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

}

