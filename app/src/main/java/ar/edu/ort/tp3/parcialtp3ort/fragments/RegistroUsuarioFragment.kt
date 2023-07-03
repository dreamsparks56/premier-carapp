package ar.edu.ort.tp3.parcialtp3ort.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import ar.edu.ort.tp3.parcialtp3ort.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegistroUsuarioFragment : Fragment() {
    lateinit var v:View
    lateinit var mail: TextInputLayout
    lateinit var username: TextInputLayout
    lateinit var pass1: TextInputLayout
    lateinit var pass2: TextInputLayout
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
            if(mail.editText.toString().length> 2 && pass1.editText.toString().length > 2  && pass2.editText.toString().length > 2) {
                if(pass1.editText.toString().equals(pass2.editText.toString())) {
                    //createAccount(mail.text.toString(), pass1.text.toString())
                    createAccountConMailDeCofirmacion(mail.editText.toString(), pass1.editText.toString())
                }else {
                    pass1.error="Las claves no son iguales"
                    pass2.error="Las claves no son iguales"
                    pass1.requestFocus()
                    pass2.requestFocus()
                }
            }else {
                Toast.makeText(this.context, "Error, los campos no cumplen con criterios de longitud mínima", Toast.LENGTH_SHORT).show()
                println(mail.editText.toString())
                println(pass1.editText.toString())
                println(pass2.editText.toString())
            }

        }



        return v
    }

    private fun createAccountConMailDeCofirmacion(mail:String, pass:String) {
        fireBaseAuth.createUserWithEmailAndPassword(mail, pass)
            .addOnCompleteListener(this.requireActivity()){task ->
                if(task.isSuccessful) {
                    val user = fireBaseAuth.currentUser!!
                    updateDisplayName(username.editText.toString())
                    sentEmailVerification(user)
                    Toast.makeText(this.context, "Cuenta creada correctamente. Se requiere verificación", Toast.LENGTH_SHORT).show()

                    println("x ahora no hay error")
                    val action = RegistroUsuarioFragmentDirections.actionRegistroUsuarioFragmentToLoginFragment()
                    v.findNavController().navigate(action)
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