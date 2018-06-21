package com.example.github.profile

import com.controllers.Controller
import com.example.BR
import com.example.R
import com.example.misc.ListVM
import eu.theappshop.baseadapter.adapter.BaseAdapter
import eu.theappshop.baseadapter.vm.VM

/**
 * A VM for displaying other VMs.
 * Takes a suspending function so that the data can be fetched async
 * and the progress be shown in the meantime.
 */
class VerticalListVM<T : VM>(
        private val parent: Controller<*>,
        // check out how we pass a function that provides a list of users
        private val getVms: suspend () -> List<T>
) : ListVM() {

    override var adapter: BaseAdapter<T>? by BindableDelegate(BR.adapter)

    init {
        updateVms()
    }

    fun updateVms() = parent.async {
        // cal the provider function and use the results
        val vms = getVms()
        // don't need to call notifyPropertyChanged, it's called by the delegate
        adapter = BaseAdapter(vms)
    }

    override fun getLayoutId() = R.layout.item_list
}
