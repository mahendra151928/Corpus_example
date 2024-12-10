package com.example.myapplication



    data class ContentResponse(
        val responseStatus: ResponseStatus,
        val content: List<Content>
    )

    data class ResponseStatus(
        val statusCode: String,
        val statusMessage: String
    )

    data class Content(
        val title: String,
        val command: String,
        val content: List<ContentItem>,
        val contentType: String,
        val displayOrder: Int
    )

    data class ContentItem(
        val id: Int,
        val title: String,
        val mobileCarouselImage: String,
        val externalLink: String?,
        val onScreenAction: String?,
        val contentType: String
    )
