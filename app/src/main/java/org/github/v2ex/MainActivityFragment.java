package org.github.v2ex;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import org.github.v2ex.api.APIClient;
import org.github.v2ex.api.Callback;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

  private static final String TAG = "MainActivityFragment";

  public MainActivityFragment() {
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    try {
      APIClient.instance().fetchSiteInfo(null, new Callback() {
        @Override public void success(final Object o) {
          Log.i(TAG, "data: " + o.toString());
          getActivity().runOnUiThread(new Runnable() {
            @Override public void run() {
              Toast.makeText(getContext(), o.toString(), Toast.LENGTH_SHORT).show();
            }
          });
        }

        @Override public void failure(String error) {
          Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
