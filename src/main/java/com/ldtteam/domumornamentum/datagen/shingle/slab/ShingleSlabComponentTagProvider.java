package com.ldtteam.domumornamentum.datagen.shingle.slab;

import com.ldtteam.domumornamentum.tag.ModTags;
import com.ldtteam.domumornamentum.util.Constants;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ShingleSlabComponentTagProvider extends BlockTagsProvider
{
    public ShingleSlabComponentTagProvider(
      final DataGenerator generatorIn,
      @Nullable final ExistingFileHelper existingFileHelper)
    {
        super(generatorIn, Constants.MOD_ID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags()
    {
        this.tag(ModTags.SHINGLES_COVER)
          .addTags(
            BlockTags.LOGS,
            BlockTags.PLANKS,
            Tags.Blocks.STONE,
            Tags.Blocks.END_STONES,
            Tags.Blocks.NETHERRACK,
            Tags.Blocks.OBSIDIAN
          );
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Shingle Slabs Tag Provider";
    }
}