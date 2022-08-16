package com.example.simpletodolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletodolist.databinding.ItemTodoBinding

class TodoAdapter (
    private val todos :MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){
    class TodoViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )

            )

    }
    fun addTodo(todo : Todo){
        todos.add(todo)
        notifyItemInserted(todos.size -1 )
    }
    fun deleteDone() {
        todos.removeAll { todo ->
            todo.done
        }
        notifyDataSetChanged()
    }
    private fun toggleStrikeThrough(tvTodoTitle: TextView, done : Boolean){
        if(done){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var curTodo = todos[position]
        holder.itemView.apply {
            findViewById<TextView>(R.id.tvTodoTitle).text = curTodo.title
            findViewById<CheckBox>(R.id.cbDone).isChecked = curTodo.done
            toggleStrikeThrough(findViewById<TextView>(R.id.tvTodoTitle),curTodo.done)
            findViewById<CheckBox>(R.id.cbDone).setOnCheckedChangeListener{_,isChecked ->
                toggleStrikeThrough(findViewById<TextView>(R.id.tvTodoTitle),isChecked)
                curTodo.done = !curTodo.done
            }

     }




    }

    override fun getItemCount(): Int {
        return todos.size
    }
}