const removeSongFromPlaylistBtns = $$1(".remove-song-from-playlist");
const idPlaylist  = $("#identifyPlaylistId").value;
removeSongFromPlaylistBtns.forEach((element) =>{
    element.addEventListener("click",(event)=>{
        event.preventDefault();
        var url = element.getAttribute("href");
        $.ajax({
            url:url,
            method:"GET",
            success:function (response){
                console.log(response);
                updatePlaylistUI(response);
            }
        })
    })
})

function updatePlaylistUI(response){
    if (response){
        var listTrack = '';
        response.forEach((track)=>{
            listTrack  += `<li>
                            <div class="result row" style="display: flex">
                                <a class="ajax-link" style="width: 85%;height: 100%;text-decoration: none" href="/tunesmusic/search/playsong?trackid=${track.id}">
                                    <img src="${track.sourceCoverImage}" alt="song cover photo">
                                    <label>${track.trackName} - ${track.artist.artistName}</label>
                                </a>
                                <div class="dropdown" style="width: 12%">
                                    <i onclick="toggleDropdown(this)" style="width: 20px; cursor: pointer;" class="fa-solid fa-ellipsis"></i>
                                    <div class="dropdown-content">
                                        <a class="remove-song-from-playlist" href="/tunesmusic/remove-from-playlist?playlistId=${idPlaylist}&trackId=${track.id}">Remove from playlist</a>
                                    </div>
                                </div>
                            </div>
                        </li>`
        })
        console.log(listTrack);
        $("#list-track-playlist").html(listTrack);
    }
}

$(document).ready(function () {
    $(".list-result-search").on("click", ".ajax-link", function (e) {
        e.preventDefault();
        var url = $(this).attr("href");
        $.ajax({
            url: url,
            method: 'GET',
            success: function (response) {
                updatePlayerBar(response);
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
})