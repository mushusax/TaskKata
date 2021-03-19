package com.example.taskkata.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.ui.*
import com.bumptech.glide.Glide
import com.example.taskkata.R
import com.example.taskkata.ui.addtask.AddItemDialogFragment
import com.firebase.ui.auth.AuthUI
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class TodoActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //Set up Bottom Sheet Modal to add new tasks
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            AddItemDialogFragment.newInstance(0).show(supportFragmentManager, "dialog")
        }

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.content_nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                    R.id.nav_today, R.id.nav_completed
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)


        //Update profile in header
        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        user?.let {
            val header = navView.getHeaderView(0)

            //Update username
            val username = it.displayName
            val textUserName: TextView = header.findViewById(R.id.user_name)
            textUserName.text = username

            //Update profile image
            val imgProfile: ImageView = header.findViewById(R.id.profile_picture)
            val userImage = it.photoUrl
            Glide.with(this).load(userImage).circleCrop().into(imgProfile)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_logout -> {
                signOut()
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                return true
            }
            R.id.nav_completed -> {
                return item.onNavDestinationSelected(navController).also { drawerLayout.close() }
            }
            R.id.nav_today -> {
                return item.onNavDestinationSelected(navController).also { drawerLayout.close() }
            }
            R.id.nav_settings -> {
                return item.onNavDestinationSelected(navController).also { drawerLayout.close() }
            }
        }
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.content_nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//        return navController.navigateUp(appBarConfiguration)
    }

    private fun signOut() {
        AuthUI.getInstance().signOut(this)

    }
}