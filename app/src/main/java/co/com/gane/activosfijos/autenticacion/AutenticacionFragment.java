package co.com.gane.activosfijos.autenticacion;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import co.com.gane.activosfijos.HomeActivity;
import co.com.gane.activosfijos.R;
import co.com.gane.activosfijos.ayuda.Utilidades;

/**
 * Created by SNavia on 12/04/2016.
 */
public class AutenticacionFragment extends Fragment {

    public Context context;

    public Button btnAutenticar;
    public TextView tvConfiguracion;
    public EditText etUsuario;
    public EditText etClave;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        try{
            // Inflate the layout for this fragment
            view  = inflater.inflate(R.layout.fragment_autenticacion, container, false);
            context = getActivity();
        }catch (Exception e){
            Utilidades.Toast(context, e.getMessage());
        }
        return view;
    }

    /**
     * Carga antes del onCreateView, se utiliza para instanciar los elementos de la vista y demas necesarios
     */
    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        ((HomeActivity)this.getActivity()).setEnableDrawerListener(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        ((HomeActivity)this.getActivity()).getSupportActionBar().hide();

        AutenticacionHandler handler = new AutenticacionHandler(this);
        etUsuario = (EditText)v.findViewById(R.id.etUsuario);
        etClave = (EditText)v.findViewById(R.id.etClave);
        btnAutenticar = (Button) v.findViewById(R.id.btnAutenticar);
        tvConfiguracion = (TextView) v.findViewById(R.id.tvConfiguracion);
        btnAutenticar.setOnClickListener(handler);
        tvConfiguracion.setOnClickListener(handler);
    }
}
