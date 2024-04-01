var checkOnline
jQuery(document).ready(function() {
    var stompClient = null;
    var userID = null;

    function  connect(){
        userID = $("#id-user").val();
        if(userID == null || userID === '') return;


        let socket = new SockJS('http://localhost:8080/tr');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);

        console.log('UserID Clien' , userID)
    }
    connect();

    function onConnected(){

        // Tell your username to the server
        stompClient.send("/app/tracking.userOnline",
            {},
            userID
        )

    }

    function onError(error) {
        console.log("error connect tracking userOnline" + error)
        connect();
    }

    var message = null;
    checkOnline = (userId , callBack) =>{
        // console.log(stompClient);
        if(message != null)
            message.unsubscribe();
        message = stompClient.subscribe(`/topic/checkOnline/${userId}`,callBack  )
    }


})


