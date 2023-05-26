package ar.edu.ort.tp3.parcialtp3ort.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import ar.edu.ort.tp3.parcialtp3ort.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainFragment : Fragment() {
    lateinit var viewMainFrag:View
     lateinit var navHostFragment: NavHostFragment
     lateinit var drawerLayout: DrawerLayout
     lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewMainFrag = inflater.inflate(R.layout.fragment_main, container, false)

        drawerLayout = viewMainFrag.findViewById(R.id.drawer_layout_main_container)
        navigationView = viewMainFrag.findViewById(R.id.nav_view)
        navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        setupBottomNavBar()

        return viewMainFrag
    }


    private fun setupBottomNavBar() {
        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        viewMainFrag.findViewById<BottomNavigationView>(R.id.bottom_bar).setupWithNavController(navController)
    }


}