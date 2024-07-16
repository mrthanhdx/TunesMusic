$(document).ready(function () {
    $(".ajax-link").click(function (e) {
        e.preventDefault();
        var url = $(this).attr("href");
        console.log($(this).attr("href"));


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