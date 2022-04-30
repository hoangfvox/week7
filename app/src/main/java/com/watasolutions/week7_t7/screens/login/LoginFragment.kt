package com.watasolutions.week7_t7.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.watasolutions.week7_t7.ListAdapter
import com.watasolutions.week7_t7.MySharedPreferences
import com.watasolutions.week7_t7.app.MyApp
import com.watasolutions.week7_t7.app.ViewModelFactory
import com.watasolutions.week7_t7.databinding.FragmentLoginBinding
import com.watasolutions.week7_t7.model.User


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var viewModel: LoginViewModel
    lateinit var adapter : ListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListAdapter()
        binding.rvUser.layoutManager = LinearLayoutManager(context)
        binding.rvUser.adapter = adapter
        registerSaveSuccess()
        registerLoadUserEvent()
        binding.btnSave.setOnClickListener {
            val username = binding.tvUsername.editText?.text.toString().trim()
            val pass = binding.tvPassword.editText?.text.toString().trim()
            viewModel.addUser(User(username, pass))
        }
        binding.btnLoad.setOnClickListener {
            adapter.setData(viewModel.loadData())
        }
    }

    private fun registerSaveSuccess(){
        viewModel.saveEventSuccess.observe(viewLifecycleOwner){
            when(it) {
                true -> {
                    binding.tvUsername.editText?.setText("")
                    binding.tvPassword.editText?.setText("")
                }
            }
        }
    }

    private fun registerLoadUserEvent(){
        viewModel.loadUserEvent.observe(viewLifecycleOwner){
            adapter.setData(viewModel.loadData())
        }
    }

}