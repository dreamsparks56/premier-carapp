package ar.edu.ort.tp3.parcialtp3ort.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import ar.edu.ort.tp3.parcialtp3ort.Models.LoginViewModel
import ar.edu.ort.tp3.parcialtp3ort.R
//import com.firebase.ui.auth.AuthUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainFragment : Fragment() {
    lateinit var viewModel: LoginViewModel
    lateinit var viewMainFrag:View
    lateinit var navHostFragment: NavHostFragment
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var navController: NavController
    lateinit var nombreUsuario:TextView
    lateinit var toolbar:Toolbar
    lateinit var activity:AppCompatActivity
    private lateinit var fireBaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fireBaseAuth = Firebase.auth


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewMainFrag = inflater.inflate(R.layout.fragment_main, container, false)

        drawerLayout = viewMainFrag.findViewById(R.id.drawer_layout_main_container)
        navigationView = viewMainFrag.findViewById(R.id.nav_view)
        navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController




        //Asigno mi toolbar nueva en el lugar del appBar.
        toolbar = viewMainFrag.findViewById(R.id.toolbar)
        activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.setDisplayShowTitleEnabled(false) //Elimine el titulo del fragment
        setupBottomNavBar()
        setupDrawerLayout()
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


        navigationView.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(activity, navController, drawerLayout)


        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setHomeAsUpIndicator(R.drawable.menu_icon)
        toolbar.setNavigationOnClickListener {
            onSupportNavigateUp()
        }
        navigationView.menu.findItem(R.id.loginFragment).setOnMenuItemClickListener {
            signOut()
            true
            //TODO: Solve crash on signout
        }
        //esto lo llamo desde la toolbar q ya tiene el manejo del action bar original.

    }

    private fun asignarNombreUsuarioAlMenu(){
        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        val headerView = navigationView.getHeaderView(0)
        nombreUsuario = headerView.findViewById<TextView>(R.id.nameUser_headerNav)
        nombreUsuario.text = viewModel.usuario.value.toString()
    }

     private fun onSupportNavigateUp(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }


        return false
    }
    private fun signOut() {
        val dialog = MaterialAlertDialogBuilder(requireContext())
        dialog.setTitle("Cerrar sesión")
        dialog.setMessage("¿Estas seguro que queres cerrar sesión?")

        dialog.setPositiveButton("Yeah") { _, _ ->
            fireBaseAuth.signOut()
            Toast.makeText(this.context,"Log Out Ok", Toast.LENGTH_SHORT).show()
            val action =  MainFragmentDirections.actionMainFragmentToLoginFragment()
            viewMainFrag.findNavController().navigate(action)
        }
        dialog.setNeutralButton("Cancel") { _, _ ->
            Toast.makeText(this.context, "Cancelled", Toast.LENGTH_SHORT).show()
        }
        dialog.create()
        dialog.setCancelable(false)
        dialog.show()




    }












}