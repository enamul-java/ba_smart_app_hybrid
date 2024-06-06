package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.dashboard.screen.dashboardfragment.beneficiary_fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog
import eraapps.bankasia.bdinternetbanking.apps.common.Constants
import eraapps.bankasia.bdinternetbanking.apps.common.data.HeaderData
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OwnBankViewBeneficiaryDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar.OwnBankViewBeneficiaryAdapter
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CreditCardRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OTPRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OwnBankRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.common.view_model.OtpViewModel
import eraapps.bankasia.bdinternetbanking.apps.util.*

@AndroidEntryPoint
class OwnBankFragment : Fragment() {

    private lateinit var globalVariable: GlobalVariable
    private lateinit var pDialog: SweetAlertDialog

    // private lateinit var pDialog: KProgressHUD
    private lateinit var dialog: Dialog


    val otpViewModel: OtpViewModel by viewModels()


    private lateinit var own_bank_acc_RecyclerView: RecyclerView
    private lateinit var inputSearchAcc: EditText

    private lateinit var viewList: List<OwnBankViewBeneficiaryDto>
    private lateinit var adapter: OwnBankViewBeneficiaryAdapter

    private lateinit var beneficaryItem: OwnBankViewBeneficiaryDto

    var nickName = ""
    var mobileNo = ""
    var emailId = ""
    var status = ""
    var otpOutMessage = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_beneficiary_own_bank, container, false)
        globalVariable = requireActivity().applicationContext as GlobalVariable
        //loanViewModel = ViewModelProvider(this).get(LoanViewModel::class.java)
        pDialog = CustomAlert.showProgressDialog(requireActivity(), globalVariable.languageCode)
        // pDialog = CustomAlert.showProgressDialog1(requireActivity(), globalVariable.languageCode)
        own_bank_acc_RecyclerView = rootView.findViewById(R.id.own_bank_acc_RecyclerView)
        inputSearchAcc = rootView.findViewById(R.id.inputSearchAcc)

        own_bank_acc_RecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        own_bank_acc_RecyclerView.setHasFixedSize(true)

        if (!NetworkUtil.isOnline(requireActivity())) {
            CustomAlert.showInternetConnectionMessage(
                requireActivity(),
                globalVariable.languageCode
            )
        } else {
            viewBeneficiary()
        }



        observeEvents()

        return rootView
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeEvents() {


    }


    @SuppressLint("NotifyDataSetChanged")
    private fun showCards() {

        adapter = OwnBankViewBeneficiaryAdapter(
            viewList as ArrayList<OwnBankViewBeneficiaryDto>,
            object : OwnBankViewBeneficiaryAdapter.OnItemClickListener {
                override fun onItemClick(item: OwnBankViewBeneficiaryDto) {
                    Log.e("destinationAccount-->", item.destinationAccount)
                    beneficaryItem = item
                    if (item.oprMode == "D") {
                        sentOtp()
                    } else {
                        showDialogDestAcc()
                    }
                }
            }

        )
        own_bank_acc_RecyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        inputSearchAcc.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            @SuppressLint("NotifyDataSetChanged")
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter.filter.filter(text)
                adapter.notifyDataSetChanged()
            }
        })

    }

    private fun viewBeneficiary() {
    }

    private fun viewBeneficiaryList() {
    }

    private fun sentOtp() {
        pDialog.show()
        val model = OTPRequestModel()
        model.mailId = Encript_Parameter.getRsa_encrypt(globalVariable.mailId)
        model.sessionId = Encript_Parameter.getRsa_encrypt(globalVariable.sessionId)
        model.actFlg = Encript_Parameter.getRsa_encrypt("")
        model.userId = Encript_Parameter.getRsa_encrypt("")
        model.mobileNumber = Encript_Parameter.getRsa_encrypt("")
        model.customerCode = Encript_Parameter.getRsa_encrypt(globalVariable.customerCode)
        model.authorization = globalVariable.token
        otpViewModel.sendTransOtp(HeaderData.headerWelcome(globalVariable), model)
    }

    private fun beneficiaryDeleteExe(otp: String) {
    }


    private fun showDialogDestAcc() {
        dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.own_bank_update_dilog)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT

        val ivClose = dialog.findViewById(R.id.ivClose) as ImageView
        val btn_cancel = dialog.findViewById(R.id.btn_cancel) as AppCompatButton
        val btn_submit = dialog.findViewById(R.id.btn_submit) as AppCompatButton
        val tv_card_no = dialog.findViewById(R.id.tv_card_no) as TextView
        val tv_card_title = dialog.findViewById(R.id.tv_card_title) as TextView

        val tv_card_no_label = dialog.findViewById(R.id.tv_card_no_label) as TextView
        val tv_card_title_label = dialog.findViewById(R.id.tv_card_title_label) as TextView
        val tv_select_status = dialog.findViewById(R.id.tv_select_status) as TextView
        val tv_update_card_beneficiary_level =
            dialog.findViewById(R.id.tv_update_card_beneficiary_level) as TextView


        val tv_active = dialog.findViewById(R.id.tv_active) as TextView
        val tv_inactive = dialog.findViewById(R.id.tv_inactive) as TextView

        val et_nick_name_value = dialog.findViewById(R.id.et_nick_name_value) as TextInputEditText
        val et_mobile_no_value = dialog.findViewById(R.id.et_mobile_no_value) as TextInputEditText
        val et_email_id_value = dialog.findViewById(R.id.et_email_id_value) as TextInputEditText

        val nick_name_input = dialog.findViewById(R.id.nick_name_input) as TextInputLayout
        val mobile_no_input = dialog.findViewById(R.id.mobile_no_input) as TextInputLayout
        val email_id_input = dialog.findViewById(R.id.email_id_input) as TextInputLayout

        if (beneficaryItem.destinationAccount.startsWith("101")) {
            tv_card_no.text = beneficaryItem.cardNo
            tv_card_title.text = beneficaryItem.destinationAccountTitle

            if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                tv_card_no_label.text = getString(R.string.card_non_bangla)
                tv_card_title_label.text = getString(R.string.card_title_bangla)
            } else {
                tv_card_no_label.text = getString(R.string.card_no)
                tv_card_title_label.text = getString(R.string.card_title)
            }


        } else {
            tv_card_no.text = beneficaryItem.destinationAccount
            tv_card_title.text = beneficaryItem.destinationAccountTitle

            if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                tv_card_no_label.text = getString(R.string.account_no_bangla)
                tv_card_title_label.text = getString(R.string.account_title_bangla)
            } else {
                tv_card_no_label.text = getString(R.string.account_no)
                tv_card_title_label.text = getString(R.string.account_title)
            }
        }
        // Label bangla
        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            tv_active.text = getString(R.string.active_bangla)
            tv_inactive.text = getString(R.string.inactive_bangla)
            btn_submit.text = getString(R.string.submit_bangla)
            btn_cancel.text = getString(R.string.cancel_bangla)
            tv_select_status.text = getString(R.string.select_status_bangla)
            tv_update_card_beneficiary_level.text =
                getString(R.string.update_own_bank_beneficiary_bangla)

            nick_name_input.hint = getString(R.string.nick_name_bangla)
            mobile_no_input.hint = getString(R.string.mobile_number_bangla)
            email_id_input.hint = getString(R.string.email_bangla)
        }


        et_nick_name_value.setText(beneficaryItem.nickname.trim())
        et_mobile_no_value.setText(beneficaryItem.mobileNumber)
        et_email_id_value.setText(beneficaryItem.email)

        if (beneficaryItem.status == "Y") {
            status = "Y"
            tv_active.setTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.app_color
                )
            )
            tv_inactive.setTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.black
                )
            )
            tv_active.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.btn_round_border_transparent_select,
                null
            )
            tv_inactive.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.btn_round_border_transparent_unselect,
                null
            )
        } else {
            status = "N"
            tv_active.setTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.black
                )
            )
            tv_inactive.setTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.app_color
                )
            )
            tv_active.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.btn_round_border_transparent_unselect,
                null
            )
            tv_inactive.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.btn_round_border_transparent_select,
                null
            )
        }
        tv_active.setOnClickListener {
            status = "Y"
            tv_active.setTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.app_color
                )
            )
            tv_inactive.setTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.black
                )
            )
            tv_active.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.btn_round_border_transparent_select,
                null
            )
            tv_inactive.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.btn_round_border_transparent_unselect,
                null
            )

        }

        tv_inactive.setOnClickListener {
            status = "N"
            tv_active.setTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.black
                )
            )
            tv_inactive.setTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.app_color
                )
            )
            tv_active.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.btn_round_border_transparent_unselect,
                null
            )
            tv_inactive.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.btn_round_border_transparent_select,
                null
            )

        }

        btn_submit.setOnClickListener {
            if (beneficaryItem.destinationAccount.isEmpty()) {
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.destinationAccountBangla,
                        globalVariable.languageCode
                    )
                } else {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.destinationAccount,
                        globalVariable.languageCode
                    )
                }
            } else if (et_nick_name_value.text.toString().isEmpty()) {
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.nick_name_bangla,
                        globalVariable.languageCode
                    )
                } else {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.nick_name_english,
                        globalVariable.languageCode
                    )
                }
            } else if (!NetworkUtil.isOnline(requireActivity())) {
                CustomAlert.showInternetConnectionMessage(
                    requireActivity(),
                    globalVariable.languageCode
                )
            } else {
                nickName = et_nick_name_value.text.toString().trim()
                mobileNo = et_mobile_no_value.text.toString().trim()
                emailId = et_email_id_value.text.toString().trim()

                addBeneficiary()
            }
        }

        btn_cancel.setOnClickListener {
            dialog.cancel()
        }

        ivClose.setOnClickListener {
            dialog.cancel()
        }


        dialog.show()
        dialog.window!!.attributes = lp
    }

    private fun showDialogDestAccDelete() {
        dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.own_bank_delete_dilog)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT

        val ivClose = dialog.findViewById(R.id.ivClose) as ImageView
        val btn_cancel = dialog.findViewById(R.id.btn_cancel) as AppCompatButton
        val btn_submit = dialog.findViewById(R.id.btn_submit) as AppCompatButton
        val tv_card_no = dialog.findViewById(R.id.tv_card_no) as TextView
        val tv_card_title = dialog.findViewById(R.id.tv_card_title) as TextView
        val tv_otp_message = dialog.findViewById(R.id.tv_otp_message) as TextView

        val tv_card_no_label = dialog.findViewById(R.id.tv_card_no_label) as TextView
        val tv_card_title_label = dialog.findViewById(R.id.tv_card_title_label) as TextView
        val tv_delete_beneficiary_level =
            dialog.findViewById(R.id.tv_delete_beneficiary_level) as TextView


        val et_otp_value = dialog.findViewById(R.id.et_otp_value) as TextInputEditText
        val otp_input = dialog.findViewById(R.id.otp_input) as TextInputLayout

        tv_otp_message.text = otpOutMessage

        if (beneficaryItem.destinationAccount.startsWith("101")) {
            tv_card_no.text = beneficaryItem.cardNo
            tv_card_title.text = beneficaryItem.destinationAccountTitle

            if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                tv_card_no_label.text = getString(R.string.card_non_bangla)
                tv_card_title_label.text = getString(R.string.card_title_bangla)
            } else {
                tv_card_no_label.text = getString(R.string.card_no)
                tv_card_title_label.text = getString(R.string.card_title)
            }

        } else {
            tv_card_no.text = beneficaryItem.destinationAccount
            tv_card_title.text = beneficaryItem.destinationAccountTitle

            if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                tv_card_no_label.text = getString(R.string.account_no_bangla)
                tv_card_title_label.text = getString(R.string.account_title_bangla)
            } else {
                tv_card_no_label.text = getString(R.string.account_no)
                tv_card_title_label.text = getString(R.string.account_title)
            }
        }
        // Label bangla
        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            btn_submit.text = getString(R.string.submit_bangla)
            btn_cancel.text = getString(R.string.cancel_bangla)
            tv_delete_beneficiary_level.text = getString(R.string.delete_beneficiary_bangla)
            otp_input.hint = getString(R.string.otp_bangla)
        }



        btn_submit.setOnClickListener {
            if (beneficaryItem.destinationAccount.isEmpty()) {
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.destinationAccountBangla,
                        globalVariable.languageCode
                    )
                } else {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.destinationAccount,
                        globalVariable.languageCode
                    )
                }
            } else if (et_otp_value.text.toString().isEmpty()) {
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.otpbangla,
                        globalVariable.languageCode
                    )
                } else {
                    CustomAlert.showErrorMessage(
                        requireActivity(),
                        TextContants.otp,
                        globalVariable.languageCode
                    )
                }
            } else if (!NetworkUtil.isOnline(requireActivity())) {
                CustomAlert.showInternetConnectionMessage(
                    requireActivity(),
                    globalVariable.languageCode
                )
            } else {
                beneficiaryDeleteExe(et_otp_value.text.toString())
            }
        }

        btn_cancel.setOnClickListener {
            dialog.cancel()
        }

        ivClose.setOnClickListener {
            dialog.cancel()
        }


        dialog.show()
        dialog.window!!.attributes = lp
    }


    private fun addBeneficiary() {

    }
}