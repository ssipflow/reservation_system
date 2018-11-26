package com.reservation.reservationui.controller;

import com.reservation.reservationui.params.Params;
import com.reservation.reservationui.rest.ResData;
import com.reservation.reservationui.service.ReservationUiService;
import com.reservation.reservationui.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UiController {

    @Autowired
    ReservationUiService reservationUiService;

    @GetMapping("/index")
    public String index(Model model){

        return "index";
    }
}
