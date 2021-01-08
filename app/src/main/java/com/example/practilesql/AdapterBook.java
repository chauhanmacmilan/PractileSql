package com.example.practilesql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practilesql.databinding.AdapterListBinding;

import java.util.ArrayList;

public class AdapterBook extends RecyclerView.Adapter<AdapterBook.MyHolder> {
    Context context;
    ArrayList<BookModel> strings;

    public AdapterBook(Context context, ArrayList<BookModel> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        AdapterListBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_list, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        BookModel s = strings.get(position);
        holder.binding.textBook.setText(s.book);
        holder.binding.textAuther.setText(s.auther);
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        AdapterListBinding binding;
        public MyHolder(@NonNull AdapterListBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
