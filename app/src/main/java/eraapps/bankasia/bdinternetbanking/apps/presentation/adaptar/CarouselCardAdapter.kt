package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.content.Context
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CardInfoResponseDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CreditCardSourceCardListDataM
import eraapps.bankasia.bdinternetbanking.apps.util.ScreenUtil
import eraapps.bankasia.bdinternetbanking.apps.util.ValidationUtil

class CarouselCardAdapter(
    //private val carouselDataList: ArrayList<CardInfoResponseDto>,
    private val carouselDataList: ArrayList<CreditCardSourceCardListDataM>,
                          actvt: AppCompatActivity,
                          context: Context,
                          cardHeight : Int, cardWidth: Int) :
    RecyclerView.Adapter<CarouselCardAdapter.CarouselItemViewHolder>() {
     var ctx: Context
     var activity: AppCompatActivity
     var ch: Int
     var cw: Int
    class CarouselItemViewHolder(view: View) : RecyclerView.ViewHolder(view)
    init {
        ctx = context
        activity = actvt
        ch = cardHeight
        cw = cardWidth
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return CarouselItemViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {
        val cardNo = holder.itemView.findViewById<TextView>(R.id.tv_card_no)
        val cardOwner = holder.itemView.findViewById<TextView>(R.id.tv_card_owner)
        val constraintLayout = holder.itemView.findViewById<ConstraintLayout>(R.id.lyt_parent)


        val marginTop = ((ch/4)*1.6).toInt()
        val marginLeft = ((cw/5.5)*1.1).toInt()
        val cardOwnerMarginL = marginLeft+5


        when(ScreenUtil.getScreenDiagonally(activity)){
            "BIG" -> {
                Log.e("", "Screen:big " )
                cardOwner.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    setMargins(cardOwnerMarginL,marginTop,0,0)
                }
                cardNo.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    setMargins(marginLeft,5,0,0)
                }

                cardNo.setTextSize(TypedValue.COMPLEX_UNIT_SP,24F)
                cardOwner.setTextSize(TypedValue.COMPLEX_UNIT_SP,22F)
            }
            "MEDIUM" ->{
                Log.e("", "Screen:medium " )

                cardOwner.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    setMargins(cardOwnerMarginL,marginTop,0,0)
                }
                cardNo.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    setMargins(marginLeft,5,0,0)
                }
                cardNo.setTextSize(TypedValue.COMPLEX_UNIT_SP,16F)
                cardOwner.setTextSize(TypedValue.COMPLEX_UNIT_SP,14F)
            }
            "SMALL" ->{


                Log.e("", "Screen:small " )

                cardOwner.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    setMargins(cardOwnerMarginL,marginTop,0,0)
                }
                cardNo.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    setMargins(marginLeft,5,0,0)
                }

                cardNo.setTextSize(TypedValue.COMPLEX_UNIT_SP,14F)
                cardOwner.setTextSize(TypedValue.COMPLEX_UNIT_SP,12F)
            }
        }

        val item = carouselDataList[position]
        cardNo.text =  ValidationUtil.cardNoMask(item.cardNo.toString())
        cardOwner.text = item.cardTitle

        // Set the background drawable from the resource ID
        //constraintLayout.setBackgroundResource(item.url)
        val binNo = item.cardNo!!.subSequence(0,6)
        Log.e("", "binNo:$binNo" )

        constraintLayout.setBackgroundResource(R.drawable.card_default)

        //('222704','222705','515780','541173')
        when(binNo){
            "222704" -> {//Master Credit Gold
                constraintLayout.setBackgroundResource(R.drawable.bin_222704)
            }
            "222705" -> {//Master Credit Silver
                constraintLayout.setBackgroundResource(R.drawable.bin_222705)
            }
            "403993" -> {//Visa Credit  Signature
                constraintLayout.setBackgroundResource(R.drawable.bin_403993)
            }
            "463764" -> {//Visa Credit Classic
                constraintLayout.setBackgroundResource(R.drawable.bin_463764)
            }
            "463766" -> {//Visa Credit Gold
                constraintLayout.setBackgroundResource(R.drawable.bin_463766)
            }
            "469343" -> {//Visa Credit Platinum
                constraintLayout.setBackgroundResource(R.drawable.bin_469343)
            }
            "515780" -> {//Master Credit Titanium
                constraintLayout.setBackgroundResource(R.drawable.bin_515780)
            }
            "541173" -> {//Master Prepaid Silver
                constraintLayout.setBackgroundResource(R.drawable.bin_541173)
            }

            //new
            "419930" -> {//Visa Islamic Credit  Platinum
                constraintLayout.setBackgroundResource(R.drawable.bin_419930)
            }
            "476629" -> {//Visa Prepaid Classic
                constraintLayout.setBackgroundResource(R.drawable.bin_476629)
            }
            "514073" -> {//World Elite
                constraintLayout.setBackgroundResource(R.drawable.bin_514073)
            }

        }

        /*
        // Use Glide to load the image from the URL and set it as the background
        Glide.with(holder.itemView.context)
            .load(item.url)
            .transform(CenterCrop(), RoundedCorners(40))
            .into(object : CustomTarget<Drawable>() {
                override fun onLoadCleared(placeholder: Drawable?) {
                    // Handle cleanup if needed.
                }

                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    constraintLayout.background = resource
                }
            })
        */
    }

    override fun getItemCount(): Int {
        return carouselDataList.size
    }

}