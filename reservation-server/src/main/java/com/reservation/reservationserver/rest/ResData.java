package com.reservation.reservationserver.rest;

import com.reservation.reservationserver.domain.Reservation;

import java.util.List;

public class ResData {
    private String message;

    private List<Reservation> dup_list;

    private List<Reservation> reserved_list;

    private List<Reservation> success_list;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Reservation> getDup_list() {
        return dup_list;
    }

    public void setDup_list(List<Reservation> dup_list) {
        this.dup_list = dup_list;
    }

    public List<Reservation> getReserved_list() {
        return reserved_list;
    }

    public void setReserved_list(List<Reservation> reserved_list) {
        this.reserved_list = reserved_list;
    }

    public List<Reservation> getSuccess_list() {
        return success_list;
    }

    public void setSuccess_list(List<Reservation> success_list) {
        this.success_list = success_list;
    }

    @Override
    public String toString() {
        return "ResData{" +
                "message='" + message + '\'' +
                ", dup_list=" + dup_list +
                ", reserved_list=" + reserved_list +
                ", success_list=" + success_list +
                '}';
    }
}
