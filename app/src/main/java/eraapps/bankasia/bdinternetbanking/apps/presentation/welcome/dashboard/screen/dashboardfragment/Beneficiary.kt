package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.dashboard.screen.dashboardfragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog
import eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar.ViewPagerAdapter
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.dashboard.screen.WelcomeDashboardActivity
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.dashboard.screen.dashboardfragment.beneficiary_fragment.MfsFragment
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.dashboard.screen.dashboardfragment.beneficiary_fragment.OtherBankFragment
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.dashboard.screen.dashboardfragment.beneficiary_fragment.OwnBankFragment
import eraapps.bankasia.bdinternetbanking.apps.util.CustomActivityClear
import eraapps.bankasia.bdinternetbanking.apps.util.CustomAlert
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants


@AndroidEntryPoint
class Beneficiary : Fragment() {
    private lateinit var globalVariable: GlobalVariable
    private lateinit var pDialog: SweetAlertDialog

    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView
    private lateinit var tab_layout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var fab_add_beneficiary: FloatingActionButton

    var tabPosition = 0
    var pageNumber = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_beneficiary, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)
        tab_layout = view.findViewById(R.id.tab_layout)
        fab_add_beneficiary = view.findViewById(R.id.fab_add_beneficiary)
        viewPager2 = view.findViewById(R.id.viewPager2)


        globalVariable = requireActivity().applicationContext as GlobalVariable
        pDialog = CustomAlert.showProgressDialog(requireActivity(), globalVariable.languageCode)

        toolbar_title.text = getString(R.string.beneficiary)

        iv_header_back.setOnClickListener {
            val intent = Intent(requireActivity(), WelcomeDashboardActivity::class.java)
            CustomActivityClear.doClearActivity(intent, requireActivity())

        }
        tab_layout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = ViewPagerAdapter(requireActivity())

        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            adapter.addFragment(OwnBankFragment(), getString(R.string.own_bank_bangla))
            adapter.addFragment(OtherBankFragment(), getString(R.string.other_bank_bangal))
            adapter.addFragment(MfsFragment(), getString(R.string.wallet_bangla))
          //  adapter.addFragment(UtilityFragment(), getString(R.string.utility_bangla))
        } else {
            adapter.addFragment(OwnBankFragment(), getString(R.string.own_bank))
            adapter.addFragment(OtherBankFragment(), getString(R.string.other_bank))
            adapter.addFragment(MfsFragment(), getString(R.string.wallet))
         //   adapter.addFragment(UtilityFragment(), getString(R.string.utility))
        }

        viewPager2.adapter = adapter
        //  viewPager2.currentItem = 0

        val bundle = this.arguments
        if (bundle != null) {
            pageNumber = bundle.getInt("viewpager")
            globalVariable.dashboardPosition = "2"
            Log.d("pageNumber", pageNumber.toString())
            viewPager2.adapter = adapter
            viewPager2.currentItem = pageNumber
        }

        TabLayoutMediator(tab_layout, viewPager2) { tab, position ->
            tab.text = adapter.getTabTitle(position)
            viewPager2.currentItem = pageNumber
            // viewPager2.currentItem = 1
            //  tabPosition = pageNumber

        }.attach()

        Log.d("pageNumber", pageNumber.toString())


        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.d("Tabposition", tab.position.toString())

                when (tab.position) {
                    0 -> {
                        tabPosition = 0
                        pageNumber = 0
                    }

                    1 -> {
                        tabPosition = 1
                        pageNumber = 1
                    }

                    2 -> {
                        tabPosition = 2
                        pageNumber = 2
                    }

                    3 -> {
                        tabPosition = 3
                        pageNumber = 3
                        fab_add_beneficiary.visibility = View.GONE
                    }

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })



        fab_add_beneficiary.setOnClickListener {
            when (pageNumber) {
                0 -> {
                    //own bank
                }

                1 -> {
                    //other bank
                }

                2 -> {
                    //mfs
                }

            }

        }


        fontset()
        return view
    }

    fun fontset() {
        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            toolbar_title.text = getString(R.string.beneficiary_bangla)

        }
    }

}