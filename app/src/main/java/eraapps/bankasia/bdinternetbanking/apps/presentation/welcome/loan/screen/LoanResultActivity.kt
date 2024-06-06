package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.adapter.ViewPagerAdapter
import eraapps.bankasia.bdinternetbanking.apps.util.CustomActivityClear
import eraapps.bankasia.bdinternetbanking.apps.view.welcome.loan.fragmentn.LoanAllFragment
import eraapps.bankasia.bdinternetbanking.apps.view.welcome.loan.fragmentn.LoanApprovedFragment
import eraapps.bankasia.bdinternetbanking.apps.view.welcome.loan.fragmentn.LoanRejectedFragment

class LoanResultActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_result)


        toolbar = findViewById(R.id.toolbar)

        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)

        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)

        tabLayout = findViewById(R.id.tablayout)
        viewPager = findViewById(R.id.viewPager)

        setSupportActionBar(toolbar)
        toolbar_title.text = getString(R.string.loan_result)

        iv_header_back.setOnClickListener {
            val intent = Intent(this, LoanDashboardActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = ViewPagerAdapter(this)


        adapter.addFragment(LoanAllFragment(), "All")
        adapter.addFragment(LoanApprovedFragment(), "Approved")
        adapter.addFragment(LoanRejectedFragment(), "Rejected")

        viewPager.adapter = adapter
        viewPager.currentItem = 0


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getTabTitle(position)
            viewPager.currentItem = tab.position
        }.attach()

    }
}