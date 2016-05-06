package co.com.gane.activosfijos.activosfijos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.com.gane.activosfijos.HomeActivity;
import co.com.gane.activosfijos.R;
import co.com.gane.activosfijos.ayuda.Constantes;
import co.com.gane.activosfijos.ayuda.Utilidades;
import co.com.gane.activosfijos.orm.entity.Activosfijos;
import co.com.gane.activosfijos.orm.repository.RepositoryActivosfijos;

/**
 * Created by Egonzalias on 12/04/2016.
 */
public class ActivosFijosHandler implements View.OnClickListener {

    private ActivosFijosFragment objFragment;
    public List<Activosfijos> activosFijos;
    public RepositoryActivosfijos rActivosfijos;
    public ActivosFijosDialog dialog;
    public ActivosFijosTAGDialog dialogTAG;

    public ActivosFijosHandler(ActivosFijosFragment objFragment) {
        this.objFragment = objFragment;
        this.activosFijos = new ArrayList();
        this.rActivosfijos = new RepositoryActivosfijos(this.objFragment.context);
        this.dialog = new ActivosFijosDialog();
        this.dialogTAG = new ActivosFijosTAGDialog();

    }

    public void printActivosFijos(){
        try {
            this.activosFijos = rActivosfijos.getAll();

            this.objFragment.adpActivosFijos = new ActivosFijosAdapter(this.activosFijos, this, this.objFragment.context);
            this.objFragment.rvActivosFijos.setAdapter(this.objFragment.adpActivosFijos);

        } catch (Exception e) {
            Utilidades.showAlertDialog(this.objFragment.context, e.toString());
        }
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.cvActivosFijosItem:
                Utilidades.showMessgaeProgress(this.objFragment.context);

                Activosfijos objActivosfijos = (Activosfijos) view.getTag();

                dialog.setHandler(this);
                dialog.setCancelable(false);
                dialog.setValoresActivoFijo(objActivosfijos);
                dialog.show(this.objFragment.getFragmentManager(),"");
                Utilidades.stopMessgaeProgress();
            break;

            case R.id.btnAsignarTagAFDialog:
                dialogTAG.setHandler(this);
                dialogTAG.show(this.objFragment.getFragmentManager(),"");
            break;

            case R.id.btnCerrarAFDialog:
                dialog.dismiss();
            break;

            case R.id.button:
                Boolean isConnected = this.objFragment.readerIsConnected();
                if (isConnected){
                    String sbTAGtoAssign = dialogTAG.etTAGActivoFijo.getText().toString();
                    Utilidades.showAlertDialog(this.objFragment.getActivity(), sbTAGtoAssign);
                    this.objFragment.runStartBuffer();
                } else {
                    Utilidades.showAlertDialog(this.objFragment.getActivity(), "Debes conectar un lector primero.");
                }
            break;
        }
    }
}