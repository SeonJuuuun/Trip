'use strict';

let accompanyIndex = {
    init: function () {
        $("#accompany-btn-save").on("click", () => {
            this.accompanySave();
        });
        $("#accompany-btn-accept").on("click", () => {
            this.accompanyAccept();
        });
        $("#accompany-btn-reject").on("click", () => {
            this.accompanyReject();
        });
        $("#realAccompany-save").on("click", () => {
            this.realAccompanySave();
        });
        $("#btn-delete").on("click", () => {
            this.accompanyDelete();
        });
    },

    realAccompanySave: function () {
        let accompanyId = $("#realAccompany-save").attr("data-accompany-id");

        let data = {
            accompanyId: accompanyId,
        };

        $.ajax({
            type: "POST",
            url: `/apply/realAccompany`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "text"
        }).done(function (res) {
            alert("동행신청이 완료되었습니다.");
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },


    accompanySave: function () {
        let boardId = $("#boardId").val();
        let title = $("#boardTitle").val();
        let username = $("#username").val();
        let nickname = $("#nickname").val();

        let data = {
            boardId: boardId,
            title: title,
            username: username,
            nickname: nickname
        };

        $.ajax({
            type: "POST",
            url: `/apply/accompany`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "text"
        }).done(function (res) {
            alert("동행신청이 완료되었습니다.");
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },

    accompanyAccept: function () {
        let accompanyId = $("#accompany-btn-accept").attr("value");

        let data = {
            accompanyId: accompanyId,
            accept: true
        };

        let realAccompanyData = Object.assign({}, data);

        this.realAccompanySave(realAccompanyData);

        this.sendAccompanyAcceptOrReject(data);

        alert("수락");
    },

    accompanyReject: function () {
        let accompanyId = $("#accompany-btn-reject").attr("value"); // or .attr("th:value");

        let data = {
            accompanyId: accompanyId,
            accept: false
        };


        this.sendAccompanyAcceptOrReject(data);
        alert("거절");
    },

    sendAccompanyAcceptOrReject: function (data) {
        $.ajax({
            type: "POST",
            url: "/apply/accompany/accept",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "text"
        }).done(function (res) {
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },

    accompanyDelete: function () {
        let accompanyId = $("#btn-delete").attr("data-accompany-id");

        $.ajax({
            type: "DELETE",
            url: `/api/accompany/reject/${accompanyId}`,
            contentType: "application/json; charset=utf-8",
            dataType: "text"
        }).done(function (res) {
            alert("동행 신청이 거절되었습니다.");
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    }
};

accompanyIndex.init();
