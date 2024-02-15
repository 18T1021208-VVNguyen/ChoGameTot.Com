let stompClient1 = null;
let userID = null;

function  connect(){
    userID  = document.querySelector('#userID');
    if(userID === undefined || userID === null) return;
    userID =userID.innerText.trim();

    var socket = new SockJS('http://localhost:8080/tr');
    stompClient1 = Stomp.over(socket);
    stompClient1.connect({}, onConnected, onError);

    console.log('UserID Clien' , userID)
}
connect();

function onConnected(){



    // Tell your username to the server
    stompClient1.send("/app/tracking.userOnline",
        {},
        userID
    )

}

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}
