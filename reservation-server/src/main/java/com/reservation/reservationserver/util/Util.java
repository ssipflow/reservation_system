package com.reservation.reservationserver.util;

import com.reservation.reservationserver.domain.Reservation;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Util {

    public static void sortByStartTime(List<Reservation> list) {
        Collections.sort(list, new Comparator<Reservation>() {
            @Override
            public int compare(Reservation o1, Reservation o2) {
                int orderByTime = o1.getStart_time().compareTo( o2.getStart_time() );
                return (orderByTime != 0) ? orderByTime : o1.getRoom().compareTo( o2.getRoom() );
            }
        });
    }
}
