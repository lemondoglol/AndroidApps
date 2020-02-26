package com.example.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

/**
 * A simple [Fragment] subclass.
 */
class MyFragment(val names: ArrayList<String>) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView: View = inflater.inflate(R.layout.fragment_my, container, false)
        val listView: ListView = fragmentView.findViewById(R.id.my_listview)
        /**
         * @param android.R.layout.simple_list_item_1 build in
         * */
        val adapter = ArrayAdapter(activity!!, android.R.layout.simple_list_item_1, names)
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, i, _ ->
            Toast.makeText(activity, "You clicked ${names[i]}", Toast.LENGTH_LONG).show()
        }

        return fragmentView
    }


}
