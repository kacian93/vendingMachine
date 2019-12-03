package kr.co.softcampus.vendingmachine;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    int price;
    int count;
    String name;
    int idx;

    public Product(int price, int count, String name) {
        this.price = price;
        this.count = count;
        this.name = name;
    }

    public Product(int price, int count, String name, int idx) {
        this.price = price;
        this.count = count;
        this.name = name;
        this.idx = idx;
    }

    protected Product(Parcel in) {
        price = in.readInt();
        count = in.readInt();
        name = in.readString();
        idx = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(price);
        dest.writeInt(count);
        dest.writeString(name);
        dest.writeInt(idx);
    }
}
