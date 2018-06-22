package com.example.misc.adapters.binding;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import eu.theappshop.baseadapter.BR;

import eu.theappshop.baseadapter.vm.VM;

/**
 * Created by Artem Sisetskyi on 6/22/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public class ViewGroupAdapters {

    @BindingAdapter("vmContent")
    public static void _bindItemSelected(ViewGroup view,
                                         VM vm) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(view.getContext()),
                vm.getLayoutId(), view, false);
        binding.setVariable(BR.viewModel, vm);
        view.addView(binding.getRoot());
    }
}
