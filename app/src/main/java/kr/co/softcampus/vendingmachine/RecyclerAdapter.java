package kr.co.softcampus.vendingmachine;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    Context context;
    private ArrayList<Product> data=null;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView idx;
        TextView name;
        TextView price;
        TextView count;
        Product product;

        public ViewHolder(View itemView) {
            super(itemView);
            idx = itemView.findViewById(R.id.index);
            name = itemView.findViewById(R.id.nameMain);
            price = itemView.findViewById(R.id.price);
            count = itemView.findViewById(R.id.count);
        }
        void onBind(final Product product) {
            this.product = product;
            name.setText(product.getName());
            idx.setText(product.getIdx()+"");
            price.setText(product.getPrice()+"");
            count.setText(product.getCount()+"");
            //どうして""を付けばできますが、つけない場合できないですか。

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    context = v.getContext();

                    Intent intent = new Intent(context, EditProduct.class);
                    intent.putExtra("product", product);
                    context.startActivity(intent);
                }
            });
        }
    }

    public RecyclerAdapter(ArrayList<Product> data){
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.management_list_view,parent,false);
        ViewHolder vh = new RecyclerAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(data.get(position));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


}
