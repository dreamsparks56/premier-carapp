package ar.edu.ort.tp3.parcialtp3ort.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import ar.edu.ort.tp3.parcialtp3ort.Models.LoginViewModel
import ar.edu.ort.tp3.parcialtp3ort.R


class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    lateinit var viewLogin:View;
    lateinit var btnLogin: Button
    lateinit var user:EditText
    lateinit var pass:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewLogin = inflater.inflate(R.layout.fragment_login, container, false)
        btnLogin = viewLogin.findViewById(R.id.button_login)

        btnLogin.setOnClickListener{
           val action =  LoginFragmentDirections.actionLoginFragmentToMainFragment()
            viewLogin.findNavController().navigate(action)
        }

        return viewLogin



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)

        viewModel.usuario.observe(viewLifecycleOwner, Observer { result ->
            user.setText(result.toString())
        })

    }


}