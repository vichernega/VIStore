package com.example.vistore.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.vistore.R
import com.example.vistore.databinding.FragmentSignUpBinding
import com.example.vistore.viewmodels.SignUpViewModel
import com.example.vistore.objects.User
import com.example.vistore.utilits.*

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel by viewModels<SignUpViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setUpClickListeners()
    }


    //observing mutableLiveData in SignUpRepository
    private fun observeViewModel() {

        viewModel.userMutableLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null){    // if user successfully signed up

                REGISTER_ACTIVITY.replaceActivity(APP_ACTIVITY)

                //saving data in USER object
                User.saveSignUpData(binding.etSignUpName.text.toString().trim { it <= ' ' },
                    binding.etSignUpSurname.text.toString().trim { it <= ' ' },
                    binding.etSignUpEmail.text.toString().trim { it <= ' ' })
            }
        })
    }



    private fun setUpClickListeners() {

        /**SIGN UP BUTTON*/
        binding.btnSignUp.setOnClickListener {

            if (isEditTextEmpty()){
                // show error message
                binding.tvSignUpErrorField.text = getString(R.string.error_fill_in_all_of_the_fields)

            } else {

                //check email
                if (!isEmailCorrect()) {
                    binding.tvSignUpErrorField.text = "Incorrect email."
                }

                //check password length
                else if (!isPasswordCorrect()) {
                    binding.tvSignUpErrorField.text = "Incorrect data. Password length \n   must be more then 8 chars"
                }

                // in case all input is correct
                else {
                    viewModel.trySignUp(binding.etSignUpEmail.text.toString().trim { it <= ' ' },
                        binding.etSignUpPassword.text.toString().trim { it <= ' ' })
                }
            }
        }

        /**LOG IN TEXT VIEW*/
        binding.tvLogIn.setOnClickListener{ replaceFragmentWithNoBackStack(LoginFragment())}
    }


    fun isEditTextEmpty(): Boolean{
        return binding.etSignUpName.text.trim { it <= ' ' }.isEmpty() ||
                binding.etSignUpSurname.text.trim { it <= ' ' }.isEmpty() ||
                binding.etSignUpEmail.text.trim { it <= ' ' }.isEmpty() ||
                binding.etSignUpPassword.text.trim { it <= ' ' }.isEmpty()
    }

    fun isPasswordCorrect(): Boolean{
        return binding.etSignUpPassword.text.toString().trim { it <= ' ' }.length >= 8
    }

    fun isEmailCorrect(): Boolean{
        return binding.etSignUpEmail.text.contains('@') && binding.etSignUpEmail.text.contains('.')
    }

}
