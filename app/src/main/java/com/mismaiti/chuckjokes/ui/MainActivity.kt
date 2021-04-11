package com.mismaiti.chuckjokes.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.ActionBar
import com.mismaiti.chuckjokes.R
import com.mismaiti.chuckjokes.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    var searchMenuItem: MenuItem? = null
    var searchQueryListener : (query: String?) -> Unit = { _ ->}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(activityMainBinding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)
        searchMenuItem = menu?.findItem(R.id.action_search)

        // Get the SearchView and set the searchable configuration
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        (menu?.findItem(R.id.action_search)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            isIconifiedByDefault = true

            maxWidth = Int.MAX_VALUE
            setPadding(0, 0, 16, 0)

            setOnSearchClickListener{
                if (!isIconified) {
                    supportActionBar!!.title = ""
                }
            }

            setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchQueryListener.invoke(query)
                    clearFocus()
                    onActionViewCollapsed()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })

            setOnCloseListener {
                supportActionBar!!.title = "Categories"
                false
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

fun ActionBar.enableHomeAsUp() {
    setDisplayShowHomeEnabled(true)
    setDisplayHomeAsUpEnabled(true)
}

fun ActionBar.disableHomeAsUp() {
    setDisplayShowHomeEnabled(false)
    setDisplayHomeAsUpEnabled(false)
}