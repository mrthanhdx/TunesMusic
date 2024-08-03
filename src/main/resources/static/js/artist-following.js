function requestFollowSubmit(event,idArtist) {
    event.preventDefault();
    var btnFollow = $("#btnFollow-"+idArtist);
    var btnFollowing = $("#btnFollowing-"+idArtist);
    var idFormRequestfollow = "#request-follow-form-"+idArtist;
    var url = $(idFormRequestfollow).attr("action")+"?idArtist="+idArtist;
    $.ajax({
        url:url,
        method:"POST",
        success:function (response){
            console.log(response);
            handleRequestFollow(response);
        }
    })

 function handleRequestFollow(response){
        if (response=="follow"){
            btnFollowing.addClass("active");
            btnFollow.removeClass("active");
        } else if (response=="unfollow"){
            btnFollow.addClass("active");
            btnFollowing.removeClass("active");
        }
 }
}