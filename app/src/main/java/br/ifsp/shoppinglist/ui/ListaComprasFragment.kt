package br.ifsp.shoppinglist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.ifsp.shoppinglist.R
import br.ifsp.shoppinglist.adapter.ListaAdapter
import br.ifsp.shoppinglist.data.Lista
import br.ifsp.shoppinglist.databinding.FragmentListaComprasBinding
import br.ifsp.shoppinglist.viewmodel.ListaViewModel
import com.google.android.material.snackbar.Snackbar

class ListaComprasFragment : Fragment() {
    private var _binding: FragmentListaComprasBinding? = null
    private val binding get() = _binding!!

    lateinit var listaAdapter: ListaAdapter

    lateinit var viewModel: ListaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaComprasBinding.inflate(inflater, container, false)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_listaComprasFragment_to_cadastroFragment)
        }

        configureRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.main_menu, menu)

                val searchView = menu.findItem(R.id.action_search).actionView as SearchView
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        // Aqui você pode realizar alguma ação se necessário
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        listaAdapter.filter.filter(newText)
                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                TODO("Not yet implemented")
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

    private fun configureRecyclerView() {
        viewModel = ViewModelProvider(this).get(ListaViewModel::class.java)
        viewModel.allLista.observe(viewLifecycleOwner) { list ->
            list?.let {
                listaAdapter.updateList(list as ArrayList<Lista>)
            }
        }
        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        listaAdapter = ListaAdapter()
        recyclerView.adapter = listaAdapter

        val listener = object : ListaAdapter.ListaListener {
            override fun onItemClick(pos: Int) {
                val c = listaAdapter.listaListaFilterable[pos]
                val bundle = Bundle()
                bundle.putInt("idLista", c.id)
                findNavController().navigate(
                    R.id.action_listaComprasFragment_to_detalheFragment,
                    bundle
                )
            }
        }
        listaAdapter.setClickListener(listener)
    }
}