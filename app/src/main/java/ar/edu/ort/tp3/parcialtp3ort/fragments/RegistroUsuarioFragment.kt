package ar.edu.ort.tp3.parcialtp3ort.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import ar.edu.ort.tp3.parcialtp3ort.R
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


class RegistroUsuarioFragment : Fragment() {
    lateinit var v:View
    lateinit var mail: EditText
    lateinit var username: EditText
    lateinit var pass1: EditText
    lateinit var pass2: EditText
    lateinit var btnRegistro: MaterialButton
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
        v = inflater.inflate(R.layout.fragment_registro_usuario, container, false)
        mail = v.findViewById(R.id.mail_registro1)
        username = v.findViewById(R.id.usuario_registro)
        pass1 = v.findViewById(R.id.pass_registro1)
        pass2 = v.findViewById(R.id.pass_registro2)
        btnRegistro =  v.findViewById(R.id.button_registro_id)

        btnRegistro.setOnClickListener {
            if(mail.text.toString().length> 2 && pass1.text.toString().length > 2  && pass2.text.toString().length > 2) {
                if(pass1.text.toString().equals(pass2.text.toString())) {
                    //createAccount(mail.text.toString(), pass1.text.toString())
                    createAccountConMailDeCofirmacion(mail.text.toString(), pass1.text.toString())
                }else {
                    Toast.makeText(this.context, "Error, las claves no son iguales", Toast.LENGTH_SHORT).show()
                    pass1.requestFocus()
                    pass2.requestFocus()
                }
            }else {
                Toast.makeText(this.context, "Error, los campos no cumplen con criterios de longitud mínima", Toast.LENGTH_SHORT).show()
                println(mail.text.toString())
                println(pass1.text.toString())
                println(pass2.text.toString())
            }

        }



        return v
    }

    private fun createAccountConMailDeCofirmacion(mail:String, pass:String) {
        fireBaseAuth.createUserWithEmailAndPassword(mail, pass)
            .addOnCompleteListener(this.requireActivity()){task ->
                if(task.isSuccessful) {
                    val user = fireBaseAuth.currentUser!!
                    val action = RegistroUsuarioFragmentDirections.actionRegistroUsuarioFragmentToLoginFragment()
                    this.lifecycleScope.launch{
                        updateDisplayName(username.text.toString())
                        sentEmailVerification(user)
                        Toast.makeText(requireContext(), "Cuenta creada correctamente. Se requiere verificación", Toast.LENGTH_SHORT).show()
                        println("x ahora no hay error")
                        view
                        v.findNavController().navigate(action)
                    }

                }else {
                    Toast.makeText(this.context,"Algo salió mal" + task.exception,Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun sentEmailVerification(user: FirebaseUser){
        user.sendEmailVerification()
    }

    private fun updateDisplayName(name: String){
        val user = fireBaseAuth.currentUser!!
        Log.d("El nombre", name)
        val nameChange = UserProfileChangeRequest.Builder()
            .setDisplayName(name)
            .build()

        user.updateProfile(nameChange)
            .addOnCompleteListener(this.requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d("El nombre actualizado", user.displayName!!)
                } else {
                    Log.d("nombre error","Algo salió mal en el cambio de usuario")
                }
            }
    }



}