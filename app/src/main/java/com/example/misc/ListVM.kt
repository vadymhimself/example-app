package com.example.misc

import android.databinding.BaseObservable
import android.databinding.Bindable
import eu.theappshop.baseadapter.adapter.BaseAdapter
import eu.theappshop.baseadapter.vm.VM


abstract class ListVM : VM, BaseObservable() {
    @get:Bindable
    abstract val adapter: BaseAdapter<*>?
}
