package com.example.github.profile

import android.view.View
import com.controllers.Controller
import com.example.ContentVMController
import com.example.R
import com.example.domain.App
import com.example.github.repositories.RepositoriesVM
import com.example.model.data.Repository
import com.example.model.data.User
import eu.theappshop.baseadapter.vm.VM

class UserVM(val parent: Controller<*>, val user: User) : VM {
    override fun getLayoutId() = R.layout.item_user

    fun onUserClick(view: View){
        val reposVm = VerticalListVM(parent, {
            App.require().api().getUserRepo(user.login).map { RepositoriesVM(parent, it) }
        })
        parent.show(ContentVMController(reposVm, "Repositories"))
    }
}