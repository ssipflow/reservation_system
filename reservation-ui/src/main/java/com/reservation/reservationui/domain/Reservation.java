package com.reservation.reservationui.domain;

import java.time.LocalDateTime;

public class Reservation {
    private Long idx;

    private String room;

    private String user;

    private String start_time;

    private String end_time;

    public Reservation() {
    }

    public Reservation(String room, String user, String start_time, String end_time) {
        this.room = room;
        this.user = user;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idx=" + idx +
                ", room='" + room + '\'' +
                ", user='" + user + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                '}';
    }
}
