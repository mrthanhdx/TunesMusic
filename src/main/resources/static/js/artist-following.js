function requestFollowSubmit(event, idArtist) {
    event.preventDefault();
    var btnFollow = $("#btnFollow-" + idArtist);
    var btnFollowing = $("#btnFollowing-" + idArtist);
    var idFormRequestfollow = "#request-follow-form-" + idArtist;
    var url = $(idFormRequestfollow).attr("action") + "?idArtist=" + idArtist;
    $.ajax({
        url: url,
        method: "POST",
        success: function (response) {
            console.log(response);
            handleRequestFollow(response);
        }
    })

    function handleRequestFollow(response) {
        if (response == "follow") {
            btnFollowing.addClass("active");
            btnFollow.removeClass("active");
        } else if (response == "unfollow") {
            btnFollow.addClass("active");
            btnFollowing.removeClass("active");
        }
    }
}

function handlePopularArtist(element, event) {
    event.preventDefault();
    var url = element.getAttribute("href");
    $.ajax({
        url: url,
        method: "GET",
        success: function (response) {
            loadPopularArtist(response);
        }
    })

    function loadPopularArtist(listArtist) {
        var btnPopularArtist = document.querySelector(".popular-artist");
        btnPopularArtist.classList.add("active");
        var btnArtistFollowing = document.querySelector(".artist-following");
        btnArtistFollowing.classList.remove("active");


        var htmls = "";
        var listArtistDisplay = $("#list-artist-display");
        listArtist.forEach((artist) => {
            const profilePicture = artist.user && artist.user.profilePicture ? artist.user.profilePicture : 'default-image.jpg';
            // console.log(artist)
            htmls += `
            <li>
                            <div class="result row">
                                <a style="width: 68%" href="/tunesmusic/artist-profile?artistId=${artist.id}">
                                    <img src="${profilePicture}" alt="${artist.artistName}">
                                    <label>${artist.artistName}</label>
                                </a>
                                <form id="request-follow-form-${artist.id}" style="width: 20%"
                                     action="/tunesmusic/artist-following/request-follow"
                                      onsubmit="requestFollowSubmit(event,${artist.id})"
                                      method="post">
                                    <div>
                                        <button id="btnFollow-${artist.id}" type="submit" class="follow active ">
                                            Follow
                                            <i class="fa-solid fa-plus"></i>
                                        </button>

                                        <button id="btnFollowing-${artist.id}" type="submit"
                                                class="following">Following
                                            <i class="fa-solid fa-check"></i>
                                        </button>

                                    </div>
                                </form>
                            </div>
                        </li>
            `;
        })
        listArtistDisplay.html(htmls);
    }
}

function handleSearchArtistFollowing(element, event) {
    event.preventDefault();
    var artistNameSearch = $(".search-artist").val();
    var searchRequest = element.getAttribute("action");
    var url = `${searchRequest}?artistName=${artistNameSearch}`;

    $.ajax({
        url:url,
        method:"GET",
        success:function (response){
            loadListArtistResult(response);
        }
    })

    function loadListArtistResult(listArtist){
        var htmls = "";
        var listArtistDisplay = $("#list-artist-display");
        listArtist.forEach((artist) => {
            console.log(artist.isFollowing);
            const profilePicture = artist.user && artist.user.profilePicture ? artist.user.profilePicture :
                '/img/userProfileImage/anonymous.jpg';
            // console.log(artist)
            htmls += `
            <li>
                            <div class="result row">
                                <a style="width: 68%" href="/tunesmusic/artist-profile?artistId=${artist.id}">
                                    <img src="${profilePicture}" alt="${artist.artistName}">
                                    <label>${artist.artistName}</label>
                                </a>
                                <form id="request-follow-form-${artist.id}" style="width: 20%"
                                     action="/tunesmusic/artist-following/request-follow"
                                      onsubmit="requestFollowSubmit(event,${artist.id})"
                                      method="post">
                                    <div>
                                    <button id="btnFollow-${artist.id}" type="submit" class="follow ${!artist.isFollowing ? 'active' : ''} "> Follow
                                             <i class="fa-solid fa-plus"></i> 
                                             </button>
                                    
                                  
               <button id="btnFollowing-${artist.id}" type="submit" class="following ${artist.isFollowing ? 'active' : ''}">Following 
               <i class="fa-solid fa-check"></i>
               </button>
                                    </div>
                                </form>
                            </div>
                        </li>
            `;
        })
        listArtistDisplay.html(htmls);
    }
}