package com.example.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.TascksViewModel
import com.example.myapplication.database.model.TasksListModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TitleFragment : BottomSheetDialogFragment() {
    private val tascksViewModel: TascksViewModel by activityViewModels()
    private lateinit var selsctetacks: TasksListModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_title, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val subtitleTextView: TextView = view.findViewById(R.id.s_subtitle_textView)
        tascksViewModel.selectedTasks.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
                subtitleTextView.text = it.subtitle
            }
        })

    }
}


