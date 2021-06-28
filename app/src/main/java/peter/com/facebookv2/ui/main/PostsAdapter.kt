package peter.com.facebookv2.ui.main


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import peter.com.facebookv2.R
import peter.com.facebookv2.databinding.PostItemBinding
import peter.com.facebookv2.pojo.PostModel

class PostsAdapter : ListAdapter<PostModel, PostViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            PostViewHolder {
        return PostViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        Log.i("ITEM", post.body + " >> " + post.title + " >> " + post.userId.toString())
        holder.bind(post)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PostModel>() {
        override fun areItemsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

}

class PostViewHolder(private var binding: PostItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(post: PostModel) {
        binding.post = post
        binding.bodyTV.text = post.body
        binding.titleTV.text = post.title
        binding.userIDTV.text = post.userId.toString()
    }
}