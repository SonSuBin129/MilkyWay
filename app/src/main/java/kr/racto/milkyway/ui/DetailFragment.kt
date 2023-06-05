package kr.racto.milkyway.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.racto.milkyway.R
import kr.racto.milkyway.databinding.FragmentDetailBinding


class DetailFragment : BottomSheetDialogFragment() {

    var binding: FragmentDetailBinding?=null
    val model:MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater,container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val room = model.selectedRoom
        binding!!.roomName.text = room.value?.roomName
        binding!!.roomAddress.text = room.value?.address
    }

}