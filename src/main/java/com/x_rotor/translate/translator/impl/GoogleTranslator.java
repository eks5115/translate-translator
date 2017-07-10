package com.x_rotor.translate.translator.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.x_rotor.translate.language.Language;
import com.x_rotor.translate.language.impl.GoogleLanguage;
import com.x_rotor.translate.translator.translation.Translation;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chensen on 2017/7/8.
 */
public class GoogleTranslator extends AbstractTranslator {

    private Logger logger = LoggerFactory.getLogger(GoogleTranslator.class);

    private Language language = new GoogleLanguage();

    private static ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");

    private Pattern patternTKK = Pattern.compile("TKK=eval\\('\\(\\(([\\w\\W]*)\\)\\(\\)\\)'\\)");

    @Override
    public Language getLanguage() {
        return language;
    }

    @Override
    public String query(String sl, String tl, String query) {

        String tk = getTK(query);

        CloseableHttpClient httpClient = HttpClients.createDefault();

        URIBuilder uriBuilder;
        HttpGet request;
        String responseString = null;

        try {
            uriBuilder = new URIBuilder("http://translate.google.cn/translate_a/single");
            uriBuilder.addParameter("client", "t")
                    .addParameter("sl", sl)
                    .addParameter("tl", tl)
                    .addParameter("hl", "zh-CN")
                    .addParameter("dt", "at")
                    .addParameter("dt", "at")
                    .addParameter("dt", "bd")
                    .addParameter("dt", "ex")
                    .addParameter("dt", "ld")
                    .addParameter("dt", "md")
                    .addParameter("dt", "qca")
                    .addParameter("dt", "rw")
                    .addParameter("dt", "rm")
                    .addParameter("dt", "ss")
                    .addParameter("dt", "t")
                    .addParameter("ie", "UTF-8")
                    .addParameter("oe", "UTF-8")
                    .addParameter("source", "btn")
                    .addParameter("ssel", "3")
                    .addParameter("tsel", "6")
                    .addParameter("kc", "0")
                    .addParameter("tk", tk)
                    .addParameter("q", query);

            request = new HttpGet(uriBuilder.build());

            CloseableHttpResponse response = httpClient.execute(request);
            responseString = EntityUtils.toString(response.getEntity());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return responseString;
    }

    /**
     * from English to ChineseSimplified, query china
     * [[["中国","china",null,null,2],[null,null,"Zhōngguó","ˈCHīnə"]],[["名词",["瓷器","瓷"],[["瓷器",["porcelain","china","chinaware"],null,0.013611027],["瓷",["porcelain","china","chinaware"],null,0.0064293938]],"china",1],["形容词",["瓷的"],[["瓷的",["china"]]],"china",3]],"en",null,null,[["china",null,[["中国",1000,true,false]],[[0,5]],"china",0,0]],0.88779527,null,[["en"],null,[0.88779527],["en"]],null,null,[["名词",[[["porcelain"],"m_en_us1232692.002"],[["dishes","plates","cups and saucers","tableware","chinaware","dinner service","crockery"],"m_en_us1232692.002"],[["chinaware"],""]],"china"]],[["名词",[["a fine white or translucent vitrified ceramic material.","m_en_us1232692.001","a plate made of china"]],"china"],["名词",[["a country in eastern Asia, the third largest and most populous in the world; population 1,338,613,000 (est. 2009); capital, Beijing; language, Chinese (Mandarin is the official form).","m_en_us1232690.001"]],"China"]],[[["There's also a red goblet, a white \u003cb\u003echina\u003c/b\u003e cup brimming with coffee, a plate of French chocolate cookies and a peeled tangerine.",null,null,null,3,"m_en_us1232692.001"],["Home to cultural and architectural wonders and famous for its fine white \u003cb\u003echina\u003c/b\u003e , the German city of Dresden still shone despite six years of war.",null,null,null,3,"m_en_us1232692.001"],["a \u003cb\u003echina\u003c/b\u003e cup",null,null,null,3,"m_en_gb0143990.001"],["Silver candelabras glowed every few feet, throwing a romantic air over the ornate silverware and fine blue and white \u003cb\u003echina\u003c/b\u003e .",null,null,null,3,"m_en_us1232692.002"],["Linda watched Karen as she sat her new doll carefully on a tree stump and placed tiny \u003cb\u003echina\u003c/b\u003e plates, cups and saucers before it.",null,null,null,3,"m_en_us1232692.001"],["Fine \u003cb\u003echina\u003c/b\u003e pots and carafes can be found to make any dinner party a success.",null,null,null,3,"m_en_us1232692.001"],["There was a loud crash of breaking \u003cb\u003echina\u003c/b\u003e in the kitchen.",null,null,null,3,"m_en_us1232692.002"],["Chipped \u003cb\u003echina\u003c/b\u003e , broken furniture and left over jumble sale items are also consigned to the rubbish tip.",null,null,null,3,"m_en_us1232692.002"],["Stuffed rabbits, glass, \u003cb\u003echina\u003c/b\u003e , wooden, ceramic, you name it… I've got it in a rabbit.",null,null,null,3,"m_en_us1232692.001"],["Knives, forks, spoons, pots and pieces of \u003cb\u003echina\u003c/b\u003e littered the kitchen.",null,null,null,3,"m_en_us1232692.002"],["But you're here for Christmas shopping so just pick up a few fine \u003cb\u003echina\u003c/b\u003e rice bowls and serving dishes.",null,null,null,3,"m_en_us1232692.001"],["Along a wall, at perhaps crotch height, are what first appear to be five broken and re-made blue and white \u003cb\u003echina\u003c/b\u003e plates.",null,null,null,3,"m_en_us1232692.001"],["Now, if you are looking for white linen tablecloths and lavish meals served on fine \u003cb\u003echina\u003c/b\u003e , you won't find it in discount business class.",null,null,null,3,"m_en_us1232692.002"],["White candles and roses were perfectly placed along the fireplace mantel and down the center of the long table which was set with the finest white \u003cb\u003echina\u003c/b\u003e I had ever seen.",null,null,null,3,"m_en_us1232692.002"],["Ceramic designer Emma Bridgewater has regular sales in schools and village halls where you can snap up seconds, over-runs and samples of kitchenware, \u003cb\u003echina\u003c/b\u003e , textiles and glass.",null,null,null,3,"m_en_us1232692.002"],["she had begun to remove the breakfast \u003cb\u003echina\u003c/b\u003e",null,null,null,3,"m_en_gb0143990.002"],["Gackt smiled as he sipped his tea from a little blue and white \u003cb\u003echina\u003c/b\u003e cup.",null,null,null,3,"m_en_us1232692.001"],["a plate made of \u003cb\u003echina\u003c/b\u003e",null,null,null,3,"m_en_gb0143990.001"],["a plate made of \u003cb\u003echina\u003c/b\u003e",null,null,null,3,"m_en_us1232692.001"],["In fact, she has not turned a dollar since November 1969, apart from auctions of her furnishings, \u003cb\u003echina\u003c/b\u003e , glassware, and tableware.",null,null,null,3,"m_en_us1232692.002"],["With Christmas only a few weeks away, customers of the Main Street Store can check out the wide selection of gifts, \u003cb\u003echina\u003c/b\u003e , household and curtains now located on the ground floor.",null,null,null,3,"m_en_us1232692.002"],["We settled in at a cosy table set with fine \u003cb\u003echina\u003c/b\u003e and featuring a ceramic piggy bank centerpiece.",null,null,null,3,"m_en_us1232692.002"],["The unusually fine clay yielded a porcelain \u003cb\u003echina\u003c/b\u003e that was translucent with a glass-like finish.",null,null,null,3,"m_en_us1232692.001"],["It is industry policy not to place clean \u003cb\u003echina\u003c/b\u003e , flatware, or glassware on the floor.",null,null,null,3,"m_en_us1232692.002"],["Tamara cut out a muffin for herself and placed it on the white \u003cb\u003echina\u003c/b\u003e plate.",null,null,null,3,"m_en_us1232692.001"],["Lady Holland dies as she is sipping tea from her fine \u003cb\u003echina\u003c/b\u003e cup in her elegant little parlor.",null,null,null,3,"m_en_us1232692.001"],["We have crystal vases, \u003cb\u003echina\u003c/b\u003e , every remaining piece of our everyday dishes, footed cake trays, ice cream makers, hand blenders, and a food processor.",null,null,null,3,"m_en_us1232692.002"],["Harel heard her sigh and looked at her poking her food on the fine \u003cb\u003echina\u003c/b\u003e plate with the silver cutlery.",null,null,null,3,"m_en_us1232692.001"],["Stuffing, cranberry sauce, gravy, potatoes and vegetables had covered the white linen cloth and fine \u003cb\u003echina\u003c/b\u003e plates sat, waiting to be filled with food.",null,null,null,3,"m_en_us1232692.001"],["Never use any abrasive powder for any other reason on \u003cb\u003echina\u003c/b\u003e ; it may remove metallic or other decorations.",null,null,null,3,"m_en_us1232692.002"]]],[["China","bone china","china clay","bull in a china shop","a bull in a china shop"]]]
     * @param response
     * @return
     */
    @Override
    public Translation parse(String response) {

        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(response).getAsJsonArray();

        JsonArray translationJsonArray = jsonArray.get(0).getAsJsonArray().get(0).getAsJsonArray();

        return new Translation(translationJsonArray.get(1).getAsString(), translationJsonArray.get(0).getAsString());
    }


    private String getTKK() {

        String TKK = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://translate.google.cn/");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);

            String contentType = response.getFirstHeader("Content-type").getValue();

            String charset = contentType.split("charset=")[1];

            // read https://translate.google.cn/
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent(), charset));
            StringBuffer buffer = new StringBuffer();

            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            // pattern TKK: from "TKK=eval('((function(){...})())')" to "function(){...}"
            Matcher matcher = patternTKK.matcher(buffer.toString());

