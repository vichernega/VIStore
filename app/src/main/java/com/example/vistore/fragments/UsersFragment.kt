package com.example.vistore.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.vistore.R
import com.example.vistore.adapters.UsersRecyclerViewAdapter
import com.example.vistore.databinding.FragmentUsersBinding
import com.example.vistore.objects.User
import com.example.vistore.objects.UserObject
import com.example.vistore.viewmodels.UsersViewModel

class UsersFragment : Fragment(R.layout.fragment_users) {

    private lateinit var binding: FragmentUsersBinding                      // ViewBinding
    private val viewModel by viewModels<UsersViewModel>()                   // ViewModel
    private lateinit var recyclerViewAdapter: UsersRecyclerViewAdapter      // RecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUsersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUsersList()                // call fun to get list of users
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.usersListLiveData.observe(viewLifecycleOwner, Observer {
            initRecyclerViewAdapter(it)                     // set list to recyclerView
            binding.progressBar.visibility = View.GONE      // hide progress bar
        })
    }

    fun initRecyclerViewAdapter(usersList: List<User>){
        recyclerViewAdapter = UsersRecyclerViewAdapter(usersList)
        binding.usersRecyclerView.adapter = recyclerViewAdapter             // connect adapter to recyclerView
    }
}