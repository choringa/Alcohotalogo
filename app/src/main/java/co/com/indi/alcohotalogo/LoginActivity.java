package co.com.indi.alcohotalogo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import asynctasks.LoginAsyncTask;
import mundo.Usuario;


public class LoginActivity extends ActionBarActivity {

    //----------------------------------
    //ATRIBUTOS
    //----------------------------------

    /**
     * Atributo del boton de login
     */
    private Button loginButton;

    /**
     * Atributos de verificacion de usuario.
     */
    private EditText username, password;

    /**
     * Atributo del usuario.
     */
    private Usuario usuario;

    /**
     * Atributo del progressDialog (ruedita de cargando)
     */
    private ProgressDialog progressDialog;

    //----------------------------------
    //CONSTANTES
    //----------------------------------

    private final static String TAG = "LoginActivity";

    //----------------------------------
    //METODOS
    //----------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);

        loginButton = (Button) findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarUsuario();
            }
        });
    }

    /**
     * Metodo que verifica el usuario
     */
    private void verificarUsuario() {
        Log.d(TAG, "verificarUsuario, username= " + username.getText() + ", pass= " + password.getText());
        String[] params = {username.getText().toString(), password.getText().toString()};
        progressDialog = ProgressDialog.show(this, getResources().getString(R.string.title_progress), getResources().getString(R.string.ret_info), true);
        new LoginAsyncTask(this).execute(params);
        if(usuario != null){
            Intent intent = new Intent(this, MapActivity.class);
            intent.putExtra("usuario", usuario);
            startActivity(intent);
        }
        else{
            Toast toast = Toast.makeText(this,getResources().getString(R.string.error_login),Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setUsuario(Usuario usuario){
        if(progressDialog.isShowing())
            progressDialog.dismiss();
        this.usuario = usuario;
    }

}
