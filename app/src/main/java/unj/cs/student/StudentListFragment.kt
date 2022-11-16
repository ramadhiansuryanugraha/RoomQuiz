package unj.cs.student

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import unj.cs.student.Adapter.StudentAdapter
import unj.cs.student.databinding.FragmentStudentListBinding

class StudentListFragment : Fragment() {
    private var _binding: FragmentStudentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StudentViewModel by activityViewModels {
        StudentViewModelFactory(
            (activity?.application as StudentApplication).database.studentDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudentListBinding.inflate(inflater, container, false)
        val root : View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = StudentAdapter{
            val action: NavDirections = StudentListFragmentDirections.actionStudentListFragmentToStudentFormFragment(it.id, "Update Student")
            view.findNavController().navigate(action)
        }
        binding.studentViewRecycler.adapter = adapter
        viewModel.allStudents.observe(this.viewLifecycleOwner) {
            items -> items.let { adapter.submitList(it) }
        }
        val addButton : FloatingActionButton = binding.addButton

        addButton.setOnClickListener{
            val action: NavDirections =
                unj.cs.student.StudentListFragmentDirections.actionStudentListFragmentToStudentFormFragment(
                    -1,
                    "Add Student"
                )
            view.findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}