package com.hong.test.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hong.test.R;
import com.hong.test.model.PersonModel;

import java.util.List;

/**
 * @author Hong
 * @Name: RecyclerViewAdapter
 * @Package com.hong.test.recyclerview
 * @Description: ${todo}
 * @date 15/11/25
 * @time 下午4:42
 * @copyright 广州市金税信息系统集成有限公司
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<PersonModel> mPageList;

    public RecyclerViewAdapter(List<PersonModel> mPageList) {
        this.mPageList = mPageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recycler_view_item, null);
//        不知道为什么在xml设置的“android:layout_width="match_parent"”无效了，需要在这里重新设置
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ViewHolder holder = viewHolder;
        PersonModel person = mPageList.get(i);
        holder.textName.setText(person.getName());
        holder.textAge.setText(person.getAge() + "");
    }

    @Override
    public int getItemCount() {
        return mPageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textName;
        public TextView textAge;

        public ViewHolder(View itemView) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.text_name);
            textAge = (TextView) itemView.findViewById(R.id.text_age);
        }
    }

}
