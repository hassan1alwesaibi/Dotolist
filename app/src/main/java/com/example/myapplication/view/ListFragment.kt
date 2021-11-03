package com.example.myapplication.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.TascksViewModel
import com.example.myapplication.TasksListAdapter
import com.example.myapplication.database.model.TasksListModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class ListFragment : Fragment() {
private val tacksList = mutableListOf<TasksListModel>()
private val tasksViewModel:TascksViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
 //-----------------------------------------------------------------------------------------------------------
     //clock message :
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val clockmessages:TextView = view.findViewById(R.id.clockmessages)
         when(hour){
             in 5..11 -> clockmessages.text = "Good Morning"
             in 12..17  ->clockmessages.text =  "Good Afternoon"
             in 18..24 ->clockmessages.text = "Good evening"
             in 1..4 -> clockmessages.text = "Good evening"
         }
//--------------------------------------------------------------------------------
        val tasksRecyclerView:RecyclerView = view.findViewById(R.id.tasks_RecyclerView)
        val addbutton:FloatingActionButton = view.findViewById(R.id.Add_Button)
        val tasksListAdapter = TasksListAdapter(tacksList,tasksViewModel,requireActivity().supportFragmentManager,requireContext())
        val filterSpinner :Spinner = view.findViewById(R.id.filter_Spinner)




        filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tasksViewModel.category = filterSpinner.selectedItem.toString()
                tasksViewModel.getTasks().observe(viewLifecycleOwner,{
                    it?.let{
                        tacksList.clear()
                        tacksList.addAll(it.sortedBy { it.calendar })
                        tasksListAdapter.notifyDataSetChanged()
                    }
                })
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

         tasksRecyclerView.adapter = tasksListAdapter


        addbutton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addTaskFragment)
        }
    }
}