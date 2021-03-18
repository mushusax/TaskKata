package com.example.taskkata.ui.settings

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.taskkata.R
import com.example.taskkata.database.TaskDao
import com.example.taskkata.database.TaskDatabase
import com.example.taskkata.ui.MainActivity

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        //Get a dao reference
        val application: Application = requireNotNull(this.activity).application
        val dao: TaskDao = TaskDatabase.getInstance(application).taskDatabaseDao

        //get viewModel
        val viewModelFactory = SettingsViewModelFactory(dao, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SettingsViewModel::class.java)

        findPreference<Preference?>("btn_reset")?.setOnPreferenceClickListener {
            viewModel.onClear()
            Toast.makeText(requireContext(), "Content Deleted Permanently", Toast.LENGTH_SHORT).show()
            true
        }

        findPreference<Preference?>("btn_delete_account")?.setOnPreferenceClickListener {
            viewModel.onDeleteAccount()
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            true
        }
    }
}