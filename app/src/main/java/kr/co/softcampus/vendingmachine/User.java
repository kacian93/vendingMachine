package kr.co.softcampus.vendingmachine;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    int idx;
    String userId;
    String password;
    String permission;
    int cash;

    public User(int idx, String userId, String password, String permission, int cash) {
        this.idx = idx;
        this.userId = userId;
        this.password = password;
        this.permission = permission;
        this.cash = cash;
    }

    public User(String userId, String password, String permission, int cash) {
        this.userId = userId;
        this.password = password;
        this.permission = permission;
        this.cash = cash;
    }

    public User(String userId, String password, int cash) {
        this.userId = userId;
        this.password = password;
        this.cash = cash;
    }

    public User(String userId, int cash) {
        this.userId = userId;
        this.cash = cash;
    }

    protected User(Parcel in) {
        idx = in.readInt();
        userId = in.readString();
        password = in.readString();
        permission = in.readString();
        cash = in.readInt();
    }


    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idx);
        dest.writeString(userId);
        dest.writeString(password);
        dest.writeString(permission);
        dest.writeInt(cash);
    }
}
