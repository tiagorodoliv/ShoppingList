package br.ifsp.shoppinglist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.ifsp.shoppinglist.R
import br.ifsp.shoppinglist.adapter.ListaAdapter
import br.ifsp.shoppinglist.data.Lista
import br.ifsp.shoppinglist.databinding.FragmentListaComprasBinding
import br.ifsp.shoppinglist.viewmodel.ListaViewModel

class ListaComprasFragment : Fragment(){
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
        val root: View = binding.root
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_listaComprasFragment_to_cadastroFragment) }

        configureRecyclerView()

        return root
    }
    private fun configureRecyclerView()
    {
        viewModel = ViewModelProvider(this).get(ListaViewModel::class.java)
        viewModel.allList.observe(viewLifecycleOwner) { list ->
            list?.let {
                listaAdapter.updateList(list as ArrayList<Lista>)
            }
        }
        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        listaAdapter = ListaAdapter()
        recyclerView.adapter = listaAdapter
    }
}