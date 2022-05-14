package kr.umaru.videoplayer.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.umaru.videoplayer.repository.model.Video
import kr.umaru.videoplayer.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
) : ViewModel() {
    private val _openEvent = SingleLiveEvent<Video>()
    val openEvent: LiveData<Video> get() = _openEvent

    fun startPlayerView() {
        _openEvent.value = Video("새 비디오","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
    }
}