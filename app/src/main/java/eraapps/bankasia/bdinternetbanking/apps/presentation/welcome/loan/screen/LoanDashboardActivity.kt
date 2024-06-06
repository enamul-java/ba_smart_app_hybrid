package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog
import eraapps.bankasia.bdinternetbanking.apps.common.data.HeaderData
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.dashboard.screen.WelcomeDashboardActivity
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.adapter.MenuSubAdapter
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.Menu
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model.DatabaseHelper
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.view_model.LoanViewModel
import eraapps.bankasia.bdinternetbanking.apps.util.CustomActivityClear
import eraapps.bankasia.bdinternetbanking.apps.util.CustomAlert
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import eraapps.bankasia.bdinternetbanking.apps.util.HelpDialog

@AndroidEntryPoint
class LoanDashboardActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView

    private lateinit var menuList: ArrayList<Menu>
    private lateinit var menuGridView: GridView
    var adapter: MenuSubAdapter? = null

    private lateinit var globalVariable: GlobalVariable
    private lateinit var loanViewModel: LoanViewModel
    private lateinit var pDialog: SweetAlertDialog
    private lateinit var db: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_dashboard)

        toolbar = findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)

        globalVariable = this.applicationContext as GlobalVariable
        loanViewModel = ViewModelProvider(this).get(LoanViewModel::class.java)
        pDialog = CustomAlert.showProgressDialog(this, globalVariable.languageCode)
        db = DatabaseHelper(this)

        menuGridView = findViewById(R.id.menuGridView)
        menuList = ArrayList<Menu>()

        setSupportActionBar(toolbar)
        toolbar_title.text = getString(R.string.loan_dashboard_new)

        iv_header_logout.setOnClickListener{
            HelpDialog.showHelpDialog(this, HelpDialog.NANO_LONAN_DASHBOARD)
        }

        iv_header_back.setOnClickListener {
            val intent = Intent(this, WelcomeDashboardActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

        menuList.add(Menu("AL", "Loan Application", R.drawable.nano_loan_apply_logo_new))
        menuList.add(Menu("LR", "Loan Result", R.drawable.nano_loan_icon_new))
        menuList.add(Menu("ER", "Early Payment", R.drawable.early_settlement))
        menuList.add(Menu("LS", "Loan Payment Status", R.drawable.icon_payment_status))

        adapter = MenuSubAdapter(this, menuList)
        menuGridView.adapter = adapter

        menuGridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val menu_soft_code =
                    view.findViewById<View>(R.id.menu_soft_code) as TextView

                when {
                    "AL" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, LoanApplicationActivity::class.java)
                       startActivity(intent)
                    }
                    "LR" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, LoanResultActivity::class.java)
                        startActivity(intent)
                    }

                    "ER" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, LoanEarlyPayment::class.java)
                        startActivity(intent)
                    }

                    "LS" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, LoanStatus::class.java)
                        startActivity(intent)
                    }
                }
            }


        if (!CustomAlert.isOnline(this@LoanDashboardActivity)) {
            CustomAlert.showInternetConnectionMessage(
                this@LoanDashboardActivity,
                globalVariable.languageCode
            )
        } else {
            //For Deploy without Early Payment
            getSlap()
        }

        /* try {
             Toast.makeText(applicationContext, "Fetching Model Slabs", Toast.LENGTH_SHORT).show()
             val fetchModelSlabs = FetchModelSlabs(applicationContext)
             fetchModelSlabs.fetchModelSlabs()
         } catch (e: Exception) {
             e.printStackTrace()
         }
         */

       // val fetchModelSlabs = FetchModelSlabs(applicationContext)

        observeViewModel()

    }

    fun observeViewModel() {
        loanViewModel.error_response.observe(this, {
            it?.let {
                pDialog.dismiss()

                CustomAlert.showErrorMessage(this, it.outMessage, globalVariable.languageCode)
            }
        })

        loanViewModel.slapDataModelResponse.observe(this, {
            it?.let {
                pDialog.dismiss()

                //Log.e("response--",it.toString())

                if(it.outCode== "0"){
                    db.deteleDatad()

                    for (i in 0 until it.components.size) {
                        db.createComponent(
                            it.components[i].component_id,
                            ""+it.components[i].data_source_id,
                            it.components[i].component_name.toString(),
                            ""+it.components[i].component_weight
                        )
                    }

                    for (i in 0 until it.variables.size) {
                        db.createVariable(
                            it.variables[i].variable_id,
                            ""+it.variables[i].component_id,
                            it.variables[i].variable_name)
                    }

                    for (i in 0 until it.values.size) {
                        db.createValue(
                            it.values[i].value_id,
                            "" + it.values[i].variable_id,
                            it.values[i].value_name,
                            "" + it.values[i].value_score,
                            "" + it.values[i].flag,
                            "" + it.values[i].min_value,
                            "" + it.values[i].max_value)
                    }
                }else{
                    var message = ""
                    try{
                        if(it.outMessage.isNullOrEmpty()){
                            message = "Slap Calling Problem!"
                        }else{
                            message = it.outMessage
                        }
                    }catch (e:Exception){

                    }
                    CustomAlert.showErrorMessage(this, message, globalVariable.languageCode)
                }

            }
        })
    }

    private fun getSlap() {
        pDialog.show()
        val model = LoanModel()
        model.authorization = globalVariable.token
        this.let { it1 -> loanViewModel.getSlap(HeaderData.headerWelcome(globalVariable),model) }
    }
}