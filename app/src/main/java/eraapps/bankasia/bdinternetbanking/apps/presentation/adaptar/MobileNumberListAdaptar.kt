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
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.Contact


class MobileNumberListAdaptar(
    private var mobileList: ArrayList<Contact>,
    context: Context,
    dialog1: Dialog,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<Contact>()
    lateinit var mcontext: Context

    //lateinit var dialog: Dialog
    var listener: OnItemClickListener
    var dialog: Dialog


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = mobileList
        listener = listenerInit
        dialog = dialog1

    }

    interface OnItemClickListener {
        fun onItemClick(item: Contact?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_mobile_recharge_dialog, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val userImage: CircleImageView = holder.itemView.findViewById(R.id.userImage)
        val tvUserNameSL: TextView = holder.itemView.findViewById(R.id.tvUserNameSL)
        val tvUserMobileNo: TextView = holder.itemView.findViewById(R.id.tvUserMobileNo)
        val lo_container: LinearLayout = holder.itemView.findViewById(R.id.lo_container)

        tvUserNameSL.text = currentItem.name
        tvUserMobileNo.text = currentItem.number.toString()

        if ((currentItem.uri == null) || currentItem.uri.equals("")) {
            userImage.setImageResource(R.drawable.ic_baseline_person_24);
        } else {
            Glide.with(mcontext)
                .load(currentItem.uri)
                .apply(RequestOptions.circleCropTransform())
                .into(userImage)
        }

        lo_container.setOnClickListener {
            // Toast.makeText(mcontext, currentItem.desc.toString(), Toast.LENGTH_SHORT).show()
            val selectedList: Contact = requestFilterList[position]
            listener.onItemClick(selectedList)
            dialog.cancel()

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
                    requestFilterList = mobileList
                } else {

                    val resultList = ArrayList<Contact>()
                    for (row in mobileList) {
                        if (
                            row.name.toString().lowercase()
                                .contains(constraint.toString().lowercase()) ||
                            row.number.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<Contact>
                notifyDataSetChanged()
            }

        }
    }

}