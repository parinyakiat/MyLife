package com.example.application_v2.home

import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.application_v2.R
import com.example.application_v2.database.product.Product

class HomeAdapter: RecyclerView.Adapter<TextItemViewHolder>() {

    var data =  listOf<Product>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

//
//    var dataName_Gym = listOf("Fitness7 Pattaya",
//        "W/common Studio",
//        "Tony Fitness",
//        "PRS Product",
//        "Refresh fitness")
//
//    var dataAddress = listOf("399/9 หมู่ที่ 10 ศูนย์การค้าพัทยา อเวนิว Pattayasaisong Rd, Bang Lamung District, Chon Buri 20150",
//        "Pattaya City, Bang Lamung District, Chon Buri 20150",
//        "27/7 Phatthaya Tai 24 Alley, Pattaya City, Bang Lamung District, Chon Buri 20150",
//        "City, Bang Lamung District, Chon Buri 20150",
//        "162/35 ม Pattaya City, Bang Lamung District, Chon Buri 20150")
//
//    var dataTime = listOf("10.00 - 22.00",
//        "Open 24 hours",
//        "Open 24 hours",
//        "10.00 - 22.00",
//        "10.00 - 22.00")
//
//    var dataType = listOf("Fitness",
//        "Fitness",
//        "Fitness",
//        "Fitness",
//        "Fitness")

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {

        holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24F)

        holder.textView.text = "Name : ${data[position].name_pro}\n" +
                "Time : ${data[position].codeid}\n" +
                "Type : ${data[position].type}\n"

        holder.textView.setTextColor(Color.BLACK)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false) as TextView

        return TextItemViewHolder(view)
    }
}

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)