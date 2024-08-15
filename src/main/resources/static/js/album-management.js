function handleAddSongToAlbum(event,element){
    event.preventDefault();
    var url = element.getAttribute("href");
    $.ajax({
        url:url,
        method:"GET",
        success:(response)=>{
            renderNewListSong(response);
        }
    })
    function renderNewListSong(newListSong){
        var htmls ="";
        var idAlbum = $("#idDetailAlbum").val();
        var listSongAlbum = $("#listSongAlbum");
        newListSong.forEach((track,i)=>{
         htmls+=  `<tr>
                            <th scope="row">${i}</th>
                            <td><img style="width: 64px;height: 64px" src="${track.sourceCoverImage}" alt="Song cover image"></td>
                            <td>${track.trackName}</td>
                            <td>${track.playCount}</td>
                            <td>${track.dayAdded}</td>
                            <td>
                                <a href="/artist/tunesmusic/album-management/remove-song-from-album?idAlbum=${idAlbum}&idSong=${track.id}"
                                   class="btn btn-danger btn-remove-song-from-album">Remove from album</a>
                            </td>
                            <td>
                                <a class="ajax-link btn btn-primary" href="/tunesmusic/playsong?id=${track.id}">Play</a>
                            </td>
                        </tr>`
        })
        listSongAlbum.html(htmls);
        handleAddToPlaylist1("success");
    }

}

function handleRemoveSongFromAlbum(element,event){
    event.preventDefault();
    var url = element.getAttribute("href");
    $.ajax({
        url:url,
        method:"GET",
        success:(response)=>{
            renderNewListSong(response);
        }
    })
    function renderNewListSong(newListSong){
        var htmls ="";
        var idAlbum = $("#idDetailAlbum").val();
        var listSongAlbum = $("#listSongAlbum");
        newListSong.forEach((track,i)=>{
            htmls+=  `<tr>
                            <th scope="row">${i}</th>
                            <td><img style="width: 64px;height: 64px" src="${track.sourceCoverImage}" alt="Song cover image"></td>
                            <td>${track.trackName}</td>
                            <td>${track.playCount}</td>
                            <td>${track.dayAdded}</td>
                            <td>
                                <a href="/artist/tunesmusic/album-management/remove-song-from-album?idAlbum=${idAlbum}&idSong=${track.id}"
                                   class="btn btn-danger btn-remove-song-from-album"">Remove from album</a>
                            </td>
                            <td>
                                <a class="ajax-link btn btn-primary" href="/tunesmusic/playsong?id=${track.id}">Play</a>
                            </td>
                        </tr>`
        })
        listSongAlbum.html(htmls);
        handleAddToPlaylist1("success");
    }
}


$(document).ready(function () {
    $(".list-songs").on("click", ".ajax-link", function (e) {
        e.preventDefault();
        var url = $(this).attr("href");
        $.ajax({
            url: url,
            method: 'GET',
            success: function (response) {
                updatePlayerBar(response);
                console.log(response);
                trackPlay = response;
            }
        })

        function updatePlayerBar(track) {
            if (track) {
                stopAudio();
                $("#group1 img").attr('src', track.sourceCoverImage);

                // Update the track name
                $('#group1 h1').text(track.trackName);

                // Update the artist name
                $('#group1 h2').text(track.artist.artistName);

                // Update the audio source
                $('#audio').attr('src', track.sourceAudio);

                // Reload the audio element
                if (!audioElement.paused){
                    stopAudio();
                }
                refreshAudio();
                audioElement.load();
                audioElement.play();
                isPlaying=true;
                updateProgress();
                audioElement.onplay = () => {
                    controlPlayer.classList.add("playing");
                }

                audioElement.onpause = () => {
                    controlPlayer.classList.remove("playing");
                }
            } else {
                console.error('No track data to update the player bar');
            }
        }
    })


    $(".list-songs").on("click", ".btn-remove-song-from-album", function (e) {
        e.preventDefault();
        var url = $(this).attr("href");
    console.log(url)
        $.ajax({
            url:url,
            method:"GET",
            success:(response)=>{
                renderNewListSong(response);
            }
        })
        function renderNewListSong(newListSong){
            var htmls ="";
            var idAlbum = $("#idDetailAlbum").val();
            var listSongAlbum = $("#listSongAlbum");
            newListSong.forEach((track,i)=>{
                htmls+=  `<tr>
                            <th scope="row">${i}</th>
                            <td><img style="width: 64px;height: 64px" src="${track.sourceCoverImage}" alt="Song cover image"></td>
                            <td>${track.trackName}</td>
                            <td>${track.playCount}</td>
                            <td>${track.dayAdded}</td>
                            <td>
                                <a href="/artist/tunesmusic/album-management/remove-song-from-album?idAlbum=${idAlbum}&idSong=${track.id}"
                                   class="btn btn-danger btn-remove-song-from-album">Remove from album</a>
                            </td>
                            <td>
                                <a class="ajax-link btn btn-primary" href="/tunesmusic/playsong?id=${track.id}">Play</a>
                            </td>
                        </tr>`
            })
            listSongAlbum.html(htmls);
            handleAddToPlaylist1("success");
        }
    })

})


function handleAddToPlaylist1(response){
    if (response=="success"){
        toastIcon.setAttribute("src", "/img/icon/success.jpg");
        toastBody.innerText = "successfully !";
        currentTime.innerText = `Just now`;
        toastTitle.innerText = "Success";
        var toastMessage = document.getElementById("liveToast");
        var toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastMessage);
        toastBootstrap.show();
        setTimeout(()=>{
            toastBootstrap.hide();
        },3000);
    } else {
        toastIcon.setAttribute("src", "/img/icon/error.jpg");
        toastBody.innerText = "this song already exist in this playlist !";
        currentTime.innerText = `Just now`;
        toastTitle.innerText = "Failed";
        var toastMessage = document.getElementById("liveToast");
        var toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastMessage);
        toastBootstrap.show();
        setTimeout(()=>{
            toastBootstrap.hide();
        },3000);
    }
}