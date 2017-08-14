package com.example.ls.lsn2_materialdesign_recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecylerview;
    private MyRecyclerAdapter mAdapter;
    private ArrayList<String> mList;
    private RecyclerView.ItemDecoration divider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setData();
    }

    private void setData() {
        mAdapter = new MyRecyclerAdapter(mList);
        //LayoutManager布局管理器，控制摆放：线性摆放，
        mRecylerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecylerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(MainActivity.this, "我是第：" + position + "条item", Toast.LENGTH_SHORT).show();
            }
        });
        mRecylerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
    }

    private boolean flag_about_vertical_or_horazion = false;

    public void changedView(View view) {
        if (divider != null) {
            mRecylerview.removeItemDecoration(divider);
        }
        if (!flag_about_vertical_or_horazion) {
            mRecylerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            divider = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
            mRecylerview.addItemDecoration(divider);
        } else {
            mRecylerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            divider = new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL);
            mRecylerview.addItemDecoration(divider);

        }
        flag_about_vertical_or_horazion = !flag_about_vertical_or_horazion;
    }

    public void additem(View v) {
        mAdapter.addList("我瞌睡了", 4);

    }

    public void removeItem(View v) {
        mAdapter.removeList(4);

    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            mList.add("item" + i);
        }
    }

    private void initView() {
        mRecylerview = (RecyclerView) findViewById(R.id.recyle_view);
        mList = new ArrayList<>();
    }
}
