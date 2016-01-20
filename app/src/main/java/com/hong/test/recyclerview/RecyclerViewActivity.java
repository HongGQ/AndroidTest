package com.hong.test.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hong.test.R;
import com.hong.test.model.PersonModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hong
 * @Name: RecyclerViewActivity
 * @Package com.hong.test.recyclerview
 * @Description: ${todo}
 * @date 15/11/25
 * @time 下午4:16
 * @copyright 广州市金税信息系统集成有限公司
 */
public class RecyclerViewActivity extends AppCompatActivity {
    /**
     * ------------------------------------
     **/
    private Context mContext;
    /**
     * ------------------------------------
     **/
    private RecyclerView mRecyclerView;
    /**
     * ------------------------------------
     **/
    private List<PersonModel> mPageList;
    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        /** ------------------------------------ **/
        mContext = this;

        /** ------------------------------------ **/
        initData();
        initView();
        setItem();
    }

    public void setItem() {
        mAdapter = new RecyclerViewAdapter(mPageList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void initData() {
        mPageList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            PersonModel person = new PersonModel(i, "Hugo_" + i, 27, 0);
            mPageList.add(person);
        }

    }

    public void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL_LIST));
    }
}
