const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);

const audioElement = $("#audio");
const btnplay2 = $("#btnPlay");
const controlPlayer = $(".control");

// console.log({iconPause},{iconPlay});
console.log(trackList);

var isPlaying = true;
if (isPlaying) {
    audioElement.play();
}

btnplay2.onclick = function (e){
    if (isPlaying) {
        audioElement.pause();
    } else {
        audioElement.play();
    }
    isPlaying = !isPlaying; // Toggle the playing state
}
audioElement.onplay = (e)=>{
    controlPlayer.classList.add("playing");
}
audioElement.onpause = (e)=>{
    controlPlayer.classList.remove("playing");
}

