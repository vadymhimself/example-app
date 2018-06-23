package com.example.misc.adapters.binding;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewStub;

import eu.theappshop.baseadapter.BR;

import eu.theappshop.baseadapter.vm.VM;

/**
 * Created by Artem Sisetskyi on 6/22/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public abstract class ViewStubAdapters {

    //todo somehow DataBinding Adapter dont work with ViewStub
    //generated code -  if (this.vsContent.isInflated()) this.vsContent.getBinding().setVariable(BR.contentVM, controllerViewModel);
    @BindingAdapter("contentVM")
    public static void _bindVMContent(ViewStub view,
                                      VM vm) {
        view.setLayoutResource(vm.getLayoutId());
        view.setOnInflateListener((stub, inflated) -> {
            ViewDataBinding binding = DataBindingUtil.bind(inflated);
            if (binding != null) {
                binding.setVariable(BR.viewModel, vm);
            }
        });
        view.inflate();
    }

    @BindingAdapter("contentVM")
    public static void _bindItemSelected(ViewGroup view,
                                         VM vm) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(view.getContext()),
                vm.getLayoutId(), view, false);
        binding.setVariable(BR.viewModel, vm);
        view.addView(binding.getRoot());
    }
}
