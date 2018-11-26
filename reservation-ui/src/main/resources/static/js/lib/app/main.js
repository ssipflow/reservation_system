var main = {
    init : function() {
        var _this = this;

        $('#btn_input').on('click', function(){
            var room_id     = $("#room_id").val();
            var user        = $("#user").val();
            var date        = $("#reserve_date").val();
            var start_time  = $("#start_time_picker").val();
            var end_time    = $("#end_time_picker").val();
            var repeat      = $("#repeat").val();

            if(room_id == "" || user == "" || date == "" || start_time == "" || end_time == "") {
                alert("반복을 제외한 모든 입력폼은 필수입니다.");
                return ;
            }

            _this.save();
        });
    },
    save : function() {
        var user        = $("#user").val();
        var room_id     = $("#room_id").val();
        var start_time  = $("#reserve_date").val() + "T" + $("#start_time_picker").val();
        var end_time    = $("#reserve_date").val() + "T" + $("#end_time_picker").val();
        var repeat      = parseInt($("#repeat").val());

        if(isNaN(repeat)) repeat = 0;

        var data = {
            user        : user,
            start_time  : start_time,
            end_time    : end_time,
            repeat      : repeat
        };

        $.ajax({
            type        : 'POST',
            url         : '/reservations/'+room_id,
            dataType    : 'JSON',
            contentType : 'application/json; charset=utf-8',
            data        : JSON.stringify(data)
        }).done(function(res) {
            var message = "";
            var status = res.message;

            // 메세지 처리
            if(status == "OK") {
                message += "정상적으로 예약되었습니다."
            } else if(status == "DUPLICATE") {
                var success_list = res.success_list;
                var dup_list = res.dup_list;

                if(success_list.length > 0){
                    message += "다음 시간으로 회의실을 예약했습니다.\n"
                    for(var i = 0; i < success_list.length; i++){
                        var success_start_time = success_list[i].start_time;
                        var success_end_time = success_list[i].end_time;

                        var success_date = success_start_time.split("T")[0];

                        var success_start_time = success_start_time.split("T")[1];
                        var success_end_time = success_end_time.split("T")[1];

                        message += "사용자: " + success_list[i].user + ", 회의실 " + room_id.split("-")[1].toUpperCase() + ", 일시: " + success_date + ", 시작: " + success_start_time + ", 종료: " + success_end_time + "\n";
                    }
                    message += "\n";
                }

                message += "회의실 " + room_id.split("-")[1] + " 를 미리 선점한 사용자 및 시간입니다.\n"
                for(var i = 0; i < dup_list.length; i++) {
                    var dup_start_time = dup_list[i].start_time;
                    var dup_end_time = dup_list[i].end_time;

                    var dup_date = dup_start_time.split("T")[0];

                    var dup_start_time = dup_start_time.split("T")[1];
                    var dup_end_time = dup_end_time.split("T")[1];

                    message += "사용자: " + dup_list[i].user + ", 일시: " + dup_date + ", 시작: " + dup_start_time + ", 종료: " + dup_end_time + "\n";
                }
            }
            $("#result").text(message);

            // Timetable 변경
            var reserved_list = res.reserved_list;
            var td_code = "";
            for(var i = 0; i < reserved_list.length; i++) {
                room_id = reserved_list[i].room;
                user = reserved_list[i].user;
                start_time = reserved_list[i].start_time.split("T")[1];
                end_time = reserved_list[i].end_time.split("T")[1];

                td_code += "<tr><td>회의실 " + room_id.split("-")[1].toUpperCase() + "</td><td>" + start_time + " - " + end_time + "</td><td>" + user + "</td></tr>"
            }

            $("#td_code").html(td_code);
        }).fail(function(error) {
            console.log(error);
        });
    }
};

main.init();