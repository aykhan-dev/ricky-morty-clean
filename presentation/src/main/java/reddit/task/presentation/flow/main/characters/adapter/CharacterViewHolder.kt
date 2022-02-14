package reddit.task.presentation.flow.main.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import reddit.task.presentation.databinding.ItemListCharacterBinding
import reddit.task.domain.model.Character

class CharacterViewHolder private constructor(
    private val binding: ItemListCharacterBinding
): RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun build(parent: ViewGroup): CharacterViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemListCharacterBinding.inflate(inflater, parent, false)
            return CharacterViewHolder(binding)
        }
    }

    fun bind(character: Character) = with(binding) {
        tvName.text = character.name
        tvStatus.text = character.status

        tvLocation.text = character.location

        tvFirstSight.text = character.firstSight

        Glide.with(ivCharacter)
            .load(character.imageUrl)
            .into(ivCharacter)
    }

}