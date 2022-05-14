package kr.umaru.videoplayer.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.umaru.videoplayer.R
import kr.umaru.videoplayer.databinding.FragmentListBinding
import kr.umaru.videoplayer.ui.player.PARAM_VIDEO

@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val listViewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.listViewModel = listViewModel
        initEvent()
        return binding.root
    }

    private fun initEvent() {
        listViewModel.openEvent.observe(this) {
            findNavController().navigate(
                R.id.action_listFragment_to_playerFragment,
                bundleOf(
                    Pair(PARAM_VIDEO, it)
                )
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}