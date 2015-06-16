package asynctasks;

import android.os.AsyncTask;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;

import co.com.indi.alcohotalogo.LoginActivity;
import conexion.ConexionConfig;
import conexion.ManejadorConexion;
import mundo.Usuario;

/**
 * Created by Choringa on 28/05/15.
 */
public class LoginAsyncTask extends AsyncTask<String,Void, Usuario>{

    private LoginActivity contextLoginActivity;

    public LoginAsyncTask(LoginActivity contextLoginActivity){
        this.contextLoginActivity = contextLoginActivity;
    }

    //--------------------------
    // METODOS OVERRIDE
    //--------------------------

    @Override
    protected Usuario doInBackground(String... params) {
        String username = params[0];
        String password = params[1];
        return darUsuario(username, password);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Usuario usuario) {
        super.onPostExecute(usuario);
        contextLoginActivity.setUsuario(usuario);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Usuario usuario) {
        super.onCancelled(usuario);
        Toast toast = Toast.makeText(contextLoginActivity, "Cancelaste la solicitud", Toast.LENGTH_SHORT);
        toast.show();
    }

    //--------------------------
    // METODOS
    //--------------------------

    /**
     * Metodo consulta el webservice si el usuario existe dados los parametros
     * @param username el username provisto por el usuario.
     * @param password el password provisto por el usuario.
     * @return el usuario si existe, null en caso contario
     */
    private Usuario darUsuario(String username, String password) {
        Usuario user = null;
        String[] nombresParametros = {ConexionConfig.PARAM_USERNAME, ConexionConfig.PARAM_PASSWORD};
        String[] valoresParametros = {username, password};
        ManejadorConexion request = new ConexionConfig();
        try {
            SoapObject soapObject = (SoapObject) request.consumirServicio2(ConexionConfig.METHOD_DAR_USUARIO, nombresParametros, valoresParametros).getProperty(0);
            int idUsuarioU = Integer.parseInt(soapObject.getProperty("idUsuario").toString());
            String nombreU = soapObject.getProperty("nombre").toString();
            String usernameU = soapObject.getProperty("username").toString();
            int tipoU = Integer.parseInt( soapObject.getProperty("tipo").toString());
            user = new Usuario(idUsuarioU,nombreU,usernameU,tipoU);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
