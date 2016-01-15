package opensource.itspr.recycler.Parcers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import opensource.itspr.recycler.Constants.GlobalConstants;
import opensource.itspr.recycler.Util.Utility;
import opensource.itspr.recycler.pojo.Movies;

/**
 * Created by Prashanth Reddy (facebook.com/pr.amrita) (github.com/itspr)  on 1/4/2016.
 */
public class NewsParcer {
  private static final String TAG_RESULTS = "results";
  private static final String TAG_TITLE = "title";
  private static final String TAG_ID = "id";
  private static final String TAG_DATE = "date";
  private static final String TAG_SOURCE = "SOURCE";
  private static final String TAG_AUTHOR = "author";
  private static final String TAG_PERMALINK = "permalink";
  private static final String TAG_FEATURED_IMG = "featured_img";
  private static final String TAG_VIEW_TYPE = "VIEW_TYPE";


  public static ArrayList<Movies> parse(String jsonData) {

    ArrayList<Movies> MovieList = new ArrayList<>();

    if(jsonData==null)
      return MovieList;

    try {
      JSONArray storyArray = new JSONObject(jsonData).getJSONArray(TAG_RESULTS);
      int length = storyArray.length();

      for(int i=0; i<length; i++)

      {


        String movie_title = GlobalConstants.NA;
        String movie_id = GlobalConstants.NA;
        String VIEW_TYPE = GlobalConstants.NA;
        String release_date = GlobalConstants.NA;
        String movie_source = GlobalConstants.NA;
        String permalink = GlobalConstants.NA;
        String featured_img = GlobalConstants.NA;
        String movie_director = GlobalConstants.NA;


        Movies movie = new Movies();
        JSONObject storyObject = storyArray.getJSONObject(i);


        if (Utility.contains(storyObject, TAG_TITLE)) {
          movie_title = storyObject.getString(TAG_TITLE);
        }

        if (Utility.contains(storyObject, TAG_ID)) {
          movie_id = storyObject.getString(TAG_ID);
        }

        if (Utility.contains(storyObject, TAG_AUTHOR)) {
          movie_director = storyObject.getString(TAG_AUTHOR);
        }


        if (Utility.contains(storyObject, TAG_DATE)) {
          release_date = storyObject.getString(TAG_DATE);
        }
        if (Utility.contains(storyObject, TAG_SOURCE)) {
          movie_source = storyObject.getString(TAG_SOURCE);
        }
        if (Utility.contains(storyObject, TAG_PERMALINK)) {
          permalink = storyObject.getString(TAG_PERMALINK);
        }

        if (Utility.contains(storyObject, TAG_FEATURED_IMG)) {
          featured_img = storyObject.getString(TAG_FEATURED_IMG);
        }

        if (Utility.contains(storyObject, TAG_VIEW_TYPE)) {
          VIEW_TYPE = storyObject.getString(TAG_VIEW_TYPE);
        }





        movie.setMovie_title(movie_title);
        movie.setMovie_id(movie_id);
        movie.setMovie_director(movie_director);
        movie.setRelease_date(release_date);
        movie.setMovie_source(movie_source);
        movie.setFeatured_img(featured_img);
        movie.setPermalink(permalink);
        movie.setVIEW_TYPE(VIEW_TYPE);






        MovieList.add(movie);
      }

    }

    catch (JSONException e) {
      e.printStackTrace();
    }

    return MovieList;

  }

}

