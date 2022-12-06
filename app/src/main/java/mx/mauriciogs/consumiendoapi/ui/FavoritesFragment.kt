package mx.mauriciogs.consumiendoapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import mx.mauriciogs.consumiendoapi.R
import mx.mauriciogs.consumiendoapi.application.PersonajesApplication
import mx.mauriciogs.consumiendoapi.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {

    private var _binding : FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory( (requireActivity().application as PersonajesApplication).repository )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.allCharactersLocalList.observe(viewLifecycleOwner) { personajes ->
            println(personajes)
        }
    }

}