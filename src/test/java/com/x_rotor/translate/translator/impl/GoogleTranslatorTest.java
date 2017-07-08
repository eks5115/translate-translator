package com.x_rotor.translate.translator.impl;

import com.x_rotor.translate.language.Language;
import com.x_rotor.translate.translator.translation.Translation;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by chensen on 2017/7/8.
 */
public class GoogleTranslatorTest {

    @Test
    public void getTKK() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        GoogleTranslator translator = GoogleTranslator.class.newInstance();
        Method getTKK = GoogleTranslator.class.getDeclaredMethod("getTKK");
        getTKK.setAccessible(true);
        String tkk = (String) getTKK.invoke(translator);

        Assert.assertNotNull(tkk);
    }

    @Test
    public void getTk() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        GoogleTranslator translator = GoogleTranslator.class.newInstance();
        Method getTK = GoogleTranslator.class.getDeclaredMethod("getTK", String.class);
        getTK.setAccessible(true);
        String tk = (String) getTK.invoke(translator, "china");

        Assert.assertNotNull(tk);
    }

    @Test
    public void translate() throws IllegalAccessException, InstantiationException, NoSuchMethodException {
        GoogleTranslator translator = GoogleTranslator.class.newInstance();
        Translation translation = translator.translate(Language.English, Language.ChineseSimplified, "china");

//        Assert.assertNotNull(translation);

    }

}
