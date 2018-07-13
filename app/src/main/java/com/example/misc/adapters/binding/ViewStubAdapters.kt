package com.example.misc.adapters.binding

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewStub

import eu.theappshop.baseadapter.BR

import eu.theappshop.baseadapter.vm.VM

/**
 * Created by Artem Sisetskyi on 6/22/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
object ViewStubAdapters {

  //todo somehow DataBinding Adapter dont work with ViewStub
  //generated code -  if (this.vsContent.isInflated()) this.vsContent.getBinding().setVariable(BR.contentVM, controllerViewModel);
  @BindingAdapter("contentVM")
  fun _bindVMContent(
    view: ViewStub,
    vm: VM
  ) {
    view.layoutResource = vm.layoutId
    view.setOnInflateListener { stub, inflated ->
      val binding = DataBindingUtil.bind<ViewDataBinding>(inflated)
      binding?.setVariable(BR.viewModel, vm)
    }
    view.inflate()
  }

  @BindingAdapter("contentVM")
  fun _bindItemSelected(
    view: ViewGroup,
    vm: VM
  ) {
    val binding = DataBindingUtil.inflate<ViewDataBinding>(
        LayoutInflater.from(view.context),
        vm.layoutId, view, false
    )
    binding.setVariable(BR.viewModel, vm)
    view.addView(binding.root)
  }
}
