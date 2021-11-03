package com.example.myapplication.view

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.TascksViewModel
import com.example.myapplication.database.model.TasksListModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.selects.select
import java.text.SimpleDateFormat
import java.util.*

class EditFragment : BottomSheetDialogFragment() {

    private val tascksViewModel: TascksViewModel by activityViewModels()
    private lateinit var selsctetacks: TasksListModel
    private lateinit var datePickerDialog: DatePickerDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var edittitleeditText: EditText = view.findViewById(R.id.edit_title_editText)
        var editsubtitleeditText: EditText = view.findViewById(R.id.edit_subtitle_editText)
        var editcalendarButton: FloatingActionButton = view.findViewById(R.id.edit_calendar_Button)
        var editday_textView: TextView = view.findViewById(R.id.editday_textView)
        var editsaveButton: FloatingActionButton = view.findViewById(R.id.edit_save_Button)

       //------- to take vule from aother fragment
        tascksViewModel.selectedTasks.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
                edittitleeditText.setText(it.title)
                editsubtitleeditText.setText(it.subtitle)
                editday_textView.setText(it.calendar)
                selsctetacks = it

                var selectday = selsctetacks.calendar
                val dateFormat = SimpleDateFormat("yyyy/MM/dd")

                val calendar = Calendar.getInstance()
                calendar.time = dateFormat.parse(selectday)

                datePickerDialog = DatePickerDialog(requireContext())

                datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE , "Ok", { _,_ ->

                    editday_textView.text = "${datePickerDialog.datePicker.year}/${datePickerDialog.datePicker.month+1}/${datePickerDialog.datePicker.dayOfMonth}"
                })
             //   datePickerDialog.updateDate(calendar.time.year,calendar.time.month,calendar.time.day)

            }
        })



        editcalendarButton.setOnClickListener {
            datePickerDialog.show()
        }



        editsaveButton.setOnClickListener {

            selsctetacks.title = edittitleeditText.text.toString()
            selsctetacks.subtitle = editsubtitleeditText.text.toString()
            selsctetacks.calendar = editday_textView.text.toString()


            tascksViewModel.updateTask(selsctetacks)
            dismiss()
        }

    }

}