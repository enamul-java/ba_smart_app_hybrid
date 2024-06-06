package eraapps.bankasia.bdinternetbanking.apps.view.welcome.loan.fragmentn

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.adapter.LoanListAdaptar
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanResultDataModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.screen.LoanDisburseActivity
import eraapps.bankasia.bdinternetbanking.apps.util.CustomAlert

import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable


class LoanApprovedFragment : Fragment() {
    private lateinit var approveAdapter: LoanListAdaptar
    private lateinit var approved_loan_recyclerview: RecyclerView

    var loanList: ArrayList<LoanResultDataModel> = ArrayList<LoanResultDataModel>()

    private lateinit var globalVariable: GlobalVariable
    private  lateinit var itemSelected: LoanResultDataModel
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_loan_approved, container, false)

        globalVariable = requireActivity().applicationContext as GlobalVariable

        approved_loan_recyclerview = view.findViewById(R.id.approved_loan_recyclerview)

       // loanList.clear()
      //  loanList.add(Loan("20,000.00 BDT", "22/11/2022 at 09.58 AM", "Personal Loan", "A"))
      //  loanList.add(Loan("50,000.00 BDT", "25/11/2022 at 11.40 PM", "Personal Loan", "A"))


        for(i in 0 until  globalVariable.loanList.size){

            if ( globalVariable.loanList[i].resultflag.toString().startsWith("A")||globalVariable.loanList[i].resultflag.toString().startsWith("D")) {

                var lm = LoanResultDataModel(
                    globalVariable.loanList[i].applied_amount,
                    globalVariable.loanList[i].applied_date,
                    globalVariable.loanList[i].applied_for,
                    globalVariable.loanList[i].contact_number,
                    globalVariable.loanList[i].customer_id,
                    globalVariable.loanList[i].customer_name,
                    globalVariable.loanList[i].result,
                    globalVariable.loanList[i].resultflag,
                    globalVariable.loanList[i].approved_amount,
                    globalVariable.loanList[i].tenor,
                    globalVariable.loanList[i].account_number,
                )
                loanList.add(lm)
            }
        }

        val mLayoutManager = LinearLayoutManager(activity)
        approved_loan_recyclerview.layoutManager = mLayoutManager
        approved_loan_recyclerview.itemAnimator = DefaultItemAnimator()

        approveAdapter =
            LoanListAdaptar(
                loanList,

                object : LoanListAdaptar.OnItemClickListener {
                    override fun onItemClick(item: LoanResultDataModel) {
                        if(item.resultflag == "A"){
                            itemSelected = item
                            globalVariable.loanItemSelected = item

                            val intent = Intent(requireActivity(), LoanDisburseActivity::class.java)
                            startActivity(intent)

                        }else if(item.resultflag == "D"){
                            CustomAlert.showErrorMessage(requireActivity(),"Loan is Already Disburse Completed.",globalVariable.languageCode)
                        }else{
                            CustomAlert.showErrorMessage(requireActivity(),"Loan is Not approved.",globalVariable.languageCode)
                        }
                    }
                },
                "A"
            )

        approved_loan_recyclerview.adapter = approveAdapter
      //  mAdapter.notifyDataSetChanged()



        return view
    }

}