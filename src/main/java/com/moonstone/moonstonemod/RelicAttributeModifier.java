package com.moonstone.moonstonemod;


import java.util.*;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class RelicAttributeModifier {
    List<Modifier> attributes;

    public void setAttributes(List<Modifier> attributes) {
        this.attributes = attributes;
    }

    RelicAttributeModifier(List<Modifier> attributes) {
        this.attributes = attributes;
    }

    public static RelicAttributeModifierBuilder builder() {
        return new RelicAttributeModifierBuilder();
    }

    public static class RelicAttributeModifierBuilder {
        private List<Modifier> attributes;

        public RelicAttributeModifierBuilder attribute(RelicAttributeModifier.Modifier attribute) {
            if (this.attributes == null)
                this.attributes = new ArrayList<>();
            this.attributes.add(attribute);
            return this;
        }

        public RelicAttributeModifierBuilder attributes(Collection<? extends RelicAttributeModifier.Modifier> attributes) {
            if (attributes == null)
                throw new NullPointerException("attributes cannot be null");
            if (this.attributes == null)
                this.attributes = new ArrayList<>();
            this.attributes.addAll(attributes);
            return this;
        }

        public RelicAttributeModifierBuilder clearAttributes() {
            if (this.attributes != null)
                this.attributes.clear();
            return this;
        }

        public RelicAttributeModifier build() {
            List<RelicAttributeModifier.Modifier> attributes = List.copyOf(this.attributes);
            return new RelicAttributeModifier(attributes);
        }

    }

    public List<Modifier> getAttributes() {
        return this.attributes;
    }

    public static class Modifier {
        private final Holder<Attribute> attribute;

        private final float multiplier;

        private final AttributeModifier.Operation operation;

        public Holder<Attribute> getAttribute() {
            return this.attribute;
        }

        public float getMultiplier() {
            return this.multiplier;
        }

        public AttributeModifier.Operation getOperation() {
            return this.operation;
        }

        public Modifier(Holder<Attribute> attribute, float multiplier, AttributeModifier.Operation operation) {
            this.attribute = attribute;
            this.multiplier = multiplier;
            this.operation = operation;
        }

        public Modifier(Holder<Attribute> attribute, float multiplier) {
            this.attribute = attribute;
            this.multiplier = multiplier;
            this.operation = AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL;
        }
    }
}
