package co.com.gane.activosfijos.configuracion;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import co.com.gane.activosfijos.HomeActivity;
import co.com.gane.activosfijos.MainActivity;
import co.com.gane.activosfijos.R;
import co.com.gane.activosfijos.ayuda.BackStack;
import co.com.gane.activosfijos.ayuda.Constantes;
import co.com.gane.activosfijos.ayuda.Utilidades;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ConfiguracionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfiguracionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public EditText etMACLector;
    public Button btnSaveConfig;
    public ConfiguracionHandler handler;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public ConfiguracionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConfiguracionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConfiguracionFragment newInstance(String param1, String param2) {
        ConfiguracionFragment fragment = new ConfiguracionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.handler = new ConfiguracionHandler(this);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_configuracion, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        this.etMACLector = (EditText)view.findViewById(R.id.etMACLector);
        this.btnSaveConfig = (Button)view.findViewById(R.id.btnSaveConfig);
        this.btnSaveConfig.setOnClickListener(this.handler);

        ((HomeActivity)this.getActivity()).toggle.setDrawerIndicatorEnabled(false);
        ((HomeActivity)this.getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((HomeActivity)this.getActivity()).getSupportActionBar().show();

        //Se configura listener para retornar al fragent anterior
        ((HomeActivity)this.getActivity()).toolbar.setNavigationOnClickListener(new BackStack(this));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    public void setSbMac(){
        try {
            JSONObject jsDatosConfig = new JSONObject(Utilidades.getDataUserPreferences(this.getActivity(), Constantes.CONSTANTES_CONFIGURACION));
            String sbMac = jsDatosConfig.getString("sbMacLector");
            ((HomeActivity)this.getActivity()).setSbMac(sbMac);
        } catch (Exception e) {
            Utilidades.showAlertDialog(this.getActivity(), "ERROR === " + e.toString());
        }
    }
}
