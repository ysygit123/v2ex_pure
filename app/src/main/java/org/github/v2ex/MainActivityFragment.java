package org.github.v2ex;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import org.github.v2ex.api.Api;
import org.github.v2ex.api.ApiClient;
import org.github.v2ex.api.Callback;
import org.github.v2ex.api.RxOkHttp;
import org.github.v2ex.model.InfoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

  private static Logger logger = LoggerFactory.getLogger(MainActivityFragment.class);

  public MainActivityFragment() {
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    try {
      ApiClient.instance().fetchSiteInfo(null, new Callback<InfoModel>() {
        @Override public void success(final InfoModel infoModel) {
          logger.info("data: " + infoModel.toString());
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
