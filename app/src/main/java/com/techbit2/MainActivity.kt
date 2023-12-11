package com.techbit2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.techbit2.homefragments.SearchFragment

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayout,
            R.string.navOpen,
            R.string.navClose
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val actionBar = supportActionBar
        actionBar!!.title = "Articles"
        actionBar.setHomeButtonEnabled(true)


        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 0){

                actionBarDrawerToggle.isDrawerIndicatorEnabled = false
                actionBarDrawerToggle.setToolbarNavigationClickListener {
                    onBackPressed()
                }
            }
            else{
                actionBarDrawerToggle.isDrawerIndicatorEnabled = true
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                if (supportFragmentManager.backStackEntryCount > 0){
                    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                }
                else{
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    else{
                        drawerLayout.openDrawer(GravityCompat.START)
                    }
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
//            true
//        } else super.onOptionsItemSelected(item)
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.search, menu)


        val searchItem = menu?.findItem(R.id.search)

        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()



                val searchFragment = SearchFragment()
                val bundle = Bundle()
                bundle.putString("query", query)
                searchFragment.arguments = bundle


                supportFragmentManager.beginTransaction()
                    .replace(R.id.homeFragment, searchFragment)
                    .addToBackStack(null)
                    .commit()
                Toast.makeText(this@MainActivity, "Looking for $query", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return true
    }


}