var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('#connecting');

let stompClient = null;
let username = null;

let tagUL =  document.querySelector('#useOn');
function  connect(){
    username = document.querySelector('#username').innerText.trim();

    var socket = new SockJS('http://localhost:8080/ws');
    console.log('socket' , socket)
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
    stompClient.debug = null

}
connect();

function onConnected(){

    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/publicChatRoom', onMessageReceived);
    stompClient.subscribe('/topic/listUserOnline', onMessageUserOnline)

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )

    connectingElement.classList.add('hidden');
}

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}
function onMessageUserOnline(payload){
    let message = JSON.parse(payload.body);
    // console.log("================ user online ======================")
    // console.log(message);

    tagUL.innerHTML = "";
    message && message.forEach((item) => {
        let liElement = document.createElement('li');
        liElement.appendChild( document.createTextNode(item.user.userName))
        tagUL.appendChild(liElement)
    })




}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);


    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');
        var usernameElement = document.createElement('strong');
        usernameElement.classList.add('nickname');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('span');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

messageForm.addEventListener('submit', sendMessage, true);