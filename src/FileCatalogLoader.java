import java.io.*;
import java.util.StringTokenizer;

/**
 * @ClassName FileCatalogLoader
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/5/24 14:50
 * @Version
 */

public class FileCatalogLoader implements CatalogLoader{
    private final static String COFFEE_PREFIX = "Coffee";
    private final static String BREWER = "Brewer";
    private final static String PRODUCT = "Product";
    private final static String DELIM = "_";

    @Override
    public Catalog loadCatalog(String fileName)
            throws FileNotFoundException, IOException, DataFormatException {
        Catalog catalog = new Catalog();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        while (line != null){
            Product product = null;
            if(line.startsWith(COFFEE_PREFIX)){
                product = readCoffee(line);
            }else if(line.startsWith(BREWER)){
                product = readBrewer(line);
            }else if(line.startsWith(PRODUCT)){
                product = readProduct(line);
            } else{
                throw new DataFormatException(line);
            }
            catalog.addProduct(product);
            line = reader.readLine();
        }

        return catalog;
    }

    private Coffee readCoffee(String line) throws DataFormatException {
        StringTokenizer stknz = new StringTokenizer(line, DELIM);
        if(stknz.countTokens() != 10){
            throw new DataFormatException(line);
        }else{
            try {
                String prefix = stknz.nextToken();
                return new Coffee(stknz.nextToken(), stknz.nextToken(), Double.parseDouble(stknz.nextToken()),
                        stknz.nextToken(), stknz.nextToken(), stknz.nextToken(), stknz.nextToken(),
                        stknz.nextToken(), stknz.nextToken());
            }catch (NumberFormatException nfe){
                throw new DataFormatException(line);
            }
        }
    }

    private CoffeeBrewer readBrewer(String line) throws DataFormatException {
        StringTokenizer stknz = new StringTokenizer(line, DELIM);
        if(stknz.countTokens() != 7){
            throw new DataFormatException(line);
        }else {
            try {
                String prefix = stknz.nextToken();
                return new CoffeeBrewer(stknz.nextToken(), stknz.nextToken(),
                        Double.parseDouble(stknz.nextToken()), stknz.nextToken(),
                        stknz.nextToken(), Integer.parseInt(stknz.nextToken()));
            }catch (NumberFormatException nfe){
                throw new DataFormatException(line);
            }
        }
    }

    private Product readProduct(String line) throws DataFormatException {
        StringTokenizer stknz = new StringTokenizer(line, DELIM);
        if(stknz.countTokens() != 4){
            throw new DataFormatException(line);
        }else{
            try {
                String prefix = stknz.nextToken();
                return new Product(stknz.nextToken(), stknz.nextToken(),
                        Double.parseDouble(stknz.nextToken()));
            }catch (NumberFormatException nfe){
                throw new DataFormatException(line);
            }
        }
    }
}
