package ar.edu.ort.tp3.parcialtp3ort.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import ar.edu.ort.tp3.parcialtp3ort.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RecuperoClaveFragment : Fragment() {
    lateinit var v: View
    lateinit var btnRecupero: Button
    lateinit var mail: TextInputLayout

    //---Variables de fireBase
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
        v = inflater.inflate(R.layout.fragment_recupero_clave, container, false)
        mail = v.findViewById(R.id.mail_recuperoClave)
        btnRecupero = v.findViewById(R.id.button_recuperoClave)

        btnRecupero.setOnClickListener {
            if (mail.editText?.text.toString().isNotEmpty()) {
                enviarPassNueva(mail.editText?.text.toString())
            }
        }

        return v
    }

    private fun enviarPassNueva(mail: String) {
        fireBaseAuth.sendPasswordResetEmail(mail)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this.context, "Mail enviado", Toast.LENGTH_SHORT).show()
                    val action =
                        RecuperoClaveFragmentDirections.actionRecuperoClaveFragmentToLoginFragment()
                    v.findNavController().navigate(action)
                } else {
                    Toast.makeText(
                        this.context,
                        "Error. No se pudo procesar el pedido",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}