package com.example.practilesql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practilesql.databinding.AdapterAutherListBinding;
import com.example.practilesql.databinding.AdapterListBinding;

import java.util.ArrayList;

public class AdapterAutherList extends RecyclerView.Adapter<AdapterAutherList.MyHolder> {
    Context context;
    ArrayList<AutherModel> autherModels;

    public AdapterAutherList(Context context, ArrayList<AutherModel> autherModels) {
        this.context = context;
        this.autherModels = autherModels;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        AdapterAutherListBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_auther_list, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        AutherModel model = autherModels.get(position);
        holder.binding.textItem.setText(model.getAuther());
    }

    @Override
    public int getItemCount() {
        return autherModels.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        AdapterAutherListBinding binding;

        public MyHolder(@NonNull AdapterAutherListBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
