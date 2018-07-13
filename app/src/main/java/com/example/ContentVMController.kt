package com.example

import com.controllers.Controller
import com.example.databinding.LayoutContentVmBinding
import eu.theappshop.baseadapter.vm.VM

/**
 * Created by Artem Sisetskyi on 6/22/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
class ContentVMController<out V : VM>(
  val viewModel: V,
  val controllerTitle: String
) : Controller<LayoutContentVmBinding>() {

  override fun getLayoutId() = R.layout.layout_content_vm

  override fun getTitle(): String {
    return controllerTitle
  }
}