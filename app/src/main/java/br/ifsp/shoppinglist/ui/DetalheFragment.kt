package br.ifsp.shoppinglist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.ifsp.shoppinglist.R
import br.ifsp.shoppinglist.data.Lista
import br.ifsp.shoppinglist.databinding.FragmentDetalheBinding
import br.ifsp.shoppinglist.viewmodel.ListaViewModel
import com.google.android.material.snackbar.Snackbar

class DetalheFragment : Fragment() {
    private var _binding: FragmentDetalheBinding? = null
    private val binding get() = _binding!!
    lateinit var lista: Lista
    lateinit var nomeEditText: EditText
    lateinit var unidadeEditText: EditText
    lateinit var descricaoEditText: EditText
    lateinit var setorEditText: EditText
    lateinit var viewModel: ListaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ListaViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
// Inflate the layout for this fragment
        _binding = FragmentDetalheBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nomeEditText = binding.commonLayout.editTextNome
        unidadeEditText = binding.commonLayout.editTextUnidade
        descricaoEditText = binding.commonLayout.editTextDescricao
        setorEditText = binding.commonLayout.editTextSetor

        val idLista = requireArguments().getInt("idLista")

        viewModel.getListaById(idLista)

        viewModel.lista.observe(viewLifecycleOwner) { result ->
            result?.let {
                lista = result
                nomeEditText.setText(lista.nome)
                unidadeEditText.setText(lista.unidade.toString())
                descricaoEditText.setText(lista.descricao)
                setorEditText.setText(lista.setor)
            }
        }

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.detalhe_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_alterarItem -> {

                        lista.nome = nomeEditText.text.toString()
                        lista.unidade = unidadeEditText.text.toString().toInt()
                        lista.descricao = descricaoEditText.text.toString()
                        lista.setor = setorEditText.text.toString()

                        viewModel.update(lista)

                        Snackbar.make(binding.root, "Item alterado", Snackbar.LENGTH_SHORT)
                            .show()

                        findNavController().popBackStack()
                        true
                    }

                    R.id.action_excluirItem -> {
                        viewModel.delete(lista)

                        Snackbar.make(binding.root, "Item apagado", Snackbar.LENGTH_SHORT).show()

                        findNavController().popBackStack()
                        true
                    }

                    else -> {
                        false
                    }
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


}