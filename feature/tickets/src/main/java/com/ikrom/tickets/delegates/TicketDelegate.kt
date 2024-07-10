package com.ikrom.tickets.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.DelegateAdapter
import com.example.utils.PriceUtils
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemTicketBinding
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


data class TicketItem(
    val badge: String?,
    val price: Int,
    val departureDate: LocalDateTime,
    val departureAirport: String,
    val arrivalDate: LocalDateTime,
    val arrivalAirport: String,
    val hasTransfer: Boolean
): AdapterItem()


class TicketDelegate: DelegateAdapter<TicketItem, TicketDelegate.ViewHolder>(TicketItem::class.java) {
    inner class ViewHolder(val binding: ItemTicketBinding): DelegateVH<TicketItem>(binding){
        override fun bind(item: TicketItem) {
            item.badge?.let {
                binding.badgeCard.visibility = View.VISIBLE
                binding.badgeText.text = item.badge
            }
            binding.price.text = "${PriceUtils.format(item.price)} ₽"
            binding.ticketTimeRange.text = "${getHHMM(item.departureDate)} — ${getHHMM(item.arrivalDate)}"
            binding.originAirlineCode.text = item.departureAirport
            binding.destinationAirlineCode.text = item.arrivalAirport

            val durationHour = "${calculateDuration(item.departureDate, item.arrivalDate)}ч в пути"
            val transfer = if (!item.hasTransfer) "/ Без пересадок" else ""
            binding.info.text = durationHour + transfer
        }

        private fun calculateDuration(startTime: LocalDateTime, endTime: LocalDateTime): Float {
            val duration = Duration.between(startTime, endTime)
            val hours = duration.toHours().toFloat()
            return hours
        }

        private fun getHHMM(dateTime: LocalDateTime): String {
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            return dateTime.format(formatter)
        }
    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemTicketBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_ticket
    }
}