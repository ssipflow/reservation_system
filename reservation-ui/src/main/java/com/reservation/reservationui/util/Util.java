package com.reservation.reservationui.util;

import com.google.gson.Gson;

public class Util {

    public static String beanToJson(Object src) {
        return new Gson().toJson(src);
    }

    public static <T> T jsonToBean(String json, Class<T> classOfT) {
        if(classOfT == null)
            return null;

        return new Gson().fromJson(json, classOfT);
    }
}
