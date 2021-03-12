package com.example.taskkata.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskkata.R
import com.example.taskkata.ui.SplashFragmentDirections
import com.google.firebase.auth.FirebaseAuth

class SplashFragment : Fragment() {

    companion object {
        val TAG = "SplashFragment"
    }

    private val splashDisplayLength: Long = 1000
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        auth = FirebaseAuth.getInstance()
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            //If user is authenticated, navigate to TodoActivity
            if (auth.currentUser != null) {
                val intent: Intent = Intent(requireContext(), TodoActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }//Otherwise navigate to the login fragment
            else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            }
        }, splashDisplayLength)
    }
}