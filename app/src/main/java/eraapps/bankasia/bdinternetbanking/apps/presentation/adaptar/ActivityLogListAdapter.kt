package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.LogListDto
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants
import eraapps.bankasia.bdinternetbanking.apps.util.ValidationUtil


class ActivityLogListAdapter(
    private var accountList: ArrayList<LogListDto>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<LogListDto>()
    lateinit var mcontext: Context
    lateinit var globalVariable: GlobalVariable

    var listener: OnItemClickListener

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = accountList
        listener = listenerInit

    }

    interface OnItemClickListener {
        fun onItemClick(item: LogListDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_activity_log_listview, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        globalVariable = mcontext.applicationContext as GlobalVariable

        val currentItem = requestFilterList[position]
        val tv_login_from: TextView = holder.itemView.findViewById(R.id.tv_login_from)
        val tv_version_info: TextView = holder.itemView.findViewById(R.id.tv_version_info)
        val tv_imei: TextView = holder.itemView.findViewById(R.id.tv_imei)
        val tv_login_time: TextView = holder.itemView.findViewById(R.id.tv_login_time)
        val tv_device_info: TextView = holder.itemView.findViewById(R.id.tv_device_info)

        tv_login_from.text = currentItem.loginFrom
        tv_version_info.text = currentItem.appVersion
        tv_imei.text = currentItem.imei
        tv_login_time.text = currentItem.loginDate
        tv_device_info.text = currentItem.deviceInfo
        /*
                btn_download.setOnClickListener {
                    val selectedList: LogListDto = requestFilterList[position]
                    listener.onItemClick(selectedList)
                }
        */


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
                    val resultList = ArrayList<LogListDto>()
                    for (row in accountList) {
                        if (
                            row.appVersion.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.imei.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.deviceInfo.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.loginDate.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.loginFrom.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.appVersion.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<LogListDto>
                notifyDataSetChanged()
            }

        }
    }

}