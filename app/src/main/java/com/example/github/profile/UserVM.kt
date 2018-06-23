package com.example.github.profile

import android.view.View
import com.controllers.Controller
import com.example.ContentVMController
import com.example.R
import com.example.domain.App
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
    parent.show(ContentVMController(reposVm, "Repo"))
    //parent.show(ProfileController(user))
  }
}