package com.vasyancoder.cameraandroid

import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vasyancoder.cameraandroid.databinding.ActivityGalleryBinding

class GalleryActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityGalleryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.galleryList.layoutManager = LinearLayoutManager(this)
        viewBinding.galleryList.adapter = GalleryAdapter(getImages(), this)
    }

    private fun getImages(): List<String> {
        val images = mutableListOf<String>()
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection =
            arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

        val orderBy = MediaStore.Video.Media.DATE_TAKEN
        val cursor = this.contentResolver.query(uri, projection, null, null, "$orderBy DESC")

        val columnIndexData = cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)

//        val columnIndexFolderName =
//            cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

        var absolutePathOfImage = ""
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(columnIndexData)

            images.add(absolutePathOfImage)
        }

        return images
    }
}