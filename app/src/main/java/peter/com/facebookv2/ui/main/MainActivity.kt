package peter.com.facebookv2.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import peter.com.facebookv2.R
import peter.com.facebookv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: PostViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_main)


        viewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        val adapter = PostsAdapter()

        viewModel.data.observe(this, Observer {data->
            adapter.submitList(data)
            binding.recyclerView.adapter = adapter
            Log.i("LIST",data.size.toString())
        })
    }
}