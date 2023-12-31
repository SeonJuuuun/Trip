'use strict';

let accompanyIndex = {
    init: function () {
        $("#accompany-btn-save").on("click", () => {
            this.accompanySave();
        });
    },

    accompanySave: function () {
        let boardId = $("#boardId").val();
        let username = $("#username").val();
        let nickname = $("#nickname").val();

        let data = {
            boardId: boardId,
            username: username,
            nickname: nickname
        };

        $.ajax({
            type: "POST",
            url: `/applyToAccompany/${boardId}`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            alert("동행신청이 완료되었습니다.");
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },
};

accompanyIndex.init();