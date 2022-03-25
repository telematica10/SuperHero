package com.ajo.superhero.detailHeroModule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ajo.superhero.R
import com.ajo.superhero.databinding.FragmentDetailBinding
import com.ajo.superhero.detailHeroModule.viewModel.DetailHeroViewModel
import com.ajo.superhero.mainModule.MainActivity

class DetailHeroFragment : Fragment() {

    private lateinit var mBinding: FragmentDetailBinding
    private lateinit var mDetailHeroViewModel: DetailHeroViewModel

    private var mActivity: MainActivity? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDetailHeroViewModel = ViewModelProvider(requireActivity())[DetailHeroViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDetailBinding.inflate(inflater, container, false)

        return mBinding.root
    }

    private fun setupActionBar() {
        mActivity = activity as? MainActivity
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mActivity?.supportActionBar?.title = getString(R.string.detail_hero_title)

        setHasOptionsMenu(true)
    }

}