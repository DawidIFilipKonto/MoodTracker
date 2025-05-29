import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.moodtracker.R


class MoodHistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mood_history, container, false)

        val moodListView = view.findViewById<ListView>(R.id.moodListView)
        val moodEntries = FakeMoodRepository.moodList.map {
            "${it.date}: ${it.mood} - ${if (it.note.length > 20) it.note.substring(0, 20) + "..." else it.note}"
        }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            moodEntries
        )

        moodListView.adapter = adapter

        moodListView.setOnItemClickListener { _, _, position, _ ->
            val selectedEntry = FakeMoodRepository.moodList[position]
            Toast.makeText(
                context,
                "Wybrano: ${selectedEntry.date}\nNastrój: ${selectedEntry.mood}\nNotatka: ${selectedEntry.note}",
                Toast.LENGTH_LONG
            ).show()
        }

        return view
    }
}