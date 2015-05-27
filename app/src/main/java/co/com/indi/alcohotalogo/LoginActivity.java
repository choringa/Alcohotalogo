package co.com.indi.alcohotalogo;

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
        if(username.getText().toString().equals("user") && password.getText().toString().equals("user")){
            Intent intent = new Intent(this, MapActivity.class);
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
}
