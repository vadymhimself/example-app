package com.example.github.profile

import android.databinding.Bindable
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.controllers.Controller
import com.example.R
import com.example.databinding.LayoutProfileBinding
import com.example.domain.App
import com.example.model.data.User
import eu.theappshop.baseadapter.adapter.BaseAdapter


class ProfileController(
        @Bindable val user: User
) : Controller<LayoutProfileBinding>(), Toolbar.OnMenuItemClickListener {

    val adapter: BaseAdapter<*>

    init {
        val api = App.require().api()
        val followersVm = VerticalListVM(this, {
           api.getFollowers(user.login).map { UserVM(this, it) }
        })

        val followingVm = VerticalListVM(this, {
            api.getFollowing(user.login).map { UserVM(this, it) }
        })

        adapter = BaseAdapter(listOf(followersVm, followingVm))
    }

    internal fun toggleFollowing(user: User) {
//        AlertDialog.Builder(activity)
//                .setTitle(if (isFollowing.get()) "Unfollow?" else "Follow?")
//                .setPositiveButton("OK") { dialog, which ->
//                    val d = ProgressDialog.show(activity, "",
//                            "Loading...", true)
//                    val shouldFollow = !isFollowing.get()
//                    Request.with(this, Api::class.java)
//                            .create({ gitHubService ->
//                                gitHubService.toggleFollowing(user,
//                                        shouldFollow)
//                            })
//                            .onFinally(???({ d.hide() }))
//                    .execute { o -> isFollowing.set(shouldFollow) }
//                }
//                .setNegativeButton("CANCEL") { dialog, which ->
//                    dialog
//                            .dismiss()
//                }
//                .show()
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_follow -> {
                toggleFollowing(user)
                return true
            }
        }
        return false
    }

    override fun getLayoutId() = R.layout.layout_profile
}
