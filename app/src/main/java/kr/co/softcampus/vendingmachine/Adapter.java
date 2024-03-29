package kr.co.softcampus.vendingmachine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    ArrayList<Product> productList;
    Context mContext;
    DBExecute dbExecute = null;
    LayoutInflater mLayoutInflater;
    TextView nameMain, priceMain, countMain, cashText;
    RelativeLayout list_item;
    int cash = 0;


    public Adapter(Context c, ArrayList<Product> list) {
        productList = list;
        mContext = c;
        mLayoutInflater = LayoutInflater.from(c);
        dbExecute = new DBExecute(mContext);
        cash = dbExecute.checkMoney();
    }


    public int getCount() {
        return productList.size();
    }

    public Product getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ItemViewHoler viewHoler;

        View v = convertView;

        if (v == null) {
            v = mLayoutInflater.inflate(R.layout.main_list_view, null);

            list_item = (RelativeLayout) v.findViewById(R.id.list_item);
            nameMain = (TextView) v.findViewById(R.id.nameMain);
            countMain = (TextView) v.findViewById(R.id.countMain);

            final Product p = productList.get(position);

            list_item.setMinimumHeight(300);
            list_item.setMinimumWidth(300);

            list_item.setTag(position);


            if (cash >= p.getPrice()&&p.getCount() != 0  ) {
                nameMain.setText(p.getName());
                priceMain.setText(p.getPrice() + "円");
                countMain.setText(p.getCount() + "個");
                list_item.setBackgroundResource(android.R.color.holo_green_light);

            } else if (p.getCount() != 0 && cash < p.getPrice()) {

                nameMain.setText(p.getName());
                priceMain.setText(p.getPrice() + "円");
                countMain.setText(p.getCount() + "個");
                list_item.setBackgroundResource(android.R.color.holo_orange_light);

            }
//            else if(p.getPrice()) {
//
//                nameMain.setText(p.getName());
//                priceMain.setText(p.getPrice() + "");
//                countMain.setText(p.getCount() + "");
//                list_item.setBackgroundResource(android.R.color.holo_orange_light);
//            }
            else {
                nameMain.setText("品切り");
                priceMain.setText(p.getPrice() + "円");
                countMain.setText("0個");
                list_item.setBackgroundResource(android.R.color.holo_orange_light);
            }
            list_item.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (p.getCount() > 1) {
                        p.setCount(p.getCount() - 1);
                        countMain.setText(p.getCount() + "個");
                        dbExecute.sellProduct(p);
                    } else if (p.getCount() == 1) {
                        p.setCount(0);
                        dbExecute.sellProduct(p);
                    }//else if
                    else {
                        nameMain.setText("品切り");
                        priceMain.setText(p.getPrice() + "円");
                        countMain.setText("0個");
                        list_item.setBackgroundResource(android.R.color.holo_orange_light);
                        p.setCount(0);
                    }//else
                    notifyDataSetChanged();
                }//onclick
            });
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
