package com.example.attendence;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class AdapterHorizontal extends RecyclerView.Adapter<AdapterHorizontal.ViewHolder> {

    List<ModelHorizontal> chatItem;
    Context context;

    public AdapterHorizontal(List< ModelHorizontal > chatItem, Context context) {
        this.chatItem = chatItem;
        this.context = context;
        Log.e( "djvhjvhvvjhc", "AdapterHorizontal: "+chatItem.size()  );
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        Log.e( "bncbcx", "onBindViewHolder: "+view  );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Text11.setText(chatItem.get(position).getCI());
        holder.Text22.setText(chatItem.get(position).getCO());

    }


    @Override
    public int getItemCount() {
        Log.e( "vjnvjbjcnjn", "getItemCount: "+chatItem.size()  );
        return chatItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Text11,Text22;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Text11 = itemView.findViewById(R.id.tex1);
            Text22 = itemView.findViewById(R.id.tex2);

        }
    }
}
