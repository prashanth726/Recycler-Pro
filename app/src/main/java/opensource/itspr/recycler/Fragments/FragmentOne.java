package opensource.itspr.recycler.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import opensource.itspr.recycler.Adapters.NewsAdapter;
import opensource.itspr.recycler.Parcers.NewsParcer;
import opensource.itspr.recycler.R;
import opensource.itspr.recycler.Util.Utility;
import opensource.itspr.recycler.Volley.CustomVolley;
import opensource.itspr.recycler.pojo.Movies;

/**
 * Created by Prashanth Reddy (facebook.com/pr.amrita) (github.com/itspr)  on 14-01-2016.
 */
public class FragmentOne extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    View root;
    NewsAdapter newsAdapter;
    RequestQueue requestQueue;
    SwipeRefreshLayout mSwipeRefreshLayout;
    LinearLayoutManager LayoutManager;
    public static String STORY_BUNDLE = "story_bundle";
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 4;
    int lastVisibleItem, visibleItemCount, totalItemCount;
    public static String REQUEST_TAG = "Feed";
    boolean isDialogShown;



    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        newsAdapter  =new NewsAdapter(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_one, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.Recycler);
        LayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(LayoutManager);
        // listAdapter adapter = new ListAdapter(getActivity());
        recyclerView.setAdapter(newsAdapter);
        mSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        requestQueue = CustomVolley.getInstance(getActivity()).getRequestQueue();



        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = LayoutManager.getItemCount();
                lastVisibleItem = LayoutManager.findLastVisibleItemPosition();

                loading = mSwipeRefreshLayout.isRefreshing();
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount) <= (lastVisibleItem + visibleThreshold)) {
                    loadFeed(totalItemCount/20 + 1, false);
                    loading = true;
                }
            }
        });

        //TODO add auto loading next pages

        if(savedInstanceState==null){


            //load first page if starting up first time
            loadFeed(1, true);

        }
        else
        {

            Log.d("Hey","Imcsk kndnnn");


            newsAdapter.set(savedInstanceState.<Movies>getParcelableArrayList(STORY_BUNDLE));
            //loadFeed(1);
            //load from bundle if recreating


            // listAdapter.set(savedInstanceState.<Story>getParcelableArrayList(STORY_BUNDLE));
        }
        return root;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(newsAdapter!=null)
            outState.putParcelableArrayList(STORY_BUNDLE, newsAdapter.getFeedList());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        //make sure response handlers are not called if
        if(requestQueue!=null)
            requestQueue.cancelAll(REQUEST_TAG);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(), "Refreshed", Toast.LENGTH_SHORT).show();
        loadFeed(1, true);
    }




    public  void showDialog(final Context context) {


        AlertDialog.Builder builder =
                new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("No Internet Connection");
        builder.setMessage("Please check your internet connection and try again");
        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isDialogShown=false;
                loadFeed(1, true);
                // new TaskLoadFeed(getActivity(), this).execute();
            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isDialogShown=false;
            }
        });

        if(!isDialogShown) {
            builder.show();
            isDialogShown=true;
        }
    }

    private void loadFeed(int page, final boolean reset){
        String url = Utility.buildNewsUri(String.valueOf(page)).toString();
        Log.d("Resopnceurl",url);
        StringRequest request = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        if (reset) newsAdapter.set(NewsParcer.parse(response));
                        else newsAdapter.add(NewsParcer.parse(response));
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("Resopnce", String.valueOf(error));
                        mSwipeRefreshLayout.setRefreshing(false);
                        showDialog(getActivity());
                        //Snackbar.make(root, "Slow OR Bad Internet Connection", Snackbar.LENGTH_LONG)
                        //    .setAction("Action", null).show();
                        Toast.makeText(getActivity(), "Slow OR Bad Internet Connection", Toast.LENGTH_SHORT).show();
                        //Snackbar.make(root, "Something went wrong", Snackbar.LENGTH_SHORT);
                    }
                });
        request.setTag(REQUEST_TAG);
        requestQueue.add(request);
        mSwipeRefreshLayout.setRefreshing(true);
    }



}
