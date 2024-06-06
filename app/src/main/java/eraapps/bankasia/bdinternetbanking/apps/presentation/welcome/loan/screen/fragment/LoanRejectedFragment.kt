package eraapps.bankasia.bdinternetbanking.apps.view.welcome.loan.fragmentn

import android.annotation.SuppressLint
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

import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable


class LoanRejectedFragment : Fragment() {

    private lateinit var recjectAdapter: LoanListAdaptar
    private lateinit var reject_loan_recyclerview: RecyclerView

    var loanList: ArrayList<LoanResultDataModel> = ArrayList<LoanResultDataModel>()

    private lateinit var globalVariable: GlobalVariable

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_loan_rejected, container, false)

        globalVariable = requireActivity().applicationContext as GlobalVariable

        reject_loan_recyclerview = view.findViewById(R.id.reject_loan_recyclerview)


        for(i in 0 until  globalVariable.loanList.size){

            if ( globalVariable.loanList[i].resultflag.toString().startsWith("N") || globalVariable.loanList[i].resultflag.toString().startsWith("U")) {

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

      //  loanList.clear()
     //   loanList.add(Loan("30,000.00 BDT", "23/11/2022 at 10.24 PM", "Personal Loan", "R"))
       // loanList.add(Loan("40,000.00 BDT", "24/11/2022 at 10.37 AM", "Personal Loan", "R"))


        val mLayoutManager = LinearLayoutManager(activity)
        reject_loan_recyclerview.layoutManager = mLayoutManager
        reject_loan_recyclerview.itemAnimator = DefaultItemAnimator()

        recjectAdapter =
            LoanListAdaptar(
                loanList,
                object : LoanListAdaptar.OnItemClickListener {
                    override fun onItemClick(item: LoanResultDataModel) {

                    }
                },
            "R")

        reject_loan_recyclerview.adapter = recjectAdapter
        recjectAdapter.notifyDataSetChanged()




        return view
    }


}