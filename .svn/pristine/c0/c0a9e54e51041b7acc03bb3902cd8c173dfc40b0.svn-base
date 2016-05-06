package co.com.gane.activosfijos.autenticacion;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import co.com.gane.activosfijos.HomeActivity;
import co.com.gane.activosfijos.R;
import co.com.gane.activosfijos.ApplicationSingleton;
import co.com.gane.activosfijos.ayuda.Constantes;
import co.com.gane.activosfijos.ayuda.Servicios;
import co.com.gane.activosfijos.ayuda.Utilidades;
import co.com.gane.activosfijos.configuracion.ConfiguracionFragment;
import co.com.gane.activosfijos.puntos.PuntosFragment;

/**
 * Created by Egonzalias on 12/04/2016.
 */
public class AutenticacionHandler implements View.OnClickListener{

    private Activity objActivity;
    private AutenticacionFragment objFragment;
    private SharedPreferences objShared;

    public AutenticacionHandler(AutenticacionFragment objFragment){
        this.objFragment = objFragment;
        this.objActivity = objFragment.getActivity();
        objShared = PreferenceManager.getDefaultSharedPreferences(this.objFragment.context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAutenticar:
                autenticar();
                break;

            case R.id.tvConfiguracion:
                objFragment.getFragmentManager().beginTransaction().replace(R.id.content_main, new ConfiguracionFragment()).addToBackStack(null).commit();
                break;
        }
    }

    /**
     * Realiza la validacion de campos vacios y envio de datos contra el server
     * @throws
     */
    private void autenticar(){
        try{
            boolean blEstado = true;

            final String sbUsuario = objFragment.etUsuario.getText().toString();
            final String sbClave =  objFragment.etClave.getText().toString();

            if(Utilidades.isEmpty(objFragment.etUsuario.getText().toString())){
                objFragment.etUsuario.setError(objFragment.getString(R.string.msj_campo_requerido));
                blEstado = false;
            }

            if(Utilidades.isEmpty(objFragment.etClave.getText().toString())){
                objFragment.etClave.setError(objFragment.getString(R.string.msj_campo_requerido));
                blEstado = false;
            }


            if(blEstado){

                /*String url = Constantes.BASE_URL + Servicios.LOGIN + "usuario" + sbUsuario + "&clave=" + sbClave;

                Utilidades.showMessgaeProgress(objFragment.context);
                // Request a string response from the provided URL.
                JsonObjectRequest jsonRq = new JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                procesarRespuestaAutenticacion(response, sbUsuario, sbClave);
                                Utilidades.hideKeyboard(objActivity,objFragment.btnAutenticar);
                                Utilidades.stopMessgaeProgress();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }
                );*/

                JSONObject response = new JSONObject(Constantes.DATOS1);

                procesarRespuestaAutenticacion(response, sbUsuario, sbClave);
                Utilidades.hideKeyboard(objActivity,objFragment.btnAutenticar);
                Utilidades.stopMessgaeProgress();



                // Add the request to the RequestQueue.
                //ApplicationSingleton.getInstance().addToRequestQueue(jsonRq);
            }

        }catch (Exception e){
            Utilidades.Toast(objFragment.context, e.getMessage());
        }
    }

    private void procesarRespuestaAutenticacion(JSONObject jsData, String sbUsuario, String sbClave){
        try{
            boolean blEstado = jsData.getBoolean(Constantes.SUCCESS);

            if(blEstado){
                JSONObject objJSON = new JSONObject();
                objJSON.put("Usuario", sbUsuario);
                objJSON.put("Clave", sbClave);
                Utilidades.setDataUserPreferences(this.objFragment.context, objJSON, Constantes.USUARIO);
                HomeActivity.replaceFragment(new PuntosFragment(),true);
            }
            else{
                String sbError = jsData.getString(Constantes.MESSAGE);
                System.out.println(">");

                Utilidades.showAlertDialog(objFragment.context, sbError);
            }

        }catch (Exception e){
            Utilidades.Toast(objFragment.context, e.getMessage());
        }
    }
}
