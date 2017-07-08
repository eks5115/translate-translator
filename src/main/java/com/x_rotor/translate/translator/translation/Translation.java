package com.x_rotor.translate.translator.translation;

/**
 * Created by chensen on 2017/7/8.
 */
public class Translation {
    private String source;
    private String translation;

    public Translation(String source, String translation) {
        this.source = source;
        this.translation = translation;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
