package org.github.v2ex;

import android.app.Application;
import android.util.Log;
import org.github.v2ex.api.ApiClient;
import timber.log.Timber;

/**
 * Created by syxc on 1/6/16.
 */
public class V2EXApp extends Application {

  @Override public void onCreate() {
    super.onCreate();

    if (V2EXConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    } else {
      Timber.plant(new CrashReportingTree());
    }
  }

  @Override public void onTerminate() {
    super.onTerminate();
    ApiClient.instance().destroy();
  }

  /**
   * CrashReportingTree
   * A tree which logs important information for crash reporting.
   */
  private static class CrashReportingTree extends Timber.Tree {
    @Override protected void log(int priority, String tag, String message, Throwable t) {
      if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
        return;
      }

      FakeCrashLibrary.log(priority, tag, message);

      if (t != null) {
        if (priority == Log.ERROR) {
          FakeCrashLibrary.logError(t);
        } else if (priority == Log.WARN) {
          FakeCrashLibrary.logWarning(t);
        }
      }
    }
  }
}
