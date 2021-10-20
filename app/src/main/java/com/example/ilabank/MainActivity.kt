package com.example.ilabank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.ilabank.adapter.ListItemAdapter
import com.example.ilabank.adapter.ScreenSlidePagerAdapter
import com.example.ilabank.entity.SubSection
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainViewModel = MainViewModel(application)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val editTextSearch = findViewById<AppCompatEditText>(R.id.editTextSearch)

        viewPager.adapter = ScreenSlidePagerAdapter(mainViewModel.sections)
        viewPager.setPageMargin(30)
        tabLayout.setupWithViewPager(viewPager)

        findViewById<RecyclerView>(R.id.list).apply {
            adapter = ListItemAdapter(mainViewModel.sections[0].list)
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        }

        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                val listItemAdapter : ListItemAdapter = findViewById<RecyclerView>(R.id.list).adapter as ListItemAdapter
                listItemAdapter.notifyDataSetChanged(mainViewModel.sections[position].list)
            }
        })

        editTextSearch.addTextChangedListener {
            val searchString = it.toString().trim();
            val screenSlidePagerAdapter : ScreenSlidePagerAdapter = viewPager.adapter as ScreenSlidePagerAdapter
            val list = screenSlidePagerAdapter.sections.get(viewPager.currentItem).list
            mainViewModel.searchItems(searchString,list)
                .observe(this@MainActivity, Observer<ArrayList<SubSection>> {
                    val listItemAdapter : ListItemAdapter = findViewById<RecyclerView>(R.id.list).adapter as ListItemAdapter
                    listItemAdapter.notifyDataSetChanged(it)
                })
        }

    }

}