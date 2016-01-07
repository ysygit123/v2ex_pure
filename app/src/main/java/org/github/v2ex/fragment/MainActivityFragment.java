package org.github.v2ex.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import org.github.v2ex.R;
import org.github.v2ex.api.ApiClient;
import org.github.v2ex.api.Callback;
import org.github.v2ex.model.InfoModel;
import timber.log.Timber;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

  public MainActivityFragment() {
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    loadData();
  }

  @Override public void onResume() {
    super.onResume();
  }

  void loadData() {
    try {
      ApiClient.instance().fetchSiteInfo(null, new Callback<InfoModel>() {
        @Override public void success(final InfoModel infoModel) {
          Timber.i("data: %s", infoModel.toString());
          Toast.makeText(getContext(), infoModel.description, Toast.LENGTH_SHORT).show();
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
