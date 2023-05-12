package com.example.youtube.data.remote.model

data class Playlist(
    val kind: String?,
    val etag: String?,
    val nextPageToken: String?,
    val pageInfo: PageInfo?,
    val items: List<Item?>?
) {
    data class PageInfo(
        val totalResults: Int?,
        val resultsPerPage: Int?
    )

    data class Item(
        val kind: String?,
        val etag: String?,
        val id: String?,
        val snippet: Snippet?,
        val contentDetails: PlaylistDetails?
    ) {
        data class Snippet(
            val publishedAt: String?,
            val channelId: String?,
            val title: String?,
            val description: String?,
            val thumbnails: Thumbnails?,
            val channelTitle: String?,
            val localized: Localized?
        ) {
            data class Thumbnails(
                val default: Default?,
                val medium: Medium?,
                val high: High?,
                val standard: Standard?,
                val maxres: Maxres?
            ) {
                data class Default(
                    val url: String?,
                    val width: Int?,
                    val height: Int?
                )

                data class Medium(
                    val url: String?,
                    val width: Int?,
                    val height: Int?
                )

                data class High(
                    val url: String?,
                    val width: Int?,
                    val height: Int?
                )

                data class Standard(
                    val url: String?,
                    val width: Int?,
                    val height: Int?
                )

                data class Maxres(
                    val url: String?,
                    val width: Int?,
                    val height: Int?
                )
            }

            data class Localized(
                val title: String?,
                val description: String?
            )
        }

        data class PlaylistDetails(
            val itemCount: Int?
        )
    }
}