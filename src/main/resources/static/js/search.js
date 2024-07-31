$(document).ready(function () {
    $(".ajax-link").click(function (e) {
        e.preventDefault();
        var url = $(this).attr("href");



        $.ajax({
            url: url,
            method: 'GET',
            success: function (response) {
                // console.log(response)
                updatePlayerBar(response);

            }
        })
    })
    var updatePlayerBar = function (track) {
        if (track) {
            // Update the cover image
            $('#group1 img').attr('src', track.sourceCoverImage);

            // Update the track name
            $('#group1 h1').text(track.trackName);

            // Update the artist name
            $('#group1 h2').text(track.artist.artistName);

            // Update the audio source
            $('#audio').attr('src', track.sourceAudio);

            // Reload the audio element
            var audio = document.getElementById('audio');
            audio.load();
            audio.play();
        } else {
            console.error('No track data to update the player bar');
        }
    }
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

