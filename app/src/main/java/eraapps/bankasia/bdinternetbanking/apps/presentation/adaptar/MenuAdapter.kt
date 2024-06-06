package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar


import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.common.Constants
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MenuModel
import eraapps.bankasia.bdinternetbanking.apps.util.getProgressDrawble
import eraapps.bankasia.bdinternetbanking.apps.util.loadOperatorsImagesClear

class MenuAdapter(
    private var activity: Activity,
    private var items: ArrayList<MenuModel>,
) : BaseAdapter() {
    private class ViewHolder(row: View) {
        var menu_name: TextView
        var menu_soft_code: TextView
        var menu_icon: ImageView

        init {
            this.menu_name = row.findViewById(R.id.menu_name)
            this.menu_soft_code = row.findViewById(R.id.menu_soft_code)
            this.menu_icon = row.findViewById(R.id.menu_icon)

        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater =
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.row_menu, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }


        val item = items[position]
        viewHolder.menu_name.text = item.menuTitle
        viewHolder.menu_soft_code.text = item.id
        item.imageId?.let { viewHolder.menu_icon.setImageResource(it) }


        /*  val image = Constrants.BASE_URL + "access/v1/menuimage/" + item.imageId
          Log.d("iamge-->", image)


          if (image == "") {
              viewHolder.menu_icon.setImageResource(R.drawable.ic_baseline_person_24)
          } else {
              viewHolder.menu_icon.loadOperatorsImagesClear(
                  image,
                  getProgressDrawble(viewHolder.menu_icon.context)
              )
          }
  */
        return view
    }

    override fun getItem(i: Int): MenuModel {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}