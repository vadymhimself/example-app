package com.example.github.repositories

import android.databinding.Bindable
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.controllers.BindableProperty
import com.controllers.Controller
import com.controllers.async
import com.example.BR
import com.example.R
import com.example.databinding.LayoutRepositoriesBinding
import com.example.domain.App
import eu.theappshop.baseadapter.adapter.FilterableAdapter
import eu.theappshop.baseadapter.misc.Filter

class RepositoriesController(
  private val login: String
) : Controller<LayoutRepositoriesBinding>(), Toolbar.OnMenuItemClickListener {

  val menuId = R.menu.menu_filter

  @get:Bindable
  var adapter: FilterableAdapter<RepoVM>? by BindableProperty(BR.adapter)

  private var languages = mutableMapOf<String, Boolean>()

  private var descendingOrder = true

  private val listOrders = arrayOf("Ascending", "Descending")

  private val filters = mutableListOf(
      Filter<RepoVM> {
        it.filter { languages[it.repo.language.toString()] ?: false }
      },
      Filter {
        if (descendingOrder) {
          it.sortedByDescending { it.repo.stargazersCount }
        } else {
          it.sortedBy { it.repo.stargazersCount }
        }
      })

  init {
    async {
      val repos = App.require()
          .api()
          .getUserRepo(login)
      languages.clear()
      for (repo in repos) {
        repo.language?.let { languages.put(it, true) }
      }
      val vms = repos.map { RepoVM(this@RepositoriesController, it) }
      adapter = FilterableAdapter(vms, filters)
    }
  }

  override fun onMenuItemClick(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.action_filter -> {
        activity?.let {
          val changedValues = mutableMapOf<String, Boolean>()
          AlertDialog.Builder(it)
              .setTitle("Choose Languages:")
              .setMultiChoiceItems(
                  languages.keys.toTypedArray(), languages.values.toBooleanArray(),
                  { _, which, isChecked ->
                    changedValues[languages.keys.elementAt(which)] = isChecked
                  })
              .setNegativeButton("Cancel", { dialog, _ -> dialog.dismiss() })
              .setPositiveButton("Apply", { dialog, _ ->
                languages.putAll(changedValues)
                adapter?.refresh()
                dialog.dismiss()
              })
              .create()
              .show()
        }
      }
      R.id.action_sort -> {
        activity?.let {
          AlertDialog.Builder(it)
              .setTitle("Choose Order:")
              .setSingleChoiceItems(listOrders,
                  if (descendingOrder) 1 else 0,
                  { dialog, which ->
                    descendingOrder = which == 1
                    dialog.dismiss()
                    adapter?.refresh()
                  }
              )
              .create()
              .show()
        }
      }
      else -> return false
    }
    return true
  }

  override fun getLayoutId() = R.layout.layout_repositories

  override fun getTitle() = "$login Repositories"
}