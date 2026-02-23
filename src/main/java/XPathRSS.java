import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.net.URI;

public class XPathRSS {
    public static void main(String[] args) throws Exception {
        try {
            if (args.length != 1) {
                throw new Exception("No arguments");
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            Document doc = factory.newDocumentBuilder().parse(URI.create((args[0])).toURL().openStream());

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();

            String version = xpath.evaluate("/rss/@version", doc);
            if (!version.equals("2.0")) {
                throw new Exception("Not RSS 2.0");
            }
            XPathExpression exprChannel = xpath.compile("//channel");
            NodeList itemsChannel = (NodeList) exprChannel.evaluate(doc, XPathConstants.NODESET);
            if (itemsChannel.getLength() == 0) {
                throw new Exception("No channel");
            }
            for (int i = 0; i < itemsChannel.getLength(); i++) {
                Node item = itemsChannel.item(i);
                String title = xpath.evaluate("title", item);
                String description = xpath.evaluate("description", item);
                String generator = xpath.evaluate("generator", item);
                String link = xpath.evaluate("link", item);

                System.out.println("Channel Title: " + title);
                System.out.println("Channel Description: " + description);
                System.out.println("Channel Generator: " + generator);
                System.out.println("Channel Link: " + link);
                System.out.println("++++++++++++++++++++");

            }

            XPathExpression expr = xpath.compile("//item");
            XPathExpression descExpr = xpath.compile(".//description"); // Note the './/' for relative search
            NodeList items = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            if (items.getLength() == 0) {
                throw new Exception("No items");
            }

            for (int i = 0; i < items.getLength(); i++) {
                Node item = items.item(i);
                String title = xpath.evaluate("title", item);
                NodeList descriptions = (NodeList) descExpr.evaluate(item, XPathConstants.NODESET);
                String link = xpath.evaluate("link", item);
                String pubDate = xpath.evaluate("pubDate", item);
                System.out.println("Title: " + title);
                System.out.println("Link: " + link);
                System.out.println("Date: " + pubDate);
                System.out.println("Description: ");
                for (int j = 0; j < descriptions.getLength(); j++) {
                    System.out.print(descriptions.item(j).getTextContent());
                }
                System.out.println("--------------------");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
