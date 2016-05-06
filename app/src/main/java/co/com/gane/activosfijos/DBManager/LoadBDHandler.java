package co.com.gane.activosfijos.DBManager;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import co.com.gane.activosfijos.ApplicationSingleton;
import co.com.gane.activosfijos.ayuda.Constantes;
import co.com.gane.activosfijos.ayuda.Servicios;
import co.com.gane.activosfijos.ayuda.Utilidades;
import co.com.gane.activosfijos.orm.entity.Bodegas;
import co.com.gane.activosfijos.orm.entity.Activosfijos;

import org.json.JSONArray;
import org.json.JSONObject;


public class LoadBDHandler {

    private static LoadBDHandler instance;
    DatabaseHelper objDatabaseHelper;
    public SharedPreferences objShared;

    private Context ctx;

    public LoadBDHandler(Context ctx) {
        this.ctx = ctx;
        objDatabaseHelper = new DatabaseHelper(ctx);
        Utilidades.showMessgaeProgress(ctx);

        obtenerBodegas();
    }

    public static LoadBDHandler getInstance(Context ctx) {
        if (instance == null) {
            instance = new LoadBDHandler(ctx);
        }
        return instance;
    }

    public static void setInstanceNull() {
        instance = null;
    }


    public static boolean isIstance() {
        return instance != null;
    }

    public void obtenerBodegas(){
        /*String url = Constantes.BASE_URL + Servicios.OBTENER_PUNTOS;

        // Request a string response from the provided URL.
        JsonObjectRequest jsonRq = new JsonObjectRequest(
                Request.Method.POST,
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        procesarObtenerBodegas(response);
                        Utilidades.stopMessgaeProgress();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        ApplicationSingleton.getInstance().addToRequestQueue(jsonRq);*/

        try{
            JSONObject response = new JSONObject(Constantes.DATOS2);
            Utilidades.stopMessgaeProgress();
            procesarObtenerBodegas(response);
        }catch (Exception e){

        }

    }
    private void procesarObtenerBodegas(JSONObject jsData) {
        try{
            boolean blEstado = jsData.getBoolean(Constantes.SUCCESS);

            if(blEstado){
                RuntimeExceptionDao<Bodegas, Integer> dao = objDatabaseHelper.getREBodegasDao();

                JSONArray rcJsonResponse = new JSONArray(jsData.getString(Constantes.MESSAGE));
                JSONObject EachRcJsonResponse;

                Bodegas objBodegas;
                for (int i = 0; i < rcJsonResponse.length(); i++){
                    EachRcJsonResponse = new JSONObject(rcJsonResponse.getString(i));
                    objBodegas = new Bodegas();
                    objBodegas.setCodigo(EachRcJsonResponse.get("codigo").toString());
                    objBodegas.setNombre(EachRcJsonResponse.get("nombre").toString());
                    objBodegas.setDireccion(EachRcJsonResponse.get("direccion").toString());
                    dao.create(objBodegas);
                }
            }
            else{
                String sbError = jsData.getString(Constantes.MESSAGE);
                Utilidades.showAlertDialog(ctx, sbError);
            }
        }catch (Exception e){
            Utilidades.Toast(ctx, e.getMessage());
        }
    }

    public void obtenerActivosFijos(final String sbCodigo){
        String sbUsuario = "";
        try {
            JSONObject objJSON = new JSONObject(Utilidades.getDataUserPreferences(this.ctx, Constantes.USUARIO));
            sbUsuario = "&usuario=" + Constantes.USER_PREFIX + objJSON.getString("Usuario");
        } catch(Exception e) {
            Utilidades.showAlertDialog(this.ctx, e.toString());
        }

        String url = Constantes.BASE_URL + Servicios.OBTENER_ACTIVOS_FIJOS + "bodega=" + sbCodigo + sbUsuario;

        //Utilidades.showMessgaeProgress(ctx);
        // Request a string response from the provided URL.
        /*JsonObjectRequest jsonRq = new JsonObjectRequest(
                Request.Method.POST,
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        procesarObtenerActivosFijos(response);
                        Utilidades.stopMessgaeProgress();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        ApplicationSingleton.getInstance().addToRequestQueue(jsonRq);*/
        try {
            JSONObject response = new JSONObject(Constantes.DATOS3);
            procesarObtenerActivosFijos(response);
        } catch (Exception e) {

        }

    }
    private void procesarObtenerActivosFijos(JSONObject jsData) {
        try{
            boolean blEstado = jsData.getBoolean(Constantes.SUCCESS);

            if(blEstado){
                RuntimeExceptionDao<Activosfijos, Integer> dao = objDatabaseHelper.getREActivosfijosDao();
                dao.executeRawNoArgs("DELETE FROM activosfijos");

                JSONArray rcJsonResponse = new JSONArray(jsData.getString(Constantes.MESSAGE));
                JSONObject obEachJsonResponse;
                Activosfijos objActivosfijos;
                for (int i = 0; i < rcJsonResponse.length(); i++) {
                    obEachJsonResponse = new JSONObject(rcJsonResponse.getString(i));
                    objActivosfijos = new Activosfijos(
                            obEachJsonResponse.getString("id_activo"),
                            obEachJsonResponse.getString("nombre"),
                            obEachJsonResponse.getString("tag"),
                            obEachJsonResponse.getString("ubicacion"),
                            obEachJsonResponse.getString("nombre_ubicacion"),
                            obEachJsonResponse.getString("clase"),
                            obEachJsonResponse.getString("nombre_clase"),
                            obEachJsonResponse.getString("tipo"),
                            obEachJsonResponse.getString("nombre_tipo"),
                            obEachJsonResponse.getString("responsable"),
                            obEachJsonResponse.getString("nombre_responsable"),
                            obEachJsonResponse.getString("id_cargo"),
                            obEachJsonResponse.getString("nombre_cargo"));

                    dao.create(objActivosfijos);
                }
            }
            else{
                String sbError = jsData.getString(Constantes.MESSAGE);
                Utilidades.showAlertDialog(ctx, sbError);
            }
        }catch (Exception e){
            Utilidades.Toast(ctx, e.getMessage());
        }
    }
}