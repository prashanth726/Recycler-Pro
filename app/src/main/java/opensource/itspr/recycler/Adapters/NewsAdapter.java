package opensource.itspr.recycler.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.volley.toolbox.ImageLoader;
import java.util.ArrayList;
import opensource.itspr.recycler.HolderNews.ItemImage;
import opensource.itspr.recycler.HolderNews.ItemLink;
import opensource.itspr.recycler.HolderNews.ItemQuote;
import opensource.itspr.recycler.HolderNews.ItemVideo;
import opensource.itspr.recycler.Holders.NewsHolder;
import opensource.itspr.recycler.R;
import opensource.itspr.recycler.Volley.CustomVolley;
import opensource.itspr.recycler.pojo.Movies;

/**
 * Created by Prashanth Reddy (facebook.com/pr.amrita) (github.com/itspr)  on 14-01-2016.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsHolder> {
    private ArrayList<Movies> storyList = new ArrayList<>();
    private Context mContext;
    private ImageLoader mImageLoader;
    private  Activity activityy;


    public NewsAdapter(Context context,Activity activity){
        mContext = context;
        mImageLoader = CustomVolley.getInstance(mContext).getImageLoader();
        activityy=activity;
    }

    public static final int VIEW_TYPE_LINK = 0;
    public static final int VIEW_TYPE_VIDEO = 2;
    public static final int VIEW_TYPE_IMAGE = 1;
    public static final int VIEW_TYPE_QUOTE = 3;

    public void set(ArrayList<Movies> data){
        storyList = data;
        notifyDataSetChanged();
    }

    public void add(ArrayList<Movies> data){


        for(Movies info : data) {
            storyList.add(info);

        }

        notifyDataSetChanged();
    }

    public void add(Movies data){
        storyList.add(data);
        notifyDataSetChanged();
    }

    public ArrayList<Movies> getFeedList(){
        return storyList;
    }

    @Override public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        if (viewType == VIEW_TYPE_LINK) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_link, parent, false);
            return new ItemLink(itemView,activityy);

        }

        if (viewType == VIEW_TYPE_VIDEO) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
            ImageView image= (ImageView) itemView.findViewById(R.id.Poster);
            image.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            return new ItemVideo(itemView,activityy);
        }
        if (viewType == VIEW_TYPE_IMAGE)
        {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
            return new ItemImage(itemView);
        }

        if (viewType == VIEW_TYPE_QUOTE)
        {

            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quote, parent, false);
            ImageView image= (ImageView) itemView.findViewById(R.id.Poster);
            image.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            return new ItemQuote(itemView);


        }

        else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quote, parent, false);
            ImageView image= (ImageView) itemView.findViewById(R.id.Poster);
            image.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            return new ItemQuote(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Movies movie = storyList.get(position);

        if (movie.VIEW_TYPE.equals("LINK")) {
            return VIEW_TYPE_LINK;
        }
        if (movie.VIEW_TYPE.equals("QUOTE")) {
            return VIEW_TYPE_QUOTE;
        }
        if (movie.VIEW_TYPE.equals("VIDEO")) {
            return VIEW_TYPE_VIDEO;
        }
        if (movie.VIEW_TYPE.equals("IMAGE")) {
            return VIEW_TYPE_IMAGE;
        }

        return VIEW_TYPE_QUOTE;
    }

    @Override public void onBindViewHolder(NewsHolder holder, int position) {

        Movies movie = storyList.get(position);

        if (movie.VIEW_TYPE.equals("LINK")) {
            String title = movie.movie_title;
            String source = movie.movie_source;
            String image = movie.featured_img;
            String permalink = movie.permalink;
            String date =movie.release_date;
            holder.Feedbind(image,title,source,permalink,date,mContext);

        }

        if (movie.VIEW_TYPE.equals("QUOTE")) {
            String title = movie.movie_title;
            String image = movie.featured_img;
            String author = movie.movie_director;
            holder.Quotebind(image,title,author,mContext);
        }

        if (movie.VIEW_TYPE.equals("VIDEO")) {
            String title = movie.movie_title;
            String image = movie.featured_img;
            String source = movie.movie_source;
            String permalink = movie.permalink;
            holder.Videobind(image,title,source,permalink,mContext);
        }

        if (movie.VIEW_TYPE.equals("IMAGE")) {
            String title = movie.movie_title;
            String image = movie.featured_img;
            holder.Imgbind(image,mContext);
        }

        String title = movie.movie_title;
        String image = movie.featured_img;
        Log.d("HelloTitle", title);
        holder.bind(image, title,mContext);

     /*if (holder instanceof ItemLink) {
            ((ItemLink) holder).bind(image,text);
    }*/
    }


    @Override public int getItemCount() {
        return storyList.size();
    }
}

