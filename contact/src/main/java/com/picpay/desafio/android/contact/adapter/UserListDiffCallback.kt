package com.picpay.desafio.android.contact.adapter

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.contact.User

class UserListDiffCallback(
    private val oldList: List<com.picpay.desafio.android.contact.User>,
    private val newList: List<com.picpay.desafio.android.contact.User>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].username.equals(newList[newItemPosition].username)
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}