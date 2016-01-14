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
public class ItemQuote extends NewsHolder {

    private TextView txtView;
private ImageView imageView;
private TextView autho;

    public ItemQuote(View itemView) {
        super(itemView);
        txtView = (TextView) itemView.findViewById(R.id.Tittle);
        imageView= (ImageView) itemView.findViewById(R.id.Poster);
        autho =(TextView) itemView.findViewById(R.id.autho);
    }

    @Override
    public void bind(String image, String text, Context context) {

    }

    @Override public void Feedbind(String Image, String title, String source, String permalink,String date,
        Context context) {

    }

    @Override public void Quotebind(String Image, String title, String author, Context context) {
        txtView.setText(title);
        autho.setText(author);

        ColorDrawable colorDrawable = new ColorDrawable(Color.LTGRAY);
        Glide.with(context).load(Image).centerCrop().placeholder(colorDrawable).crossFade().into(imageView);
        //Picasso.with(context).load(Image).resize(300,150).centerCrop().into(imageView);
    }

    @Override public void Imgbind(String Image, Context context) {

    }

    @Override public void Videobind(String Image, String title, String source, String permalink,
        Context context) {

    }
}
