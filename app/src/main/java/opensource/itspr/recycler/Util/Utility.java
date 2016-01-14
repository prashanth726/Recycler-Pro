package opensource.itspr.recycler.Util;

import android.net.Uri;

import org.json.JSONObject;

import opensource.itspr.recycler.Constants.GlobalConstants;

/**
 * Created by Prashanth Reddy (facebook.com/pr.amrita) (github.com/itspr)  on 04-01-2016.
 */
public class Utility {

    public static Uri buildNewsUri(String Page)

    {
        return Uri.parse(GlobalConstants.FEED_URL).buildUpon()
                .appendQueryParameter(GlobalConstants.PAGE_PARAM, Page)
                .build();


    }

    public static boolean contains(JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key) && !jsonObject.isNull(key) ? true : false;
    }
}
