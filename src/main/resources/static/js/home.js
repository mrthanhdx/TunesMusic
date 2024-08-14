const $1 = document.querySelector.bind(document);
const $$1 = document.querySelectorAll.bind(document);

var audioElement = $1("#audio");
const btnplay2 = $1("#btnPlay");
const controlPlayer = $1(".control");
const songInfo = $1("#group1");
const btnNext = $1(".btn-next");
const btnPrev = $1(".btn-prev");
const progresBar = $1("#progress");
var trackPlay;
var currentIndex;


    var refreshAudio = function (){
        audioElement = $1("#audio");
}
//ajax handle
$(document).ready(function () {
    $(".list-favorite-songs").on("click", ".ajax-link", function (e) {
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

// Check if trackPlay is defined and find its index in trackList
if (trackPlay != null && trackList) {
    currentIndex = trackList.findIndex(e => e.id === trackPlay.id);
}
var stopAudio = function () {
    audioElement.pause();
    audioElement.currentTime = 0;
}
var updateSongInfo = function (index) {
    console.log(trackList);
    // Update songInfo HTML based on currentIndex
    if (currentIndex !== undefined && trackList[index]) {
        songInfo.innerHTML = `
            <img src="${trackList[index].sourceCoverImage}" alt="Album Cover" />
            <div class="info">
                <h1>${trackList[index].trackName}</h1>
                <br />
                <h2>${trackList[index].artist.artistName}</h2>
                <audio id="audio" src="${trackList[index].sourceAudio}"></audio>
            </div>
        `;
        // Set audio source
        audioElement.src = trackList[index].sourceAudio;
        audioElement.load();
    }
}

var playAudio = function () {
    if (audioElement.src && audioElement.readyState === 4) {
        // Attempt to play audio
        audioElement.play().catch(error => {
            console.error('Error playing audio:', error);
        });
        updateProgress();
    } else {
        console.log('Hi guys');
    }
}
var updateProgress = function () {
    audioElement.ontimeupdate = function () {
        progresBar.value = Math.floor(audioElement.currentTime / audioElement.duration * 100);
    }
}

// Initial play attempt
playAudio();

var isPlaying = true;

btnplay2.onclick = function () {
    if (isPlaying) {
        audioElement.pause();
    } else {
        playAudio();
    }
    isPlaying = !isPlaying; // Toggle the playing state
}

//Button Next song handle
btnNext.onclick = () => {
    if (currentIndex < trackList.length - 1) {

        currentIndex++;
    } else {
        currentIndex = 0;
    }
    updateSongInfo(currentIndex);
    trackPlay = trackList[currentIndex];
    audioElement.src = trackPlay.sourceAudio;
    audioElement.load();
    audioElement.addEventListener('canplaythrough', function() {
        playAudio();
        isPlaying = true;
    });
}
//Button Previous song handle
btnPrev.onclick = () => {
    if (currentIndex > 0) {
        currentIndex--;
    } else {
        currentIndex = trackList.length - 1;
    }
    updateSongInfo(currentIndex);
    trackPlay = trackList[currentIndex];
    audioElement.src = trackPlay.sourceAudio;
    audioElement.load();
    audioElement.addEventListener('canplaythrough', function() {
        playAudio();
        isPlaying = true;
    });
}
progresBar.oninput = () => {
    console.log(progresBar.value);
    audioElement.currentTime = audioElement.duration / 100 * progresBar.value;
    audioElement.play();
}
audioElement.onplay = () => {
    controlPlayer.classList.add("playing");
}

audioElement.onpause = () => {
    controlPlayer.classList.remove("playing");
}

function handleSearch(event) {
    event.preventDefault();
    const keysearch = document.getElementById('keysearch').value;
    const url = `/tunesmusic/search/search-song?keysearch=${encodeURIComponent(keysearch)}`;
    window.location.href = url;
}

window.onkeydown = function(e) {
    if (e.keyCode == 32) { // space bar
        btnplay2.click(); // call the click method
    }
};
