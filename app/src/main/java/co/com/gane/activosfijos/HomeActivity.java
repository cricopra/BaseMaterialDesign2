package co.com.gane.activosfijos;

import android.app.Fragment;
import android.app.FragmentManager;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

//import co.com.gane.activosfijos.BluetoothReader.AsciiCommander;
//import co.com.gane.activosfijos.BluetoothReader.DeviceListActivity;
//import co.com.gane.activosfijos.BluetoothReader.ReadWriteModel;
//import co.com.gane.activosfijos.BluetoothReader.TSLBluetoothDeviceActivity;
import org.json.JSONObject;

import co.com.gane.activosfijos.DBManager.DatabaseHelper;
import co.com.gane.activosfijos.DBManager.LoadBDHandler;
import co.com.gane.activosfijos.autenticacion.AutenticacionFragment;
import co.com.gane.activosfijos.ayuda.BluetoothReader;
import co.com.gane.activosfijos.ayuda.Constantes;
import co.com.gane.activosfijos.ayuda.Utilidades;


/**
 * Created by SNavia on 12/04/2016.
 */
public class HomeActivity extends BluetoothReader implements NavigationView.OnNavigationItemSelectedListener {

    public ActionBarDrawerToggle toggle;
    public Toolbar toolbar;
    private DrawerLayout drawer;
    public static HomeActivity instance;
    public LoadBDHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.deleteDatabase("activosfijos.db");
        dbHandler = new LoadBDHandler(this);
        instance = this;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();*/
        configDrawer();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new AutenticacionFragment(), false);
    }

    @Override
    public void setSbMac(String sbMac) {
        super.setSbMac(sbMac);
    }

    @Override
    public void bluetoothConnect() throws Exception {
        super.bluetoothConnect();
    }

    @Override
    public void StartBuffer() {
        super.StartBuffer();
    }

    @Override
    public void bluetoothDisconnect() {
        super.bluetoothDisconnect();
    }

    public void setEnableDrawerListener(int mode){
        this.drawer.setDrawerLockMode(mode);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fm = getFragmentManager();
            if(fm.getBackStackEntryCount() > 0 ) {
                fm.popBackStack();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            //noinspection SimplifiableIfStatement
            case R.id.action_settings:
                break;

        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Reemplaza un fragment en el activity principal
     * @param fragment - Pantalla a reemplzar
     * @param isAddStack - Boolean que condiciona si adiciona o no la pila
     */
    public static void replaceFragment(Fragment fragment, boolean isAddStack){
        if(isAddStack) {
            HomeActivity.instance.getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in, R.animator.slide_out, R.animator.fade_in, R.animator.fade_out).replace(R.id.content_main, fragment).addToBackStack(null).commit();
        } else {
            HomeActivity.instance.getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in, R.animator.slide_out, R.animator.fade_in, R.animator.fade_out).replace(R.id.content_main, fragment).commit();
        }
    }

    public void configDrawer() {
        drawer = (DrawerLayout) HomeActivity.instance.findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                instance, drawer, instance.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(instance.toggle);
        toggle.syncState();
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public Boolean isConnected () {
        return readerIsConnected();
    }

/*    public AsciiCommander getCommanderFromActivity() {
        return getCommander();
    }

    //FUNCIONES DEL LECTOR DE RFID
    public void selectDevice()
    {
        // Launch the DeviceListActivity to see devices and do scan
        Intent serverIntent = new Intent(this, DeviceListActivity.class);
        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_INSECURE);
    }
    public void reconnectDevice()
    {
        getCommander().connect(null);
    }
    private void UpdateUI()
    {
        boolean isConnected = getCommander().isConnected();
        //boolean canIssueCommand = isConnected & !mModel.isBusy();
//        mReadActionButton.setEnabled(canIssueCommand);
//        // Only enable the write button when there is at least a partial EPC
//        mWriteActionButton.setEnabled(canIssueCommand && mTargetTagEditText.getText().length() != 0);
    }

    public void disconnectDevice()
    {
        //this.mDevice = null;
        getCommander().disconnect();
    }
    public void displayReaderState()
    {
        String connectionMsg = "El lector: " + (getCommander().isConnected() ? getCommander().getConnectedDeviceName() : "se ha desconectado.");
        Utilidades.showAlertDialog(this,connectionMsg);
    }*/
}
