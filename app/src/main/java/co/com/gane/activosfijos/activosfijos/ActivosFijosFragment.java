package co.com.gane.activosfijos.activosfijos;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

//import co.com.gane.activosfijos.BluetoothReader.DeviceListActivity;
//import co.com.gane.activosfijos.BluetoothReader.TSLBluetoothDeviceActivity;
import co.com.gane.activosfijos.HomeActivity;
import co.com.gane.activosfijos.R;
import co.com.gane.activosfijos.ayuda.Constantes;
import co.com.gane.activosfijos.ayuda.Utilidades;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ActivosFijosFragment newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivosFijosFragment extends Fragment{

    public Context context;
    public ActivosFijosHandler handler;
    public RecyclerView rvActivosFijos;
    public ActivosFijosAdapter adpActivosFijos;
    public String sbTitulo;
    public String sbDireccion;
    public String sbCodigo;
    public TextView tvDireccion;
    public View view;
    public MenuItem btnConnectReader;
    public MenuItem btnDisconnectReader;

    public ActivosFijosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            view = inflater.inflate(R.layout.fragment_activos_fijos, container, false);
            context = getActivity();
            init(view);
            setHasOptionsMenu(true);

            ((HomeActivity)this.getActivity()).getSupportActionBar().setTitle(this.sbDireccion);
        } catch (Exception e) { }

        return view;
    }

    private void init(View view) {
        rvActivosFijos = (RecyclerView) view.findViewById(R.id.rvActivosFijos);
        rvActivosFijos.setHasFixedSize(true);
        LinearLayoutManager rvActivosFijosLLM = new LinearLayoutManager(context);
        rvActivosFijosLLM.setOrientation(LinearLayoutManager.VERTICAL);
        rvActivosFijos.setLayoutManager(rvActivosFijosLLM);

        String jsonActivosFijosResponse = Utilidades.getDataUserPreferences(this.context, Constantes.JSON_ACTIVOS_FIJOS);
        try {
            JSONObject obJsonResponse = new JSONObject(jsonActivosFijosResponse);
            this.sbCodigo = obJsonResponse.getString("sbCodigo");
            this.sbTitulo = obJsonResponse.getString("sbTitulo");
            this.sbDireccion = obJsonResponse.getString("sbDireccion");
        } catch(Exception e) {
            Utilidades.showAlertDialog(this.context, e.toString());
        }

        HomeActivity.instance.dbHandler.obtenerActivosFijos(this.sbCodigo);
        this.handler = new ActivosFijosHandler(this);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.activos_fijos_menu, menu);
        btnConnectReader = menu.findItem(R.id.connectReader);
        btnDisconnectReader = menu.findItem(R.id.disconnectReader);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        boolean isConnected = ((HomeActivity) getActivity()).isConnected();
        btnConnectReader.setEnabled(!isConnected);
        btnDisconnectReader.setEnabled(isConnected);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.connectReader:
                try {
                    ((HomeActivity) getActivity()).bluetoothConnect();
                    boolean isConnected = readerIsConnected();
                    btnConnectReader.setEnabled(isConnected);
                    btnDisconnectReader.setEnabled(isConnected);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.disconnectReader:
                try {
                    ((HomeActivity) getActivity()).bluetoothDisconnect();
                    boolean isConnected = readerIsConnected();
                    btnConnectReader.setEnabled(isConnected);
                    btnDisconnectReader.setEnabled(isConnected);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        this.handler.printActivosFijos();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void runStartBuffer (){
        ((HomeActivity) getActivity()).StartBuffer();
    }

    public Boolean readerIsConnected(){
        return ((HomeActivity) getActivity()).isConnected();
    }
}
