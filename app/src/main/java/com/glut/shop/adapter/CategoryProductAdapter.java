package com.glut.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.glut.shop.R;
import com.glut.shop.activity.CartActivity;
import com.glut.shop.bean.CategoryBean;
import com.glut.shop.util.ToastUtils;
import com.glut.shop.widget.GridViewForScrollView;
import com.glut.shop.widget.MarqueeView;

import java.util.List;

public class CategoryProductAdapter extends BaseAdapter {
    private static final String TAG = "CategoryProductAdapter";

    private Context context;
    private List<CategoryBean.DataBean> datas;

    public CategoryProductAdapter(Context context, List<CategoryBean.DataBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        if (datas != null) {
            return datas.size();
        } else {
            return 10;
        }
    }

    @Override
    public Object getItem(int position) {
        return datas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        CategoryBean.DataBean dataBean = datas.get(position);
        List<CategoryBean.DataBean.DataListBean> dataList = dataBean.getDataList();
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.category_product, null);
            viewHolder = new ViewHolder();
            viewHolder.gridView = (GridViewForScrollView)view.findViewById(R.id.gridView);
            viewHolder.category_product_name = (TextView)view.findViewById(R.id.tv_category_product_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }
        ProductSummaryAdapter adapter = new ProductSummaryAdapter(context, dataList);
        viewHolder.category_product_name.setText(dataBean.getModuleTitle());
        viewHolder.gridView.setAdapter(adapter);
        viewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showToast(context, "点击" + dataList.get(position).getTitle());
            }
        });
        return view;
    }

    private static class ViewHolder {
        private GridViewForScrollView gridView;
        private TextView category_product_name;
    }

}
