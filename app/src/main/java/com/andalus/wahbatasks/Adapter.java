package com.andalus.wahbatasks;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder>
{
    List<Inventory> list;
    Context context;
    private ListItemClickListener mOnListItemClick;


    public Adapter( List<Inventory> list, ListItemClickListener listItemClickListener) {
        this.list = list;
        this.mOnListItemClick=listItemClickListener;
    }

    public interface ListItemClickListener
    {
        void onListItemClickListener(List<Inventory> list, int index);
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        context=viewGroup.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder viewHolder, int i) {
        Inventory inventory=list.get(i);
        viewHolder.nameTextView.setText(inventory.getName());
        viewHolder.sizeTextView.setText(inventory.getSize());
        viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(inventory.getImage()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView imageView;
        TextView nameTextView;
        TextView sizeTextView;

        public viewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.image_view);
            nameTextView=(TextView)itemView.findViewById(R.id.name_text_view);
            sizeTextView=(TextView)itemView.findViewById(R.id.size_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            mOnListItemClick.onListItemClickListener(list, position);

        }
    }
}
