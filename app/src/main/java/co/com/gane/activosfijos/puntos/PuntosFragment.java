package co.com.gane.activosfijos.puntos;


import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import co.com.gane.activosfijos.HomeActivity;
import co.com.gane.activosfijos.MainActivity;
import co.com.gane.activosfijos.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link PuntosFragment newInstance} factory method to
 * create an instance of this fragment.
 */
public class PuntosFragment extends Fragment {

    public Context context;
    private PuntosHandler handler;
    public PuntosAdapter adpPuntos;
    public RecyclerView rvPuntos;


    public PuntosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        // Inflate the layout for this fragment

        try {
            view = inflater.inflate(R.layout.fragment_puntos, container, false);
            context = getActivity();
            init(view);
            setHasOptionsMenu(true);
        } catch (Exception e) {
        }

        return view;
    }

    private void init(View view) {
        rvPuntos = (RecyclerView) view.findViewById(R.id.rvPuntos);
        rvPuntos.setHasFixedSize(true);
        LinearLayoutManager rvPuntosLLM = new LinearLayoutManager(context);
        rvPuntosLLM.setOrientation(LinearLayoutManager.VERTICAL);
        rvPuntos.setLayoutManager(rvPuntosLLM);

        handler = new PuntosHandler(this);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.puntos_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.btnFiltrarPuntos).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(handler);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        ((HomeActivity)this.getActivity()).setEnableDrawerListener(DrawerLayout.LOCK_MODE_UNLOCKED);
        ((HomeActivity)this.getActivity()).getSupportActionBar().show();
        ((HomeActivity)this.getActivity()).configDrawer();
        handler.printPuntos();
    }

    @Override
    public void onResume() {
        ((HomeActivity) getActivity()).setActionBarTitle("Puntos");
        super.onResume();
    }
}
