var checkOnline = null;
jQuery(document).ready(function() {

    // var stompClient = null;
    var userID = null;
    // var stompClient = null;

    const stompClient = new StompJs.Client({
        brokerURL: 'ws://localhost:8080/gs-guide-websocket'
    });

    stompClient.onConnect = (frame) => {
        // setConnected(true);
        console.log('Connected: ' + frame);
        sendTracking();

    };

    function sendTracking() {
             userID = $("#id-user").val();
             if(userID == null || userID === '') {
                 disconnect();
                 return ;
             };
            stompClient.publish({
                destination: "/app/tracking.userOnline",
                body: userID
        });
    }

    function connect() {
        stompClient.activate();
    }

    function disconnect() {
        stompClient.deactivate();
        setConnected(false);
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
        // console.log(stompClient);
        if(message != null)
            message.unsubscribe();
        message = stompClient.subscribe(`/topic/checkOnline/${userId}`,callBack  )
    }

})


