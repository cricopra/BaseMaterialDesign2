package co.com.gane.activosfijos.activosfijos;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import co.com.gane.activosfijos.HomeActivity;
import co.com.gane.activosfijos.R;

public class ActivosFijosTAGDialog extends DialogFragment{

    EditText etTAGActivoFijo;
    public Button btnConfirmarAsignarTagAFDialog;
    public ActivosFijosHandler handler;
    public View view;

    public void setHandler (ActivosFijosHandler handler) {
        this.handler = handler;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        this.view = layoutInflater.inflate(R.layout.dialog_activos_fijos_tag, null);

        etTAGActivoFijo = (EditText) view.findViewById(R.id.editText);
        btnConfirmarAsignarTagAFDialog = (Button) view.findViewById(R.id.button);
        btnConfirmarAsignarTagAFDialog.setOnClickListener(this.handler);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        etTAGActivoFijo.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(this.view);
        return builder.create();
    }
}
