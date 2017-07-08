package com.x_rotor.translate.translator.impl;

import com.x_rotor.translate.language.Language;
import com.x_rotor.translate.translator.translation.Translation;
import com.x_rotor.translate.translator.Translator;

/**
 * Created by chensen on 2017/7/8.
 */
public abstract class AbstractTranslator implements Translator {

    @Override
    public Translation translate(String sl, String tl, String query) {

        Language language = getLanguage();

        String response = query(language.getLangCode(sl), language.getLangCode(tl), query);

        if (response == null) {
            return null;
        }

        return parse(response);
    }

    abstract public Language getLanguage();

    /**
     *
     * @param sl
     * @param tl
     * @param query
     * @return
     */
    abstract public String query(String sl, String tl, String query);

    /**
     * parse response
     * @param response
     * @return
     */
    abstract public Translation parse(String response);
}
