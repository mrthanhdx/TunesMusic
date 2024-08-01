$(document).ready(function () {



    $(".ajax-link").click(function (e) {
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

// JavaScript code to toggle dropdown and playlist list
function toggleDropdown(element) {
    var dropdownContent = element.nextElementSibling;
    dropdownContent.classList.toggle("show");
}

// Show playlist list on hover
document.querySelectorAll('.playlist-dropdown').forEach(function(playlistDropdown) {
    playlistDropdown.addEventListener('mouseover', function() {
        // alert("mouse over")
        var playlistList = playlistDropdown.querySelector('.playlist-list');
        playlistList.classList.add('show');
    });
    playlistDropdown.addEventListener('mouseout', function() {
        var playlistList = playlistDropdown.querySelector('.playlist-list');
        playlistList.classList.remove('show');
    });
});

// Close dropdown and playlist list when clicked outside
window.onclick = function(event) {
    if (!event.target.matches('.fa-ellipsis')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        for (var i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
        var playlistLists = document.getElementsByClassName("playlist-list");
        for (var i = 0; i < playlistLists.length; i++) {
            var openPlaylistList = playlistLists[i];
            if (openPlaylistList.classList.contains('show')) {
                openPlaylistList.classList.remove('show');
            }
        }
    }
}

var addSongToPlaylistBtns = $$1(".btn-add-song-to-playlist");

const toastIcon = document.getElementById("toastIcon");
const toastBody = document.querySelector(".toast-body");
const currentTime = document.getElementById("currentTime");
const toastTitle = document.getElementById("toastTitle");
const date = new Date();
const offset = 7 * 60 * 60 * 1000; // GMT+7 offset in milliseconds
const gmt7Date = new Date(date.getTime() + offset);

const hours = gmt7Date.getHours().toString().padStart(2, '0');
const minutes = gmt7Date.getMinutes().toString().padStart(2, '0');
const day = gmt7Date.getDate().toString().padStart(2, '0');
const month = (gmt7Date.getMonth() + 1).toString().padStart(2, '0');
const year = gmt7Date.getFullYear();
addSongToPlaylistBtns.forEach((element) => {
    if (!element.hasAttribute("data-event-listener-attached")) {
        element.addEventListener("click", (event) => {
            event.preventDefault();
           var url = element.getAttribute("href");


            console.log(url);

           $.ajax({
               url:url,
               method: "GET",
               success:function (response){
                   handleAddToPlaylist(response);
               }
           })
        });
        element.setAttribute("data-event-listener-attached", "true");
    }
});
function handleAddToPlaylist(response){
    if (response=="success"){
        toastIcon.setAttribute("src", "/img/icon/success.jpg");
        toastBody.innerText = "Add song to playlist successfully !";
        currentTime.innerText = `${hours}:${minutes} ${day}-${month}-${year}`;
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
        currentTime.innerText = `${hours}:${minutes} ${day}-${month}-${year}`;
        toastTitle.innerText = "Failed";
        var toastMessage = document.getElementById("liveToast");
        var toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastMessage);
        toastBootstrap.show();
        setTimeout(()=>{
            toastBootstrap.hide();
        },3000);
    }
}