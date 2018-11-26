package com.reservation.reservationserver.controller;

import com.reservation.reservationserver.domain.ReservationRepo;
import com.reservation.reservationserver.params.Params;
import com.reservation.reservationserver.rest.ResData;
import com.reservation.reservationserver.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class ReservationController {

    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    ReservationService reservationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "Hello World!";
    }

    /**
     * GET reservation list
     * Response reservation list
     */
    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public ResponseEntity<ResData> getReservation(@RequestParam("d") String d) {
        // 월 시작, 마지막 일 params 세팅
        LocalDateTime tmpTime = null;
        if(d == null || "".equals(d)) {
            tmpTime = LocalDateTime.now();
        } else {
            tmpTime = LocalDateTime.parse(d);
        }

        LocalDateTime start_time = LocalDateTime.of(tmpTime.getYear(), tmpTime.getMonthValue(), tmpTime.getDayOfMonth(), 0, 0, 0);
        LocalDateTime end_time = start_time.plusDays(1);

        Params params = new Params();
        params.setStart_time(start_time);
        params.setEnd_time(end_time);

        ResponseEntity<ResData> response = null;
        ResData resData = null;
        try {
            resData = reservationService.getReservation(params);
        } catch (Exception e) {
            resData.setMessage(e.getMessage());
        }

        response = new ResponseEntity<>(resData, HttpStatus.OK);

        return response;
    }

    /**
     * POST reservation
     * Response status code
     */
    @RequestMapping(value = "/reservations/{room_id}", method = RequestMethod.POST)
    public ResponseEntity<ResData> postReservation(@RequestBody Params params, @PathVariable("room_id") String room_id) {

        ResponseEntity<ResData> response = null;
        ResData resData = null;

        params.setRoom(room_id);
        try {
            resData = reservationService.saveReservation(params);
        } catch (Exception e) {
            resData.setMessage(e.getMessage());
        }

        response = new ResponseEntity<>(resData, HttpStatus.OK);

        return response;
    }
}
