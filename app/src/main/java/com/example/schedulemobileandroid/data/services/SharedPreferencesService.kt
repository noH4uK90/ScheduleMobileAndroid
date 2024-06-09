package com.example.schedulemobileandroid.data.services

import android.content.SharedPreferences
import com.example.schedulemobileandroid.models.Account
import com.example.schedulemobileandroid.models.FullAccount
import com.example.schedulemobileandroid.models.GroupModel
import com.google.gson.Gson
import javax.inject.Inject

interface ISharedPreferencesService {
    fun getAccount(): FullAccount?
    fun getGroup(): GroupModel?
    fun setAccount(account: FullAccount?)
    fun setGroup(group: GroupModel?)
    fun clear()
}

class SharedPreferencesService @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ISharedPreferencesService {
    override fun getAccount(): FullAccount? {
        val jsonAccount = sharedPreferences.getString("account", null)
        return Gson().fromJson(jsonAccount, FullAccount::class.java)
    }

    override fun getGroup(): GroupModel? {
        val jsonGroup = sharedPreferences.getString("group", null)
        return Gson().fromJson(jsonGroup, GroupModel::class.java)
    }

    override fun setAccount(account: FullAccount?) {
        val jsonAccount = Gson().toJson(account)
        sharedPreferences.edit().putString("account", jsonAccount).apply()
    }

    override fun setGroup(group: GroupModel?) {
        val jsonGroup = Gson().toJson(group)
        sharedPreferences.edit().putString("group", jsonGroup).apply()
    }

    override fun clear() {
        sharedPreferences.edit().putString("account", null).apply()
        sharedPreferences.edit().putString("group", null).apply()
    }

}