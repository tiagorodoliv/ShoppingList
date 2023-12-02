package br.ifsp.shoppinglist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.ifsp.shoppinglist.R
import br.ifsp.shoppinglist.data.Lista
import br.ifsp.shoppinglist.databinding.FragmentCadastroBinding
import br.ifsp.shoppinglist.viewmodel.ListaViewModel
import com.google.android.material.snackbar.Snackbar

class CadastroFragment : Fragment(){
    private var _binding: FragmentCadastroBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ListaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListaViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCadastroBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.cadastro_menu, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_salvarLista -> {
                        val nome = binding.commonLayout.editTextNome.text.toString()
                        val unidade = binding.commonLayout.editTextUnidade.text.toString().toInt()
                        val descricao = binding.commonLayout.editTextDescricao.text.toString()
                        val setor = binding.commonLayout.editTextSetor.text.toString()

                        val lista = Lista(0,nome, unidade, descricao, setor)
                        viewModel.insert(lista)
                        Snackbar.make(binding.root, "Produto inserido", Snackbar.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}