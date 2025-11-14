package com.example.prm392_client.ui.messages.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

//public class MemberInfo implements Parcelable {
//    @SerializedName("name") public String Name;
//    @SerializedName("nickName") public String NickName;
//    @SerializedName("isIngroup") public boolean IsIngroup;
//
//    protected MemberInfo(Parcel in) {
//        Name = in.readString();
//        NickName = in.readString();
//        IsIngroup = in.readByte() != 0;
//    }
//
//    public static final Creator<MemberInfo> CREATOR = new Creator<MemberInfo>() {
//        @Override
//        public MemberInfo createFromParcel(Parcel in) { return new MemberInfo(in); }
//        @Override
//        public MemberInfo[] newArray(int size) { return new MemberInfo[size]; }
//    };
//
//    @Override
//    public int describeContents() { return 0; }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(Name);
//        dest.writeString(NickName);
//        dest.writeByte((byte) (IsIngroup ? 1 : 0));
//    }
//}
public class MemberInfo implements Parcelable {
    @SerializedName("name") public String Name;
    @SerializedName("nickName") public String NickName;
    @SerializedName("isIngroup") public boolean IsIngroup;

    protected MemberInfo(Parcel in) {
        Name = in.readString();
        NickName = in.readString();
        IsIngroup = in.readByte() != 0;
    }

    public static final Creator<MemberInfo> CREATOR = new Creator<MemberInfo>() {
        @Override public MemberInfo createFromParcel(Parcel in) { return new MemberInfo(in); }
        @Override public MemberInfo[] newArray(int size) { return new MemberInfo[size]; }
    };

    @Override public int describeContents() { return 0; }
    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name); dest.writeString(NickName);
        dest.writeByte((byte) (IsIngroup ? 1 : 0));
    }
}
