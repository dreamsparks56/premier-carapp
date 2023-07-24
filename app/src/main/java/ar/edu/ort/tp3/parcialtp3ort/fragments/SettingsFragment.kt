package ar.edu.ort.tp3.parcialtp3ort.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.os.LocaleListCompat
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import ar.edu.ort.tp3.parcialtp3ort.R


class SettingsFragment : PreferenceFragmentCompat() {
    lateinit var v: View

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        val localePreference: ListPreference?  = findPreference("language")
        val themePreference: ListPreference? = findPreference("dark_theme")
        getLanguage(localePreference!!)
        localePreference.setOnPreferenceChangeListener { _, newValue ->
            setLanguage(newValue.toString())
            true
        }
        themePreference?.setOnPreferenceChangeListener { _, newValue ->
            Log.d("new value", newValue.toString())
            when(newValue.toString()) {
                "l" -> AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                "d" -> AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                "sd" -> AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
            }
            true
        }
    }



    private fun setLanguage(language: String) {
        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(language)

        // Call this on the main thread as it may require Activity.restart()
        AppCompatDelegate.setApplicationLocales(appLocale)
    }

    private fun getLanguage(preference: ListPreference) {
        val locale = AppCompatDelegate.getApplicationLocales()[0]
        preference.summaryProvider =
            Preference.SummaryProvider<ListPreference> { _ ->
                when (locale?.language) {
                    preference.entryValues[0] -> preference.entries[0]
                    preference.entries[1] -> preference.entries[1]
                    else -> {preference.entries[0]}
                }
            }
        }


    /*private fun setupChangeEmail() {
        val userViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        val emailText: TextView = emailButton.findViewById(R.id.email_button_placeholder)
        setupEmailText(userViewModel.email.value!!, emailText)
        val newEmail = userViewModel.email.value!!.replaceFirstChar { 'p' }
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

    }*/

    /*private fun setupEmailText(email: String, textView: TextView) {
        var textBefore: String = email.substringBefore('@')
            textBefore = textBefore.replaceRange(0..textBefore.length/2, "********")
        val textAfter: String = textBefore + "@" +  email.substringAfter('@')
        textView.text = textAfter
    }*/


}