package net.simplifiedcoding.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.legacy.app.ActionBarDrawerToggle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.data.repositories.NewsDatabase
import com.example.myapplication.ui.fragments.EmptyFragment
import com.example.myapplication.ui.fragments.EmptyFragment.Companion.newInstance
import com.example.myapplication.ui.fragments.HomeFragment
import com.example.myapplication.ui.fragments.SaveItem
import com.example.myapplication.ui.fragments.SavedFragmnent
import com.example.myapplication.ui.movies.MoviesViewModelFactory
import com.example.myapplication.ui.movies.ParallelNetworkCallsViewModel
import com.example.myapplication.ui.movies.nestedAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_saved_fragmnent.*
import net.simplifiedcoding.data.repositories.MoviesRepository
import net.simplifiedcoding.ui.movies.MoviesAdapter


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ParallelNetworkCallsViewModel
    private lateinit var navController: NavController
    private val homeFragment = HomeFragment()
    private val emptyFragment = EmptyFragment()
    private val savedFragmnent = SavedFragmnent()
    private val saveItem = SaveItem()
    private val fragmentManager = supportFragmentManager
    private var activeFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val newsRepository = MoviesRepository(NewsDatabase.invoke(this))
        val viewModelProviderFactory = MoviesViewModelFactory(application, newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(
            ParallelNetworkCallsViewModel::class.java
        )
        fragmentManager.beginTransaction().apply {
            add(R.id.container, savedFragmnent, "Saved").hide(savedFragmnent)
            add(R.id.container, emptyFragment,"Empty").hide(emptyFragment)
            add(R.id.container,saveItem,"saveItem").hide(saveItem)
            add(R.id.container, homeFragment) }.commit()
        initListeners()
    }

    private fun initListeners() {
        bottomNavView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.Home -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment)
                        .commit()
                    activeFragment = homeFragment
                    true
                }

                R.id.Empty -> {
                    fragmentManager.beginTransaction().hide(activeFragment)
                        .show(emptyFragment).commit()
                    activeFragment = emptyFragment
                    true
                }

                R.id.Saved -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(savedFragmnent)
                        .commit()
                    activeFragment = savedFragmnent
                    arrow.setOnClickListener(View.OnClickListener {
                        fragmentManager.beginTransaction().hide(activeFragment).show(saveItem).commit()
                        activeFragment = saveItem

                    })
                    true
                }

                else -> false
            }
        }
    }
}