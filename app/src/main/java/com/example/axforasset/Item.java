package com.example.axforasset;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    private String title;
    private String description;
    private Integer price;
    private int imageResourceId;

    // Constructor, getters, and setters

    public Item(String title, Integer price, String description, int imageResourceId) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    protected Item(Parcel in) {
        title = in.readString();
        description = in.readString();
        price = in.readInt(); // Add this line
        imageResourceId = in.readInt();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(price); // Add this line
        dest.writeInt(imageResourceId);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public Integer getPrice() {
        return price;
    }
}
