
package com.example.easyafya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HealthRequestAdapter(
    private val requests: MutableList<Patient>
) : RecyclerView.Adapter<HealthRequestAdapter.HealthRequestViewHolder>() {

    class HealthRequestViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val name: TextView =
            itemView.findViewById(R.id.patientNameTextView)

        val phone: TextView =
            itemView.findViewById(R.id.patientPhoneTextView)

        val location: TextView =
            itemView.findViewById(R.id.patientLocationTextView)

        val need: TextView =
            itemView.findViewById(R.id.patientNeedTextView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HealthRequestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_health_request, parent, false)

        return HealthRequestViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: HealthRequestViewHolder,
        position: Int
    ) {
        val request = requests[position]

        holder.name.text = request.name
        holder.phone.text = request.phone
        holder.location.text = request.location
        holder.need.text = request.healthNeed
    }

    override fun getItemCount(): Int = requests.size

    fun addRequest(request: Patient) {
        requests.add(request)
        notifyItemInserted(requests.lastIndex)
    }
}