            matcher.find();

            TKK = matcher.group(1);

            logger.debug("Google TKK: "+TKK);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return TKK;
    }

    private String getTK(String query) {
        String tk = null;

        String TKK = getTKK();
        String script ="function tk(a) {"
                +"var TKK = eval('(("+TKK+")())');\n"
                +"function b(a, b) { for (var d = 0; d < b.length - 2; d += 3) { var c = b.charAt(d + 2), c = 'a' <= c ? c.charCodeAt(0) - 87 : Number(c), c = '+' == b.charAt(d + 1) ? a >>> c : a << c; a = '+' == b.charAt(d) ? a + c & 4294967295 : a ^ c } return a }\n"
                +"for (var e = TKK.split('.'), h = Number(e[0]) || 0, g = [], d = 0, f = 0; f < a.length; f++) {"
                +"var c = a.charCodeAt(f);"
                +"128 > c ? g[d++] = c : (2048 > c ? g[d++] = c >> 6 | 192 : (55296 == (c & 64512) && f + 1 < a.length && 56320 == (a.charCodeAt(f + 1) & 64512) ? (c = 65536 + ((c & 1023) << 10) + (a.charCodeAt(++f) & 1023), g[d++] = c >> 18 | 240, g[d++] = c >> 12 & 63 | 128) : g[d++] = c >> 12 | 224, g[d++] = c >> 6 & 63 | 128), g[d++] = c & 63 | 128)"
                +"}"
                +"a = h;"
                +"for (d = 0; d < g.length; d++) a += g[d], a = b(a, '+-a^+6');"
                +"a = b(a, '+-3^+b+-f');"
                +"a ^= Number(e[1]) || 0;"
                +"0 > a && (a = (a & 2147483647) + 2147483648);"
                +"a %= 1E6;"
                +"return a.toString() + '.' + (a ^ h)\n"
                +"}";

        try {
            engine.eval(script);
            Invocable invocable = (Invocable) engine;
            tk = (String) invocable.invokeFunction("tk", query);

            logger.debug("Google tk: "+tk);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return tk;
    }
}
