package com.example.fzthtv.viewmodel

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.example.fzthtv.player.MetaDataReader
import com.example.fzthtv.player.VideoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    val player: Player,
    private val metaDataReader: MetaDataReader
) : ViewModel() {

    val sampleUri : Uri = Uri.parse("https://storage.googleapis.com/exoplayer-test-media-1/gen-3/screens/dash-vod-single-segment/video-avc-baseline-480.mp4")
    private val videoUris = savedStateHandle.getStateFlow("videoUris", emptyList<Uri>())

    val videoItems = videoUris.map { uris ->
        uris.map { uri ->
            VideoItem(
                contentUri = uri,
                mediaItem = MediaItem.fromUri(uri),
                name = metaDataReader.getMetaDataFromUri(uri)?.fileName ?: "No name"
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        player.prepare()
    }

    fun addVideoUri(uri: Uri = sampleUri){
        savedStateHandle["videoUris"] = videoUris.value + uri
        player.addMediaItem(MediaItem.fromUri(uri))
    }

    fun playVideo(uri: Uri = sampleUri){
        player.setMediaItem(
            videoItems.value.find { it.contentUri == uri }?.mediaItem ?: return
        )
    }

    override fun onCleared(){
        super.onCleared()
        player.release()
    }
}
