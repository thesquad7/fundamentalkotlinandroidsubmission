package filemon.submission.bangkit.a1stbangkitsubmmison.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import filemon.submission.bangkit.a1stbangkitsubmmison.data.model.UserResponse
import filemon.submission.bangkit.a1stbangkitsubmmison.databinding.ItemUserBinding


class FollowAdapter(private val UserList: List<UserResponse>) : RecyclerView.Adapter<FollowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = UserList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gitUser = UserList[position]
        Glide.with(holder.itemView.context)
            .load(gitUser.avatarUrl)
            .centerCrop()
            .circleCrop()
            .into(holder.binding.ciAvatar)
        holder.apply {
            binding.apply {
                tvUsername.text = gitUser.login
            }
        }
    }

    class ViewHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root)
}