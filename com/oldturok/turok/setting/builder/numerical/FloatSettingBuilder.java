package com.oldturok.turok.setting.builder.numerical;

import com.oldturok.turok.setting.impl.numerical.FloatSetting;
import com.oldturok.turok.setting.impl.numerical.NumberSetting;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class FloatSettingBuilder extends NumericalSettingBuilder<Float> {
   public NumberSetting build() {
      return new FloatSetting((Float)this.initialValue, this.predicate(), this.consumer(), this.name, this.visibilityPredicate(), (Float)this.min, (Float)this.max);
   }

   public FloatSettingBuilder withMinimum(Float minimum) {
      return (FloatSettingBuilder)super.withMinimum(minimum);
   }

   public FloatSettingBuilder withName(String name) {
      return (FloatSettingBuilder)super.withName(name);
   }

   public FloatSettingBuilder withListener(BiConsumer<Float, Float> consumer) {
      return (FloatSettingBuilder)super.withListener(consumer);
   }

   public FloatSettingBuilder withMaximum(Float maximum) {
      return (FloatSettingBuilder)super.withMaximum(maximum);
   }

   public FloatSettingBuilder withRange(Float minimum, Float maximum) {
      return (FloatSettingBuilder)super.withRange(minimum, maximum);
   }

   public FloatSettingBuilder withConsumer(BiConsumer<Float, Float> consumer) {
      return (FloatSettingBuilder)super.withConsumer(consumer);
   }

   public FloatSettingBuilder withValue(Float value) {
      return (FloatSettingBuilder)super.withValue((Number)value);
   }

   public FloatSettingBuilder withVisibility(Predicate<Float> predicate) {
      return (FloatSettingBuilder)super.withVisibility(predicate);
   }

   public FloatSettingBuilder withRestriction(Predicate<Float> predicate) {
      return (FloatSettingBuilder)super.withRestriction(predicate);
   }
}
