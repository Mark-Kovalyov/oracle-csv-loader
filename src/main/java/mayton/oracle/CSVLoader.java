package mayton.oracle;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Iterator;

import static java.sql.DriverManager.*;

public class CSVLoader {

    static Logger logger = LoggerFactory.getLogger(CSVLoader.class);

    public static void main(String[] args) throws Exception{
        String path = "emp.csv";
        String enc = "UTF-8";
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = getConnection("jdbc:oracle:thin:scott/tiger@127.0.0.1:1521/XE");
        CSVParser parser = CSVParser.parse(
                path,
                CSVFormat.TDF
                        .withDelimiter(',')
                        .withFirstRecordAsHeader()
                        .withQuote('\"')
                        .withSkipHeaderRecord()
        );
        logger.info("[2]");
        Iterator<CSVRecord> i = parser.iterator();
        logger.info("[3]");
        while(i.hasNext()){
            CSVRecord row = i.next();
            String ip = row.get(0);
            logger.info("{}",ip);
        }
        parser.close();
        conn.close();
    }

}
