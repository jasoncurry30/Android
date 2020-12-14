package com.jalen.hotels.util;


import com.jalen.hotels.bean.Hotel;

import java.util.List;

/**
 */
public interface QueryHotelCallBack {
    void success(List<Hotel> movements);

    void fail(String e);
}
