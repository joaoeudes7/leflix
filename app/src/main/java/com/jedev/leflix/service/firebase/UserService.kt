package com.jedev.leflix.service.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.jedev.leflix.model.User

class UserService: EntityService<User>("user") {
    fun save(user: User): Task<DocumentSnapshot> {
        return this.add(user)
    }


    fun searchByName(name: String): Task<QuerySnapshot> {
        return this.collectionReference.whereEqualTo("name", name).get()
    }
}