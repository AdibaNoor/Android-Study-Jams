package com.example.andy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class homepage : AppCompatActivity() {
    lateinit var toggle : ActionBarDrawerToggle
    lateinit var  drawerLayout : DrawerLayout
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        drawerLayout  = findViewById(R.id.drawer_layout)
        val navView : NavigationView = findViewById(R.id.navigationview)
        auth = FirebaseAuth.getInstance()

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_open_drawer,R.string.navigation_close_drawer)
        drawerLayout.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            it.isChecked = true
            when(it.itemId){
                //R.id.home_page -> replacefragment(Homefragment(),it.title.toString())
                R.id.search_donor -> replacefragment(sendEmailfragment(),it.title.toString())
                R.id.createrequest ->replacefragment(create_request_fragment(),it.title.toString())
                R.id.a_plus -> replacefragment(a_plus(),it.title.toString())
                R.id.a_minus -> replacefragment(a_minus(),it.title.toString())
                R.id.b_minus -> replacefragment(b_minus(),it.title.toString())
                R.id.b_plus -> replacefragment(b_plus(),it.title.toString())
                R.id.ab_plus -> replacefragment(ab_plus(),it.title.toString())
                R.id.ab_minus -> replacefragment(ab_minus(),it.title.toString())
                R.id.o_minus ->replacefragment(o_minus(),it.title.toString())
                R.id.o_plus ->replacefragment(o_plus(),it.title.toString())
                R.id.profile -> replacefragment(ProfileFragment(),it.title.toString())
                R.id.notification -> Toast.makeText(applicationContext, "clicked home", Toast.LENGTH_SHORT).show()
               // R.id.logout -> Toast.makeText(applicationContext, "clicked home", Toast.LENGTH_SHORT).show()
                R.id.logout -> UserLogout()

            }
            true
        }
    }
    private fun replacefragment(fragment: Fragment,title : String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout,fragment)
        fragmentTransaction.commit()

        drawerLayout.closeDrawers()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun UserLogout(){
        auth.signOut()
        startActivity(Intent(this, login::class.java))
        finish()

    }

}