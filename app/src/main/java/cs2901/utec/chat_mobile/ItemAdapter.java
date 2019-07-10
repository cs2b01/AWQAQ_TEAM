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

public class ItemAdapter  extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    public JSONArray elements;
    private Context mContext;
    public ItemAdapter(JSONArray elements, Context mContext)
    {
        this.elements = elements;
        this.mContext = mContext;

    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView firstLine;
        TextView secondLine;
        RelativeLayout container;

        public ViewHolder(View itemView)
        {
            super(itemView);
            firstLine = itemView.findViewById(R.id.element_view_first_line);
            secondLine = itemView.findViewById(R.id.element_view_second_line);
            container = itemView.findViewById(R.id.element_view_container);
        }
    }
    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(
                R.layout.information_view, parent, false
        );
        return new ItemAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        try{
            JSONObject element = elements.getJSONObject(position);
            String name = element.getString("name");
            String information = element.getString("information");
            holder.firstLine.setText(name);
            holder.secondLine.setText(information);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    @Override
    public int getItemCount() {
        return elements.length();
    }


}