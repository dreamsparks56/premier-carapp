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
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import ar.edu.ort.tp3.parcialtp3ort.Models.LoginViewModel
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.tools.ImageFetching
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.imageview.ShapeableImageView
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
    lateinit var headerView: View
    lateinit var navController: NavController
    lateinit var nombreUsuario:TextView
    lateinit var toolbar:Toolbar
    lateinit var toolbarPic: ShapeableImageView
    lateinit var drawerPic: ShapeableImageView
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
        return viewMainFrag
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        drawerLayout = viewMainFrag.findViewById(R.id.drawer_layout_main_container)
        navigationView = viewMainFrag.findViewById(R.id.nav_view)
        navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        //Asigno mi toolbar nueva en el lugar del appBar.
        toolbar = viewMainFrag.findViewById(R.id.toolbar)
        activity = requireActivity() as AppCompatActivity
        setupDrawerLayout()
        setupBottomNavBar()
        viewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        headerView = navigationView.getHeaderView(0)
        asignarNombreUsuarioAlMenu()
        asignarImagenesDePerfil()
    }

    override fun onResume() {
        super.onResume()
        Log.d("Images onresume", "Image has been updated: ${viewModel.photoUrl}")
        actualizarImagenesDePerfil()
    }

    private fun setupBottomNavBar() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        viewMainFrag.findViewById<BottomNavigationView>(R.id.bottom_bar).setupWithNavController(navController)
    }


    private fun setupDrawerLayout() {
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        navigationView.setupWithNavController(navController)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener{ _, _, _ ->
                toolbar.title = ""
        }
        navigationView.menu.findItem(R.id.loginFragment).setOnMenuItemClickListener {
            signOutDialog()
            true
            //TODO: Solve crash on signout
        }
        //esto lo llamo desde la toolbar q ya tiene el manejo del action bar original.
    }

    private fun asignarNombreUsuarioAlMenu(){
        nombreUsuario = headerView.findViewById<TextView>(R.id.nameUser_headerNav)
        nombreUsuario.text = viewModel.usuario.value.toString()
    }

    private fun asignarImagenesDePerfil() {
        toolbarPic = viewMainFrag.findViewById(R.id.perfilFragment)
        drawerPic = headerView.findViewById(R.id.perfilFragment)
        actualizarImagenesDePerfil()
        toolbarPic.setOnClickListener {
            navController.navigate(R.id.action_global_perfilFragment)
        }
        drawerPic.setOnClickListener {
            navController.navigate(R.id.action_global_perfilFragment)
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    private fun actualizarImagenesDePerfil() {
        ImageFetching.getImageWebOrLocal(toolbar, toolbarPic, viewModel.photoUrl, R.drawable.avatar_car)
        ImageFetching.getImageWebOrLocal(drawerLayout, drawerPic, viewModel.photoUrl, R.drawable.avatar_car)
    }

    private fun signOutDialog() {
        val dialog = MaterialAlertDialogBuilder(requireContext())
        dialog.setTitle("Cerrar sesión")
        dialog.setMessage("¿Estas seguro que queres cerrar sesión?")

        dialog.setPositiveButton("Yeah") { _, _ ->
            signOut()
        }
        dialog.setNeutralButton("Cancel") { _, _ ->
            Toast.makeText(this.context, "Cancelled", Toast.LENGTH_SHORT).show()
        }
        dialog.create()
        dialog.setCancelable(false)
        dialog.show()
    }

    private fun signOut() {
        fireBaseAuth.signOut()
        Toast.makeText(this.context,"Log Out Ok", Toast.LENGTH_SHORT).show()
        val action =  MainFragmentDirections.actionMainFragmentToLoginFragment()
        viewMainFrag.findNavController().navigate(action)
    }












}