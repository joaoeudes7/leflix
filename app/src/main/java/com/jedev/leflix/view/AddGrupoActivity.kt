package com.jedev.leflix.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.jedev.leflix.R
import com.jedev.leflix.model.Group
import com.jedev.leflix.service.firebase.GroupService
import com.jedev.leflix.utils.ImageUtils
import com.jedev.leflix.utils.PRIVACY
import kotlinx.android.synthetic.main.activity_add_grupo.*
import java.util.*

class AddGrupoActivity : AppCompatActivity() {
    private lateinit var group: Group
    private lateinit var imageUri: Uri
    private var storage: FirebaseStorage? = FirebaseStorage.getInstance()
    private var reference: StorageReference? = storage!!.reference

    companion object {
        private const val PICK_IMAGE_REQUEST = 7
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_grupo)

        icongrupo.setOnClickListener {
            ImageUtils.selectByGallery(this, PICK_IMAGE_REQUEST)
        }

        cadastrargrupo.setOnClickListener {
            val name = nomegrupo.text.toString().trim()
            val description = descricaogrupo.text.toString().trim()
            val type = if (switch1.isChecked) PRIVACY.PUBLIC else PRIVACY.PRIVATE

            if (arrayOf(name, description).contains("")) {
                Toast.makeText(this, "Preencha os Campos!", Toast.LENGTH_SHORT).show()
            } else {
                uploadImage().addOnCompleteListener {
                    if (it.isSuccessful) {
                        val urlPhoto = it.result.toString()

                        group = Group(name, description, urlPhoto, type)
                        GroupService().add(group)
                        finish()
                    }
                }

                Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun uploadImage(): Task<Uri> {
        val imagemref = reference!!.child("images/" + UUID.randomUUID().toString())
        return imagemref.putFile(imageUri).continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> {
            return@Continuation imagemref.downloadUrl
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {

            imageUri = data.data!!

                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                ImageUtils.compressImage(bitmap)

                icongrupo.setImageBitmap(bitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}


