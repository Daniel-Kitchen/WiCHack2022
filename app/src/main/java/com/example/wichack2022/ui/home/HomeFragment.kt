package com.example.wichack2022.ui.home

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wichack2022.R
import com.example.wichack2022.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
      val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

      _binding = FragmentHomeBinding.inflate(inflater, container, false)
      val root: View = binding.root

      val textView: TextView = binding.textHome
      homeViewModel.text.observe(viewLifecycleOwner) {
        textView.text = it
      }
      view?.findViewById<TextView>(R.id.text_home2)?.text = ""

      val sharedPreferences = context?.let { PreferenceManager.getDefaultSharedPreferences(it) }

      val dayCount = sharedPreferences!!.getInt("day", 0)
      if (dayCount != 0){
          view?.let { createResume(it) }
      }
      return root
  }

    private fun createResume(view: View){

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        // find the toast_button by its ID and set a click listener
        view.findViewById<Button>(R.id.button).setOnClickListener {
            if(view.findViewById<Button>(R.id.button).text == "Check in"){
                //val sharedPreferences = requireContext().let { PreferenceManager.getDefaultSharedPreferences(it) }
                val sharedPref = requireActivity().getSharedPreferences("com.example.wichack2022", Context.MODE_PRIVATE)

                val dayCount = sharedPref.getInt("day", -1)+1
                view.findViewById<TextView>(R.id.text_home2)?.text = dayCount.toString()
                sharedPref.edit().putInt("day", dayCount).apply()

            }
            else{
                view.findViewById<Button>(R.id.button).text = "Check in"
                // create a Toast with some text, to appear for a short time
                val myToast = Toast.makeText(context, "Challenge Started!", Toast.LENGTH_SHORT)
                val sharedPref = requireActivity().getSharedPreferences("com.example.wichack2022", Context.MODE_PRIVATE)
                sharedPref.edit().putInt("day", 1).apply()

                view.findViewById<TextView>(R.id.text_home2)?.text = "1"
                // show the Toast
                myToast.show()
            }

        }

    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}