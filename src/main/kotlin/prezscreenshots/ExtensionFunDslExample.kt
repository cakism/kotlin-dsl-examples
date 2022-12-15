package prezscreenshots


private fun List<String>.toTrackNames() = this.map { "Track name: $this" }


private fun extensionTest() {
    val list = listOf("MyCoolSong", "AnotherTrack").toTrackNames()
}



