package com.rememberdev.productapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.rememberdev.productapp.databinding.ActivityMainBinding
import com.rememberdev.productapp.presentation.home.HomeFragment
import com.rememberdev.productapp.presentation.info.InfoFragment
import com.rememberdev.productapp.presentation.search.SearchFragment

class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBarMain.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, HomeFragment())
                .commit()
            supportActionBar?.title = getString(R.string.app_name)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        var title = getString(R.string.app_name)
        when (item.itemId) {
            R.id.nav_home -> {
                fragment = HomeFragment()
                title = getString(R.string.app_name)
            }

            R.id.nav_search -> {
                fragment = SearchFragment()
                title = getString(R.string.menu_search)
            }

            R.id.nav_info -> {
                fragment = InfoFragment()
                title = getString(R.string.menu_info)
            }
        }
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit()
        }
        supportActionBar?.title = title

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun moveToDynamicFavoriteActivity() {
        startActivity(
            Intent(
                this,
                Class.forName("com.rememberdev.productapp.dynamicfavorite.DynamicFavoriteActivity")
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_fav -> {
                try {
                    moveToDynamicFavoriteActivity()
                } catch (e: Exception) {
                    Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerBroadcastReceiver(){
        broadcastReceiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context, intent: Intent) {
                when(intent.action){
                    Intent.ACTION_POWER_CONNECTED -> {
                        Toast.makeText(this@MainActivity, getString(R.string.power_connected), Toast.LENGTH_SHORT).show()
                    }
                    Intent.ACTION_POWER_DISCONNECTED -> {
                        Toast.makeText(this@MainActivity, getString(R.string.power_disconnected), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        val intentFilter = IntentFilter()
        intentFilter.apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onStart() {
        super.onStart()
        registerBroadcastReceiver()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }
}