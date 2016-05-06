package co.com.gane.activosfijos.activosfijos;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import co.com.gane.activosfijos.R;
import co.com.gane.activosfijos.ayuda.Utilidades;
import co.com.gane.activosfijos.orm.entity.Activosfijos;

public class ActivosFijosDialog extends DialogFragment{

    TextView tvIdActivoFijo;
    TextView tvNombre;
    TextView tvUbicacion;
    TextView tvTag;
    TextView tvNombreClase;
    TextView tvNombreTipo;
    TextView tvNombreResponsable;
    TextView tvNombreCargo;
    private Activosfijos objActivosfijos;
    public View view;
    public Button btnCerrarAFDialog;
    public Button btnAsignarTagAFDialog;
    public ActivosFijosHandler handler;

    public void setValoresActivoFijo (Activosfijos objActivosfijos) {
       this.objActivosfijos = objActivosfijos;
    }

    public void setHandler (ActivosFijosHandler handler) {
        this.handler = handler;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        this.view = layoutInflater.inflate(R.layout.dialog_activos_fijos, null);

        tvIdActivoFijo = (TextView) view.findViewById(R.id.tvIdActivoDialog);
        tvNombre = (TextView) view.findViewById(R.id.tvNombreDialog);
        tvUbicacion = (TextView) view.findViewById(R.id.tvUbicacionDialog);
        tvTag = (TextView) view.findViewById(R.id.tvTagDialog);
        tvNombreClase = (TextView) view.findViewById(R.id.tvNombreClaseDialog);
        tvNombreTipo = (TextView) view.findViewById(R.id.tvNombreTipoDialog);
        tvNombreResponsable = (TextView) view.findViewById(R.id.tvNombreResponsableDialog);
        tvNombreCargo = (TextView) view.findViewById(R.id.tvNombreCargoDialog);
        btnAsignarTagAFDialog = (Button) view.findViewById(R.id.btnAsignarTagAFDialog);
        btnCerrarAFDialog = (Button) view.findViewById(R.id.btnCerrarAFDialog);

        btnAsignarTagAFDialog.setOnClickListener(handler);
        btnCerrarAFDialog.setOnClickListener(handler);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        tvIdActivoFijo.setText(this.objActivosfijos.getId_activo());
        tvNombre.setText(this.objActivosfijos.getNombre());
        tvUbicacion.setText(this.objActivosfijos.getNombre_ubicacion());
        tvTag.setText(this.objActivosfijos.getTag());
        tvNombreClase.setText(this.objActivosfijos.getNombre_clase());
        tvNombreTipo.setText(this.objActivosfijos.getNombre_tipo());
        tvNombreResponsable.setText(this.objActivosfijos.getNombre_responsable());
        tvNombreCargo.setText(this.objActivosfijos.getNombre_cargo());

        builder.setView(this.view);

        return builder.create();
    }
}
