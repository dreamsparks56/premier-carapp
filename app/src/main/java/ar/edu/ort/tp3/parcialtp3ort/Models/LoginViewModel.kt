package ar.edu.ort.tp3.parcialtp3ort.Models

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel:ViewModel() {
    val auth = Firebase.auth
    val usuario = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    var photoUrl: Uri? = Uri.EMPTY
    //val clave = MutableLiveData<String>()


    /*fun guardarCredenciales(nombre: String, password: String) {
        usuario.value = nombre
        clave.value = password
    }*/
    fun guardarCredenciales(user: FirebaseUser?) {
        user?.let {
            // Name, email address, and profile photo Url
            photoUrl = it.photoUrl
            usuario.value = it.displayName
            email.value = it.email
            // Check if user's email is verified
            //val emailVerified = it.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            //val uid = it.uid
        }
    }

    fun currentUser(): FirebaseUser? {
        return auth.currentUser
    }
}