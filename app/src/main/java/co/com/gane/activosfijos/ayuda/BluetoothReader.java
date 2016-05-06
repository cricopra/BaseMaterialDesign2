package co.com.gane.activosfijos.ayuda;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

import co.com.gane.activosfijos.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Propiedad intelectual de Play Technologies S.A.S
 * SNavia
 * Playtechla.com 2016
 * Todos los derechos reservados
 */
public class BluetoothReader extends AppCompatActivity {
    public static final String TAG = "PlayTech.TAG.DemoPrinter";
    private static final UUID UUID_DEVICE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    public String sbMac;
    private OutputStream osBuffer;
    private InputStream isBuffer;
    private BluetoothSocket bluetoothSocket;
    private boolean isConnected = false;
    public Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.ctx = this;
    }

    public void setSbMac(String sbMac){
        this.sbMac = sbMac;
    }

    public void bluetoothConnect() throws Exception{
        try {
            if("".equals(this.sbMac)) {
                throw new Exception("MAC de la impresora invalida");
            }

            BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
            BluetoothDevice bd = ba.getRemoteDevice(this.sbMac);
            this.bluetoothSocket = this.createBluetoothSocket(bd);
            this.bluetoothSocket.connect();

            this.osBuffer = this.bluetoothSocket.getOutputStream();
            this.isBuffer = this.bluetoothSocket.getInputStream();

            this.isConnected = true;

            //new Inspector(this.isBuffer).start();
        } catch (Exception e){
            throw e;
        }
    }

    public void StartBuffer(){
        new Inspector(this.isBuffer).start();
    }

    public void bluetoothDisconnect(){
        try {
            this.osBuffer.close();
            this.bluetoothSocket.close();

            this.isConnected = false;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Boolean readerIsConnected(){
        return this.isConnected;
    }

    private void print(byte[] rcByte){
        try {
            if (this.osBuffer != null) {
                osBuffer.write(rcByte);
                osBuffer.write("\n\nPlay Technologies S.A.S\nTodos los derechos reservados\n".getBytes());
                osBuffer.write(0x0A);
            }
        } catch (Exception e){
            Toast.makeText(this, "Error al imprimir:" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        if (Build.VERSION.SDK_INT >= 10) {
            try {
                final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[]{UUID.class});
                return (BluetoothSocket) m.invoke(device, UUID_DEVICE);
            } catch (Throwable e) {
                Log.w(TAG, "Could not create Insecure RFComm Connection", e);
                return device.createRfcommSocketToServiceRecord(UUID_DEVICE);
            }
        }
        return device.createRfcommSocketToServiceRecord(UUID_DEVICE);
    }

    private View.OnClickListener bluetoothLinkListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject jsDatosConfig = new JSONObject(Utilidades.getDataUserPreferences(ctx, Constantes.CONSTANTES_CONFIGURACION));
                    sbMac = jsDatosConfig.getString("sbMacLector");
                } catch(Exception e){
                    Utilidades.showAlertDialog(ctx, "ERROR === " + e.toString());
                }
                new BluetoothLinkTask().execute();
            }
        };
    }

    private View.OnClickListener onlyTextListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    print("Prueba de impresion exitosa".getBytes());
                } catch (Exception e){
                    Toast.makeText(BluetoothReader.this, "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    private View.OnClickListener stylesListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String sbPrint = "Text normal\n" +
                        BluetoothPrinter.left("Texto izquierda") + "\n" +
                        BluetoothPrinter.right("Texto derecha") + "\n" +
                        BluetoothPrinter.center("Texto centrado") + "\n" +
                        BluetoothPrinter.left("") +
                        BluetoothPrinter.bold("Texto en negrita") + "\n" +
                        BluetoothPrinter.fontLarge("Fuente large") + "\n" +
                        BluetoothPrinter.fontSmall("Fuente small") + "\n" +
                        BluetoothPrinter.reverse("Invertido") + "\n" +
                        BluetoothPrinter.center(BluetoothPrinter.reverse("Centrado invertido")) + "\n" +
                        BluetoothPrinter.left("Estilo normal");

                    byte[] rcPrint = BluetoothPrinter.print(BluetoothReader.this, 0, 0, sbPrint);

                    print(rcPrint);
                } catch (Exception e){
                    Toast.makeText(BluetoothReader.this, "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    private View.OnClickListener barCodeListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    byte[] rcPrint = BluetoothImagePrinter.getBarcode("1234567890123", 0, 67, 2, 96, 0, 2);

                    print(rcPrint);
                } catch (Exception e){
                    Toast.makeText(BluetoothReader.this, "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    private View.OnClickListener QRListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    byte[] rcPrint = BluetoothImagePrinter.getDataQRCode("PlayTech.QR.0123456789", 5);

                    print(rcPrint);
                } catch (Exception e){
                    Toast.makeText(BluetoothReader.this, "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    private View.OnClickListener imageListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Bitmap bitmapOrg = BitmapFactory.decodeResource(BluetoothReader.this.getResources(), R.mipmap.playtech_h);
                    ByteArrayOutputStream bao = new ByteArrayOutputStream();
                    //bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 100, bao);
                    byte[] rcImage = bao.toByteArray();
                    rcImage = BluetoothImagePrinter.getImage(Base64.encodeToString(rcImage, Base64.DEFAULT), 370);

                    print(rcImage);
                } catch (Exception e){
                    Toast.makeText(BluetoothReader.this, "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    private class BluetoothLinkTask extends AsyncTask<String, Void, Object> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Utilidades.showMessgaeProgress(ctx);
        }

        @Override
        protected Object doInBackground(String... params) {
            if(!isConnected){
                try {
                    bluetoothConnect();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            else{
                bluetoothDisconnect();
            }

            return  null;
        }

        @Override
        protected void onPostExecute(Object result) {
            Utilidades.stopMessgaeProgress();
        }
    }

    class Inspector extends Thread{

        private InputStream is;

        public Inspector(InputStream is){
            this.is = is;
        }

        @Override
        public void run() {
            byte[] buffer = new byte[256];
            int bytes;
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = is.read(buffer);
                    Log.d(TAG, "Read lector:[" + bytes + "] -> " + new String(buffer).substring(0, bytes));
                } catch (Throwable e) {
                    Log.e("ERROR", e.getMessage(), e);
                    break;
                }
            }

        }
    }
}
