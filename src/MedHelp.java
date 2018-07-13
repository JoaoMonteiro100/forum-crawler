import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;

public class MedHelp {

    public static void workit() throws IOException {
        PrintWriter writer = new PrintWriter("medhelp.txt", "UTF-8");

        for(int i = 1; i < 2; i++) {
            crawl("https://www.medhelp.org/forums/Diabetes---Type-1/show/220?page=" + i, writer);
        }
/*
        for(int i = 1; i < 2; i++) {
            crawl("https://www.medhelp.org/forums/Pregnancy-Ages-18-24-/show/152?page=" + i, writer);
        }

        for(int i = 1; i < 2; i++) {
            crawl("https://www.medhelp.org/forums/Exercise--Fitness/show/69?page=" + i, writer);
        }

        for(int i = 1; i < 2; i++) {
            crawl("https://www.medhelp.org/forums/Anxiety/show/71?page=" + i, writer);
        }
*/
        for(int i = 1; i < 2; i++) {
            crawl("https://www.medhelp.org/forums/Depression/show/57?page=" + i, writer);
        }

        for(int i = 1; i < 2; i++) {
            crawl("https://www.medhelp.org/forums/Heart-Disease/show/72?page=" + i, writer);
        }

        for(int i = 1; i < 2; i++) {
            crawl("https://www.medhelp.org/forums/High-Blood-Pressure---Hypertension/show/1222?page=" + i, writer);
        }
/*
        for(int i = 1; i < 2; i++) {
            crawl("https://www.medhelp.org/forums/Heart-Rhythm/show/92?page=" + i, writer);
        }
*/
        writer.close();
    }

    private static void crawl(String url, PrintWriter writer) throws IOException {
        Document doc = Jsoup.connect(url).get();
        System.out.println(url);

        Elements discussions = doc.select(".subj_title");
        System.out.println(discussions.size());

        for (Element discussion : discussions) {
            Elements elems = discussion.select("a");
            checkContents(elems.get(0).absUrl("href"), writer);
            //writer.println("%s\n\t%s" + headline.attr("title") + headline.absUrl("href"));
        }
    }

    private static void checkContents(String url, PrintWriter writer) throws IOException {
        Document thread = Jsoup.connect(url).get();

        writer.println(thread.select(".subj_title").text());
        writer.println(thread.select("#subject_msg").text());
        String attempt = thread.select(".resp_body").text();
        //attempt = attempt.replace("<br/>", "");
        writer.println(attempt);
    }
}
