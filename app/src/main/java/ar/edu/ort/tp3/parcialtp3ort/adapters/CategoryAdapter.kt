package ar.edu.ort.tp3.parcialtp3ort.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3.parcialtp3ort.Models.AutoViewModel
import ar.edu.ort.tp3.parcialtp3ort.R
import ar.edu.ort.tp3.parcialtp3ort.entities.Category
import ar.edu.ort.tp3.parcialtp3ort.fragments.InicioFragmentDirections
import ar.edu.ort.tp3.parcialtp3ort.holders.CategoryHolder

class CategoryAdapter(
    private val categoryList: MutableList<Category>,
    private val viewModelStoreOwner: ViewModelStoreOwner,
    private val navController: NavController
) : RecyclerView.Adapter<CategoryHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_card, parent, false)
        return CategoryHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val category = categoryList[position]
        val layout = holder.getCardLayout()
        val viewModel = ViewModelProvider(viewModelStoreOwner)[AutoViewModel::class.java]
        holder.setName(category.name)
        layout.setCardBackgroundColor(category.color)
        holder.setImage(
            category.image,
            String.format("Car from the %1s category", category.name)
        )
        layout.setOnClickListener {
            viewModel.buscar(category.type, category.field)
            val action = InicioFragmentDirections.actionHomeFragmentToAutoFragment()
            navController.navigate(action)
        }
    }

    override fun getItemCount() = categoryList.size


}