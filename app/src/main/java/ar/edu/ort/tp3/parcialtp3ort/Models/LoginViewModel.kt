package ar.edu.ort.tp3.parcialtp3ort.Models

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

class LoginViewModel:ViewModel() {
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

    fun updatePhotoUri(user: FirebaseUser?, uri: Uri) {
        val imageChange = UserProfileChangeRequest.Builder()
            .setPhotoUri(uri)
            .build()

        user!!.updateProfile(imageChange)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d("La imagen", user.photoUrl.toString())
                    photoUrl = user.photoUrl
                } else {
                    Log.e("Error con la imagen", "Algo salió mal")
                }
            }
    }
}