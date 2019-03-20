import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String args[]) throws IOException, InterruptedException {
        System.out.println("Check");
        Scanner URLscanner = new Scanner(System.in);
        System.out.println("Введите ссылку на товар: ");
        String URL = URLscanner.next();
        System.out.println("Как часто проверять товар (в минутах): ");
        String StringTimeDuration = URLscanner.next();
        int TimeDuration = Integer.parseInt(StringTimeDuration);
        //URL = "https://www.bonprix.ru/produkty/kurtka-udlinennogo-pokroya-dizajn-maite-kelly-chernyj-959818/?psTemplate=ada_superbox";
        check(URL, TimeDuration);
    }

    public static void check(String URL, int TimeDuration) throws IOException, InterruptedException {
        Document BonPrixURL = Jsoup.connect(URL).get();
        Elements PriceTag = BonPrixURL.getElementsByClass("integer-place");
        Elements HasOtherColor =  BonPrixURL.getElementsByClass("product-display-name");
        Elements NameOfProduct = BonPrixURL.getElementsByAttributeValue("itemprop", "name");
        //Elements AvaliblaeSizes = BonPrixURL.getElementsByAttributeValue("id", "product-variant-size");
        String UnforamtedPrice = "" + PriceTag.text();
        String UnformatedColor = "" + HasOtherColor.text();
        String UnformatedName = "" + NameOfProduct.text();
        //String UnformatedSizes = "" + AvaliblaeSizes;
        for(;;){
            System.out.println("Имя: " + FormingOfName(UnformatedName));
            System.out.println("Цена сейчас: " + FormingOfPrice(UnforamtedPrice) + "руб.");
            System.out.println("Доступные сейчас цвета: " + FormingOfColor(UnformatedColor) + ";");
            //System.out.println("Доступные сейчас размеры: " + FormingOfSizes(UnformatedSizes));
            TimeUnit.MINUTES.sleep(TimeDuration);
        }
    }

    public static String FormingOfPrice(String UnformatedPrice){
        StringBuffer SBFormatedPrice = new StringBuffer(UnformatedPrice);
        SBFormatedPrice.delete(5, UnformatedPrice.length());
        String FormatedPrice = SBFormatedPrice.toString();
        return FormatedPrice;
    }

    public static String FormingOfColor(String UnformatedColor){
        String FormatedColor = UnformatedColor.replace(" ", ",");
        return FormatedColor;
    }

    public static String FormingOfName(String UnformatedName){
        String FormatedName = null;
        StringBuffer SBFormatedName = new StringBuffer(UnformatedName);
        int IndexOfSpaces;
        SBFormatedName.delete(0, IndexOfSpaces = UnformatedName.indexOf("   "));
        FormatedName = SBFormatedName.toString();
        return FormatedName.replace("   ", "");
    }

    public static String FormingOfSizes(String UnformatedSizes){
        String FormatedSizes = null;
        FormatedSizes = UnformatedSizes;
        return FormatedSizes;
    }
}