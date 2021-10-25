package com.example.ilabank

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.ilabank.entity.SectionType
import com.example.ilabank.entity.SubSection

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val sections : List<SectionType>
    get() = getSectionS()

     private fun getSectionS() : List<SectionType>{
         val sections : ArrayList<SectionType> = ArrayList()

         val mens : ArrayList<SubSection> = ArrayList()
         mens.add(SubSection(R.drawable.men_perfume,"perfume"))
         mens.add(SubSection(R.drawable.men_watch,"watch"))
         mens.add(SubSection(R.drawable.men_shoes,"shoes"))
         mens.add(SubSection(R.drawable.men_cream,"cream"))
         mens.add(SubSection(R.drawable.men_pants,"pants"))
         mens.add(SubSection(R.drawable.men_sandal,"sandal"))
         mens.add(SubSection(R.drawable.men_shirt,"shirt"))
         mens.add(SubSection(R.drawable.men_trimmer,"trimmer"))
         mens.add(SubSection(R.drawable.men_wallets,"wallet"))
         mens.add(SubSection(R.drawable.men_jeans,"jeans"))
         sections.add(SectionType(0,R.drawable.men,mens))

         val womens : ArrayList<SubSection> = ArrayList()
         womens.add(SubSection(R.drawable.women_make_up,"makeup"))
         womens.add(SubSection(R.drawable.women_shoes,"shoes"))
         sections.add(SectionType(1,R.drawable.women,womens))

         val kids : ArrayList<SubSection> = ArrayList()
         kids.add(SubSection(R.drawable.kids_games,"games"))
         kids.add(SubSection(R.drawable.kids_shoes,"shoes"))
         sections.add(SectionType(2,R.drawable.kids,kids))

         return sections
     }

    fun searchItems(searchString: String, subsections: ArrayList<SubSection>) : LiveData<ArrayList<SubSection>> = liveData {
        emit(subsections.filter {
            it.title.contains(searchString,false)
        } as ArrayList<SubSection>)
    }

}