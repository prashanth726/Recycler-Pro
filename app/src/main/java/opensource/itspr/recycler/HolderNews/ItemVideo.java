package opensource.itspr.recycler.HolderNews;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import opensource.itspr.recycler.Holders.NewsHolder;
import opensource.itspr.recycler.R;


/**
 * Created by Prashanth Reddy (facebook.com/pr.amrita) (github.com/itspr)  on 13-12-2015.
 */
public class ItemVideo extends NewsHolder {

    private TextView txtView;
    private TextView sourc;
    private ImageView imageView;
    private ImageView playIcon;

    public ItemVideo(View itemView) {
        super(itemView);
       imageView= (ImageView) itemView.findViewById(R.id.Poster);
        playIcon = (ImageView) itemView.findViewById(R.id.play);
        txtView = (TextView) itemView.findViewById(R.id.Title);


    }

    @Override
    public void bind(String image, String text, Context context) {
       // txtView.setText(text);

       // imageView.setImageResource(image);
    }

    @Override public void Feedbind(String Image, String title, String source, String permalink,String date,
        Context context) {

    }

    @Override public void Quotebind(String Image, String title, String author, Context context) {

    }

    @Override public void Imgbind(String Image, Context context) {

    }

    @Override public void Videobind(String Image, String title, String source, String permalink,
        Context context) {
 txtView.setText(title);

        Drawable drawable1 = ContextCompat.getDrawable(context, R.drawable.ic_play_circle_outline_black_24dp);
        Drawable drawable = DrawableCompat.wrap(drawable1);
        DrawableCompat.setTint(drawable, Color.WHITE);
        playIcon.setImageDrawable(drawable);
        ColorDrawable colorDrawable = new ColorDrawable(Color.LTGRAY);
        Glide.with(context).load(Image).centerCrop().placeholder(colorDrawable).crossFade().into(imageView);
    }
}
