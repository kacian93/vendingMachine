package kr.co.softcampus.vendingmachine;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    ArrayList<Product> productList;
    Context mContext;
    LayoutInflater mLayoutInflater;
    TextView nameMain, priceMain, countMain;

    public Adapter(Context c, ArrayList<Product> list){
        productList = list;
        mContext =c ;
        mLayoutInflater = LayoutInflater.from(c);
    }


    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Product getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v==null){
            v = mLayoutInflater.inflate(R.layout.main_list_view,null);
            nameMain= v.findViewById(R.id.nameMain);
            priceMain = v.findViewById(R.id.priceMain);
            countMain = v.findViewById(R.id.countMain);

            Product p = productList.get(position);
            nameMain.setText(p.getName());
            priceMain.setText(p.getPrice()+"");
            countMain.setText(p.getCount()+"");

        }
        return v;
    }

    public void setProductList(ArrayList<Product> productList){
        this.productList= productList;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }
}
