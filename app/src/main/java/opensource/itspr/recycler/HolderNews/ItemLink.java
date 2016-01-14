package opensource.itspr.recycler.HolderNews;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import opensource.itspr.recycler.Holders.NewsHolder;
import opensource.itspr.recycler.R;


/**
 * Created by Prashanth Reddy (facebook.com/pr.amrita) (github.com/itspr)  on 13-12-2015.
 */
public class ItemLink extends NewsHolder {

    private TextView txtView;
    private ImageView imageView;
    private  TextView  sourcee;
    private  TextView datee;
    public ItemLink(View itemView) {
        super(itemView);
      txtView  = (TextView) itemView.findViewById(R.id.txt);
        sourcee = (TextView) itemView.findViewById(R.id.source);
        datee = (TextView) itemView.findViewById(R.id.date);

        imageView = (ImageView) itemView.findViewById(R.id.Poster);
    }

    @Override
    public void bind(String image, String text, Context context) {

    }

    @Override public void Feedbind(String Image, String title, String source, String permalink,String date,
        Context context) {
        ColorDrawable colorDrawable = new ColorDrawable(Color.LTGRAY);
       // Drawable drawable1 = ContextCompat.getDrawable(context, R.drawable.ic_update_black_24dp);
        //Drawable drawable = DrawableCompat.wrap(drawable1);
        //DrawableCompat.setTint(drawable, Color.RED);

        Glide.with(context).load(Image).centerCrop().placeholder(colorDrawable).crossFade().into(imageView);
        txtView.setText(title);
        sourcee.setText(source);
        datee.setText(date);

    }

    @Override public void Quotebind(String Image, String title, String author, Context context) {

    }

    @Override public void Imgbind(String Image, Context context) {

    }

    @Override public void Videobind(String Image, String title, String source, String permalink,
        Context context) {

    }
}
