package opensource.itspr.recycler.Util;


import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Prashanth Reddy (facebook.com/pr.amrita) (github.com/itspr)  on 12/2/14.
 */
public class NetworkUtil {

    public static boolean haveNetworkConnection(Context context) {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo() != null;
        }
        return false;
    }
}

