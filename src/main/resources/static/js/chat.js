import {getUserFlows} from './CallApi.js'
$(document).ready(function() {
    // =Chuc Nang Click vao User Follow ==================

    $("#user-flows").on("click",()=>{
        let idUser = $("#id-user").val();
        if(idUser == null || idUser === '') return;
        $('#hop-thu').removeClass('btn-primary');
        $('#hop-thu').addClass('btn-light')

        $('#user-flows').removeClass('btn-light');
        $('#user-flows').addClass('btn-primary')
        $('.box-message-chat').empty();

        $.ajax(getUserFlows(idUser))
    })


    $("#hop-thu").on("click",()=>{
        var idUser = $("#id-user").val();
        if(idUser == null || idUser === '') return;
        $('#hop-thu').removeClass('btn-light');
        $('#hop-thu').addClass('btn-primary')

        $('#user-flows').removeClass('btn-primary');
        $('#user-flows').addClass('btn-light');
        $('.box-message-chat').empty();

    })


});