package com.kaishengit.test;

import com.kaishengit.util.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;


public class JsoupTestCase {

    @Test
    public void testGetPic() throws Exception {
        for (int i = 2; i < 5; i++) {
            Document document = Jsoup.connect("http://www.topit.me/tag/风景?p=1").cookie("is_click", "1").get();
            Elements elements = document.select("#content .catalog .e>a");
            for (Element element : elements) {
                String href = element.attr("href");

                Document bigPicDoc = null;

                bigPicDoc = Jsoup.connect(href).cookie("is_click", "1").get();
                Element picElement = bigPicDoc.select("#content>a").first();
                String picSrc = picElement.attr("href");

                System.out.println(picSrc);
                String fileName = picSrc.substring(picSrc.lastIndexOf("/") + 1);
                HttpUtil.getRequestStream(picSrc, "E:/Picture/" + fileName);

                Thread.sleep(2000);
            }
        }
    }
}
