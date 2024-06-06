package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NotificationDataModel

class NotificationAdapterNew(private val notifications: List<NotificationDataModel>) : RecyclerView.Adapter<NotificationAdapterNew.NotificationViewHolder>() {

    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tvMessageTitle)
        val date: TextView = itemView.findViewById(R.id.tvDate)
        val body: TextView = itemView.findViewById(R.id.tvMessageDetails)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_notification_list, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]
        holder.title.text = notification.title
        holder.body.text = notification.body
        holder.date.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return notifications.size
    }
}