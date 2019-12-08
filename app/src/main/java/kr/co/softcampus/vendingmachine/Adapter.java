package kr.co.softcampus.vendingmachine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    ArrayList<Product> productList;
    Context mContext;
    DBExecute dbExecute=null;
    LayoutInflater mLayoutInflater;
    TextView nameMain, priceMain, countMain, cashText;
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
        dbExecute = new DBExecute(mContext);
        if(v==null){
            v = mLayoutInflater.inflate(R.layout.main_list_view,null);
            cashText = v.findViewById(R.id.cash_text);
            list_item = v.findViewById(R.id.list_item);
            nameMain= v.findViewById(R.id.nameMain);
            priceMain = v.findViewById(R.id.priceMain);
            countMain = v.findViewById(R.id.countMain);

            final Product p = productList.get(position);

            list_item.setMinimumHeight(300);
            list_item.setMinimumWidth(300);

            if(p.getCount()!=0) {
                nameMain.setText(p.getName());
                priceMain.setText(p.getPrice() + "");
                countMain.setText(p.getCount() + "");
                list_item.setBackgroundResource(android.R.color.holo_green_light);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (p.getCount() > 1) {
                            p.setCount(p.getCount() - 1);
                            if (v.getId() == R.id.countMain) {
                                countMain.setText(p.getCount() + "");
                            }
                            dbExecute.sellProduct(p);
                        } else if (p.getCount() == 1) {
                            p.setCount(0);
                            dbExecute.sellProduct(p);
                        }//else if
                        else {
                            nameMain.setText("品切り");
                            priceMain.setText(p.getPrice() + "");
                            countMain.setText("0");
                            list_item.setBackgroundResource(android.R.color.holo_orange_light);
                            p.setCount(0);
                        }//setonClick
                        notifyDataSetChanged();
                    }
                });
            }
//            else if(p.getPrice()) {
//
//                nameMain.setText(p.getName());
//                priceMain.setText(p.getPrice() + "");
//                countMain.setText(p.getCount() + "");
//                list_item.setBackgroundResource(android.R.color.holo_orange_light);
//            }
            else{
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
