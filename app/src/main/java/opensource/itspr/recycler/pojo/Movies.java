package opensource.itspr.recycler.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Prashanth Reddy (facebook.com/pr.amrita) (github.com/itspr)  on 1/4/2016.
 */
public class Movies implements Parcelable {

  public String movie_id;
  public String movie_title;
  public String VIEW_TYPE;
  public String release_date;
  public String movie_source;
  public String movie_director;
  public String featured_img;
  public String permalink;


  public Movies(){}

  protected Movies(Parcel in) {
    movie_id = in.readString();
    movie_title = in.readString();
    release_date = in.readString();
    movie_director = in.readString();
    featured_img = in.readString();
    permalink = in.readString();
    VIEW_TYPE = in.readString();
    movie_source = in.readString();

  }


  /**
   * Setting
   */

  public void setMovie_title(String story_title) {
    this.movie_title = story_title;
  }

  public void setPermalink(String permalink) {
    this.permalink = permalink;
  }

  public void setFeatured_img(String featured_img) {
    this.featured_img = featured_img;
  }

  public void setMovie_id(String story_id) {
    this.movie_id = story_id;
  }

  public void setVIEW_TYPE(String VIEW_TYPE) {
    this.VIEW_TYPE = VIEW_TYPE;
  }

  public void setRelease_date(String story_date) {
    this.release_date = story_date;
  }

  public void setMovie_source(String story_source) {
    this.movie_source = story_source;
  }

  public void setMovie_director(String movie_director) {
    this.movie_director = movie_director;
  }

  /**
   * Getting
   */
  public String getMovie_title() {
    return movie_title;
  }

  public String getMovie_id() {
    return movie_id;
  }

  public String getMovie_date() {
    return release_date;
  }
  public String getMovie_source() {
    return movie_source;
  }

  public String getVIEW_TYPE() {
    return VIEW_TYPE;
  }
  public String getMovie_director() {
    return movie_director;
  }
  public String getPermalink() {
    return permalink;
  }

  public String getFeatured_img() {
    return featured_img;
  }



  public static final Creator<Movies> CREATOR = new Creator<Movies>() {
    @Override
    public Movies createFromParcel(Parcel in) {
      return new Movies(in);
    }

    @Override
    public Movies[] newArray(int size) {
      return new Movies[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(movie_id);
    dest.writeString(movie_title);
    dest.writeString(release_date);
    dest.writeString(movie_director);
    dest.writeString(permalink);
    dest.writeString(featured_img);
    dest.writeString(VIEW_TYPE);

  }
}
