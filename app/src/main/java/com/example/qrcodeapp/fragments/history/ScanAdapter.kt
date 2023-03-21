package com.example.qrcodeapp.fragments.history


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.qrcodeapp.R
import com.example.qrcodeapp.model.Scan
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.custom_row.view.id_txt
import kotlinx.android.synthetic.main.item_scan.view.*

class ScanAdapter : RecyclerView.Adapter<ScanAdapter.MyViewHolder>() {

    private var scanList = emptyList<Scan>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.item_scan, parent, false)

       )
    }

    override fun getItemCount(): Int {
        return scanList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curItem = scanList[position]
        holder.itemView.id_txt.text = curItem.id.toString()
        holder.itemView.name_txt.text = curItem.name
        holder.itemView.content_txt.text = curItem.content

        holder.itemView.item_scan.setOnClickListener {
            val action = ScanHistoryFragmentDirections.actionScanHistoryFragmentToUpdateScanFragment()
            holder.itemView.findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(scan: List<Scan>) {
        this.scanList = scan
        notifyDataSetChanged()
    }


}