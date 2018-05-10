import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ForumSaude {

    public static void workit() throws IOException {
        PrintWriter writer = new PrintWriter("forumsaude.txt", "UTF-8");

        for(int i = 1; i < 137; i++) {
            crawl("https://forumsaude.com/conversas/pagina-" + i, writer);
        }

        writer.close();
    }

    private static void crawl(String url, PrintWriter writer) throws IOException {
        Document doc = Jsoup.connect(url).get();
        System.out.println(url);

        Elements discussions = doc.select("#Discussions .Discussion");
        System.out.println(discussions.size());

        for (Element discussion : discussions) {
            checkContents(discussion.select(".DiscussionTopic a").attr("href"), writer);
            //writer.println("%s\n\t%s" + headline.attr("title") + headline.absUrl("href"));
        }
    }

    private static void checkContents(String url, PrintWriter writer) throws IOException {
        Document thread = Jsoup.connect(url).get();

        writer.println(thread.select(".ContentInfo h1 a").attr("title"));

        Elements comments = thread.select("#Comments  li");

        for (Element comment : comments) {
            String attempt = comment.select(".CommentBody p").toString();
            attempt = attempt.replace("<p>", "");
            attempt = attempt.replace("</p>", "");
            attempt = attempt.replace("<br>", "");
            writer.println(attempt);
        }
    }
}
