package com.example.github.profile

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.databinding.Bindable
import android.databinding.ObservableBoolean
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.controllers.Controller
import com.controllers.ControllerPagerAdapter
import com.example.R
import com.example.databinding.LayoutProfileBinding
import com.example.model.data.User


class ProfileController(
        @Bindable val user: User
) : Controller<LayoutProfileBinding>(), Toolbar.OnMenuItemClickListener {

    val pagerAdapter = ControllerPagerAdapter(this)
    val isFollowing = ObservableBoolean()

    init {
//        pagerAdapter.addController(RepositoriesController(username))
//        pagerAdapter.addController(FollowersController(username))
//        pagerAdapter.addController(FollowingController(username))
//
//        Request.with(this, Api::class.java)
//                .create({ gitHubService -> gitHubService.isFollowing(username) })
//                .onError({ e -> isFollowing.set(false) }) // error means not
//                // following
//                .execute({ o -> isFollowing.set(true) })
//
//        Request.with(this, Api::class.java)
//                .create({ gitHubService -> gitHubService.getUser(username) })
//                .execute({ user ->
//                    this.user = user
//                    notifyPropertyChanged(BR.user)
//                })
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
            R.id.action_follow -> if (isFollowing != null) {
                toggleFollowing(user)
                return true
            }
        }
        return false
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_profile
    }
}
