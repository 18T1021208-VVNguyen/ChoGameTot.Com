var checkOnline = null;
$(document).ready(function() {

    // filter user not login
    var userID = $("#id-user").val();
    if(userID === undefined || userID == null || userID === "") {
        console.log("user is null");
        return ;
    }


    console.log("user is login ");
    const stompClient = new StompJs.Client({
        brokerURL: 'ws://localhost:8080/gs-guide-websocket'
    });

    stompClient.onConnect = (frame) => {
        console.log('Connected: ' + frame);
        sendTracking();
    };

    function sendTracking() {
        console.log("begin send ")
            stompClient.publish({
                destination: "/app/tracking.userOnline"
        });
    }

    function connect() {
        console.log("connecting socket!")
            stompClient.activate();
    }

    function disconnect() {
        stompClient.deactivate();
        console.log("Disconnected");
    }

    stompClient.onWebSocketError = (error) => {
        console.error('Error with websocket', error);
    };
    connect();
//========================================================
//
//     function  connect(){
//         userID = $("#id-user").val();
//         if(userID == null || userID === '') return;
//
//
//         let socket = new SockJS('http://localhost:8080/gs-guide-websocket');
//         stompClient = Stomp.over(socket);
//         stompClient.connect({}, onConnected, onError);
//
//         console.log('UserID Clien' , userID)
//     }
//     connect();
//
//     function onConnected(){
//
//         // Tell your username to the server
//         stompClient.send("/app/tracking.userOnline",
//             {},
//             userID
//         )
//
//     }
//
//     function onError(error) {
//         console.log("error connect tracking userOnline" + error)
//         connect();
//     }

    var message = null;
    checkOnline = (userId , callBack) =>{
        if(message != null)
            message.unsubscribe();
        message = stompClient.subscribe(`/topic/checkOnline/${userId}`,callBack  )
    }

})


