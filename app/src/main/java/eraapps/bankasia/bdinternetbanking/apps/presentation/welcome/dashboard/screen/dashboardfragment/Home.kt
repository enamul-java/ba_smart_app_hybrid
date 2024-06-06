package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.dashboard.screen.dashboardfragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import de.hdodenhof.circleimageview.CircleImageView
import eraapps.bankasia.bdinternetbanking.apps.BuildConfig
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog
import eraapps.bankasia.bdinternetbanking.apps.common.data.HeaderData
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountListDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.AccountDetailsModel
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MenuModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar.AccountListAdaptar
import eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar.MenuAdapter
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.common.view_model.AccountViewModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.common.view_model.MenuViewModel
import eraapps.bankasia.bdinternetbanking.apps.util.*
import eraapps.bankasia.bdinternetbanking.apps.util.ValidationUtil.Companion.stringUnderline


@AndroidEntryPoint
class Home : Fragment() {

    //************Nano Loan*************************

    private lateinit var globalVariable: GlobalVariable
    private lateinit var pDialog: SweetAlertDialog
    private lateinit var ac_infor_recyclerview: RecyclerView

    private lateinit var menuList: ArrayList<MenuModel>
    private lateinit var menuGridView: GridView
    private lateinit var iv_tab_right_icon: ImageView
    private lateinit var profile_image: CircleImageView
    private lateinit var tv_primary_ac: TextView
    private lateinit var tvWelcomeCustomerName: TextView
    private lateinit var tv_Notification: TextView
    private lateinit var tvWelcome: TextView
    private lateinit var tv_all_acinfo: TextView
    private lateinit var btnTapforBalance: AppCompatButton
    private lateinit var ivNotification: ImageView

    // private lateinit var dialog: BottomSheetDialog
    private lateinit var dialog: Dialog

    private var ac_infoList: ArrayList<AccountDetailsModel> = ArrayList()

    var adapter: MenuAdapter? = null
    private lateinit var acAdaptar: AccountListAdaptar

    val accountViewModel: AccountViewModel by viewModels()
    val menuViewModel: MenuViewModel by viewModels()

    private var accountFlag = "S"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for requireActivity() fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        globalVariable = requireActivity().applicationContext as GlobalVariable
        pDialog = CustomAlert.showProgressDialog(requireActivity(), globalVariable.languageCode)
        menuGridView = view.findViewById(R.id.menuGridView)
        iv_tab_right_icon = view.findViewById(R.id.iv_tab_right_icon)
        profile_image = view.findViewById(R.id.profile_image)
        tv_primary_ac = view.findViewById(R.id.tv_primary_ac)
        tvWelcomeCustomerName = view.findViewById(R.id.tvWelcomeCustomerName)
        btnTapforBalance = view.findViewById(R.id.btnTapforBalance)
        tv_all_acinfo = view.findViewById(R.id.tv_all_acinfo)
        ivNotification = view.findViewById(R.id.ivNotification)
        tv_Notification = view.findViewById(R.id.tv_Notification)
        tvWelcome = view.findViewById(R.id.tvWelcome)
        dialog = BottomSheetDialog(requireActivity())


        //***************Nano Loan******************
        nanoLoanObserveViewModel()

        iv_tab_right_icon.setOnClickListener {
            if (globalVariable.primaryAc.isNotEmpty()) {
                // load the animation
                val animFadein: Animation = AnimationUtils.loadAnimation(
                    requireActivity(), R.anim.right_to_left
                )

                // start the animation
                iv_tab_right_icon.startAnimation(animFadein)
                soureceAccountBalance()
            }
        }

        tv_Notification.bringToFront()

        btnTapforBalance.setOnClickListener {
            if (globalVariable.primaryAc.isNotEmpty()) {
                // load the animation
                val animFadein: Animation = AnimationUtils.loadAnimation(
                    requireActivity(), R.anim.right_to_left
                )

                // start the animation
                iv_tab_right_icon.startAnimation(animFadein)
                soureceAccountBalance()
            }
        }

