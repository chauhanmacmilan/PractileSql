package com.example.practilesql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.practilesql.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context;
    ActivityMainBinding binding;

    String autherName = "";
    DBHelper dbHelper;

    ArrayList<AutherModel> autherModels = new ArrayList<>();
    AdapterAutherList adapterAutherList;

    String id, aName;
    int posi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MainActivity.this;
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        dbHelper = new DBHelper(context);

        autherModels = dbHelper.getAllAuther();
        adapterAutherList = new AdapterAutherList(context, autherModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        binding.recyclerVIewAuther.setLayoutManager(linearLayoutManager);
        binding.recyclerVIewAuther.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerVIewAuther.setAdapter(adapterAutherList);
        binding.recyclerVIewAuther.addOnItemTouchListener(new RecyclerTouchListener(context, binding.recyclerVIewAuther, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                posi = position;
                binding.linearPop.setVisibility(View.VISIBLE);
                binding.editAutherUpdate.setText(autherModels.get(position).getAuther());
                id = autherModels.get(position).getId();
                aName = autherModels.get(position).getAuther();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        adapterAutherList.notifyDataSetChanged();
        clickEvent();
    }

    public void clickEvent() {
        binding.addBoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidate()) {
                    if(dbHelper.insertAuther(autherName)) {
                        ArrayList<AutherModel> model = dbHelper.getAllAuther();
                        autherModels.clear();
                        autherModels.addAll(model);
                        adapterAutherList.notifyDataSetChanged();
                        Toast.makeText(context, "Data Inserted.", Toast.LENGTH_SHORT).show();
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
                if(dbHelper.updateAuther(Integer.parseInt(autherModels.get(posi).getId()), n)){
                    ArrayList<AutherModel> model = dbHelper.getAllAuther();
                    autherModels.clear();
                    autherModels.addAll(model);
                    adapterAutherList.notifyDataSetChanged();
                    binding.editAuther.setText("");
                    binding.editAutherUpdate.setText("");
                    id = "";
                    aName = "";
                    posi = 0;
                    binding.linearPop.setVisibility(View.GONE);
                }
            }
        });
        binding.deleteAuther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.deleteAuther(autherModels.get(posi).id) > 0) {
                    ArrayList<AutherModel> model = dbHelper.getAllAuther();
                    autherModels.clear();
                    autherModels.addAll(model);
                    adapterAutherList.notifyDataSetChanged();
                    binding.editAuther.setText("");
                    binding.editAutherUpdate.setText("");
                    id = "";
                    aName = "";
                    posi = 0;
                    binding.linearPop.setVisibility(View.GONE);
                }
            }
        });
        binding.showBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowBook.class);
                intent.putExtra("auther", autherModels.get(posi).auther);
                startActivity(intent);
                id = "";
                aName = "";
                posi = 0;
                binding.linearPop.setVisibility(View.GONE);
            }
        });
        /*binding.showBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidateAuther()) {
                    ArrayList<String> strings = new ArrayList<>();
                    strings = dbHelper.getAllBook(autherName);
                    if(strings.size() > 0) {
                        for(int i = 0; i < strings.size(); i++) {
                            Log.d("PracticleMilan", strings.get(i));
                        }
                        Intent intent = new Intent(MainActivity.this, ShowBook.class);
                        intent.putStringArrayListExtra("listName", strings);
                        intent.putExtra("auther", autherName);
                        startActivity(intent);
                    } else {
                        Toast.makeText(context, "No Books Available By This Auther", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Provide Correct Information", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    public boolean checkValidate(){
        autherName = binding.editAuther.getText().toString().trim();
        if(!autherName.equals("")) {
            return true;
        } else {
            return false;
        }
    }

}