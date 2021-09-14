package com.ldtteam.domumornamentum.datagen.global;

import com.ldtteam.datagenerators.recipes.RecipeIngredientJson;
import com.ldtteam.datagenerators.recipes.RecipeIngredientKeyJson;
import com.ldtteam.datagenerators.recipes.RecipeResultJson;
import com.ldtteam.datagenerators.recipes.shaped.ShapedPatternJson;
import com.ldtteam.datagenerators.recipes.shaped.ShapedRecipeJson;
import com.ldtteam.domumornamentum.block.ArchitectsCutterBlock;
import com.ldtteam.domumornamentum.block.ModBlocks;
import com.ldtteam.domumornamentum.block.decorative.ExtraBlock;
import com.ldtteam.domumornamentum.block.types.ExtraBlockType;
import com.ldtteam.domumornamentum.util.DataGeneratorConstants;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class GlobalRecipeProvider implements DataProvider
{
    private final DataGenerator generator;

    public GlobalRecipeProvider(DataGenerator generator)
    {
        this.generator = generator;
    }

    @Override
    public void run(@NotNull final HashCache cache) throws IOException
    {
        final ArchitectsCutterBlock block = ModBlocks.getInstance().getArchitectsCutter();
        final ShapedPatternJson pattern =  new ShapedPatternJson(" X ","SSS","LLL");
        final Map<String, RecipeIngredientKeyJson> keys = new HashMap<>();
        keys.put("X", new RecipeIngredientKeyJson(new RecipeIngredientJson(Items.IRON_INGOT.getRegistryName().toString(), false)));
        keys.put("S", new RecipeIngredientKeyJson(new RecipeIngredientJson(Items.STONE_SLAB.getRegistryName().toString(), false)));
        keys.put("L", new RecipeIngredientKeyJson(new RecipeIngredientJson(BlockTags.LOGS.getName().toString(), true)));

        final ShapedRecipeJson json = new ShapedRecipeJson("global", pattern, keys, new RecipeResultJson(4, block.asItem().getRegistryName().toString()));
        final Path recipeFolder = this.generator.getOutputFolder().resolve(DataGeneratorConstants.RECIPES_DIR);
        final Path blockstatePath = recipeFolder.resolve(block.getRegistryName().getPath() + ".json");

        DataProvider.save(DataGeneratorConstants.GSON, cache, DataGeneratorConstants.serialize(json), blockstatePath);
    }

    @NotNull
    @Override
    public String getName()
    {
        return "Global Blocks Recipe Provider";
    }
}