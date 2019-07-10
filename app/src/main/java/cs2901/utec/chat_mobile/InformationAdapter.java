package cs2901.utec.chat_mobile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InformationAdapter  extends RecyclerView.Adapter<InformationAdapter.ViewHolder> {
    public JSONArray elements;
    private Context mContext;
    public InformationAdapter(JSONArray elements, Context mContext)
    {
        this.elements = elements;
        this.mContext = mContext;

    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView firstLine;
        TextView secondLine;
        RelativeLayout container;

        public ViewHolder(View informationView)
        {
            super(informationView);
            firstLine = informationView.findViewById(R.id.element_view_first_line);
            secondLine = informationView.findViewById(R.id.element_view_second_line);
            container = informationView.findViewById(R.id.element_view_container);
        }
    }
    @NonNull
    @Override
    public InformationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(
                R.layout.information_view, parent, false
        );
        return new InformationAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull InformationAdapter.ViewHolder holder, int position) {
        try{
            JSONObject element = elements.getJSONObject(position);
            String cost = element.getString("cost");
            String quantity = element.getString("quantity");
            holder.firstLine.setText(quantity);
            holder.secondLine.setText(cost);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    @Override
    public int getItemCount() {
        return elements.length();
    }
}