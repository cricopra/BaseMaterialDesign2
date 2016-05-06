package co.com.gane.activosfijos.ayuda;

import android.app.Fragment;
import android.view.View;

/**
 * Created by Playtech2 on 12/04/2016.
 */
public class BackStack implements View.OnClickListener {

    private Fragment frg;

    public BackStack(Fragment frg) {
        this.frg = frg;
    }

    @Override
    public void onClick(View v) {
        try {
            this.frg.getFragmentManager().popBackStack();
        } catch (Exception e){
            //Log.e(TAG, e.getMessage(), e);
        }
    }
}
