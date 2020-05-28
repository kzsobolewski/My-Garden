package com.kzsobolewski.mygarden.plants.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.kzsobolewski.domain.models.TrefleDetailedPlant
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.databinding.FragmentNewPlantBinding
import com.kzsobolewski.mygarden.main.activities.MainActivity
import com.kzsobolewski.mygarden.main.fragments.INavigationFragment
import com.kzsobolewski.mygarden.plants.viewmodels.NewPlantViewModel
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_new_plant.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NewPlantFragment : Fragment(), INavigationFragment {

    val viewModel by viewModel<NewPlantViewModel> { parametersOf(mainActivity.activityScope) }

    override val mainActivity: MainActivity
        get() = activity as MainActivity

    companion object {
        private val SELECT_PHOTO_CODE = 123
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding =
            FragmentNewPlantBinding
                .inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        (activity as MainActivity).setToolbarForSideScreen(getString(R.string.create_new_plant))
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (arguments?.getParcelable<TrefleDetailedPlant>("TREFLE_KEY"))?.let {
            viewModel.apply {
                trefleDetails = it
                fillTheName()
            }
        }
        viewModel.isPlantSaved.observe(viewLifecycleOwner, Observer { saved ->
            if (saved) {
                Navigation.findNavController(view)
                    .navigate(R.id.action_newPlantFragment_to_tabsFragment)
                onBackPressed()
                hideKeyboard()
            }
        })
        circle_thumbnail_view.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, SELECT_PHOTO_CODE)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            SELECT_PHOTO_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    launchImageCrop(data?.data)
                }
            }
            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    CropImage.getActivityResult(data).uri.let {
                        thumbnail_photo.apply {
                            setImageURI(it)
                            //nie chcemy tego robic jakos przy zapisywaniu roslinki
                            viewModel.uploadImageToFirebase(it)
                            setPadding(0, 0, 0, 0)
                        }
                    }
                }
            }
        }

    }

    private fun launchImageCrop(uri: Uri?) {
        CropImage.activity(uri)
            .setGuidelines(CropImageView.Guidelines.ON)
            .setAspectRatio(1, 1)
            .start(requireContext(), this)
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

}