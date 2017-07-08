package com.x_rotor.translate.language.impl;

import com.x_rotor.translate.language.Language;

import java.util.HashMap;

/**
 * Created by chensen on 2017/7/9.
 */
public class AbstractLanguage implements Language {

    HashMap<String, String> languageMap = new HashMap<>();

    @Override
    public String getLangCode(String lang) {
        return languageMap.get(lang);
    }
}
