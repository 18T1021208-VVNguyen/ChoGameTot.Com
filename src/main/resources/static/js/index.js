$(document).ready(function(){
//===========================================================================
    // open mo tin nhan
    $('#open-box-message').click(()=>{
        $('.header-btn-box-message').removeClass('d-none')
        $('.header-btn-box-message').addClass('d-block')
    })
// close mo tin nhan
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
//===========================================================================
    let temp =  $('#input-texta').prop('scrollHeight');


    $('#input-texta').on("input",(e)=>{
           // console.log(e.target.scrollHeight);
            if(e.target.value === ''){
                $('#input-texta').css({ height:35+'px'})
            }
             $('#input-texta').css({ height: 'auto' } )
             $('#input-texta').css({ height: e.target.scrollHeight+'px' } )
            console.log(e.target.scrollHeight)
    })

//===========================================================================
    // open box account
    $('#open-box-account').click(()=>{
        $('.box-account').removeClass('d-none')
        $('.box-account').addClass('d-block')
    })

    $(document).click(function (e)
    {
        // Đối tượng container chứa popup
        var container = $("#open-box-account");
        // Nếu click bên ngoài đối tượng container thì ẩn nó đi
        if (!container.is(e.target) && container.has(e.target).length === 0)
        {
            $('.box-account').addClass('d-none')
            $('.box-account').removeClass('d-block')
        }
    });
//===========================================================================
});