package com.brizz.stockbittest.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brizz.stockbittest.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<V : ViewModel> : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var mViewModel: V

    val viewModel: V get() = mViewModel

    abstract fun injectViewModel()
    abstract fun initView()
    @LayoutRes
    abstract fun getLayoutResourceId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_StockbitTest)
        super.onCreate(savedInstanceState)
        injectViewModel()
        setContentView(getLayoutResourceId())
        initView()
    }
}