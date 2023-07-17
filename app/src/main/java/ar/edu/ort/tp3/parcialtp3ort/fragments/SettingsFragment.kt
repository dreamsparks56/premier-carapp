package ar.edu.ort.tp3.parcialtp3ort.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import ar.edu.ort.tp3.parcialtp3ort.R



class SettingsFragment : Fragment() {
    lateinit var v: View
    lateinit var languageEn: Button
    lateinit var languageEs: Button
    lateinit var languageGroup: RadioGroup


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
        getLanguage()

        languageEn.setOnClickListener {
            setLanguage("en")
        }
        languageEs.setOnClickListener {
            setLanguage("es")
        }
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


}