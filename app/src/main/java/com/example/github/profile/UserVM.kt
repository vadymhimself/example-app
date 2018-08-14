package com.example.github.profile

import android.view.View
import com.controllers.Controller
import com.example.R
import com.example.Show
import com.example.domain.App
import com.example.github.repositories.RepositoriesController
import com.example.model.data.User
import eu.theappshop.baseadapter.vm.VM

class UserVM(
  val parent: Controller<*>,
  val user: User
) : VM {
  override fun getLayoutId() = R.layout.item_user

  fun onUserClick(view: View) {
    App.require().bus().post(Show(RepositoriesController(user.login)))
  }
}