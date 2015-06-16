package conexion;

import org.ksoap2.serialization.SoapObject;

/**
 * Created by Choringa on 20/04/15.
 */
public class ConexionConfig extends ManejadorConexion{

    /**
     * Protocolo y ubicación del servicio a consultar
     */
    private final static String NAME_SPACE = "http://WS/";

    /**
     * Ubicación de despliegue y nombre del servicio (schema location)
     */
    private final static String NOMBRE_SERVICIO = "http://192.168.0.17:8080/ElVeciWS/ElVeciWS?wsdl";

    /**
     * Línea que permite configurar la conexión http en Android
     */
    public final static String CONEXION_HTTP_3G = NOMBRE_SERVICIO + "";

    /**
     * Línea que permite configurar la conexión http en Android
     */
    public final static String CONEXION_HTTP_WIFI = NOMBRE_SERVICIO + "";






    //-----------------------------
    // CONSTANTES PARAMETROS
    //-----------------------------

    /**
     * Parametros web de los metodos del web service
     */
    public final static String PARAM_USERNAME = "username";

    public final static String PARAM_PASSWORD = "password";

    public final static String PARAM_ID_USER = "idUser";

    //-----------------------------
    // CONSTANTES METODOS
    //-----------------------------

    /**
     * Constantes del nombre de los metodos del web service.
     */
    public static final String METHOD_DAR_USUARIO = "darUsuario";



    //-------------------
    // CONSTRUCTOR
    //-------------------

    /**
     * Constructor de la conexion
     */
    public ConexionConfig() {
        super(NAME_SPACE, NOMBRE_SERVICIO, CONEXION_HTTP_3G);
    }


    //-------------------
    // METODOS
    //-------------------

    /**
     * Metodo para procesar las respuestas del web service
     * Procesa la respuesta de un servicio si la respuesta del server es en bytes[]
     */
    @Override
    public SoapObject procesarRespuesta(SoapObject body) {
        return body;
    }

    /**
     * Metodo para procesar las respuestas del web service
     * Procesa la respuesta de un servicio si la respuesta del server no es en bytes[] como strings por ejemplo
     */
    @Override
    public String procesarRespuesta2(SoapObject body) {
        return body.getProperty(0).toString();
    }

}
