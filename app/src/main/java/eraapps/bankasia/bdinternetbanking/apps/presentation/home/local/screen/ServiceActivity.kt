package eraapps.bankasia.bdinternetbanking.apps.presentation.home.local.screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import eraapps.bankasia.bdinternetbanking.apps.util.CustomAppCompatActivity
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog
import eraapps.bankasia.bdinternetbanking.apps.common.data.HeaderData
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.BillModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar.ServiceListAdapter
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.login.screen.LoginActivity
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.common.view_model.MenuViewModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.dashboard.screen.WelcomeDashboardActivity
import eraapps.bankasia.bdinternetbanking.apps.util.CustomActivityClear
import eraapps.bankasia.bdinternetbanking.apps.util.CustomAlert
import eraapps.bankasia.bdinternetbanking.apps.util.Encript_Parameter
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants

@AndroidEntryPoint
class ServiceActivity : CustomAppCompatActivity() {
    private lateinit var globalVariable: GlobalVariable
    private lateinit var pDialog: SweetAlertDialog

    private lateinit var toolbar: Toolbar
    private lateinit var toolbar_layout: LinearLayout
    private lateinit var iv_header_back: ImageView
    private lateinit var iv_header_logout: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var serviceRecycler: RecyclerView

    var serviceList: ArrayList<BillModel> = ArrayList()
    private lateinit var adapter: ServiceListAdapter

    val menuViewModel: MenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        globalVariable = this.applicationContext as GlobalVariable
        pDialog = CustomAlert.showProgressDialog(this, globalVariable.languageCode)

        toolbar = findViewById(R.id.toolbar)
        toolbar_layout = findViewById(R.id.toolbar_layout)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)
        serviceRecycler = findViewById(R.id.serviceRecycler)

        iv_header_logout.visibility = View.INVISIBLE


        iv_header_back.setOnClickListener {
            goBack()
        }


        // Service List Add Start ********

        if (TextContants.banglaLanguageCode.equals(globalVariable.languageCode)
        ) {
            /* result.add(ServiceModel("ব্যালেন্স অনুসন্ধান", "BE"))
             result.add(ServiceModel("হিসাবের বিবরণ দেখুন", "VS"))
             result.add(ServiceModel("ফান্ড ট্রান্সফার (ব্যাংক এশিয়া অ্যাকাউন্টের মধ্যে)", "FTBAA"))
             result.add(
                 ServiceModel(
                     "আইবিএফটি অন্য ব্যাংকে ফান্ড ট্রান্সফার এনপিএসবির মাধ্যমে",
                     "IOBN"
                 )
             )
             result.add(ServiceModel("ইএফটি অন্য ব্যাংকে ফান্ড ট্রান্সফার", "IOBN"))
             result.add(ServiceModel("স্টান্ডিং ইন্সট্রাকশন্স", "SI"))
             result.add(ServiceModel("মোবাইল রিচার্জ", "MR"))
             result.add(ServiceModel("বিকাশ ফান্ড ট্রান্সফার", "BK"))
             result.add(ServiceModel("ডেসকো,ডিপিডিসি,ওয়াসার বিল পেমেন্ট", "DBPAB"))
             result.add(ServiceModel("ক্রেডিট কার্ড বিবরণ দেখুন", "VCCS"))
             result.add(ServiceModel("পেওনিয়ার পেআউট", "PP"))
             result.add(ServiceModel("পেওনিয়ার ব্যালেন্স দেখুন", "VPB"))
             result.add(ServiceModel("পাসওয়ার্ড পরিবর্তন করুন", "CP"))*/

            toolbar_title.setText(R.string.service_bangla)
        } else {
            /* result.add(ServiceModel("Balance Enquiry", "BE"))
             result.add(ServiceModel("Account  Statement", "AS"))
             result.add(ServiceModel("Fund Transfer to own Bank", "FTBAA"))
             result.add(ServiceModel("IBFT to other Bank", "FTAB"))
             result.add(ServiceModel("EFT to other Bank", "IOBN"))
             result.add(ServiceModel("Standing Instruction", "SI"))
             result.add(ServiceModel("Mobile Recharge", "MR"))
             result.add(ServiceModel("Fund Transfer to bKash Wallet ", "BK"))
             result.add(ServiceModel("VISA QR Pay", "BAAB"))
             result.add(ServiceModel("Bangla QR pay", "SC"))
             result.add(ServiceModel("Credit Card Statement", "VCS"))
             result.add(ServiceModel("Payoneer Payments", "WBP"))
             result.add(ServiceModel("Utility bill payment(DESCO/WASA/DPDC)", "WBPAB"))
             result.add(ServiceModel("Cheque ( View status & Stop payment)", "DBP"))
             result.add(ServiceModel("View Credit Card Statement", "VCCS"))
             result.add(ServiceModel("Change Password", "CP"))*/

            toolbar_title.setText(R.string.service)
        }

        // Service List Add End ********

        if (!CustomAlert.isOnline(this)) {
            CustomAlert.showInternetConnectionMessage(
                this,
                globalVariable.languageCode
            )
        } else {
            serviceList()
        }

        //  serviceListShow()

        observeViewModel()
    }


    private fun observeViewModel() {
        menuViewModel.dashboardMenuResponse
            .observe(this) {
                pDialog.dismiss()
                serviceList.clear()
                for (i in it.indices) {
                    serviceList.add(
                        BillModel(
                            it[i].softCode.toString(),
                            it[i].menuTitleE.toString(),
                            it[i].itemid.toString()
                        )
                    )
                }

                adapter = ServiceListAdapter(
                    serviceList,
                    this,
                    object : ServiceListAdapter.OnItemClickListener {
                        override fun onItemClick(item: BillModel) {


                        }
                    })
                serviceRecycler.layoutManager = LinearLayoutManager(applicationContext)
                serviceRecycler.adapter = adapter
                serviceRecycler.setHasFixedSize(true)


            }

        menuViewModel.errorResponse
            .observe(this) {
                pDialog.dismiss()
                CustomAlert.showErrorMessage(this, it.message, globalVariable.languageCode)
            }
    }


    private fun goBack() {

        val id = intent.getStringExtra("W")
        if (id == "W") {
            val intent = Intent(this, WelcomeDashboardActivity::class.java)
            intent.putExtra("LAN", "LAN")
            CustomActivityClear.doClearActivity(intent, this)
            startActivity(intent)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

    }

    /*  private fun serviceListShow() {
          adapter = ServiceListAdapter(result)
          serviceRecycler.layoutManager = LinearLayoutManager(applicationContext)
          serviceRecycler.adapter = adapter
          serviceRecycler.setHasFixedSize(true)

      }*/

    private fun serviceList() {
        pDialog.show()
        val model = AccountRequestModel()
        model.mailId = Encript_Parameter.getRsa_encrypt(globalVariable.mailId)
        model.hardCode = Encript_Parameter.getRsa_encrypt("SERVICE")
        model.authorization = globalVariable.token
        menuViewModel.dashboardMenu(HeaderData.headerHome(globalVariable), model)
    }

}



