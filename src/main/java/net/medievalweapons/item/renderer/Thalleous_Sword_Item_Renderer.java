package net.medievalweapons.item.renderer;

import net.medievalweapons.entity.model.Thalleous_Sword_Entity_Model;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public enum Thalleous_Sword_Item_Renderer {
    INSTANCE;

    private final Thalleous_Sword_Entity_Model thalleous_Sword_Entity_Model = new Thalleous_Sword_Entity_Model();

    public boolean render(LivingEntity entity, ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded,
            MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model) {
        if (renderMode == ModelTransformation.Mode.GUI || renderMode == ModelTransformation.Mode.GROUND
                || renderMode == ModelTransformation.Mode.FIXED) {
            return false;
        }

        matrices.push();
        model.getTransformation().getTransformation(renderMode).apply(leftHanded, matrices);
        if (entity.isBlocking()) {
            matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(20F));
            matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-20F));
        }
        matrices.translate(-0.05D, 0.84D, 0.0D);
        matrices.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(vertexConsumers,
                this.thalleous_Sword_Entity_Model
                        .getLayer(new Identifier("medievalweapons", "textures/entity/thalleous_sword.png")),
                false, stack.hasGlint());
        this.thalleous_Sword_Entity_Model.render(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        matrices.pop();
        return true;
    }
}