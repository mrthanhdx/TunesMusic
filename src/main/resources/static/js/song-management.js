const btnRefreshData = $(".btn-refresh");

btnRefreshData.on("click", () => {
    $("#trackName").val("");
    $("#trackImage").val("");
    $("#trackAudio").val("");
});