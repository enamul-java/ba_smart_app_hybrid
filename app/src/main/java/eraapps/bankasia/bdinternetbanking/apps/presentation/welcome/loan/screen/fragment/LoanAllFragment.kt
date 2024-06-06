package eraapps.bankasia.bdinternetbanking.apps.view.welcome.loan.fragmentn

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog
import eraapps.bankasia.bdinternetbanking.apps.common.data.HeaderData
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.adapter.LoanListAdaptar
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanResultDataModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.screen.LoanDisburseActivity
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.view_model.LoanViewModel
import eraapps.bankasia.bdinternetbanking.apps.util.CustomAlert
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable


class LoanAllFragment : Fragment() {
    private lateinit var mAdapter: LoanListAdaptar
    private lateinit var all_loan_recyclerview: RecyclerView
    var loanList: ArrayList<LoanResultDataModel> = ArrayList<LoanResultDataModel>()

   private lateinit var globalVariable: GlobalVariable
    private lateinit var loanViewModel: LoanViewModel
    private lateinit var pDialog: SweetAlertDialog
    private  lateinit var itemSelected: LoanResultDataModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_loan_all, container, false)
        all_loan_recyclerview = view.findViewById(R.id.all_loan_recyclerview)


        globalVariable = requireActivity().applicationContext as GlobalVariable
        loanViewModel = ViewModelProvider(this).get(LoanViewModel::class.java)
        pDialog = CustomAlert.showProgressDialog(activity,globalVariable.languageCode)



        val mLayoutManager = LinearLayoutManager(activity)
        all_loan_recyclerview.layoutManager = mLayoutManager
        all_loan_recyclerview.itemAnimator = DefaultItemAnimator()



        if (!CustomAlert.isOnline(activity)) {
            CustomAlert.showInternetConnectionMessage(
                activity,
              "  globalVariable.languageCode"
            )
        } else {
            loanResult()
        }

        observeViewModel();

        return view
    }

    fun observeViewModel() {

        activity?.let {
            loanViewModel.error_response.observe(it) {
                it?.let {
                    pDialog.dismiss()

                    CustomAlert.showErrorMessage(activity,
                        it.outMessage,
                        globalVariable.languageCode)
                }
            }
        }

        activity?.let {
            loanViewModel.resultResponse.observe(it) { it ->
                it?.let {

                    pDialog.dismiss()

                    loanList = it as ArrayList<LoanResultDataModel>
                    globalVariable.loanList = loanList


                    mAdapter =
                        LoanListAdaptar(
                            loanList,
                            object : LoanListAdaptar.OnItemClickListener {
                                override fun onItemClick(item: LoanResultDataModel) {


                                    if(item.resultflag == "A"){
                                        itemSelected = item
                                        globalVariable.loanItemSelected = item

                                        Log.e("item----", item.toString() )

                                        val intent = Intent(requireActivity(), LoanDisburseActivity::class.java)
                                        startActivity(intent)

                                    }else if(item.resultflag == "D"){
                                        CustomAlert.showErrorMessage(requireActivity(),"Loan is Already Disburse Completed.",globalVariable.languageCode)
                                    }else{
                                        CustomAlert.showErrorMessage(requireActivity(),"Loan is Not approved.",globalVariable.languageCode)
                                    }

                                    //disburseLoanExecute()

                                }
                            },
                            "ALL"
                        )

                    all_loan_recyclerview.adapter = mAdapter
                    // mAdapter.notifyDataSetChanged()

                }
            }
        }


        activity?.let {
            loanViewModel.disburseExecuteResponse.observe(it) {
                it?.let {
                    pDialog.dismiss()


                    CustomAlert.showErrorMessage(activity,
                        it.outMessage,
                        globalVariable.languageCode)
                }
            }
        }
    }


    private fun loanResult() {
        pDialog.show()
        var model = LoanModel()
        model.authorization = globalVariable.token
        model.customerCode = globalVariable.customerCode
        this.let { it1 -> loanViewModel.loanResult(HeaderData.headerWelcome(globalVariable),model) }
    }

    private fun disburseLoanExecute() {
        pDialog.show()
        var model = LoanModel()
        model.authorization = globalVariable.token
        model.mailId = globalVariable.mailId
        model.sessionId = globalVariable.sessionId
        model.customerCode = globalVariable.customerCode
        model.creditScoreId = itemSelected.customer_id.toString()
        model.receiverAccountNo = "04934004459"
        model.amount= "25000"
        model.emi = "2000"
        model.month = "2"
        model.loanId = "123456"
        model.sanctionDate = "27/05/2023"
        model.remarks = "Test Remarks"
        this.let { it1 -> loanViewModel.disburseLoanExecute(HeaderData.headerWelcome(globalVariable),model) }
    }
}