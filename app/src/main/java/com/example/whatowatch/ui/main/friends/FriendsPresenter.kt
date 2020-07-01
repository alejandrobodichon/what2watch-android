package com.example.whatowatch.ui.main.friends

import androidx.fragment.app.FragmentManager
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import com.example.whatowatch.model.IdModel
import com.example.whatowatch.model.UserModel
import com.example.whatowatch.network.callback.AuthCallback
import com.example.whatowatch.network.services.GetFriendSuggestions
import com.example.whatowatch.network.services.GetFriends
import com.example.whatowatch.network.services.PostFriend
import com.example.whatowatch.shareable.Callback
import com.example.whatowatch.shareable.ComboModelSearcherDialogFragment
import com.example.whatowatch.utils.SharedPreferencesUtils
import okhttp3.ResponseBody

import javax.inject.Inject

class FriendsPresenter @Inject constructor(private val retrofitServices: RetrofitServices) : BasePresenter<IFriendsView>() {
    @Inject
    internal lateinit var sharedPreferencesUtils: SharedPreferencesUtils
    private  var selectedFriend :UserModel? = null

    fun setFilter( fragmentManager: FragmentManager) {
        view?.showProgressBar()
        val combo =
            ComboModelSearcherDialogFragment().newInstance(
                sharedPreferencesUtils.users!!,
                "Friends",
                object :
                    Callback {
                    override fun onResponseOk(vararg objs: Any) {
                        selectedFriend = objs[0] as UserModel?
                        view?.setSelectedUser(selectedFriend)
                        view?.hideProgressBar()
                    }

                    override fun onResponseError(vararg objs: Any) {
                        view?.hideProgressBar()
                    }
                })
        combo.show(fragmentManager, "dialog")
    }

    fun postFriend(){
        view?.showProgressBar( )
        selectedFriend?.id?.let {
            retrofitServices.getService(PostFriend::class.java)
                .postFriend(IdModel( selectedFriend?.id!!), sharedPreferencesUtils.user?.id.toString()).enqueue(
                object : AuthCallback<List<UserModel>>(this) {
                    override fun onResponseSuccessful(friends: List<UserModel>?) {
                        sharedPreferencesUtils.friends = friends
                        view?.showError("Friend added")
                        view?.renderFriends(friends)
                        getFriendSuggestions()
                        view?.hideProgressBar()
                    }

                    override fun onResponseFailed(responseBody: ResponseBody?, code: Int) {
                        view?.showError("Error adding a friend.")
                        view?.hideProgressBar()
                    }

                    override fun onCallFailure(t: Throwable) {
                        view?.showError("Connection error, please try again.")
                        view?.hideProgressBar()
                    }

                })
        }
    }

    fun getFriendSuggestions(){
        view?.showProgressBar( )
        retrofitServices.getService(GetFriendSuggestions::class.java).getFriendSuggestions(sharedPreferencesUtils.user?.id.toString()).enqueue(
            object : AuthCallback<List<UserModel>>(this) {
                override fun onResponseSuccessful(friendSuggestions: List<UserModel>?) {
                    sharedPreferencesUtils.users = friendSuggestions!!
                    //view?.setFriendSuggestions(friendSuggestions)
                    view?.hideProgressBar()
                }

                override fun onResponseFailed(responseBody: ResponseBody?, code: Int) {
                    view?.showError("Error retrieving users.")
                    view?.hideProgressBar()
                }

                override fun onCallFailure(t: Throwable) {
                    view?.showError("Connection error, please try again.")
                    view?.hideProgressBar()
                }

            })

    }

    fun getFriends(){
        view?.showProgressBar( )
        retrofitServices.getService(GetFriends::class.java).getFriends(sharedPreferencesUtils.user?.id.toString()).enqueue(
            object : AuthCallback<List<UserModel>>(this) {
                override fun onResponseSuccessful(friends: List<UserModel>?) {
                    sharedPreferencesUtils.friends = friends
                    view?.renderFriends(friends)
                    view?.hideProgressBar()
                }

                override fun onResponseFailed(responseBody: ResponseBody?, code: Int) {
                    view?.showError("Error retrieving users.")
                    view?.hideProgressBar()
                }

                override fun onCallFailure(t: Throwable) {
                    view?.showError("Connection error, please try again.")
                    view?.hideProgressBar()
                }

            })

    }

}
