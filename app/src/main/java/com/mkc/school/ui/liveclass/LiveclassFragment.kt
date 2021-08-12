package com.mkc.school.ui.liveclass

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.data.pojomodel.model.GradeModel
import com.mkc.school.data.pojomodel.model.LiveclassModel
import com.mkc.school.databinding.FragmentLiveclassBinding
import com.mkc.school.ui.base.BaseFragment
import com.mkc.school.ui.liveclass.adapter.LiveclassAdapter
import java.util.ArrayList


class LiveclassFragment : BaseFragment<FragmentLiveclassBinding, LiveclassViewModel>(),
    LiveclassNavigator, LiveclassAdapter.OnLiveclassItemClick {

    companion object {
        val TAG = LiveclassFragment::class.java.simpleName
        fun newInstance(): LiveclassFragment {
            val args = Bundle()
            val fragment = LiveclassFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var liveclassViewModel: LiveclassViewModel? = null
    private var binding: FragmentLiveclassBinding? = null
    private var layoutManager: LinearLayoutManager? = null
    private var liveclassAdapter: LiveclassAdapter? = null
    private var liveClassList: ArrayList<LiveclassModel> = ArrayList<LiveclassModel>()


    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_liveclass
    override val viewModel: LiveclassViewModel
        get() {
            liveclassViewModel = ViewModelProvider(
                this,
                ViewModelFactory(requireActivity().application)
            ).get(
                LiveclassViewModel::class.java
            )
            return liveclassViewModel as LiveclassViewModel
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        liveclassViewModel!!.navigator = this
        binding = viewDataBinding

        loadDummyData()
        initview()

    }

    private fun loadDummyData() {
        liveClassList.add(LiveclassModel("Math","Mr. Sujay Bose","https://theracquet.org/wp-content/uploads/2020/04/thumbnail_Image-2-1-900x675.jpg", 1))
    }

    private fun initview() {

        binding?.rvLiveClass?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        layoutManager!!.setOrientation(LinearLayoutManager.VERTICAL)
        binding?.rvLiveClass?.setLayoutManager(layoutManager)
        liveclassAdapter = LiveclassAdapter(activity, liveClassList, this)
        binding?.rvLiveClass?.setAdapter(liveclassAdapter)
    }

    override fun onClick() {}

    override fun onLiveclassItemClick(position: Int, action: String?) {
    }

}