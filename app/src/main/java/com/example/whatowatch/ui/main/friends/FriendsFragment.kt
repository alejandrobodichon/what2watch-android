package com.example.whatowatch.ui.main.friends

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.whatowatch.R
import com.example.whatowatch.model.UserModel
import com.example.whatowatch.shareable.FriendsAdapter
import com.example.whatowatch.shareable.WhatToWhatchFragment
import kotlinx.android.synthetic.main.friends_fragment.*

import javax.inject.Inject


class FriendsFragment @Inject constructor() :
    WhatToWhatchFragment<FriendsPresenter>(), IFriendsView {

    override fun layout(): Int = R.layout.friends_fragment

    override fun init() {
        setToolbarData(
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_hamburger),
            "Friends"
        )
        setImageBackground()
        presenter.getFriendSuggestions()
        presenter.getFriends()
        etSearchFriends.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus)
                presenter.setFilter( requireFragmentManager())
        }
        etSearchFriends.setOnClickListener {
            presenter.setFilter( requireFragmentManager())
        }

        btAddFriend.setOnClickListener {
            if (!etSearchFriends.text.isNullOrEmpty()){
                presenter.postFriend()
            }
        }
    }

    override fun setSelectedUser(userModel: UserModel?) {
        etSearchFriends.setText(userModel?.username)
    }

    override fun setFriendSuggestions(friendSuggestions: List<UserModel>?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun renderFriends(friends: List<UserModel>?) {
        etSearchFriends.text.clear()
        rvFriends.layoutManager = GridLayoutManager(requireContext(),2)
        rvFriends.adapter = FriendsAdapter(requireContext()).also {
            it.submitList(friends)
        }
    }

}