package com.example.whatowatch.utils

import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import ar.com.wolox.wolmo.core.util.SharedPreferencesManager
import com.example.whatowatch.model.UserModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import javax.inject.Inject

@ApplicationScope
class SharedPreferencesUtils @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) {

    private val gson: Gson = Gson()

    var user: UserModel?
        get() {
            val fieldJson = sharedPreferencesManager[USER, null]
            val listType = object : TypeToken<UserModel>() {}.type
            return gson.fromJson(fieldJson,listType)
        }
        set(user) {
            sharedPreferencesManager.store(USER, gson.toJson(user))
        }

    var name: String?
        get() {
            val fieldJson = sharedPreferencesManager[NAME, null]
            val stringType = object : TypeToken<String>() {}.type
            return gson.fromJson(fieldJson,stringType)
        }
        set(name) {
            sharedPreferencesManager.store(NAME, gson.toJson(name))
        }

    var users: List<UserModel>?
        get() {
            val fieldJson = sharedPreferencesManager[USERS, null]
            val cityModelType = object : TypeToken<List<UserModel>>() {}.type
            return gson.fromJson(fieldJson,cityModelType)
        }
        set(users) {
            sharedPreferencesManager.store(USERS, gson.toJson(users))
        }

    var friends: List<UserModel>?
        get() {
            val fieldJson = sharedPreferencesManager[FRIENDS, null]
            val cityModelType = object : TypeToken<List<UserModel>>() {}.type
            return gson.fromJson(fieldJson,cityModelType)
        }
        set(friends) {
            sharedPreferencesManager.store(FRIENDS, gson.toJson(friends))
        }
//
    var isRegistered: Boolean?
        get() {
            val fieldJson = sharedPreferencesManager[KEY_CITY_3, null]
            val cityModelType = object : TypeToken<Boolean>() {}.type
            return gson.fromJson(fieldJson,cityModelType)
        }
        set(isRegistered) {
            sharedPreferencesManager.store(KEY_CITY_3, gson.toJson(isRegistered))
        }
//
//    var cityModel4: CityModel?
//        get() {
//            val fieldJson = sharedPreferencesManager[KEY_CITY_4, null]
//            val cityModelType = object : TypeToken<CityModel>() {}.type
//            return gson.fromJson(fieldJson,cityModelType)
//        }
//        set(cityModel4) {
//            sharedPreferencesManager.store(KEY_CITY_4, gson.toJson(cityModel4))
//        }
//
//    var cityModel5: CityModel?
//        get() {
//            val fieldJson = sharedPreferencesManager[KEY_CITY_5, null]
//            val cityModelType = object : TypeToken<CityModel>() {}.type
//            return gson.fromJson(fieldJson,cityModelType)
//        }
//        set(cityModel5) {
//            sharedPreferencesManager.store(KEY_CITY_5, gson.toJson(cityModel1))
//        }

    companion object {
        private const val NAME = "name"
        private const val USER = "user"
        private const val USERS = "users"
        private const val FRIENDS = "friends"
        private const val KEY_CITY_2 = "key_city_2"
        private const val KEY_CITY_3 = "key_city_3"
        private const val KEY_CITY_4 = "key_city_4"
        private const val KEY_CITY_5 = "key_city_5"
    }
}
