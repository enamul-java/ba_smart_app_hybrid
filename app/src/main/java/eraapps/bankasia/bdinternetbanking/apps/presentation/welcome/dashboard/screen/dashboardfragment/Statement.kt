package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.dashboard.screen.dashboardfragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emon.raihan.dynamicutility.util.CustomDailog
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import eraapps.bankasia.bdinternetbanking.apps.BuildConfig
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog
import eraapps.bankasia.bdinternetbanking.apps.common.Constants.BASE_URL
import eraapps.bankasia.bdinternetbanking.apps.common.data.HeaderData
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.AccountStatementDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountListDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.CodeDesOptions
import eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar.StatementListAdaptar
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.common.view_model.AccountViewModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.dashboard.screen.WelcomeDashboardActivity
import eraapps.bankasia.bdinternetbanking.apps.util.*
import eraapps.bankasia.bdinternetbanking.apps.util.download.DownloadAndOpen
import eraapps.bankasia.bdinternetbanking.apps.util.download.DownloadFineName
import eraapps.bankasia.bdinternetbanking.apps.util.download.DownloadState
import eraapps.bankasia.bdinternetbanking.apps.util.download.DownloadUrl

@AndroidEntryPoint
class Statement : Fragment() {
    private lateinit var globalVariable: GlobalVariable
    private lateinit var pDialog: SweetAlertDialog
    private lateinit var mAdapter: StatementListAdaptar


    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_download: ImageView

    private lateinit var sp_account_value: MaterialAutoCompleteTextView
    private lateinit var et_from_date: TextInputEditText
    private lateinit var et_to_date: TextInputEditText
    private lateinit var btn_search: Button
    private lateinit var btn_today: Button
    private lateinit var btnLastWeek: Button
    private lateinit var btnLastMonth: Button
    private lateinit var statetment_param_layout: LinearLayout
    private lateinit var layout_transaction_details: LinearLayout
    private lateinit var statement_recyclerview: RecyclerView

    private lateinit var account_input: TextInputLayout
    private lateinit var from_date_input: TextInputLayout
    private lateinit var to_date_input: TextInputLayout
    private lateinit var tv_or: TextView


    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList()
    var acStatementList: ArrayList<AccountStatementDto> = ArrayList()
    var accountNo = ""
    var fromDate = ""
    var toDate = ""

    var transactionDate = ""
    var checkNo = ""
    var remarks = ""
    var debtAmount = ""
    var creditAmount = ""
    var availaleBalance = ""

