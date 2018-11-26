package com.reservation.reservationui.service;

import com.reservation.reservationui.params.Const;
import com.reservation.reservationui.params.Params;
import com.reservation.reservationui.rest.ResData;
import com.reservation.reservationui.util.Util;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReservationUiService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getReservation(Params params) {
        String url = Const.API_SERVER_ENDPOINT + "/reservations?d="+params.getStart_time();

        ResponseEntity<ResData> response = restTemplate.getForEntity(url, ResData.class);
        ResData resData = response.getBody();

        return Util.beanToJson(resData);
    }

    public String postReservation(Params params) {

        String url = Const.API_SERVER_ENDPOINT + "/reservations/"+params.getRoom();
        String reqBody = Util.beanToJson(params);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        ResponseEntity<ResData> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(reqBody, headers), ResData.class);
        ResData resData = response.getBody();

        return Util.beanToJson(resData);
    }
}
