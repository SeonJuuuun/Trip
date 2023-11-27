'use strict';

let starIndex = {
    init: function (userId) {
        this.userId = userId;
    },

    event: function () {
        $("#star-save").on("click", () => {
            this.starSave();
        });
    },


    starSave: function () {
        let selectedStar = $('input.star_radio:checked').data('title');

        // 사용자가 작성한 리뷰 내용
        let reviewContent = $('#reviewContent').val();

        // 이 데이터를 서버로 전송
        let data = {
            selectedStar: selectedStar,
            reviewContent: reviewContent,
            userId: this.userId
        };

        $.ajax({
            type: "POST",
            url: "/api/star",  // API 엔드포인트 URL
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",  // 서버로부터의 응답 데이터 타입
        }).done(function (res) {
            alert("작성 완료되었습니다.");
        }).fail(function (err) {
            alert("작성 완료되었습니다.");
        });
    }
};

starIndex.init();
starIndex.event();