    val accountViewModel: AccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_statement, container, false)
        globalVariable = requireActivity().applicationContext as GlobalVariable
        pDialog = CustomAlert.showProgressDialog(requireActivity(), globalVariable.languageCode)
        toolbar = view.findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_download = toolbar.findViewById(R.id.iv_header_download)

        sp_account_value = view.findViewById(R.id.sp_account_value)
        et_from_date = view.findViewById(R.id.et_from_date)
        et_to_date = view.findViewById(R.id.et_to_date)
        btn_search = view.findViewById(R.id.btn_search)
        btn_today = view.findViewById(R.id.btn_today)
        btnLastWeek = view.findViewById(R.id.btnLastWeek)
        btnLastMonth = view.findViewById(R.id.btnLastMonth)
        statetment_param_layout = view.findViewById(R.id.statetment_param_layout)
        layout_transaction_details = view.findViewById(R.id.layout_transaction_details)
        statement_recyclerview = view.findViewById(R.id.statement_recyclerview)

        account_input = view.findViewById(R.id.account_input)
        from_date_input = view.findViewById(R.id.from_date_input)
        to_date_input = view.findViewById(R.id.to_date_input)
        tv_or = view.findViewById(R.id.tv_or)

        toolbar_title.text = getString(R.string.statement)

        iv_header_back.setOnClickListener {
            val intent = Intent(requireActivity(), WelcomeDashboardActivity::class.java)

            if (layout_transaction_details.isVisible) {
                layout_transaction_details.visibility = View.GONE
                statetment_param_layout.visibility = View.VISIBLE
            } else {
                CustomActivityClear.doClearActivity(intent, requireActivity())
            }

        }

        //  iv_header_download.visibility = View.GONE

        CustomDailog.verifyStoragePermissions(requireActivity())
        CustomDailog.requestPermission(requireActivity())


        sp_account_value.setOnItemClickListener { parent, arg1, position, id ->
            if (position > 0) {
                accountNo = codeDesOptions[position].code.toString()
            } else {
                accountNo = ""
            }
        }

        if (globalVariable.sourceAcStatementList.isEmpty()) {
            if (!NetworkUtil.isOnline(requireActivity())) {
                CustomAlert.showInternetConnectionMessage(
                    requireActivity(),
                    globalVariable.languageCode
                )
            } else {
                sourceAcforStatement()
            }
        } else {
            codeDesOptions.add(CodeDesOptions("Select Account", ""))
            for (i in 0 until globalVariable.sourceAcStatementList.size) {
                codeDesOptions.add(
                    CodeDesOptions(
                        globalVariable.sourceAcStatementList[i].accountTitle,
                        globalVariable.sourceAcStatementList[i].accountNumber,
                    )
                )
            }

            val arrayAdapter =
                ArrayAdapter(requireActivity(), R.layout.dropdown_item, codeDesOptions)
            sp_account_value.setAdapter(arrayAdapter)
            /*if (codeDesOptions.size > 1) {
                sp_account_value.setText(sp_account_value.adapter.getItem(1).toString(), false)
                accountNo = codeDesOptions[1].desc.toString()
            } else {
                sp_account_value.setText(sp_account_value.adapter.getItem(0).toString(), false)
                accountNo = ""
            }*/
            Log.d("accountNumber", globalVariable.statmentSelectAc)
            Log.d("codeDesOptions", codeDesOptions.size.toString())

            try {
                // val accountNumber = requireActivity().intent.getStringExtra("accountNumber")!!
                val accountNumber = globalVariable.statmentSelectAc
                for (i in 0 until codeDesOptions.size) {
                    Log.d("code", codeDesOptions[i].code.toString())
                    if (codeDesOptions[i].code.toString() == accountNumber) {
                        sp_account_value.setText(
                            sp_account_value.adapter.getItem(i).toString(),
                            false
                        )
                        accountNo = codeDesOptions[i].code.toString()
                        globalVariable.statmentSelectAc = "0"
                        break
                    } else {
                        if (codeDesOptions.size > 1) {
                            sp_account_value.setText(
                                sp_account_value.adapter.getItem(1).toString(), false
                            )
                            accountNo = codeDesOptions[1].code.toString()
                        } else {
                            sp_account_value.setText(
                                sp_account_value.adapter.getItem(0).toString(), false
                            )
                            accountNo = ""
                        }
                    }

                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }


        }

        iv_header_download.setOnClickListener {
            if (!ValidationUtil.editTextValidation(accountNo)) {
                sp_account_value.requestFocus()
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.sourcAccountBangla,
                        globalVariable.languageCode
                    )

                } else {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.sourcAccount,
                        globalVariable.languageCode
                    )
                }
            } else if (!ValidationUtil.editTextValidation(et_from_date.text.toString())) {
                et_from_date.requestFocus()
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.fromDatebangla,
                        globalVariable.languageCode
                    )

                } else {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.fromDate,
                        globalVariable.languageCode
                    )
                }
            } else if (!ValidationUtil.editTextValidation(et_to_date.text.toString())) {
                et_to_date.requestFocus()
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.toDatebangla,
                        globalVariable.languageCode
                    )

                } else {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.toDate,
                        globalVariable.languageCode
                    )
                }
            } else if (!NetworkUtil.isOnline(requireActivity())) {
                CustomAlert.showInternetConnectionMessage(
                    requireActivity(),
                    globalVariable.languageCode
                )
            } else {
                accountStatementReport()
            }
        }

        fromDate = DateUtil.geCurrendDate()
        toDate = DateUtil.geCurrendDate()
        et_from_date.setText(fromDate)
        et_to_date.setText(toDate)


        et_from_date.setOnClickListener { CustomDailog.fromDate(requireActivity(), et_from_date) }
        et_to_date.setOnClickListener { CustomDailog.toDate(requireActivity(), et_to_date) }

        btnLastMonth.setOnClickListener {
            fromDate = DateUtil.getCommission_date(30)
            toDate = DateUtil.geCurrendDate()

            et_from_date.setText(fromDate)
            et_to_date.setText(toDate)
            if (!ValidationUtil.editTextValidation(accountNo)) {
                sp_account_value.requestFocus()
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.sourcAccountBangla,
                        globalVariable.languageCode
                    )

                } else {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.sourcAccount,
                        globalVariable.languageCode
                    )
                }
            } else if (!NetworkUtil.isOnline(requireActivity())) {
                CustomAlert.showInternetConnectionMessage(
                    requireActivity(),
                    globalVariable.languageCode
                )
            } else {

                accountStatement()


            }

        }

        btnLastWeek.setOnClickListener {
            fromDate = DateUtil.getCommission_date(7)
            toDate = DateUtil.geCurrendDate()

            et_from_date.setText(fromDate)
            et_to_date.setText(toDate)
            if (!ValidationUtil.editTextValidation(accountNo)) {
                sp_account_value.requestFocus()
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.sourcAccountBangla,
                        globalVariable.languageCode
                    )

                } else {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.sourcAccount,
                        globalVariable.languageCode
                    )
                }
            } else if (!NetworkUtil.isOnline(requireActivity())) {
                CustomAlert.showInternetConnectionMessage(
                    requireActivity(),
                    globalVariable.languageCode
                )
            } else {

                accountStatement()


            }
        }

        btn_today.setOnClickListener {
            fromDate = DateUtil.geCurrendDate()
            toDate = DateUtil.geCurrendDate()

            et_from_date.setText(fromDate)
            et_to_date.setText(toDate)
            if (!ValidationUtil.editTextValidation(accountNo)) {
                sp_account_value.requestFocus()
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.sourcAccountBangla,
                        globalVariable.languageCode
                    )

                } else {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.sourcAccount,
                        globalVariable.languageCode
                    )
                }
            } else if (!NetworkUtil.isOnline(requireActivity())) {
                CustomAlert.showInternetConnectionMessage(
                    requireActivity(),
                    globalVariable.languageCode
                )
            } else {

                accountStatement()


            }

        }

        btn_search.setOnClickListener {

            if (!ValidationUtil.editTextValidation(accountNo)) {
                sp_account_value.requestFocus()
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.sourcAccountBangla,
                        globalVariable.languageCode
                    )

                } else {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.sourcAccount,
                        globalVariable.languageCode
                    )
                }
            } else if (!ValidationUtil.editTextValidation(et_from_date.text.toString())) {
                et_from_date.requestFocus()
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.fromDatebangla,
                        globalVariable.languageCode
                    )

                } else {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.fromDate,
                        globalVariable.languageCode
                    )
                }
            } else if (!ValidationUtil.editTextValidation(et_to_date.text.toString())) {
                et_to_date.requestFocus()
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.toDatebangla,
                        globalVariable.languageCode
                    )

                } else {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.toDate,
                        globalVariable.languageCode
                    )
                }
            } else if (!NetworkUtil.isOnline(requireActivity())) {
                CustomAlert.showInternetConnectionMessage(
                    requireActivity(),
                    globalVariable.languageCode
                )
            } else {
                accountStatement()
            }
        }


        observeEvents()
        fontset()
        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeEvents() {

        accountViewModel.sourceAcforStatement_response.observe(viewLifecycleOwner) {
            if ("0" == it.outCode) {
                // pDialog.dismiss()
                sourceAcforStatementList()

            } else if ("2" == it.outCode) {
                pDialog.dismiss()
                CustomActivityClear.forceLogout(requireActivity(), it.outMessage.toString())

            } else {
                pDialog.dismiss()
                CustomAlert.showErrorMessage(
                    requireActivity(),
                    it.outMessage,
                    globalVariable.languageCode
                )
            }

        }
        accountViewModel.accountStatement_response.observe(viewLifecycleOwner) {
            if ("0" == it.outCode) {
                ///   pDialog.dismiss()
                accountStatementList()
            } else if ("2" == it.outCode) {
                pDialog.dismiss()
                CustomActivityClear.forceLogout(requireActivity(), it.outMessage.toString())

            } else {
                pDialog.dismiss()
                CustomAlert.showErrorMessage(
                    requireActivity(),
                    it.outMessage,
                    globalVariable.languageCode
                )
            }

        }

        accountViewModel.accountStatementReport_response.observe(viewLifecycleOwner) {
            if ("0" == it.outCode) {
                if(BuildConfig.BUILD_TYPE.equals("debug")) {
                    pDialog.show()
                    val urlEndPoint = DownloadUrl.getBaseUrl(accountNo)
                    val url =
                        urlEndPoint + "mailId=" + globalVariable.mailId +
                                "&sessionId=" + globalVariable.sessionId +
                                "&fromDate=" + et_from_date.text.toString() +
                                "&toDate=" + et_to_date.text.toString()
                    try {
                        var type = DownloadState.DownLoadType.PDF;
                        var fileName = DownloadFineName.getFileName("bank_asi_ac_statement", type);

                        DownloadAndOpen(fileName,requireActivity(),url, type,
                            object : DownloadState {
                                override fun downloadProgress(progress: Int) {
                                    //Download Progress
                                }
                                override fun downloadStatus(state: DownloadState.DownLoadState?) {
                                    //Download Status
                                    if(state!!.equals(DownloadState.DownLoadState.START)){
                                        //pDialog.show()
                                    }else if(state!!.equals(DownloadState.DownLoadState.FINISH)){
                                        pDialog.dismiss()
                                    }
                                }

                            }).download()
                    }catch (e:java.lang.Exception){
                        pDialog.dismiss()
                        Log.e("Download", "Statement Download Problem : "+e.toString())
                    }
                }

                if(BuildConfig.BUILD_TYPE.equals("release")) {
                    pDialog.dismiss()
                    val sub = BASE_URL.substring(0, BASE_URL.length - 14)
                    val url =
                        sub + "Bank-asia-smart-app/AccountStatementDownloadApi?mailId=" +
                                globalVariable.mailId + "&sessionId=" +
                                globalVariable.sessionId + "&fromDate=" +
                                et_from_date.text.toString() + "&toDate=" +
                                et_to_date.text.toString()
                    val uriUrl = Uri.parse(url)
                    val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
                    startActivity(launchBrowser)
                }

            } else if ("2" == it.outCode) {
                pDialog.dismiss()
                CustomActivityClear.forceLogout(requireActivity(), it.outMessage.toString())

            } else {
                pDialog.dismiss()
                CustomAlert.showErrorMessage(
                    requireActivity(),
                    it.outMessage,
                    globalVariable.languageCode
                )
            }

        }

        accountViewModel.accountStatementListResponse.observe(viewLifecycleOwner) {

            pDialog.dismiss()
            acStatementList.clear()
            if (it.isNotEmpty()) {
                acStatementList = it as ArrayList<AccountStatementDto>
                statetment_param_layout.visibility = View.GONE
                layout_transaction_details.visibility = View.VISIBLE
                iv_header_download.visibility = View.VISIBLE
                val mLayoutManager =
                    LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                statement_recyclerview.layoutManager = mLayoutManager
                statement_recyclerview.itemAnimator = DefaultItemAnimator()

                mAdapter =
                    StatementListAdaptar(
                        acStatementList,
                        object : StatementListAdaptar.OnItemClickListener {
                            override fun onItemClick(item: AccountStatementDto) {
                                transactionDate = item.transactionDate
                                checkNo = item.checkNo
                                remarks = item.remarks
                                debtAmount = item.debtAmount
                                creditAmount = item.creditAmount
                                availaleBalance = item.availaleBalance
                                showDialogAccount()

                            }
                        })

                statement_recyclerview.adapter = mAdapter
                mAdapter.notifyDataSetChanged()


            }else{
                pDialog.dismiss()
                CustomAlert.showErrorMessage(requireActivity(), "No Transactions History found.", globalVariable.languageCode)
            }


        }

        accountViewModel.sourceAcforStatementListResponse.observe(viewLifecycleOwner) {

            pDialog.dismiss()
            globalVariable.sourceAcStatementList.clear()
            if (it.isNotEmpty()) {
                globalVariable.sourceAcStatementList = (it as ArrayList<SourceAccountListDto>?)!!
                codeDesOptions.add(CodeDesOptions("Select Account", ""))
                for (i in 0 until globalVariable.sourceAcStatementList.size) {
                    codeDesOptions.add(
                        CodeDesOptions(
                            globalVariable.sourceAcStatementList[i].accountTitle,
                            globalVariable.sourceAcStatementList[i].accountNumber,
                        )
                    )
                }

                val arrayAdapter =
                    ArrayAdapter(requireActivity(), R.layout.dropdown_item, codeDesOptions)
                sp_account_value.setAdapter(arrayAdapter)
                /*  val accountNumber = requireActivity().intent.getStringExtra("accountNumber")!!

                 if (codeDesOptions.size > 1) {
                      sp_account_value.setText(sp_account_value.adapter.getItem(1).toString(), false)
                      accountNo = codeDesOptions[1].desc.toString()
                  } else {
                      sp_account_value.setText(sp_account_value.adapter.getItem(0).toString(), false)
                      accountNo = ""
                  }*/
                Log.d("accountNumber", globalVariable.statmentSelectAc)
                Log.d("codeDesOptions", codeDesOptions.size.toString())
                try {
                    // val accountNumber = requireActivity().intent.getStringExtra("accountNumber")!!
                    val accountNumber = globalVariable.statmentSelectAc
                    for (i in 0 until codeDesOptions.size) {
                        Log.d("code", codeDesOptions[i].code.toString())
                        Log.d("code", codeDesOptions[i].desc.toString())
                        if (codeDesOptions[i].code == accountNumber) {
                            sp_account_value.setText(
                                sp_account_value.adapter.getItem(i).toString(),
                                false
                            )
                            accountNo = codeDesOptions[i].code.toString()
                            globalVariable.statmentSelectAc = "0"
                            break
                        } else {
                            if (codeDesOptions.size > 1) {
                                sp_account_value.setText(
                                    sp_account_value.adapter.getItem(1).toString(), false
                                )
                                accountNo = codeDesOptions[1].code.toString()
                            } else {
                                sp_account_value.setText(
                                    sp_account_value.adapter.getItem(0).toString(), false
                                )
                                accountNo = ""
                            }
                        }

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }


        }

        accountViewModel.errorResponse.observe(viewLifecycleOwner) {
            pDialog.dismiss()
            CustomAlert.showErrorMessage(requireActivity(), it.message, globalVariable.languageCode)

        }
    }


    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>, grantResults: IntArray,
    ) {

        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                // PERMISSION GRANTED
            } else {
                // PERMISSION NOT GRANTED
                Toast.makeText(
                    requireActivity(),
                    "Cannot write images to external storage",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        //  super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            REQUEST_EXTERNAL_STORAGE -> {
//
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.isEmpty()
//                    || grantResults[0] != PackageManager.PERMISSION_GRANTED
//                ) {
//                    Toast.makeText(
//                        requireActivity(),
//                        "Cannot write images to external storage",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
    }


    private fun showDialogAccount() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.transaction_details_dialog)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT

        val tv_trns_details_level = dialog.findViewById(R.id.tv_trns_details_level) as TextView
        val ivClose = dialog.findViewById(R.id.ivClose) as ImageView
        val tv_source_account_no = dialog.findViewById(R.id.tv_source_account_no) as TextView
        val tv_debit_amount = dialog.findViewById(R.id.tv_debit_amount) as TextView
        val tv_credit_amount = dialog.findViewById(R.id.tv_credit_amount) as TextView
        val tv_date = dialog.findViewById(R.id.tv_date) as TextView
        val tv_balance = dialog.findViewById(R.id.tv_balance) as TextView
        val tv_reference = dialog.findViewById(R.id.tv_reference) as TextView
        val tv_particulars = dialog.findViewById(R.id.tv_particulars) as TextView

        val tv_source_account_no_label =
            dialog.findViewById(R.id.tv_source_account_no_label) as TextView

        if (accountNo.startsWith("101")) {
            tv_source_account_no.text = sp_account_value.text.toString()
            tv_source_account_no_label.text = getString(R.string.source_card_no)
        } else {
            tv_source_account_no.text = accountNo
            tv_source_account_no_label.text = getString(R.string.source_account_no)
        }

        if (accountNo.startsWith("101VI")) {
            //  tv_source_account_no.text = sp_account_value.text.toString()
            tv_debit_amount.text = ValidationUtil.commaSeparateAmountUSD(debtAmount)
            tv_credit_amount.text = ValidationUtil.commaSeparateAmountUSD(creditAmount)
            tv_date.text = transactionDate
            tv_balance.text = ValidationUtil.commaSeparateAmountUSD(availaleBalance)
            tv_reference.text = checkNo
            tv_particulars.text = remarks

        } else {
            //  tv_source_account_no.text = accountNo
            tv_debit_amount.text = ValidationUtil.commaSeparateAmount(debtAmount)
            tv_credit_amount.text = ValidationUtil.commaSeparateAmount(creditAmount)
            tv_date.text = transactionDate
            tv_balance.text = ValidationUtil.commaSeparateAmount(availaleBalance)
            tv_reference.text = checkNo
            tv_particulars.text = remarks

        }



        ivClose.setOnClickListener {
            dialog.cancel()
        }



        dialog.show()
        dialog.window!!.attributes = lp
    }

    private fun sourceAcforStatement() {
        pDialog.show()
        val model = AccountRequestModel()
        model.mailId = Encript_Parameter.getRsa_encrypt(globalVariable.mailId)
        model.sessionId = Encript_Parameter.getRsa_encrypt(globalVariable.sessionId)
        model.companyCode = Encript_Parameter.getRsa_encrypt(globalVariable.companycode)
        model.currentDate = Encript_Parameter.getRsa_encrypt("")
        model.accat = Encript_Parameter.getRsa_encrypt("")
        model.authorization = globalVariable.token
        accountViewModel.sourceAcforStatement(HeaderData.headerWelcome(globalVariable), model)
    }

    private fun sourceAcforStatementList() {
        val model = AccountRequestModel()
        model.mailId = Encript_Parameter.getRsa_encrypt(globalVariable.mailId)
        model.sessionId = Encript_Parameter.getRsa_encrypt(globalVariable.sessionId)
        model.companyCode = Encript_Parameter.getRsa_encrypt(globalVariable.companycode)
        model.currentDate = Encript_Parameter.getRsa_encrypt("")
        model.accat = Encript_Parameter.getRsa_encrypt("")
        model.authorization = globalVariable.token
        accountViewModel.sourceAcforStatementList(HeaderData.headerWelcome(globalVariable), model)
    }

    private fun accountStatement() {
        pDialog.show()
        val model = AccountRequestModel()
        model.mailId = Encript_Parameter.getRsa_encrypt(globalVariable.mailId)
        model.sessionId = Encript_Parameter.getRsa_encrypt(globalVariable.sessionId)
        model.companyCode = Encript_Parameter.getRsa_encrypt(globalVariable.companycode)
        model.accountNumber = Encript_Parameter.getRsa_encrypt(accountNo)
        model.fromDate = Encript_Parameter.getRsa_encrypt(et_from_date.text.toString())
        model.toDate = Encript_Parameter.getRsa_encrypt(et_to_date.text.toString())
        model.authorization = globalVariable.token
        accountViewModel.accountStatement(HeaderData.headerWelcome(globalVariable), model)
    }

    private fun accountStatementReport() {
        pDialog.show()
        val model = AccountRequestModel()
        model.mailId = Encript_Parameter.getRsa_encrypt(globalVariable.mailId)
        model.sessionId = Encript_Parameter.getRsa_encrypt(globalVariable.sessionId)
        model.companyCode = Encript_Parameter.getRsa_encrypt(globalVariable.companycode)
        model.accountNumber = Encript_Parameter.getRsa_encrypt(accountNo)
        model.fromDate = Encript_Parameter.getRsa_encrypt(et_from_date.text.toString())
        model.toDate = Encript_Parameter.getRsa_encrypt(et_to_date.text.toString())
        model.authorization = globalVariable.token
        accountViewModel.accountStatementReport(HeaderData.headerHome(globalVariable), model)
    }

    private fun accountStatementList() {
        pDialog.show()
        val model = AccountRequestModel()
        model.mailId = Encript_Parameter.getRsa_encrypt(globalVariable.mailId)
        model.sessionId = Encript_Parameter.getRsa_encrypt(globalVariable.sessionId)
        model.companyCode = Encript_Parameter.getRsa_encrypt(globalVariable.companycode)
        model.accountNumber = Encript_Parameter.getRsa_encrypt(accountNo)
        model.fromDate = Encript_Parameter.getRsa_encrypt(et_from_date.text.toString())
        model.toDate = Encript_Parameter.getRsa_encrypt(et_to_date.text.toString())
        model.authorization = globalVariable.token
        accountViewModel.accountStatementList(HeaderData.headerWelcome(globalVariable), model)
    }

    fun fontset() {
        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            toolbar_title.text = getString(R.string.statement_bangla)
            btn_today.text = getString(R.string.today_bangla)
            btnLastWeek.text = getString(R.string.last_week_bangla)
            btnLastMonth.text = getString(R.string.last_month_bangla)
            btn_search.text = getString(R.string.search_bangla)

            account_input.hint = getString(R.string.select_account_no_bangla)
            from_date_input.hint = getString(R.string.from_date_bangla)
            to_date_input.hint = getString(R.string.to_date_bangla)
            tv_or.text = getString(R.string.search_with_custom_date_range_bangla)
        }
    }
}