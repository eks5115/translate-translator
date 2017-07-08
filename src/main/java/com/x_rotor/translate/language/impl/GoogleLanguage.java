package com.x_rotor.translate.language.impl;

import com.x_rotor.translate.language.Language;

/**
 * Created by chensen on 2017/7/9.
 */
public class GoogleLanguage extends AbstractLanguage {

    public GoogleLanguage() {
        languageMap.put(ChineseSimplified, "zh-CH");
        languageMap.put(ChineseTraditional, "zh-TW");
        languageMap.put(English, "en");
    }
}