        tv_primary_ac.text = globalVariable.primaryAc
        tvWelcomeCustomerName.text = globalVariable.userName

        // Dialog init
        dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.bottom_sheet_acc_info)
        ac_infor_recyclerview = dialog.findViewById(R.id.ac_infor_recyclerview) as RecyclerView


        // tvWelcome.text=""
        profile_image.setOnClickListener {
            //profile image
        }

        tv_all_acinfo.setOnClickListener {
            accountFlag = "S"
            if (!NetworkUtil.isOnline(requireActivity())) {
                CustomAlert.showInternetConnectionMessage(
                    requireActivity(),
                    globalVariable.languageCode
                )
            } else {
                getAccuntBalanceCasa()
            }

        }

        tv_Notification.setOnClickListener {
            //notification
        }

        ivNotification.setOnClickListener {
            //notification
        }

        try {
            if (globalVariable.userImage == "") {
                profile_image.setImageResource(R.drawable.ic_baseline_person_24)
            } else {
                profile_image.loadOperatorsImagesClear(
                    globalVariable.userImage, getProgressDrawble(profile_image.context)
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


        /*   if (globalVariable.dashboardMenuList.isEmpty()) {
               dashboardMenu()
           } else {
               adapter = MenuAdapter(requireActivity(), globalVariable.dashboardMenuList)
               menuGridView.adapter = adapter
           }
   */
        if (globalVariable.dashboardPosition != "0" || globalVariable.dashboardPosition.isEmpty()) {
            globalVariable.dashboardPosition = "0"
        }

        menuList = ArrayList()

        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            menuList.add(MenuModel("FTR", "ট্রান্সফার", R.drawable.icon_transfer))
            menuList.add(MenuModel("UTI", "ইউটিলিটি", R.drawable.icon_bills_pay))
            menuList.add(MenuModel("TOP", "রিচার্জ", R.drawable.icon_recharge))
            menuList.add(MenuModel("MFS", "ওয়ালেট ট্রান্সফার", R.drawable.icon_mfs))
            menuList.add(MenuModel("PA", "পেওনিয়ার", R.drawable.icon_payoneer))
            menuList.add(MenuModel("CAS", "কার্ডের সেবা সমূহ", R.drawable.icon_cards))
            menuList.add(MenuModel("ACS", "একাউন্ট পরিষেবা", R.drawable.icon_ac_service))
            menuList.add(MenuModel("INSP", "বীমা পরিশোধ", R.drawable.icon_insurance_pay))
            menuList.add(MenuModel("GOVF", "সরকারি ফী", R.drawable.icon_govt_fee))
            menuList.add(MenuModel("TUF", "টিউশন ফী", R.drawable.icon_tution_fee))
            menuList.add(MenuModel("ONPU", "ই-কমার্স", R.drawable.icon_online_purchase))

            //Conditional on off nano loan
            //if(CheckEligibility().checkEligibility(requireActivity())) {
             menuList.add(MenuModel("DNL", "Digital Nano Loan", R.drawable.nano_loan_dashboard))
            /*}else{
                menuList.add(MenuModel("CALL", "কন্টাক্ট সেন্টার", R.drawable.icon_call_center))
            }*/
            if(BuildConfig.FLAVOR.equals("luat") ||
                BuildConfig.BUILD_TYPE.equals("debug")) {
                menuList.add(MenuModel("IDTP", "Binimoy", R.drawable.icon_binimoy))
            }
        } else {
            menuList.add(MenuModel("FTR", "Transfer", R.drawable.icon_transfer))
            menuList.add(MenuModel("UTI", "Utility", R.drawable.icon_bills_pay))
            menuList.add(MenuModel("TOP", "Recharge", R.drawable.icon_recharge))
            menuList.add(MenuModel("MFS", "Wallet Transfer", R.drawable.icon_mfs))
            menuList.add(MenuModel("PA", "Payoneer", R.drawable.icon_payoneer))
            menuList.add(MenuModel("CAS", "Card Services", R.drawable.icon_cards))
            menuList.add(MenuModel("ACS", "A/C Services", R.drawable.icon_ac_service))
            menuList.add(MenuModel("INSP", "Insurance Pay", R.drawable.icon_insurance_pay))
            menuList.add(MenuModel("GOVF", "Govt. Fee", R.drawable.icon_govt_fee))
            menuList.add(MenuModel("TUF", "Tuition Fee", R.drawable.icon_tution_fee))
            menuList.add(MenuModel("ONPU", "e-Commerce", R.drawable.icon_online_purchase))

            //remove for version 2.0.3
            //if(CheckEligibility().checkEligibility(requireActivity())) {
            menuList.add(MenuModel("DNL", "Digital Nano Loan", R.drawable.nano_loan_dashboard))
            /*}else{
                menuList.add(MenuModel("CALL", "Contact Center", R.drawable.icon_call_center))
            }*/
            if(BuildConfig.FLAVOR.equals("luat") ||
                BuildConfig.BUILD_TYPE.equals("debug")) {
                menuList.add(MenuModel("IDTP", "Binimoy", R.drawable.icon_binimoy))
            }
        }


        adapter = MenuAdapter(requireActivity(), menuList)
        menuGridView.adapter = adapter

        menuGridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val menu_soft_code = view.findViewById<View>(R.id.menu_soft_code) as TextView
                val menu_name = view.findViewById<View>(R.id.menu_name) as TextView


                when {
                    "FTR" == menu_soft_code.text.toString() -> {



                    }

                    "UTI" == menu_soft_code.text.toString() -> {



                    }

                    "TOP" == menu_soft_code.text.toString() -> {



                    }

                    "MFS" == menu_soft_code.text.toString() -> {



                    }

                    "ONPU" == menu_soft_code.text.toString() -> {



                    }

                    "TUF" == menu_soft_code.text.toString() -> {

                    }

                    "CAS" == menu_soft_code.text.toString() -> {

                    }

                    "PA" == menu_soft_code.text.toString() -> {

                    }

                    "INSP" == menu_soft_code.text.toString() -> {

                    }

                    "GOVF" == menu_soft_code.text.toString() -> {

                    }

                    "ACS" == menu_soft_code.text.toString() -> {


                    }

                    "CALL" == menu_soft_code.text.toString() -> {

                        if (!checkCallPermission()) {
                            getCallPermission()
                        } else {
                            CustomActivityClear.callDialog(
                                requireActivity(), globalVariable.languageCode
                            )
                        }

                    }

                    "DNL" == menu_soft_code.text.toString() -> {
                        //nano loan

                    }

                    "IDTP" == menu_soft_code.text.toString() -> {
                        //binimoy

                    }

                    else -> {
                        CustomAlert.showComingSoon(
                            requireActivity(),
                            menu_name.text.toString(),
                            globalVariable.languageCode
                        )
                    }

                }
            }

        observeEvents()
        fontSet()
        return view
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun observeEvents() {

        accountViewModel.sourceAccountBalanceResponse.observe(viewLifecycleOwner) {
            if ("0" == it.outCode) {
                btnTapforBalance.text = it.availaleBalance
                object : CountDownTimer(10000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        //  btnTapforBalance.setText("" + millisUntilFinished / 1000)

                    }

                    @SuppressLint("SetTextI18n")
                    override fun onFinish() {
                        btnTapforBalance.text = "Tap for Balance"
                    }
                }.start()

            } else if ("2" == it.outCode) {
                pDialog.dismiss()
                CustomActivityClear.forceLogout(requireActivity(), it.outMessage.toString())

            } else {
                pDialog.dismiss()
                CustomAlert.showErrorMessage(
                    requireActivity(), it.outMessage, globalVariable.languageCode
                )
            }

        }

        accountViewModel.account_balance_casa_response.observe(viewLifecycleOwner) {
            try {
                ac_infoList.clear()
                ac_infor_recyclerview.removeAllViews()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            if ("0" == it.outCode) {
                getAccuntBalanceCasaList()
            } else if ("2" == it.outCode) {
                pDialog.dismiss()
                CustomActivityClear.forceLogout(requireActivity(), it.outMessage.toString())

            } else {
                pDialog.dismiss()
                CustomAlert.showErrorMessage(
                    requireActivity(), it.outMessage, globalVariable.languageCode
                )
            }

        }

        accountViewModel.getAccuntBalanceCasaListResponse.observe(viewLifecycleOwner) {
            pDialog.dismiss()
            ac_infoList.clear()
            if (it.isNotEmpty()) {
                ac_infoList = (it as ArrayList<AccountDetailsModel>?)!!

                if (!dialog.isShowing) {
                    dialogOpen()
                }

            }
            adapter()

        }

        menuViewModel.dashboardMenuResponse.observe(viewLifecycleOwner) {
            pDialog.dismiss()
            if (it.isNotEmpty()) {
                globalVariable.dashboardMenuList.clear()
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    for (i in it.indices) {
                        globalVariable.dashboardMenuList.add(
                            MenuModel(
                                it[i].softCode.toString(),
                                it[i].menuTitleB.toString(),
                                it[i].itemid!!.toInt(),
                            )
                        )
                    }
                } else {
                    for (i in it.indices) {
                        globalVariable.dashboardMenuList.add(
                            MenuModel(
                                it[i].softCode.toString(),
                                it[i].menuTitleE.toString(),
                                it[i].itemid!!.toInt(),
                            )
                        )
                    }
                }

                adapter = MenuAdapter(requireActivity(), globalVariable.dashboardMenuList)
                menuGridView.adapter = adapter
            }

        }

        menuViewModel.errorResponse.observe(viewLifecycleOwner) {
            pDialog.dismiss()
            CustomAlert.showErrorMessage(requireActivity(), it.message, globalVariable.languageCode)

        }

        accountViewModel.errorResponse.observe(viewLifecycleOwner) {
            pDialog.dismiss()
            CustomAlert.showErrorMessage(requireActivity(), it.message, globalVariable.languageCode)

        }

    }

    fun getCallPermission() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val READ_CONTACTS = ContextCompat.checkSelfPermission(
                    requireActivity(), Manifest.permission.CALL_PHONE
                )


                if (READ_CONTACTS != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                        requireActivity(), arrayOf(
                            Manifest.permission.CALL_PHONE
                        ), 1
                    )


                }


            }


        } catch (ex: Exception) {
            ex.message?.let { Log.e("", it) }
        }
    }

    private fun checkCallPermission(): Boolean {
        val result =
            ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CALL_PHONE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Toast.makeText(requireActivity(), "Call Permission is Ok", Toast.LENGTH_SHORT).show()
        } else {
            getCallPermission()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun adapter() {
        if (ac_infoList.isEmpty()) {
            ac_infor_recyclerview.visibility = View.GONE
        } else {
            ac_infor_recyclerview.visibility = View.VISIBLE
        }
        val mLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        ac_infor_recyclerview.layoutManager = mLayoutManager
        ac_infor_recyclerview.itemAnimator = DefaultItemAnimator()

        acAdaptar =
            AccountListAdaptar(ac_infoList, object : AccountListAdaptar.OnItemClickListener {
                override fun onItemClick(item: AccountDetailsModel) {
                    //   val intent = Intent(requireActivity(), MfsTransfer::class.java)
                    //   startActivity(intent)
                    globalVariable.statmentSelectAc = item.accountNumber.toString()
                    requireActivity().findNavController(R.id.nav_host_fragment)
                        .navigate(R.id.navigation_statement)
                    dialog.dismiss()

                }
            })
        ac_infor_recyclerview.adapter = acAdaptar
        acAdaptar.notifyDataSetChanged()
    }

    @SuppressLint("InflateParams")
    private fun dialogOpen() {
        // dialog = Dialog(requireActivity())
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        // dialog.setContentView(R.layout.bottom_sheet_acc_info)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT


        val tv_account_details_level =
            dialog.findViewById(R.id.tv_account_details_level) as TextView
        val ivClose = dialog.findViewById(R.id.ivClose) as ImageView
        val btn_casa = dialog.findViewById(R.id.btn_casa) as Button
        val btn_fdr_dps = dialog.findViewById(R.id.btn_fdr_dps) as Button
        val btn_loan = dialog.findViewById(R.id.btn_loan) as Button
        val btn_credit = dialog.findViewById(R.id.btn_credit) as Button

        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            tv_account_details_level.text = getString(R.string.account_details_bangla)
            btn_casa.text = getString(R.string.casa_bangla)
            btn_fdr_dps.text = getString(R.string.fdr_dps_bangla)
            btn_loan.text = getString(R.string.loan_bangla)
            btn_credit.text = getString(R.string.credit_card_bangla)
        }

        btn_credit.visibility = View.GONE

        ac_infor_recyclerview = dialog.findViewById(R.id.ac_infor_recyclerview) as RecyclerView


        btn_casa.setOnClickListener {
            accountFlag = "S"
            btn_casa.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
            btn_fdr_dps.setTextColor(
                ContextCompat.getColor(
                    requireActivity(), R.color.black
                )
            )
            btn_loan.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
            btn_credit.setTextColor(
                ContextCompat.getColor(
                    requireActivity(), R.color.black
                )
            )

            btn_casa.background = ResourcesCompat.getDrawable(
                resources, R.drawable.btn_round_border_ac_info_select, null
            )
            btn_fdr_dps.background = ResourcesCompat.getDrawable(
                resources, R.drawable.btn_round_border_transparent_unselect, null
            )
            btn_loan.background = ResourcesCompat.getDrawable(
                resources, R.drawable.btn_round_border_transparent_unselect, null
            )
            btn_credit.background = ResourcesCompat.getDrawable(
                resources, R.drawable.btn_round_border_transparent_unselect, null
            )
            getAccuntBalanceCasa()
        }
        btn_fdr_dps.setOnClickListener {
            accountFlag = "D"
            btn_fdr_dps.setTextColor(
                ContextCompat.getColor(
                    requireActivity(), R.color.white
                )
            )
            btn_loan.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
            btn_casa.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
            btn_credit.setTextColor(
                ContextCompat.getColor(
                    requireActivity(), R.color.black
                )
            )

            btn_fdr_dps.background = ResourcesCompat.getDrawable(
                resources, R.drawable.btn_round_border_ac_info_select, null
            )
            btn_casa.background = ResourcesCompat.getDrawable(
                resources, R.drawable.btn_round_border_transparent_unselect, null
            )
            btn_loan.background = ResourcesCompat.getDrawable(
                resources, R.drawable.btn_round_border_transparent_unselect, null
            )
            btn_credit.background = ResourcesCompat.getDrawable(
                resources, R.drawable.btn_round_border_transparent_unselect, null
            )
            getAccuntBalanceCasa()
        }
        btn_loan.setOnClickListener {
            accountFlag = "L"
            btn_loan.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
            btn_fdr_dps.setTextColor(
                ContextCompat.getColor(
                    requireActivity(), R.color.black
                )
            )
            btn_casa.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
            btn_credit.setTextColor(
                ContextCompat.getColor(
                    requireActivity(), R.color.black
                )
            )

            btn_loan.background = ResourcesCompat.getDrawable(
                resources, R.drawable.btn_round_border_ac_info_select, null
            )
            btn_fdr_dps.background = ResourcesCompat.getDrawable(
                resources, R.drawable.btn_round_border_transparent_unselect, null
            )
            btn_casa.background = ResourcesCompat.getDrawable(
                resources, R.drawable.btn_round_border_transparent_unselect, null
            )
            btn_credit.background = ResourcesCompat.getDrawable(
                resources, R.drawable.btn_round_border_transparent_unselect, null
            )
            getAccuntBalanceCasa()
        }
        btn_credit.setOnClickListener {
            accountFlag = "C"
            btn_credit.setTextColor(
                ContextCompat.getColor(
                    requireActivity(), R.color.white
                )
            )
            btn_fdr_dps.setTextColor(
                ContextCompat.getColor(
                    requireActivity(), R.color.black
                )
            )
            btn_casa.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
            btn_loan.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))

            btn_credit.background = ResourcesCompat.getDrawable(
                resources, R.drawable.btn_round_border_ac_info_select, null
            )
            btn_fdr_dps.background = ResourcesCompat.getDrawable(
                resources, R.drawable.btn_round_border_transparent_unselect, null
            )
            btn_loan.background = ResourcesCompat.getDrawable(
                resources, R.drawable.btn_round_border_transparent_unselect, null
            )
            btn_casa.background = ResourcesCompat.getDrawable(
                resources, R.drawable.btn_round_border_transparent_unselect, null
            )
            getAccuntBalanceCasa()
        }


        ivClose.setOnClickListener { dialog.dismiss() }

        // dialog.setContentView(bottomSheet)
        dialog.show()
        dialog.window!!.attributes = lp

    }

    private fun soureceAccountBalance() {
        val model = AccountRequestModel()
        model.mailId = Encript_Parameter.getRsa_encrypt(globalVariable.mailId)
        model.sessionId = Encript_Parameter.getRsa_encrypt(globalVariable.sessionId)
        model.companyCode = Encript_Parameter.getRsa_encrypt(globalVariable.companycode)
        model.currentDate = Encript_Parameter.getRsa_encrypt("")
        model.accountNo = Encript_Parameter.getRsa_encrypt(globalVariable.primaryAc)
        model.authorization = globalVariable.token
        accountViewModel.soureceAccountBalance(HeaderData.headerWelcome(globalVariable), model)
    }

    private fun getAccuntBalanceCasa() {
        pDialog.show()
        val model = AccountRequestModel()
        model.mailId = Encript_Parameter.getRsa_encrypt(globalVariable.mailId)
        model.sessionId = Encript_Parameter.getRsa_encrypt(globalVariable.sessionId)
        model.companyCode = Encript_Parameter.getRsa_encrypt(globalVariable.companycode)
        model.currentDate = Encript_Parameter.getRsa_encrypt("")
        model.accat = Encript_Parameter.getRsa_encrypt(accountFlag)
        model.authorization = globalVariable.token
        accountViewModel.getAccuntBalanceCasa(HeaderData.headerWelcome(globalVariable), model)
    }

    private fun getAccuntBalanceCasaList() {
        val model = AccountRequestModel()
        model.mailId = Encript_Parameter.getRsa_encrypt(globalVariable.mailId)
        model.sessionId = Encript_Parameter.getRsa_encrypt(globalVariable.sessionId)
        model.companyCode = Encript_Parameter.getRsa_encrypt(globalVariable.companycode)
        model.currentDate = Encript_Parameter.getRsa_encrypt("")
        model.accat = Encript_Parameter.getRsa_encrypt(accountFlag)
        model.authorization = globalVariable.token
        accountViewModel.getAccuntBalanceCasaList(HeaderData.headerWelcome(globalVariable), model)
    }

    private fun dashboardMenu() {
        pDialog.show()
        val model = AccountRequestModel()
        model.mailId = Encript_Parameter.getRsa_encrypt(globalVariable.mailId)
        model.hardCode = Encript_Parameter.getRsa_encrypt("DM")
        model.authorization = globalVariable.token
        menuViewModel.dashboardMenu(HeaderData.headerHome(globalVariable), model)
    }

    private fun fontSet() {
        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            stringUnderline(getString(R.string.all_account_info_bangla), tv_all_acinfo)
            // tv_all_acinfo.text = getString(R.string.all_account_info_bangla)
            btnTapforBalance.text = getString(R.string.btn_tap_for_balance_bangla)
            tvWelcome.text = getString(R.string.welcome_bangla)
        }

    }


    private fun nanoLoanEligibilityCheck(){

    }

    fun nanoLoanObserveViewModel() {

    }


}