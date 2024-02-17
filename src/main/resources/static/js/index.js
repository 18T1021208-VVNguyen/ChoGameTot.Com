$(document).ready(function(){

    $('#open-box-message').click(()=>{
        $('.header-btn-box-message').removeClass('d-none')
        $('.header-btn-box-message').addClass('d-block')
    })

    $(document).click(function (e)
    {
        // Đối tượng container chứa popup
        var container = $("#open-box-message");
       // Nếu click bên ngoài đối tượng container thì ẩn nó đi
         if (!container.is(e.target) && container.has(e.target).length === 0)
        {
            $('.header-btn-box-message').addClass('d-none')
            $('.header-btn-box-message').removeClass('d-block')
        }
    });

    let temp =  $('#textarea').prop('scrollHeight');


    $('#textarea').on("input",(e)=>{
           // console.log(e.target.scrollHeight);
            if(e.target.value === ''){
                $('#textarea').css({ height:35+'px'})
            }
             $('#textarea').css({ height: 'auto' } )
             $('#textarea').css({ height: e.target.scrollHeight+'px' } )
            console.log(e.target.scrollHeight)
    })

    // let rowCount = 1;
    //
    // $('#textarea').on("input", (e)=>{
    //         if(temp !== e.target.scrollHeight && rowCount < 6){
    //             rowCount ++ ;
    //         }
    //         if(e.target.value == ''){
    //             rowCount = 1;
    //         }
    //
    //     $('#textarea').attr('rows', rowCount);
    // })
    // console.log($('.input-chat > textarea').width())
});