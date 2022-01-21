package re.sin.noteapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import re.sin.noteapplication.viewmodel.PageViewModel
import androidx.fragment.app.viewModels
import re.sin.noteapplication.databinding.FragmentMainTabBinding

class PlaceholderFragment : Fragment() {
    private var _binding: FragmentMainTabBinding? = null
    private val binding get() = _binding!!
    private val pageViewModel: PageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel.apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainTabBinding.inflate(inflater, container, false)

        pageViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.sectionLabel.text = it
        })
        return binding.root
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}