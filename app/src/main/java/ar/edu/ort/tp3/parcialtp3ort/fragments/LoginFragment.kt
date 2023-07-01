package ar.edu.ort.tp3.parcialtp3ort.fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import ar.edu.ort.tp3.parcialtp3ort.Models.LoginViewModel
import ar.edu.ort.tp3.parcialtp3ort.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: LoginViewModel
    lateinit var viewLogin:View
    lateinit var btnLogin: Button
    lateinit var user:EditText
    lateinit var pass:EditText
    lateinit var  btnRegistro :TextView
    lateinit var  btnRecupero :TextView
    lateinit var  btnGoogleSignIn :Button
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 62870
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewLogin = inflater.inflate(R.layout.fragment_login, container, false)
        btnLogin = viewLogin.findViewById(R.id.button_login)
        user = viewLogin.findViewById(R.id.usuario_login)
        pass = viewLogin.findViewById(R.id.pass_login)
        btnRegistro = viewLogin.findViewById(R.id.id_button_registro_login)
        btnRecupero = viewLogin.findViewById(R.id.id_button_recuperoClave_login)

        btnGoogleSignIn = viewLogin.findViewById(R.id.btn_google_sign_in)

        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)

        btnLogin.setOnClickListener{
            //signIn(user.text.toString(), pass.text.toString())

            if(user.text.toString().length> 2 && pass.text.toString().length > 2)  {
                //    signIn(mail.text.toString(), pass.text.toString())
                signInMilConfirmado(user.text.toString(), pass.text.toString())
            }else {
                Toast.makeText(this.context,"Error. El mail y el pass tiene q tener un mínimo de 2 caracteres", Toast.LENGTH_SHORT).show()

            }
        }

        btnGoogleSignIn.setOnClickListener {
            signInGoogle()
        }

        //Login google:
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN) // Esto crea un nuevo constructor de opciones de inicio de sesión de Google.
            .requestIdToken(getString(R.string.default_web_client_id)) //Aquí se solicita el token de identificación (idToken) del usuario. El método
            .requestEmail() //Esto es necesario para obtener el correo electrónico del usuario desde la cuenta de Google y utilizarlo en tu aplicación si es necesario.
            .build() // Finalmente, este método construye y devuelve la instancia de GoogleSignInOptions con la configuración proporcionada.

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        btnRegistro.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegistroUsuarioFragment()
            viewLogin.findNavController().navigate(action)
        }

        btnRecupero.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRecuperoClaveFragment()
            viewLogin.findNavController().navigate(action)
        }




        return viewLogin



    }

    private fun signInGoogle() {
        val signInIntent: Intent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data) //Consiga la cuenta de google q se inicio sesion
            val exception = task.exception

            if (task.isSuccessful) {
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    println( "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                    print(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    // Log.w(TAG, "Google sign in failed", e)
                    Toast.makeText(requireContext(), "Fallo inicio de sesión de google:(", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Log.w(TAG, exception.toString())
                Toast.makeText(requireContext(), "Hubo algun error con google:(", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //  Log.d(TAG, "signInWithCredential:success")
                    Toast.makeText(this.context, "Inicio de sesión con google correcto :)", Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user!!)
                    //Aca voy a ir al HOME.


                } else {
                    // Error con el log
                    // Log.w(TAG, "Error con login de google", task.exception)
                    Toast.makeText(this.context, "Fallo el  login con google :(", Toast.LENGTH_SHORT).show()
                }
            }
    }


    override fun onStart() {
        super.onStart()

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null && currentUser.isEmailVerified) {
            updateUI(currentUser)
        }
    }


    private fun updateUI(user: FirebaseUser) {
        viewModel.guardarCredenciales(user)
        val action =  LoginFragmentDirections.actionLoginFragmentToMainFragment()
        viewLogin.findNavController().navigate(action)
    }
/*
    private fun signIn(email: String, password: String) {
        Log.d("The email", email)
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this.requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user!!)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        this.context,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }
*/
    private fun signInMilConfirmado(email:String, password:String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this.requireActivity()){task ->
                if(task.isSuccessful) {
                    val user = auth.currentUser
                   val verificado = user?.isEmailVerified
                   if(verificado == true) {
                        Toast.makeText(this.context,"Authenticación exitosa", Toast.LENGTH_SHORT).show()
                        updateUI(user!!) // este método lo derivará al inicio.
                  } else {
                        Toast.makeText(this.context,"Error. Falta confirmar cuenta. Revisá tu mail", Toast.LENGTH_SHORT).show()
                  }

                }else {
                    Toast.makeText(this.context,"Error de email y/o password", Toast.LENGTH_SHORT).show()
                }
            }




    }


}

