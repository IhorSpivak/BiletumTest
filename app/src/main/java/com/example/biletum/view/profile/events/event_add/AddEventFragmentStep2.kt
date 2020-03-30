package com.example.biletum.view.profile.events.event_add

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.view.View

import com.example.biletum.view.profile.BaseFragment
import com.example.biletum.view_models.EventsViewModel

import kotlinx.android.synthetic.main.fragment_add_event2.*
import java.util.*
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat
import android.content.SharedPreferences
import android.util.Base64
import com.example.biletum.helper.USER_KEY
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.*
import javax.inject.Inject


class AddEventFragmentStep2: BaseFragment(com.example.biletum.R.layout.fragment_add_event2) {

    private lateinit var viewModel: EventsViewModel
    private val IMAGE_DIRECTORY = "/demonuts"
    private val REQUEST_CAMERA = 1;
    private val GALLERY = 5
    private val CAMERARESULT = 6
    var currentApiVersion = Build.VERSION.SDK_INT
    @Inject
    lateinit var sharedPreferences: SharedPreferences


    companion object {
        fun newInstance(): AddEventFragmentStep2 {

            val f = AddEventFragmentStep2()

            val bdl = Bundle(1)

            f.setArguments(bdl)

            return f

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(EventsViewModel::class.java)


        addParametersToEvent()

        if(currentApiVersion >=  Build.VERSION_CODES.M)
        {
            if(checkPermission()) {

            }
            else
            {
                requestPermission()
            }
        }

        rl_mail_image.setOnClickListener {
            showPictureDialog()
        }

    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(context!!,"android.permission.CAMERA"
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun addParametersToEvent() {
        AddEventActivity.CreateEvent.descr = "test"

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
                        iv!!.setImageBitmap(bitmap)
                        viewModel.uploadImage(sharedPreferences.getString(USER_KEY, "").toString(), buildImageBodyPart("image", bitmap))
                    }
                    catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
            6 -> {
                if (data != null) {
                    val bitmap = data!!.extras!!.get("data") as Bitmap
                    iv!!.setImageBitmap(bitmap)
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
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
        //create a file to write bitmap data
        val file = File(context!!.cacheDir, fileName)
        file.createNewFile()

        //Convert bitmap to byte array
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50 /*ignored for PNG*/, bos)
        val bitMapData = bos.toByteArray()

        //write the bytes in file
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