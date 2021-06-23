package com.oldturok.turok.setting.impl;

import com.oldturok.turok.setting.Setting;
import com.oldturok.turok.setting.converter.BooleanConverter;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class BooleanSetting extends Setting<Boolean> {
   private static final BooleanConverter converter = new BooleanConverter();

   public BooleanSetting(Boolean value, Predicate<Boolean> restriction, BiConsumer<Boolean, Boolean> consumer, String name, Predicate<Boolean> visibilityPredicate) {
      super(value, restriction, consumer, name, visibilityPredicate);
   }

   public BooleanConverter converter() {
      return converter;
   }
}
