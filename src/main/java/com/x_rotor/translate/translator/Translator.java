package com.x_rotor.translate.translator;

import com.x_rotor.translate.translator.translation.Translation;

/**
 * Created by chensen on 2017/7/8.
 */
public interface Translator {
    Translation translate(String sl, String tl, String query);
}
