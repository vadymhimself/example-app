package com.example.github.profile

import android.databinding.Bindable
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.controllers.Controller
import com.example.R
import com.example.databinding.LayoutProfileBinding
import com.example.domain.App
import com.example.github.repositories.RepoVM
import com.example.model.data.User
import eu.theappshop.baseadapter.adapter.BaseAdapter

class ProfileController(
  @Bindable val user: User
) : Controller<LayoutProfileBinding>(), Toolbar.OnMenuItemClickListener {

  val adapter: BaseAdapter<*>

  init {
    val followersVm = VerticalListVM(this, {
      App.require()
          .api()
          .getFollowers(user.login)
          .map { UserVM(this, it) }
    })

    val followingVm = VerticalListVM(this, {
      App.require()
          .api()
          .getFollowing(user.login)
          .map { UserVM(this, it) }
    })

    val repoVm = VerticalListVM(this, {
      App.require()
          .api()
          .getUserRepo(user.login)
          .map { RepoVM(this, it) }
    })

    adapter = BaseAdapter(listOf(followersVm, followingVm, repoVm))
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
