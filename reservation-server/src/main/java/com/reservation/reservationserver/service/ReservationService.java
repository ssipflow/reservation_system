package com.reservation.reservationserver.service;

import com.reservation.reservationserver.domain.Reservation;
import com.reservation.reservationserver.domain.ReservationRepo;
import com.reservation.reservationserver.params.Const;
import com.reservation.reservationserver.params.Params;
import com.reservation.reservationserver.rest.ResData;
import com.reservation.reservationserver.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationRepo reservationRepo;

    public ResData getReservation(Params params) {
        ResData resData = new ResData();
        String message = Const.MESSAGE_GET_SUCCESS;
        List<Reservation> reservedList = null;

        try {
            reservedList = reservationRepo.findByReserveByMonth(params.getStart_time(), params.getEnd_time());
        } catch (Exception e) {
            message = e.getMessage();
        }

        resData.setMessage(message);
        Util.sortByStartTime(reservedList);
        resData.setReserved_list(reservedList);

        return resData;
    }

    public synchronized ResData saveReservation(Params params) {
        ResData resData = new ResData();
        String message = Const.MESSAGE_POST_SUCCESS;
        List<Reservation> dupList = new ArrayList<>();
        List<Reservation> successList = new ArrayList<>();

        try{
            if(params.getRepeat() == 0){
                // 반복이 아닐 경우
                List<Reservation> list = reservationRepo.findByReserveRangeByRoom(params.getStart_time(), params.getEnd_time(), params.getRoom());
                if(list.size() > 0) {
                    // Response message
                    message = Const.MESSAGE_POST_DUPLICATE;
                    dupList.addAll(list);
                } else {
                    successList.add(new Reservation(params.getRoom(), params.getUser(), params.getStart_time(), params.getEnd_time()));
                    reservationRepo.save(new Reservation(params.getRoom(), params.getUser(), params.getStart_time(), params.getEnd_time()));
                }
            } else if(params.getRepeat() > 0) {
                // 반복일 경우
                int repeat = params.getRepeat();
                for(int i = 0; i < repeat; i++) {
                    LocalDateTime start_time = params.getStart_time().plusDays(7*i);
                    LocalDateTime end_time = params.getEnd_time().plusDays(7*i);

                    List<Reservation> list = reservationRepo.findByReserveRangeByRoom(start_time, end_time, params.getRoom());
                    if(list.size() > 0) {
                        dupList.addAll(list);
                        message = Const.MESSAGE_POST_DUPLICATE;
                        continue;
                    } else if(list.size() == 0) {
                        successList.add(new Reservation(params.getRoom(), params.getUser(), start_time, end_time));
                        reservationRepo.save(new Reservation(params.getRoom(), params.getUser(), start_time, end_time));
                    }
                }
            } else {
                throw new Exception(Const.MESSAGE_ERROR);
            }
        } catch (Exception e) {
            message = e.getMessage();
        }

        LocalDateTime tmp_start_time = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getDayOfMonth(), 0, 0, 0);
        LocalDateTime tmp_end_time = tmp_start_time.plusDays(1);
        List<Reservation> reservedList = reservationRepo.findByReserveByMonth(tmp_start_time, tmp_end_time);
        Util.sortByStartTime(reservedList);

        resData.setMessage(message);
        resData.setDup_list(dupList);
        resData.setReserved_list(reservedList);
        resData.setSuccess_list(successList);

        return resData;
    }
}
