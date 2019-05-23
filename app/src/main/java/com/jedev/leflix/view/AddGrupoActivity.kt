package com.jedev.leflix.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.jedev.leflix.R
import com.jedev.leflix.model.Group
import com.jedev.leflix.utils.ImageUtils
import kotlinx.android.synthetic.main.activity_add_grupo.*
import java.util.*

class AddGrupoActivity : AppCompatActivity() {
    private lateinit var grupos: Group
    private lateinit var imageUri: Uri
    var storage: FirebaseStorage? = FirebaseStorage.getInstance()
    var reference: StorageReference? = storage!!.reference

    companion object {
        private const val PICK_IMAGE_REQUEST = 7
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_grupo)
        icongrupo.setOnClickListener {
            ImageUtils.selectByGallery(this, PICK_IMAGE_REQUEST)
        }





        cadastrargrupo.setOnClickListener(View.OnClickListener {


            val name = nomegrupo.text.toString().trim()
            val descricao = descricaogrupo.text.toString().trim()
            if (switch1.isChecked) {
                var privado = switch1.text.toString()
            } else {
                var publico = switch1.text.toString()
            }
            if (arrayOf(name, descricao).contains("")) {
                alertError("Preencha os Campos!")
            } else {
                grupos = Group(name, descricao)
                uploadImage().addOnCompleteListener {
                    if (it.isSuccessful) {
                        val urlFile = it.result
                        this.grupos.addAttach(urlFile.toString())
                        grupos.save()
                    }
                }


                Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show()

            }

        })
    }


    private fun alertError(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()

    }


    private fun uploadImage(): Task<Uri> {
        val imagemref = reference!!.child("images/" + UUID.randomUUID().toString())
        return imagemref.putFile(imageUri).continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> {
            return@Continuation imagemref.downloadUrl
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == AppCompatActivity.RESULT_OK) {
            if (data != null) {
                imageUri = data.data!!

                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                ImageUtils.compressImage(bitmap)

                icongrupo.setImageBitmap(bitmap)


            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}


