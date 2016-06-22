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

        for (int i = 7; i < 10; i++) {
            //Document document = Jsoup.connect("http://image.baidu.com/search/flip?tn=baiduimage&word=兰博基尼&pn=20").get();
            Document document = Jsoup.connect("http://www.topit.me/tag/风景?p="+i).cookie("is_click", "1").get();

            Elements elements = document.select("#content .catalog .e>a");
            //Elements elements = document.select("#imgContainer .imgid .imgitem>a");
            for (Element element : elements) {
                String href = element.attr("href");
                System.out.println(href);

                /*Document bigPicDoc = Jsoup.connect(href).cookie("is_click", "1").get();
                Element picElement = bigPicDoc.select("#content>a").first();
                String picSrc = picElement.attr("href");

                System.out.println(picSrc);
                String fileName = picSrc.substring(picSrc.lastIndexOf("/") + 1);
                HttpUtil.getRequestStream(picSrc, "E:/Picture/" + fileName);

                Thread.sleep(1000);*/
            }

        }
    }
}
