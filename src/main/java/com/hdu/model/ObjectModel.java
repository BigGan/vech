package com.hdu.model;

public interface ObjectModel<T> extends Model<T> {
    T getObj();

    Model setObj(T var1);
}
