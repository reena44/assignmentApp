package com.example.assignmenttestapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignmenttestapp.model.AppData

/**AssignmentViewModel*/

class AssignmentViewModel():ViewModel(){
    val payUsingInfo = MutableLiveData("")

    val data = MutableLiveData<AppData>()
    fun setData(appData: AppData) {
        data.value = appData
    }
}