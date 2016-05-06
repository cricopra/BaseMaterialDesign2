package co.com.gane.activosfijos.ayuda;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import org.json.JSONObject;

import co.com.gane.activosfijos.R;

/**
 * Created by Egonzalias on 11/04/2016.
 */
public class Utilidades {

    private static ProgressDialog pDialog;


    public static void Toast(Context context, String sbMsg) {
        Toast toast = Toast.makeText(context, sbMsg, Toast.LENGTH_LONG);
        toast.show();
    }

    public static boolean isEmpty(String data){
        if(data == null || data.trim().equals(""))
            return true;
        return false;
    }

    public static String getVersion(Context context){
        String sbVersion = "";
        PackageInfo pInfo;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            sbVersion = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sbVersion;
    }

    public static void showMessgaeProgress(Context context){
        pDialog = new ProgressDialog(context);
        pDialog.setMessage(context.getString(R.string.msj_cargando));
        pDialog.show();
    }

    public static void stopMessgaeProgress(){
        if(pDialog!=null){
            pDialog.dismiss();
        }
    }

    public static void showCustomMessgaeProgress(Context context, String mensaje){
        pDialog = new ProgressDialog(context);
        pDialog.setMessage(mensaje);
        pDialog.show();
    }

    public static void stopCustomMessgaeProgress(){
        if(pDialog!=null){
            pDialog.dismiss();
        }
    }

    public static void hideKeyboard(Activity activity, View viewToHide) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(viewToHide.getWindowToken(), 0);
        } catch (Exception e){
            Log.w("", "Imposible cerrar el teclado");
        }
    }

   /* public static void showKeyboard(Activity activity, View focusEditText) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(focusEditText, InputMethodManager.SHOW_IMPLICIT);
        } catch (Exception e){
            Log.w("", "Imposible abrir el teclado");
        }
    }*/

    public static void showAlertDialog(Context context, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .
                // setIcon(R.drawable.play18).
                        setTitle(context.getString(R.string.app_name))
                .setCancelable(false)
				/*
				 * .setNegativeButton("Cancelar", new
				 * DialogInterface.OnClickListener() { public void
				 * onClick(DialogInterface dialog, int id) { dialog.cancel(); }
				 * })
				 */
                .setPositiveButton("Continuar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // metodo que se debe implementar
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static String getDataUserPreferences(Context context, String sbConstante) {
        try{
            if(context != null){
                SharedPreferences objShared = PreferenceManager.getDefaultSharedPreferences(context);
                String sbData = objShared.getString(sbConstante, "");
                return sbData;
            }
        }catch (Exception e){
            throw e;
        }
        return null;
    }

    public static void setDataUserPreferences(Context context, JSONObject jsonData, String sbConstante){
        try{
            SharedPreferences objShared = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor edit = objShared.edit();
            edit.putString(sbConstante, jsonData.toString());
            edit.commit();
        }catch (Exception e){
            throw e;
        }
    }

    public static void clearAllPreferencesSession(Context context){
        SharedPreferences objShared = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = objShared.edit();
        //edit.putString(Constants.JSON_USER, "");
        //edit.putBoolean(Constants.SESSION_CURRENT, false);
        edit.commit();
        System.out.println(objShared.getAll());
    }
}
