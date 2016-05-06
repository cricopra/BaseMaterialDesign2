package co.com.gane.activosfijos.puntos;

import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.com.gane.activosfijos.HomeActivity;
import co.com.gane.activosfijos.R;
import co.com.gane.activosfijos.activosfijos.ActivosFijosFragment;
import co.com.gane.activosfijos.orm.entity.Bodegas;
import co.com.gane.activosfijos.orm.repository.RepositoryBodega;
import co.com.gane.activosfijos.ayuda.Constantes;
import co.com.gane.activosfijos.ayuda.Utilidades;

/**
 * Created by Egonzalias on 12/04/2016.
 */
public class PuntosHandler implements android.support.v7.widget.SearchView.OnQueryTextListener, View.OnClickListener {

    private PuntosFragment objFragment;
    public List<Bodegas> puntos;
    public List<Bodegas> puntosFiltrado;

    public RepositoryBodega rBodega;

    public PuntosHandler(PuntosFragment objFragment){
        this.objFragment = objFragment;
        this.puntos = new ArrayList<Bodegas>();
        rBodega = new RepositoryBodega(this.objFragment.context);
    }

    // Métodos para obtener las Bodegas
    public void printPuntos(){
        this.puntos = rBodega.getAll();
        this.objFragment.adpPuntos = new PuntosAdapter(this.puntos, this);
        this.objFragment.rvPuntos.setAdapter(this.objFragment.adpPuntos);
    }

    // Métodos de Filtro
    @Override
    public boolean onQueryTextSubmit(String s) {
        filtrar(s);
        return false;
    }
    @Override
    public boolean onQueryTextChange(String s) {
        filtrar(s);
        return false;
    }
    public void filtrar(String sbFiltroPuntos) {

        PuntosAdapter objAdapter = new PuntosAdapter(this.puntos, this);
        this.puntosFiltrado = objAdapter.filtrar(sbFiltroPuntos);
        PuntosAdapter objAdapterFiltrado = new PuntosAdapter(this.puntosFiltrado, this);

        this.objFragment.rvPuntos.setAdapter(objAdapterFiltrado);

    }

    // Método al presionar en una bodega para cargar los activos fijos.
    @Override
    public void onClick(View view) {
        TextView tvCodigo = (TextView) view.findViewById(R.id.txtCodigoCvPuntosItem);
        TextView tvTitulo = (TextView) view.findViewById(R.id.txtTituloCvPuntosItem);
        TextView tvDireccion = (TextView) view.findViewById(R.id.txtDireccionCvPuntosItem);
        String sbCodigo = tvCodigo.getText().toString();
        String sbTitulo = tvTitulo.getText().toString();
        String sbDireccion = tvDireccion.getText().toString();

        try {
            JSONObject jsonDataSession;
            jsonDataSession = new JSONObject();
            jsonDataSession.put("sbCodigo", sbCodigo);
            jsonDataSession.put("sbTitulo", sbTitulo);
            jsonDataSession.put("sbDireccion", sbDireccion);

            Utilidades.setDataUserPreferences(this.objFragment.context, jsonDataSession, Constantes.JSON_ACTIVOS_FIJOS);
            HomeActivity.replaceFragment(new ActivosFijosFragment(),true);
        } catch (Exception e) {
            Utilidades.showAlertDialog(this.objFragment.context, e.toString());
        }
    }
}
