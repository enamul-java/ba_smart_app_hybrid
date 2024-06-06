package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.common.Constants
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NotificationDto
import java.util.ArrayList


class NotificationListAdapter(
    private var notificationList: ArrayList<NotificationDto>,
    context: Context,
    dialog1: Dialog,
    listenerInit: OnItemClickListener,
) :
    RecyclerView.Adapter<NotificationListAdapter.MyViewHolder>(), Filterable {

    var requestFilterList = ArrayList<NotificationDto>()
     var mcontext: Context

    var listener: OnItemClickListener
    var dialog: Dialog

    init {
        this.requestFilterList = notificationList
        this.listener = listenerInit
        this.dialog = dialog1
        this.mcontext = context

    }

    interface OnItemClickListener {
        fun onItemClick(item: NotificationDto?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val listView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_notification_list, parent, false)
        return MyViewHolder(listView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        holder.tvMessageTitle.text = currentItem.title.toString()
        holder.tvDate.text = currentItem.programedate.toString()
        holder.tvMessageDetails.text = currentItem.message.toString()

        val imagload =
            Constants.BASE_URL+"access/v1/notification/image/"+currentItem.imageid

        if (currentItem.imageflg == "Y") {
            holder.ivImage.visibility = View.VISIBLE
            Glide.with(mcontext).load(imagload)
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .into(holder.ivImage)
        }

        holder.itemClick.setOnClickListener {
            val selectedList: NotificationDto = requestFilterList[position]
            listener.onItemClick(selectedList)
            dialog.cancel()

        }
    }

    override fun getItemCount(): Int {
        return requestFilterList.size
        notifyDataSetChanged()
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isEmpty()) {
                    requestFilterList = notificationList
                } else {

                    val resultList = ArrayList<NotificationDto>()
                    for (row in notificationList) {
                        if (
                            row.title.toString().lowercase()
                                .contains(constraint.toString().lowercase())

                            || row.title.toString().lowercase()
                                .contains(constraint.toString().lowercase()

                                )
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
                requestFilterList = results?.values as ArrayList<NotificationDto>
                notifyDataSetChanged()
            }

        }
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var tvMessageTitle: TextView
        var tvDate: TextView
        var tvMessageDetails: TextView
        var ivImage: ImageView
        var itemClick: CardView

        init {
            tvMessageTitle = itemView.findViewById(R.id.tvMessageTitle)
            tvDate = itemView.findViewById(R.id.tvDate)
            tvMessageDetails = itemView.findViewById(R.id.tvMessageDetails)
            ivImage = itemView.findViewById(R.id.ivImage)
            itemClick = itemView.findViewById(R.id.itemClick)

        }
    }

}