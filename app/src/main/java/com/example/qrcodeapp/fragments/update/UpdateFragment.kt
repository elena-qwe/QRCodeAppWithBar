package com.example.qrcodeapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.qrcodeapp.R
import com.example.qrcodeapp.model.User
import com.example.qrcodeapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.update_firstname_et.editText?.setText(args.currentUser.firstName)
        view.update_lastname_et.editText?.setText(args.currentUser.lastName)
        view.update_department_et.editText?.setText(args.currentUser.department)
        view.update_login_et.editText?.setText(args.currentUser.email)
        view.update_password_et.editText?.setText(args.currentUser.password)

        view.update_add_btn.setOnClickListener {
            updateItem()
        }

        //add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {

        val firstname = update_firstname_et.editText?.text.toString()
        val lastname = update_lastname_et.editText?.text.toString()
        val department = update_department_et.editText?.text.toString()
        val email = update_login_et.editText?.text.toString()
        val password = update_password_et.editText?.text.toString()

        if (inputCheck(firstname, lastname, department, email, password)) {

            //create user object
            val updatedUser =
                User(args.currentUser.id, firstname, lastname, department, email, password)
            //update current user
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(
                requireContext(), "???????????? ?????????????? ??????????????????", Toast.LENGTH_LONG
            ).show()
            //navigation back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(
                requireContext(), "?????????????????? ???????????????? ????????????, ???????????????????? ?????? ??????", Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun inputCheck(
        firstname: String,
        lastname: String,
        department: String,
        email: String,
        password: String
    ): Boolean {
        return !(TextUtils.isEmpty(firstname) || TextUtils.isEmpty(lastname) || TextUtils.isEmpty(
            department
        ) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("????") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(
                requireContext(),
                "?????????????? ??????????????: ${args.currentUser.firstName}",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        builder.setNegativeButton("??????") { _, _ -> }
        builder.setTitle("?????????????? ${args.currentUser.firstName}?")
        builder.setMessage("???? ??????????????, ?????? ???????????? ?????????????? ${args.currentUser.firstName}?")
        builder.create().show()
    }

}