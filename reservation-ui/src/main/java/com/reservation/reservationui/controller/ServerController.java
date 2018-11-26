package com.reservation.reservationui.controller;

import com.reservation.reservationui.params.Params;
import com.reservation.reservationui.service.ReservationUiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class ServerController {

    @Autowired
    ReservationUiService reservationUiService;

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public String getReservation(@RequestParam("d") String d) {
        Params params = new Params();

        if(d == null || "".equals(d)) {
            d = LocalDateTime.now().toString();
        }

        params.setStart_time(d);

        return reservationUiService.getReservation(params);
    }

    @RequestMapping(value = "/reservations/{room_id}", method = RequestMethod.POST)
    public String postReservation(@RequestBody Params params, @PathVariable("room_id") String room_id) {
        params.setRoom(room_id);

        return reservationUiService.postReservation(params);
    }
}
