package com.ariefrahmanfajri.apicallingapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ariefrahmanfajri.apicallingapp.databinding.UserItemBinding
import com.ariefrahmanfajri.apicallingapp.domain.User

class UserAdapter(private val list: List<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                user.apply {
                    tvName.text = name
                    tvGender.text = gender
                    tvEmail.text = email
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val view = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}