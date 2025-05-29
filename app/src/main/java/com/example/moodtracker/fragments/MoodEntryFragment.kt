package com.example.moodtracker.fragments

import MoodEntry
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.moodtracker.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MoodEntryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mood_entry, container, false)

        val saveButton = view.findViewById<Button>(R.id.saveButton)
        val noteEditText = view.findViewById<EditText>(R.id.noteEditText)
        val moodRadioGroup = view.findViewById<RadioGroup>(R.id.moodRadioGroup)
        val categorySpinner = view.findViewById<Spinner>(R.id.categorySpinner)
        val sleptWellCheckBox = view.findViewById<CheckBox>(R.id.sleptWellCheckBox)
        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        val importantSwitch = view.findViewById<Switch>(R.id.importantSwitch)


        val categories = arrayOf("Szkoła", "Dom", "Znajomi", "Zdrowie", "Inne")
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            categories
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = spinnerAdapter

        saveButton.setOnClickListener {
            val selectedMood = when (moodRadioGroup.checkedRadioButtonId) {
                R.id.happyRadioButton -> "Wesoły"
                R.id.averageRadioButton -> "Przeciętny"
                R.id.sadRadioButton -> "Smutny"
                else -> ""
            }

            if (selectedMood.isEmpty()) {
                Toast.makeText(context, "Wybierz nastrój!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val currentDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())

            val newEntry = MoodEntry(
                date = currentDate,
                mood = selectedMood,
                note = noteEditText.text.toString(),
                category = categorySpinner.selectedItem.toString(),
                sleptWell = sleptWellCheckBox.isChecked,
                rating = ratingBar.rating,
                isImportant = importantSwitch.isChecked
            )

            FakeMoodRepository.addMood(newEntry)
            Toast.makeText(context, "Zapisano nastrój!", Toast.LENGTH_SHORT).show()


            findNavController().navigate(R.id.action_moodEntryFragment_to_moodHistoryFragment)
        }

        return view
    }
}