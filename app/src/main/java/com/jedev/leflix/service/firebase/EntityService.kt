package com.jedev.leflix.service.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import com.jedev.leflix.model.IEntity


open class EntityService<T: IEntity>(collection: String) {

    private val firestore = FirebaseFirestore.getInstance()
    val collectionReference = firestore.collection(collection)

    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build()
    }

    protected fun getAll(): Task<QuerySnapshot> {
        return this.collectionReference.get()
    }

    protected fun getById(id: String): Task<DocumentSnapshot> {
        return this.collectionReference.document(id).get()
    }

    protected fun add(data: T): Task<DocumentSnapshot> {
        val newDoc = this.collectionReference.document()
        newDoc.set(data)
        return newDoc.get()
    }

    protected fun update(id: String, data: T) {
        this.collectionReference.document(id).set(data)
    }

    protected fun delete(id: String) {
        this.collectionReference.document(id).delete()
    }
}