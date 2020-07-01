package com.example.whatowatch.ui.main.friends


import com.example.whatowatch.model.UserModel
import com.example.whatowatch.shareable.IWhatToWhatchView


interface IFriendsView:IWhatToWhatchView {
    fun setSelectedUser(userModel: UserModel?)
    fun setFriendSuggestions(friendSuggestions: List<UserModel>?)
    fun renderFriends(friends: List<UserModel>?)

}
