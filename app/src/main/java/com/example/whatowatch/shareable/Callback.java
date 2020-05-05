package com.example.whatowatch.shareable;

public interface Callback {
    void onResponseOk(Object... objs);
    void onResponseError(Object... objs);
}