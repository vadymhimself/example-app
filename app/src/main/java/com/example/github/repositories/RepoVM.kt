package com.example.github.repositories

import com.controllers.Controller
import com.example.R
import com.example.model.data.Repository
import eu.theappshop.baseadapter.vm.DiffVM
import eu.theappshop.baseadapter.vm.VM

/**
 * Created by Artem Sisetskyi on 6/22/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
class RepoVM(
  val parent: Controller<*>,
  val repo: Repository
) : DiffVM {

  override fun isEqualItem(vm: VM?) = vm is RepoVM

  override fun isEqualContent(vm: VM?) = this == vm

  override fun getLayoutId() = R.layout.item_repository
}