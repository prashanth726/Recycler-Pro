package opensource.itspr.recycler.Holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class NewsHolder extends RecyclerView.ViewHolder {

  public NewsHolder(View itemView) {
    super(itemView);
  }

  public abstract void bind(String Image, String text, Context context);


  public abstract void Feedbind(String Image, String title,String source,String permalink,String date,Context context);
  public abstract void Quotebind(String Image, String title, String author, Context context);
  public abstract void Imgbind(String Image, Context context);
  public abstract void Videobind(String Image, String title, String source,String permalink, Context context);


}
