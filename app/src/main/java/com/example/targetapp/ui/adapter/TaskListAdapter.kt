package com.example.targetapp.ui.adapter

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.widget.TextViewCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.targetapp.R
import com.example.targetapp.TargetModel
import com.example.targetapp.Utils

class TaskListAdapter internal constructor(
context: Context,where : String
) : RecyclerView.Adapter<TaskListAdapter.TargetViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var targets= emptyList<TargetModel>() // Cached copy of words
    private  val where=where
    private  val context=context
    inner class TargetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val targetItemView: TextView = itemView.findViewById(R.id.tv_target_name)
        val targetItemStatusView: ImageView = itemView.findViewById(R.id.bt_status)
        val targetItemClickView: RelativeLayout = itemView.findViewById(R.id.rl_click_target)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TargetViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return TargetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TargetViewHolder, position: Int) {
        val current = targets[position]
        if(where.equals(Utils.WHERE_TYPE.TARGET_LİST_FRAGMENT) || where.equals(
                Utils.WHERE_TYPE.COMPLETED_LİST_FRAGMENT
            ) ){

            holder.targetItemStatusView.visibility= View.VISIBLE

            if(current.targetStatus){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.targetItemStatusView.setBackgroundTintList(context.getResources().getColorStateList(
                        R.color.green
                    ))
                }

            }
            else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.targetItemStatusView.setBackgroundTintList(context.getResources().getColorStateList(
                        R.color.red
                    ))
                }
            }

            holder.targetItemClickView.setOnClickListener {


                    val bundle = bundleOf("targetArgsId" to targets[position].id)
                    holder.itemView.findNavController().navigate(
                        R.id.action_bottomNavFragmentHome_to_targetListDetailFragment,
                        bundle)

            }

        }
        else{
            holder.targetItemStatusView.visibility= View.GONE

        }

        holder.targetItemView.text = current.target

    }

    internal fun setTask(target: List<TargetModel>) {
        this.targets = target
        notifyDataSetChanged()
    }

    override fun getItemCount() = targets.size
}

