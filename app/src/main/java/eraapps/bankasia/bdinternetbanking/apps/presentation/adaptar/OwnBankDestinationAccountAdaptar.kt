package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.app.Dialog
import android.content.Context
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


class OwnBankDestinationAccountAdaptar(

    private var accountList: ArrayList<OwnBankViewBeneficiaryDto>,
    indialog: Dialog,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<OwnBankViewBeneficiaryDto>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener
    var dialog: Dialog


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = accountList
        listener = listenerInit
        dialog = indialog

    }

    interface OnItemClickListener {
        fun onItemClick(item: OwnBankViewBeneficiaryDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_own_bank_view_dialog, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val tvNickName: TextView = holder.itemView.findViewById(R.id.tvNickName)
        val tvAcNo: TextView = holder.itemView.findViewById(R.id.tvAcNo)
        val tvAcTitle: TextView = holder.itemView.findViewById(R.id.tvAcTitle)
        val lo_container: LinearLayout? = holder.itemView.findViewById(R.id.lo_container)


        tvAcNo.text = currentItem.cardNo
        tvAcTitle.text = currentItem.destinationAccountTitle

        if (currentItem.nickname.isEmpty() || currentItem.nickname == "" || currentItem.nickname == null || currentItem.nickname == "null" || currentItem.nickname == " ") {
            tvNickName.text = currentItem.destinationAccountTitle
        } else {
            tvNickName.text = currentItem.nickname
        }

        lo_container!!.setOnClickListener {
            val selectedList: OwnBankViewBeneficiaryDto = requestFilterList[position]
            listener.onItemClick(selectedList)
            dialog.dismiss()

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
                            row.accountNoDescription.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.nickname.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.destinationAccount.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.destinationAccountTitle.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.mobileNumber.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.email.toString().lowercase()
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

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                requestFilterList = results?.values as ArrayList<OwnBankViewBeneficiaryDto>
                notifyDataSetChanged()
            }

        }
    }

}