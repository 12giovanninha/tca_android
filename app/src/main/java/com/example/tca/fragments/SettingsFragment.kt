package com.example.tca.fragments

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tca.*
import com.example.tca.adapters.CategoryAdapter
import com.example.tca.dao.CategoryDAO
import com.example.tca.models.game.Game
import kotlinx.android.synthetic.main.fragment_settings.view.*

class SettingsFragment : Fragment() {

    private var daoCategory: CategoryDAO = CategoryDAO()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        view.set.visibility = View.GONE
        view.choose.visibility = View.GONE
        view.btSave.visibility = View.GONE
        view.userProfile.text = UserRoomController.getUser(requireActivity()).name
        view.email_profile.text = UserRoomController.getUser(requireActivity()).email
        view.pg.visibility = View.VISIBLE
        view.btSave.setOnClickListener { saveSettings() }
        view.btLogout.setOnClickListener { logout() }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.pg.visibility = View.VISIBLE
        daoCategory.findAll { categoriesAPI ->
            categoryAdapter = CategoryAdapter(view)
            view.rvCategory.adapter = categoryAdapter
            view.rvCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun saveSettings() {
        val game = Game(
            difficulty = "",
            outCategory = categoryAdapter.getSelectedItem()
        )
        if (game.outCategory?.name?.isNotEmpty()!!){
            val toast: Toast = Toast.makeText(activity, "Selected Category", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 10)
            toast.show()
            GameRoomController.setGame(requireActivity(), game)
        }else{

        }
    }

    private fun logout(){
        UserRoomController.exitUser(requireActivity(), 1)
        GameRoomController.rollback(1)

        val intent = Intent(requireActivity(), MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent)
    }

}
