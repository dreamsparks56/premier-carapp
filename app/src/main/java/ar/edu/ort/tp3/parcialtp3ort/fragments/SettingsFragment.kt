package ar.edu.ort.tp3.parcialtp3ort.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModelProvider
import ar.edu.ort.tp3.parcialtp3ort.Models.LoginViewModel
import ar.edu.ort.tp3.parcialtp3ort.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class SettingsFragment : Fragment() {
    lateinit var v: View
    lateinit var languageEn: Button
    lateinit var languageEs: Button
    lateinit var languageGroup: RadioGroup
    lateinit var emailButton: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_configuracion, container, false)
        languageEn = v.findViewById(R.id.languageEnglish)
        languageEs = v.findViewById(R.id.languageSpanish)
        languageGroup = v.findViewById(R.id.languageGroup)
        emailButton = v.findViewById(R.id.email_button)
        getLanguage()

        languageEn.setOnClickListener {
            setLanguage("en")
        }
        languageEs.setOnClickListener {
            setLanguage("es")
        }
        setupChangeEmail()
        return v
    }



    private fun setLanguage(language: String) {
        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(language)

        // Call this on the main thread as it may require Activity.restart()
        AppCompatDelegate.setApplicationLocales(appLocale)
    }

    private fun getLanguage() {
        // Call this to get the selected locale and display it in your App
        val selectedLocale = AppCompatDelegate.getApplicationLocales()[0]
        when (selectedLocale?.language) {
            "en" -> languageGroup.check(languageEn.id)
            "es" -> languageGroup.check(languageEs.id)
        }
    }

    private fun setupChangeEmail() {
        val userViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        val emailText: TextView = emailButton.findViewById(R.id.email_button_placeholder)
        setupEmailText(userViewModel.email.value!!, emailText)
        val newEmail = userViewModel.email.value!!.replaceFirstChar { 'a' }
        emailButton.setOnClickListener {
            val emailDialog = MaterialAlertDialogBuilder(requireContext())
            emailDialog.setTitle("Does nothing")
                .setMessage("New Email: ${newEmail} ?")
                .setPositiveButton("Aceptar") { dialog, _ ->
                    userViewModel.updateEmail(newEmail)
                }.setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }
            emailDialog.create()
            emailDialog.setCancelable(true)
            emailDialog.show()
        }

    }

    private fun setupEmailText(email: String, textView: TextView) {
        var textBefore: String = email.substringBefore('@')
            textBefore = textBefore.replaceRange(0..textBefore.length/2, "********")
        val textAfter: String = textBefore + "@" +  email.substringAfter('@')
        textView.text = textAfter
    }


}