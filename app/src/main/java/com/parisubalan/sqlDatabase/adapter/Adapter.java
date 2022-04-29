package com.parisubalan.sqlDatabase.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.parisubalan.sqlDatabase.R;
import com.parisubalan.sqlDatabase.pojo.PojoClass;
import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;
    ArrayList<PojoClass> pojoArrayList;
    public Adapter (Context context, ArrayList<PojoClass> arrayList)
    {
        this.context = context;
        this.pojoArrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_viewer,parent,false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PojoClass pojo_class = pojoArrayList.get(position);
        holder.list_view.setText(pojo_class.getName()+" " + pojo_class.getMobile()+ " " +
                pojo_class.getStandard() + " " + pojo_class.getSection());
    }

    @Override
    public int getItemCount() {
        return pojoArrayList.size();
    }

    /*public void filterList(ArrayList<Pojo_Class> filteredList) {
        pojoArrayList = filteredList;
        notifyDataSetChanged();
    }*/

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView list_view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            list_view = itemView.findViewById(R.id.list_view);
        }
    }
}
