package ar.edu.ort.tp3.parcialtp3ort.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import ar.edu.ort.tp3.parcialtp3ort.Models.LoginViewModel
import ar.edu.ort.tp3.parcialtp3ort.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainFragment : Fragment() {
    lateinit var viewModel: LoginViewModel
    lateinit var viewMainFrag:View
     lateinit var navHostFragment: NavHostFragment
     lateinit var drawerLayout: DrawerLayout
     lateinit var navigationView: NavigationView
     lateinit var navController: NavController
     lateinit var  nombreUsuario:TextView

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
        navController = navHostFragment.navController
        setupBottomNavBar()
       // setupDrawerLayout()
        asignarNombreUsuarioAlMenu()



        return viewMainFrag
    }


    private fun setupBottomNavBar() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        viewMainFrag.findViewById<BottomNavigationView>(R.id.bottom_bar).setupWithNavController(navController)
    }


    private fun setupDrawerLayout() {
        val activity = requireActivity() as AppCompatActivity
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_view) as NavHostFragment
        val navController = navHostFragment.navController

        navigationView.setupWithNavController(navController)

        // Listener para cuando se realiza la navegación
        navController.addOnDestinationChangedListener { _, _, _ ->
            // Aquí configuras el icono izquierdo de la appbar como el del drawer
            activity.supportActionBar?.setHomeAsUpIndicator(R.drawable.menu_icon)
        }
    }

    private fun asignarNombreUsuarioAlMenu(){
        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        val headerView = navigationView.getHeaderView(0)
        nombreUsuario = headerView.findViewById<TextView>(R.id.nameUser_headerNav)
        nombreUsuario.text = viewModel.usuario.value.toString()
    }


}