package com.example.a1084081.oriondemoapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.a1084081.oriondemoapp.model.User;
import com.example.a1084081.oriondemoapp.util.AtoZComparator;
import com.example.a1084081.oriondemoapp.util.DividerItemDecoration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    ProgressDialog pDialog ;
    RecyclerView mRecyclerView;
    List<User> posts = new ArrayList<User>();
    SimpleItemRecyclerViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Contacts");

        mRecyclerView =(RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));



        if (!isInternetConnectionAvailable(getBaseContext())) {
            showNetworkErrorSnackBar();
//            showErrorDialog();
        }else{
            getUserData();
        }


    }

    private void getUserData(){
        //request user data from url

        String url = "http://jsonplaceholder.typicode.com/users";

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading contacts...");
        pDialog.setCancelable(false);
        pDialog.show();

        StringRequest req = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                        pDialog.hide();
                        Gson gson =new GsonBuilder().create();

//parse as a User array (which we convert into a list)
                        posts = Arrays.asList(gson.fromJson(response, User[].class));
                        setupRecyclerView(posts);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.hide();
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);

    }

    private void showNetworkErrorSnackBar() {

        final Snackbar snackBar = Snackbar.make(mRecyclerView, R.string.snackbar_network_error_text, Snackbar.LENGTH_LONG);
        snackBar.setAction(R.string.snackbar_network_error_action_text, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isInternetConnectionAvailable(getBaseContext())) {
                    snackBar.dismiss();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!isInternetConnectionAvailable(getBaseContext())) {
                                snackBar.show();
                            }else{
                                getUserData();
                            }
                        }
                    }, 1000);
                }
            }
        })  // action text on the right side
                .setActionTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimary))
                .setDuration(Snackbar.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_a_to_z) {
            Collections.sort(posts,new AtoZComparator());
            setupRecyclerView(posts);
            return true;
        }

        if (id == R.id.action_z_to_a) {
            Collections.sort(posts,Collections.reverseOrder(new AtoZComparator()));
            setupRecyclerView(posts);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void setupRecyclerView( List<User> items) {

        mAdapter  = new SimpleItemRecyclerViewAdapter(items);
        mRecyclerView.setAdapter(mAdapter);
    }


    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<User> mValues;

        public SimpleItemRecyclerViewAdapter(List<User> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.user_list_content, parent, false);
            return new SimpleItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final SimpleItemRecyclerViewAdapter.ViewHolder holder, final int position) {
            User mItem = mValues.get(position);
            holder.mIdView.setText(mItem.getName().toUpperCase());
            holder.mContentView.setText(mItem.getEmail().toUpperCase());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Context context = v.getContext();
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("user", posts.get(position));

                        context.startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public User mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.textViewName);
                mContentView = (TextView) view.findViewById(R.id.textViewEmail);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }

    public boolean isInternetConnectionAvailable(Context context) {

        // check network connection
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return null != networkInfo && networkInfo.isConnected();
    }
}
