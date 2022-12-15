package prezscreenshots

private data class Track(val url: String)

private class MusicPlayer {
    fun play(track: Track) = println("playing $track")

    infix fun playTrack(track: Track) = println("playing $track from infix fun")

}

private fun main() {
    val musicPlayer = MusicPlayer()
    val track = Track("cool track")

    // Regular
    musicPlayer.play(track)

    // infix fun
    musicPlayer playTrack track
}

