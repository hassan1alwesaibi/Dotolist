package com.example.myapplication.view

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

import com.example.myapplication.R
import com.example.myapplication.TascksViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddTaskFragment : Fragment() {

    private val tasksViewModel: TascksViewModel by activityViewModels()
    var dueDate = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleeditText: EditText = view.findViewById(R.id.edit_title_editText)
        val subtitleeditText: EditText = view.findViewById(R.id.edit_subtitle_editText)
        var dayTextView: TextView = view.findViewById(R.id.day_textView1)
        val calendarButton: FloatingActionButton = view.findViewById(R.id.edit_calendar_Button)
        val saveButton: FloatingActionButton = view.findViewById(R.id.edit_save_Button)

        val datePickerDailog = DatePickerDialog(requireActivity())
        datePickerDailog.setTitle("")

        datePickerDailog.setButton(DialogInterface.BUTTON_POSITIVE , "Ok", { _,_ ->

            dueDate = "${datePickerDailog.datePicker.year}/${datePickerDailog.datePicker.month+1}/${datePickerDailog.datePicker.dayOfMonth}"
            dayTextView.text = dueDate
        })

        calendarButton.setOnClickListener {

            datePickerDailog.show()
        }

        saveButton.setOnClickListener {
            dayTextView.text = dueDate
            val title = titleeditText.text.toString()
            val subtitle = subtitleeditText.text.toString()
            if (title.isNotEmpty() && subtitle.isNotEmpty() && dueDate.isNotEmpty()) {
                tasksViewModel.addTask(title, subtitle,dueDate)
                findNavController().popBackStack()
            }

        }

    }




}



