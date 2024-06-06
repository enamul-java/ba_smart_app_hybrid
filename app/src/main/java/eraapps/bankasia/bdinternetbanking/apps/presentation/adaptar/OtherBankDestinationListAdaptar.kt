package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.common.Constants
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.OtherBankOptions
import eraapps.bankasia.bdinternetbanking.apps.util.ValidationUtil
import eraapps.bankasia.bdinternetbanking.apps.util.getProgressDrawble
import eraapps.bankasia.bdinternetbanking.apps.util.loadOperatorsImages
import eraapps.bankasia.bdinternetbanking.apps.util.loadOperatorsImagesClear
import java.util.*


class OtherBankDestinationListAdaptar(
    private var accountList: ArrayList<OtherBankOptions>,
    indialog: Dialog,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<OtherBankOptions>()
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
        fun onItemClick(item: OtherBankOptions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_beneficiary_list_dialog, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val tvNickName: TextView? = holder.itemView.findViewById(R.id.tvNickName)
        val tvAccountno: TextView? = holder.itemView.findViewById(R.id.tvAccountno)
        val tvType: TextView? = holder.itemView.findViewById(R.id.tvType)
        val tvBankName: TextView? = holder.itemView.findViewById(R.id.tvBankName)
        val tvAcTitle: TextView? = holder.itemView.findViewById(R.id.tvAcTitle)
        val iv_bankicon: ImageView = holder.itemView.findViewById(R.id.iv_bankicon)
        val lo_container: LinearLayout? = holder.itemView.findViewById(R.id.lo_container)

        if (currentItem.nickName!!.isEmpty() || currentItem.nickName == null || currentItem.nickName == "null" || currentItem.nickName == "") {
            if (tvNickName != null) {
                tvNickName.text = currentItem.accountTitle
            }
        } else {
            if (tvNickName != null) {
                tvNickName.text = currentItem.nickName
            }
        }

        if (tvBankName != null) {
            tvBankName.text = currentItem.bankName
        }
        if (tvAcTitle != null) {
            tvAcTitle.text = currentItem.accountTitle
        }

        if (tvAccountno != null) {
            if (currentItem.type == "C") {
                tvAccountno.text = ValidationUtil.cardNoMask(currentItem.accountNo.toString())
                tvType!!.text = "Card"
            } else {
                tvAccountno.text = currentItem.accountNo.toString()
                tvType!!.text = "Account"

            }
        }
        val image = Constants.BASE_URL + "access/v1/bankimage/" + currentItem.bankCode
        Log.d("bankimage-->", image)



        iv_bankicon.loadOperatorsImages(image, getProgressDrawble(iv_bankicon.context))


        lo_container!!.setOnClickListener {
            val selectedList: OtherBankOptions = requestFilterList[position]
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

                    val resultList = ArrayList<OtherBankOptions>()
                    for (row in accountList) {
                        if (
                            row.desc.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.nickName.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.accountNo.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.accountTitle.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.bankName.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.emailId.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.mobileNo.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<OtherBankOptions>
                notifyDataSetChanged()
            }

        }
    }

}