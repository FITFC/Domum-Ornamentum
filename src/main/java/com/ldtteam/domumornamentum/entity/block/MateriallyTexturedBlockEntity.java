package com.ldtteam.domumornamentum.entity.block;

import com.ldtteam.domumornamentum.client.model.data.MaterialTextureData;
import com.ldtteam.domumornamentum.client.model.properties.ModProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.common.util.Constants;
import org.jetbrains.annotations.NotNull;

public class MateriallyTexturedBlockEntity extends BlockEntity
{

    private MaterialTextureData textureData = MaterialTextureData.EMPTY;

    public MateriallyTexturedBlockEntity(final BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state)
    {
        super(tileEntityTypeIn, pos, state);
    }

    public void updateTextureDataWith(final MaterialTextureData materialTextureData)
    {
        this.textureData = materialTextureData;
        this.requestModelDataUpdate();
    }

    @Override
    public @NotNull CompoundTag getUpdateTag()
    {
        return save(new CompoundTag());
    }

    @NotNull
    @Override
    public CompoundTag save(@NotNull final CompoundTag compound)
    {
        final CompoundTag superData = super.save(compound);

        superData.put("textureData", textureData.serializeNBT());

        return superData;
    }

    @Override
    public void load(@NotNull final CompoundTag nbt)
    {
        super.load(nbt);

        this.textureData = new MaterialTextureData();
        if (nbt.contains("textureData", Constants.NBT.TAG_COMPOUND))
        {
            this.textureData.deserializeNBT(nbt.getCompound("textureData"));
        }

        this.requestModelDataUpdate();
    }

    @NotNull
    @Override
    public IModelData getModelData()
    {
        return new ModelDataMap.Builder()
          .withInitial(ModProperties.MATERIAL_TEXTURE_PROPERTY, this.textureData)
          .build();
    }


}