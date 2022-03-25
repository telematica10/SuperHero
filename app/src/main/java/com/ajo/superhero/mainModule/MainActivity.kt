package com.ajo.superhero.mainModule

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajo.superhero.HeroResponse
import com.ajo.superhero.R
import com.ajo.superhero.databinding.ActivityMainBinding
import com.ajo.superhero.detailHeroModule.DetailHeroFragment
import com.ajo.superhero.detailHeroModule.viewModel.DetailHeroViewModel
import com.ajo.superhero.mainModule.adapter.HeroAdapter
import com.ajo.superhero.mainModule.adapter.OnClickListener
import com.ajo.superhero.mainModule.viewModel.MainViewModel
import com.ajo.superhero.utils.TypeError
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var mLinearLayoutManager: LinearLayoutManager
    private lateinit var mBinding: ActivityMainBinding

    private lateinit var mHeroAdapter: HeroAdapter
    private lateinit var mGridLayout: GridLayoutManager

    private lateinit var mMainViewModel: MainViewModel
    private lateinit var mDetailHeroViewModel: DetailHeroViewModel

    //    val url = Constants.BASE_URL + Constants.API_PATH + typeMethod
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupViewModel()
        setupRecyclerView()

//        mHeroAdapter = HeroAdapter(mutableListOf())
//        mLinearLayoutManager = LinearLayoutManager(this)
//        mBinding.rv.apply{
//            layoutManager = mLinearLayoutManager
//            adapter = mHeroAdapter
//        }
    }

    private fun setupRecyclerView() {
        mHeroAdapter = HeroAdapter(this)
        mGridLayout = GridLayoutManager(this, resources.getInteger(R.integer.main_columns))

        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = mGridLayout
            adapter = mHeroAdapter
        }
    }

    private fun setupViewModel() {
        mMainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mMainViewModel.getHeros().observe(this) { heros ->
            mBinding.progressBar.visibility = View.GONE
            mHeroAdapter.submitList(heros)
        }
        mMainViewModel.isShowProgress().observe(this) { isShowProgress ->
            mBinding.progressBar.visibility = if (isShowProgress) View.VISIBLE else View.GONE
        }
        mMainViewModel.getTypeError().observe(this) { typeError ->
            val msgRes = when (typeError) {
                TypeError.GET -> R.string.main_error_get
                else -> R.string.main_error_unknown
            }
            Snackbar.make(mBinding.root, msgRes, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun launchDetailFragment(hero: HeroResponse = HeroResponse()) {
        mDetailHeroViewModel.setHeroSelected(hero)

        val fragment = DetailHeroFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.containerMain, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onClick(hero: HeroResponse) {
        launchDetailFragment(hero)
    }


}