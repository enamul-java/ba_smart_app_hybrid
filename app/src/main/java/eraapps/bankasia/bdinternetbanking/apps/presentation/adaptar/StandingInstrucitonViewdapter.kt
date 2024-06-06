package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OwnBankViewBeneficiaryDto


class StandingInstrucitonViewdapter(

    private var accountList: ArrayList<OwnBankViewBeneficiaryDto>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<OwnBankViewBeneficiaryDto>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = accountList
        listener = listenerInit

    }

    interface OnItemClickListener {
        fun onItemClick(item: OwnBankViewBeneficiaryDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_standing_ins_view_list, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val tvSL: TextView = holder.itemView.findViewById(R.id.tvSL)
        val tvEffectiveDate: TextView = holder.itemView.findViewById(R.id.tvEffectiveDate)
        val tvAccountNo: TextView = holder.itemView.findViewById(R.id.tvAccountNo)
        val tvDestionAccountNo: TextView = holder.itemView.findViewById(R.id.tvDestionAccountNo)
        val tvLimitAmount: TextView = holder.itemView.findViewById(R.id.tvLimitAmount)
        val tvInstructionFrequcy: TextView = holder.itemView.findViewById(R.id.tvInstructionFrequcy)


        val lo_container: LinearLayout = holder.itemView.findViewById(R.id.lo_container)


        tvSL.text = currentItem.slNo
        tvEffectiveDate.text = currentItem.requestDate
        tvAccountNo.text = currentItem.sourceAccountNo
        tvDestionAccountNo.text = currentItem.destinationAccount
        tvLimitAmount.text = currentItem.amount
        tvInstructionFrequcy.text = currentItem.instyeDes




        lo_container.setOnClickListener {
            val selectedList: OwnBankViewBeneficiaryDto = requestFilterList[position]
            listener.onItemClick(selectedList)


        }
    }

    override fun getItemCount(): Int {
        return requestFilterList.size
        //Log.e("mobileNumberList--->", requestFilterList.size.toString())
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isEmpty()) {
                    requestFilterList = accountList
                } else {

                    val resultList = ArrayList<OwnBankViewBeneficiaryDto>()
                    for (row in accountList) {
                        if (
                            row.destinationAccount.lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.nickname.lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.mobileNumber.lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.destinationAccountTitle.lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.email.lowercase()
                                .contains(constraint.toString().lowercase())
                        ) {
                            resultList.add(row)
                        }
                    }
                    requestFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = requestFilterList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                requestFilterList = results?.values as ArrayList<OwnBankViewBeneficiaryDto>
                notifyDataSetChanged()
            }

        }
    }

}