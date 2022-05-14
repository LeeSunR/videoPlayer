package kr.umaru.videoplayer.ui.player

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import kr.umaru.videoplayer.R
import kr.umaru.videoplayer.databinding.FragmentPlayerBinding
import kr.umaru.videoplayer.repository.model.Video
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import android.support.v4.media.session.MediaSessionCompat
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector


const val PARAM_VIDEO = "param_video"

class PlayerFragment : Fragment() {

    private lateinit var video: Video

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            this.video = it.getSerializable(PARAM_VIDEO) as Video
        }
    }

    private lateinit var binding: FragmentPlayerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player, container, false)
        binding.video = video
        initPlayer()
        return binding.root
    }

    private fun initPlayer() {
        val player = ExoPlayer.Builder(requireContext()).build()
        val mediaSession = MediaSessionCompat(requireContext(), "sample")
        val mediaSessionConnector = MediaSessionConnector(mediaSession)
        mediaSessionConnector.setPlayer(player)
        binding.exoPlayerView.player = player
        player.playWhenReady = true
        val mediaItem: MediaItem = MediaItem.fromUri(video.url)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }
}