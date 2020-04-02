package com.example.biletum.view.profile.events.event_add.fragments

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View

import com.example.biletum.view.profile.BaseFragment
import com.example.biletum.view_models.EventsViewModel

import kotlinx.android.synthetic.main.fragment_add_event2.*
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat
import android.content.SharedPreferences
import com.example.biletum.data.network.model.models.EventCategory
import com.example.biletum.helper.USER_KEY
import com.example.biletum.view.profile.events.event_add.activity.AddEventActivity
import com.example.biletum.view.profile.events.event_add.adapters.CountryAdapter
import com.example.biletum.view.profile.events.event_add.adapters.ImageAdapter
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.*
import javax.inject.Inject
import androidx.recyclerview.widget.GridLayoutManager
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add_event.*
import android.R
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class AddEventFragmentStep2: BaseFragment(com.example.biletum.R.layout.fragment_add_event2) {

    private lateinit var viewModel: EventsViewModel
    private val IMAGE_DIRECTORY = "/demonuts"
    private val REQUEST_CAMERA = 1;
    private val GALLERY = 5
    private val CAMERARESULT = 6
    private var FLAG = 0
    private var urlMain = ""
    var currentApiVersion = Build.VERSION.SDK_INT
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    var listImages = ArrayList<String>()





    @Inject
    lateinit var imageAdapter: ImageAdapter

    companion object {
        fun newInstance(): AddEventFragmentStep2 {

            val f =
                AddEventFragmentStep2()

            val bdl = Bundle(1)

            f.setArguments(bdl)

            return f

        }
    }




        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(EventsViewModel::class.java)
        viewModel.uploadImageData.observe(this, androidx.lifecycle.Observer {
            when (it != null) {
                true -> {
                    handleUrlImage(it.url)
                }
                false -> {

                }
            }
        })

        viewModel.uploadMainImageData.observe(this, androidx.lifecycle.Observer {
            when (it != null) {
                true -> {
                    handleUrlMainImage(it.url)
                }
                false -> {

                }
            }
        })

        if(listImages.isEmpty()){
            rv_images.visibility = View.INVISIBLE
            ll_empty.visibility = View.VISIBLE

        } else{
            rv_images.visibility = View.VISIBLE
            ll_empty.visibility = View.GONE
        }

        rv_images?.apply {
            rv_images.layoutManager = GridLayoutManager(activity!!, 3)
            imageAdapter.collection = listImages
            adapter = imageAdapter
            imageAdapter.onItemClick = { item ->
                FLAG = 2
                showPictureDialog()
            }
        }

        addParametersToEvent()

        if(currentApiVersion >=  Build.VERSION_CODES.M)
        {
            if(checkPermission()) {
            }
            else {
                requestPermission()
            }
        }

        rl_mail_image.setOnClickListener {
            FLAG = 1
            showPictureDialog()
        }

        ll_empty.setOnClickListener {
            FLAG = 2
            showPictureDialog()

        }

        btn_skip.setOnClickListener {
            val view = activity!!.view_pager
            view.currentItem = view.currentItem + 1
        }

    }

    private fun handleUrlMainImage(url: String) {
        iv_image_event.visibility = View.VISIBLE
        urlMain = url
        Picasso.get().load(url).into(iv_image_event)
    }

    private fun handleUrlImage(url: String) {
        if (listImages.isEmpty()) {
            listImages.add("https://i.ibb.co/k58FDT6/Group-1.png")
            listImages.add(url)
            imageAdapter.updateList(listImages)
            rv_images.visibility = View.VISIBLE
            ll_empty.visibility = View.INVISIBLE
            pb_mul_image.visibility = View.GONE
        } else {
            listImages.add(url)
            imageAdapter.updateList(listImages)
            rv_images.visibility = View.VISIBLE
            ll_empty.visibility = View.INVISIBLE
            pb_mul_image.visibility = View.GONE
        }

    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(context!!,"android.permission.CAMERA"
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun addParametersToEvent() {
        AddEventActivity.CreateEvent.photos = listImages
        AddEventActivity.CreateEvent.image = urlMain
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(activity!!, arrayOf("android.permission.CAMERA"), REQUEST_CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            5 -> {
                if (data != null) {
                    val contentURI = data!!.data
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(context!!.contentResolver, contentURI)
                        sendImage(bitmap)
                    }
                    catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
            6 -> {
                if (data != null) {
                    val bitmap = data!!.extras!!.get("data") as Bitmap
                    sendImage(bitmap)
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun sendImage(bitmap: Bitmap?) {
        when(FLAG){
            1 ->{
                iv_image_event.visibility = View.INVISIBLE
                pb_main.visibility = View.VISIBLE
                tv_add_main.visibility = View.INVISIBLE
                viewModel.uploadMainImage(sharedPreferences.getString(USER_KEY, "").toString(), buildImageBodyPart("image", bitmap!!))
            }
            2 ->{
                pb_mul_image.visibility = View.VISIBLE
                viewModel.uploadImage(sharedPreferences.getString(USER_KEY, "").toString(), buildImageBodyPart("image", bitmap!!))
            }
        }

    }

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(context)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERARESULT)
    }

    private fun buildImageBodyPart(fileName: String, bitmap: Bitmap):  MultipartBody.Part {
        val leftImageFile = convertBitmapToFile(fileName, bitmap)
        val reqFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(),    leftImageFile)
        return MultipartBody.Part.createFormData(fileName,     leftImageFile.name, reqFile)
    }
    private fun convertBitmapToFile(fileName: String, bitmap: Bitmap): File {
        val file = File(context!!.cacheDir, fileName)
        file.createNewFile()
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50 /*ignored for PNG*/, bos)
        val bitMapData = bos.toByteArray()

        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        try {
            fos?.write(bitMapData)
            fos?.flush()
            fos?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }

}