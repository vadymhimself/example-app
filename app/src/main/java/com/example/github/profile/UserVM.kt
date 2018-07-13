package com.example.github.profile

import android.support.v7.widget.Toolbar
import android.view.View
import com.controllers.Controller
import com.example.R
import com.example.domain.App
import com.example.github.repositories.RepositoriesController
import com.example.github.repositories.RepoVM
import com.example.model.data.User
import eu.theappshop.baseadapter.vm.VM

class UserVM(
  val parent: Controller<*>,
  val user: User
) : VM {
  override fun getLayoutId() = R.layout.item_user

  fun onUserClick(view: View) {
    val reposVm = VerticalListVM(parent, {
      App.require()
          .api()
          .getUserRepo(user.login)
          .map { RepoVM(parent, it) }
    })
    parent.show(RepositoriesController(user.login))
    //parent.show(ProfileController(user))
  }
}