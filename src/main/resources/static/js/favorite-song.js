const showAllFavoriteSongBtn = $("#show-all-favorite-song");
const favoriteSongContent = $(".list-favorite-songs");
showAllFavoriteSongBtn.click((event)=>{
    event.preventDefault();
    $.ajax({
        url: '/tunesmusic/favorite-song/show-all',
        method: 'GET',
        dataType: 'json',
        success: function(response) {
            let htmlContent = '';
            response.forEach((track) => {
                htmlContent += `
        <ul>
          <li>
            <div class="result row" style="display: flex">
              <a class="ajax-link" style="width: 82%;height: 100%;text-decoration: none" href="/tunesmusic/search/playsong?trackid=${track.id}">
                <img src="${track.sourceCoverImage}" alt="song cover photo">
                <label>${track.trackName} - ${track.artist.artistName}</label>
              </a>
              <div class="dropdown" style="width: 12%">
                <i onclick="toggleDropdown(this)" style="width: 20px; cursor: pointer;" class="fa-solid fa-ellipsis"></i>
                <div class="dropdown-content">
                  <a href="/tunesmusic/remove-from-favorite?trackId=${track.id}">Remove from favorite</a>
                </div>
              </div>
            </div>
          </li>
        </ul>
      `;
            });
            $('.list-favorite-songs').html(htmlContent);
        }
    });
})






function showAllFavoriteSong(response){
    let htmlContent = '';
    response.forEach((track) =>{
        htmlContent += ` <ul>
                            <li>
                                <div class="result row" style="display: flex">
                                    <a class="ajax-link" style="width: 82%;height: 100%;text-decoration: none" href="/tunesmusic/search/playsong?trackid=${track.id}">
                                        <img src="${track.sourceCoverImage}" alt="song cover photo">
                                        <label>${track.trackName} - ${track.artist.artistName}</label>
                                    </a>
                                    <div class="dropdown" style="width: 12%">
                                        <i onclick="toggleDropdown(this)" style="width: 20px; cursor: pointer;" class="fa-solid fa-ellipsis"></i>
                                        <div class="dropdown-content">
                                            <a href="/tunesmusic/remove-from-favorite?trackId=${track.id}">Remove from favorite</a>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
`
    });
    favoriteSongContent.innerHTML = htmlContent;
}

function handleRemoveFromFavorite(trackId,event,element){
    event.preventDefault();
    var url = element.getAttribute("href");
    $.ajax({
        url:url,
        method: "GET",
        success:function (response){
            loadNewFavoriteList(response);
        }
    })
}
function loadNewFavoriteList(response) {
    var htmls = "";
    response.forEach((track) => {
        htmls += `
      <li>
        <div class="result row" style="display: flex">
          <a class="ajax-link" style="width: 78%;height: 100%;text-decoration: none" href="/tunesmusic/search/playsong?trackid=${track.id}">
            <img src="${track.sourceCoverImage}" alt="song cover photo">
            <label>${track.trackName} - ${track.artist.artistName}</label>
          </a>
          <div class="dropdown" style="width: 12%">
            <i onclick="toggleDropdown(this)" style="width: 20px; cursor: pointer;" class="fa-solid fa-ellipsis"></i>
            <div class="dropdown-content">
              <a onclick="handleRemoveFromFavorite(${track.id}, event, this)" href="/tunesmusic/remove-from-favorite?trackId=${track.id}">Remove from favorite</a>
            </div>
          </div>
        </div>
      </li>
    `;
    });
    $(".list-favorite-songs").html(`
    <ul>
      ${htmls}
    </ul>
  `);
}
