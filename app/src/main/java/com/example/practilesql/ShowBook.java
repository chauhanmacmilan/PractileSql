package com.example.practilesql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.practilesql.databinding.ActivityShowBookBinding;

import java.util.ArrayList;

public class ShowBook extends AppCompatActivity {
    Context context;
    ActivityShowBookBinding binding;
    ArrayList<BookModel> bookModels = new ArrayList<>();
    AdapterBook adapterBook;
    String bookName, aName;
    DBHelper dbHelper;
    String id;
    int posi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = ShowBook.this;
        binding = DataBindingUtil.setContentView(ShowBook.this, R.layout.activity_show_book);
        dbHelper = new DBHelper(context);
        if(getIntent() != null) {
            aName = getIntent().getStringExtra("auther");
        }
        bookModels = dbHelper.getAllBook(aName);
        adapterBook = new AdapterBook(context, bookModels);
        LinearLayoutManager  linearLayoutManager = new LinearLayoutManager(context);
        binding.recyclerVIewBook.setLayoutManager(linearLayoutManager);
        binding.recyclerVIewBook.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerVIewBook.setAdapter(adapterBook);
        binding.recyclerVIewBook.addOnItemTouchListener(new RecyclerTouchListener(context, binding.recyclerVIewBook, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                posi = position;
                binding.linearPop.setVisibility(View.VISIBLE);
                binding.editAutherUpdate.setText(bookModels.get(position).getBook());
                id = bookModels.get(position).getId();
                bookName = bookModels.get(position).getBook();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        adapterBook.notifyDataSetChanged();
        clickEvent();
    }


    public void clickEvent() {
        binding.addBoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidate()) {
                    String nameb = binding.editAuther.getText().toString().trim();
                    if(dbHelper.insertBook(nameb, aName)) {
                        ArrayList<BookModel> model = dbHelper.getAllBook(aName);
                        bookModels.clear();
                        bookModels.addAll(model);
                        adapterBook.notifyDataSetChanged();
                        Toast.makeText(context, "Data Inserted.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Provide Not", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Provide Correct Information", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.updateAuther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = binding.editAutherUpdate.getText().toString().trim();
                if(dbHelper.updateBook(Integer.parseInt(bookModels.get(posi).getId()), n)){
                    ArrayList<BookModel> model = dbHelper.getAllBook(aName);
                    bookModels.clear();
                    bookModels.addAll(model);
                    adapterBook.notifyDataSetChanged();
                    binding.editAuther.setText("");
                    binding.editAutherUpdate.setText("");
                    id = "";
                    posi = 0;
                    binding.linearPop.setVisibility(View.GONE);
                }
            }
        });
        binding.deleteAuther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.deleteBook(bookModels.get(posi).id) > 0) {
                    ArrayList<BookModel> model = dbHelper.getAllBook(aName);
                    bookModels.clear();
                    bookModels.addAll(model);
                    adapterBook.notifyDataSetChanged();
                    binding.editAuther.setText("");
                    binding.editAutherUpdate.setText("");
                    id = "";
                    posi = 0;
                    binding.linearPop.setVisibility(View.GONE);
                }
            }
        });
    }

    public boolean checkValidate(){
        bookName = binding.editAuther.getText().toString().trim();
        if(!bookName.equals("")) {
            return true;
        } else {
            return false;
        }
    }

}