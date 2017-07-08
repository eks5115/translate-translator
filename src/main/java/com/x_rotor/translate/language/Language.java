package com.x_rotor.translate.language;

import java.util.HashMap;

/**
 * Created by chensen on 2017/7/9.
 */
public interface Language {

    String ChineseSimplified = "zh-CH";
    String ChineseTraditional = "zh-TW";
    String English = "en";

    String getLangCode(String lang);
}
