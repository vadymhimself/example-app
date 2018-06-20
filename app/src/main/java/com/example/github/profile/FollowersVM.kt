package com.example.github.profile

import com.controllers.Controller
import com.controllers.async
import com.example.BR
import com.example.R
import com.example.domain.App
import com.example.misc.ListVM
import com.example.model.data.User
import eu.theappshop.baseadapter.adapter.BaseAdapter

class FollowersVM(val parent: Controller<*>, val user: User) : ListVM() {

    override var adapter : BaseAdapter<UserVM> = BaseAdapter()

    init {
        updateFollowers()
    }

    fun updateFollowers() = parent.async {
        val users = App.require().api().getFollowers(user.login).map {
            UserVM(parent, it)
        }
        adapter = BaseAdapter(users)
        notifyPropertyChanged(BR.adapter)
    }

    override fun getLayoutId() = R.layout.item_list
}
