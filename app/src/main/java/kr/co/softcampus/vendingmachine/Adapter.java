package kr.co.softcampus.vendingmachine;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    ArrayList<Product> productList;
    Context mContext;
    LayoutInflater mLayoutInflater;
    TextView nameMain, priceMain, countMain;
    RelativeLayout list_item;

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

            list_item = v.findViewById(R.id.list_item);
            nameMain= v.findViewById(R.id.nameMain);
            priceMain = v.findViewById(R.id.priceMain);
            countMain = v.findViewById(R.id.countMain);

            Product p = productList.get(position);

            list_item.setMinimumHeight(300);
            list_item.setMinimumWidth(300);

            if(p.getCount()!=0) {
                nameMain.setText(p.getName());
                priceMain.setText(p.getPrice() + "");
                countMain.setText(p.getCount() + "");
                list_item.setBackgroundResource(android.R.color.holo_green_light);

            }else{
                nameMain.setText("品切り");
                priceMain.setText(p.getPrice() + "");
                countMain.setText("0");
                list_item.setBackgroundResource(android.R.color.holo_orange_light);
            }

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
