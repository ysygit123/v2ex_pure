package org.github.v2ex.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

import org.github.v2ex.R;


/**
 * Created by shaoyi yu on 2015/12/20.
 */
public class SnackbarUtil {

    private static Snackbar mSnackbar;

    public static void show(View view, String msg, int flag) {

        if (flag == 0) {
            mSnackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        } else {
            mSnackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        }

        mSnackbar.show();
        mSnackbar.setAction(R.string.main_close, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackbar.dismiss();
            }
        });
    }
}
