package com.x_rotor.translate.translator.impl;

import com.x_rotor.translate.language.Language;
import com.x_rotor.translate.language.impl.AbstractLanguage;
import com.x_rotor.translate.translator.translation.Translation;
import com.x_rotor.translate.translator.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chensen on 2017/7/8.
 */
public abstract class AbstractTranslator implements Translator {

    private Logger logger = LoggerFactory.getLogger(AbstractLanguage.class);

    @Override
    public Translation translate(String sl, String tl, String query) {

        logger.debug("translate start: "+sl+"-->"+tl+", query:"+query);

        Language language = getLanguage();

        String response = query(language.getLangCode(sl), language.getLangCode(tl), query);

        if (response == null) {

            logger.warn("response is null!");
            return null;
        }

        Translation translation = parse(response);

        logger.debug("translate end: "+sl+"-->"+tl+", "+translation.getSource()+"-->"+translation.getTranslation());

        return translation;
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
