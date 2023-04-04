package com.jydev.mindtravelapplication.ui.main.mindshare.post

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.jydev.mindtravelapplication.base.BaseFragment
import com.jydev.mindtravelapplication.data.model.MindSharePostsRequest
import com.jydev.mindtravelapplication.databinding.FragmentMindSharePostBinding
import com.jydev.mindtravelapplication.domain.model.MindSharePostCategory
import com.jydev.mindtravelapplication.domain.model.MindSharePostDetail
import com.jydev.mindtravelapplication.ui.main.mindshare.MindShareViewModel
import com.jydev.mindtravelapplication.ui.main.mindshare.post.detail.MindSharePostDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MindSharePostFragment private constructor() :
    BaseFragment<FragmentMindSharePostBinding>(FragmentMindSharePostBinding::inflate) {
    private val viewModel by viewModels<MindSharePostViewModel>()
    private val mindShareViewModel by viewModels<MindShareViewModel>({requireParentFragment()})
    private val adapter by lazy {
        MindSharePostAdapter {postId ->
            context?.let {
                startActivity(Intent(it,MindSharePostDetailActivity::class.java).apply {
                    putExtra(MindSharePostDetailActivity.POST_ID,postId)
                })
            }
        }
    }

    override fun onViewCreateLifeCycle() {
        viewModel.fetchMindSharePosts(MindSharePostsRequest(category = getCategory()))
        binding.initView()
        observeData()
    }

    private fun FragmentMindSharePostBinding.initView() {
        postRecyclerview.adapter = adapter
        swipeLayout.setOnRefreshListener {
            adapter.refresh()
            swipeLayout.isRefreshing = false
        }
    }

    private fun observeData() {
        viewModel.observeErrorHandling()
        viewModel.posts.observe(viewLifecycleOwner) {
            CoroutineScope(Dispatchers.Main).launch {
                adapter.submitData(it)
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            mindShareViewModel.postEvent.collect{
                adapter.refresh()
            }
        }
    }

    private fun getCategory(): MindSharePostCategory {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            arguments?.getSerializable(CATEGORY, MindSharePostCategory::class.java)!!
        else
            arguments?.getSerializable(CATEGORY) as MindSharePostCategory
    }

    companion object {
        private const val CATEGORY = "category"
        fun getMindSharePostFragment(category: MindSharePostCategory): MindSharePostFragment {
            return MindSharePostFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(CATEGORY, category)
                }
            }
        }
    }
}