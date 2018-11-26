package com.reservation.reservationserver.params;

import java.time.LocalDateTime;

public class Params {
    private String room;

    private String user;

    private LocalDateTime start_time;

    private LocalDateTime end_time;

    private Integer repeat;

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

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    public Integer getRepeat() {
        return repeat;
    }

    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }

    @Override
    public String toString() {
        return "Params{" +
                "room='" + room + '\'' +
                ", user='" + user + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", repeat=" + repeat +
                '}';
    }
}
