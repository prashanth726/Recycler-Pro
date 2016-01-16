package opensource.itspr.recycler.Util.customtabs;

import android.app.Activity;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;

/**
 * Created by parth on 1/15/2016.
 */
public class OpenCustomTab extends Activity {
  private CustomTabActivityHelper mCustomTabActivityHelper;
  private void setupCustomTabHelper(String permlink) {
    mCustomTabActivityHelper = new CustomTabActivityHelper();
    mCustomTabActivityHelper.setConnectionCallback(mConnectionCallback);
    mCustomTabActivityHelper.mayLaunchUrl(Uri.parse(permlink), null, null);
  }

  public static void openCustomTab(String permalink,Activity activity) {
    CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
  //CustomTabActivityHelper.openCustomTab(
  //      activity, intentBuilder.build(), Uri.parse(permalink), new WebviewFallback());
  }

  private CustomTabActivityHelper.ConnectionCallback mConnectionCallback = new CustomTabActivityHelper.ConnectionCallback() {
    @Override
    public void onCustomTabsConnected() {
    //  SnackbarFactory.createSnackbar(MainActivity.this, mLayoutMainCoordinator, "Connected to service").show();
    }

    @Override
    public void onCustomTabsDisconnected() {
    //  SnackbarFactory.createSnackbar(MainActivity.this, mLayoutMainCoordinator, "Disconnected from service").show();
    }
  };
}
