let arrayMessageSub = [];

export function  getUserFlows(userId){
    if(arrayMessageSub.length > 0){
        arrayMessageSub.forEach( (ele,index) =>{
            ele.unsubscribe();
        })

        console.log("has subrice");
    }
    return {
        type: 'GET',
        url: '/user-follow',
        contentType : "application/json; charset=UTF-8",
        // data: JSON.stringify(requestBody),
        data: $.param({user: userId}),
        dataType: 'json',
        cache: false,
        async : true,
        success: function (data, status) {
            data.forEach(e => {
                // Goi ham dang ki socket,
                console.log(e)
                $('.box-message-chat').append(`
                                <div class="box-message-chat-item">
                <div class="avatar">
                    <img src="${window.location.origin}/images/avatar/noImg.jpg">
                    <i id="user-flow-on-off-${e.userFollowId}" class="fa-solid fa-circle" ${e.isOnline ? 'style="color: #00FF00"' : ''}></i>
                </div>
                <div class="chatbox">
                    <span class="chatbox-name">${e.name}</span>

                </div>
            </div>
                `)
                let message =  checkOnline(e.userFollowId , (payload)=>{
                    var isOnline = JSON.parse(payload.body);

                    if(isOnline){
                        $(`#user-flow-on-off-${e.userFollowId}`).css({ 'color': '#00FF00'})
                    }else {
                        $(`#user-flow-on-off-${e.userFollowId}`).css({ 'color': '#6c757d'} )
                    }
                    console.log("online : "+ isOnline);

                })

                arrayMessageSub.push(message);
            })

        }
    }
}

