package com.example.apiroomdb.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.apiroomdb.databinding.ItemEmployeeBinding
import com.example.apiroomdb.entity.EmployeeEntity

class EmployeeAdapter(
    private val onItemClick: (EmployeeEntity) -> Unit,
    private val onDeleteClick: (EmployeeEntity) -> Unit
) : ListAdapter<EmployeeEntity, EmployeeAdapter.EmployeeViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = ItemEmployeeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class EmployeeViewHolder(private val binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(employee: EmployeeEntity) {
            binding.tvName.text = employee.name
            binding.tvMobile.text = employee.mobile
            binding.tvDesignation.text = employee.designation

            binding.root.setOnClickListener {
                onItemClick(employee)
            }

            // If you have a delete button in your item layout
            binding.btnDelete.setOnClickListener {
                onDeleteClick(employee)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<EmployeeEntity>() {
        override fun areItemsTheSame(oldItem: EmployeeEntity, newItem: EmployeeEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: EmployeeEntity, newItem: EmployeeEntity) =
            oldItem == newItem
    }
}
