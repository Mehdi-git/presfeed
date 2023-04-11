package dev.mehdi.bakhtiari.presfeed.ui.newsdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.mehdi.bakhtiari.presfeed.data.model.ArticleUiModel
import dev.mehdi.bakhtiari.presfeed.databinding.FragmentNewsDetailsBinding
import dev.mehdi.bakhtiari.presfeed.ui.topheadlines.TopHeadlinesFragment.Companion.HEADLINES_FRAGMENT_ARG
import dev.mehdi.bakhtiari.presfeed.utils.toast
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsDetailsFragment : Fragment() {

    private val viewModel: NewsDetailsViewModel by viewModels()
    private var _binding: FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        handleBundle()
        observeToHeadlinesNews()
    }

    private fun handleBundle() {
        arguments?.getParcelable<ArticleUiModel>(HEADLINES_FRAGMENT_ARG).let {
            viewModel.setArticleDetails(it)
        }
    }

    private fun observeToHeadlinesNews() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.error.observe(viewLifecycleOwner) { error ->
                toast(error, Toast.LENGTH_LONG)
            }
            viewModel.articleDetails.observe(viewLifecycleOwner) { article ->
                binding.article = article
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}