package cs2901.utec.chat_mobile;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ItemsActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        mRecyclerView = findViewById(R.id.main_recycler_view);
    }
    @Override
    protected void onResume(){
        super.onResume();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        getItems();
    }
    public Activity getActivity(){
        return this;
    }


    public void getItems(){
        String url = "http://10.0.2.2:8000/items";
        RequestQueue queue = Volley.newRequestQueue(this);
        Map<String, String> params = new HashMap();
        JSONObject parameters = new JSONObject(params);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray data = response.getJSONArray("data");
                            mAdapter = new ItemAdapter(data, getActivity());
                            mRecyclerView.setAdapter(mAdapter);

                        }catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        queue.add(jsonObjectRequest);

    }
    public void onbtnInformationClicked(View view){
        Intent intent = new Intent(getActivity(),InformationActivity.class);
        startActivity(intent);
    }

}