package co.com.gane.activosfijos.configuracion;

import android.support.v4.app.Fragment;
import android.view.View;

import org.json.JSONObject;

import co.com.gane.activosfijos.HomeActivity;
import co.com.gane.activosfijos.ayuda.Constantes;
import co.com.gane.activosfijos.ayuda.Utilidades;

/**
 * Created by Playtech2 on 15/04/2016.
 */
public class ConfiguracionHandler implements View.OnClickListener{

    public ConfiguracionFragment objFragment;

    public ConfiguracionHandler (ConfiguracionFragment objFragment){
        this.objFragment = objFragment;
    }
    @Override
    public void onClick(View view) {
        try {
            String sbMacLector = this.objFragment.etMACLector.getText().toString();
            JSONObject jsDatos = new JSONObject();
            jsDatos.put("sbMacLector", sbMacLector);
            Utilidades.setDataUserPreferences(this.objFragment.getActivity(),jsDatos, Constantes.CONSTANTES_CONFIGURACION);
            this.objFragment.setSbMac();
            Utilidades.showAlertDialog(this.objFragment.getActivity(), "Cambios Guardados.");
        } catch(Exception e) {

        }
    }
}
