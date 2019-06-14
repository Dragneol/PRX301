/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;

/**
 *
 * @author ahhun
 */
public class HTMLProcessor {

    public static void processCGVNowShowing(String data, CrawlerConfiguration config, String outputFilePath)
            throws IOException {
        String startSubstring = config.HOME_START;
        String endSubstring = config.HOME_END;
        processHTMLFile(data, outputFilePath, startSubstring, endSubstring, null);
    }

    public static void processCGVDetailMovie(String data, CrawlerConfiguration config, String outputFilePath)
            throws IOException {
        String startSubstring = config.DETAIL_START;
        String endSubstring = config.DETAIL_END;
        String[] removeList = config.DETAIL_REMOVE_LIST;
        processHTMLFile(data, outputFilePath, startSubstring, endSubstring, removeList);
    }
    
    public static void processCGVSchedule(String data, CrawlerConfiguration config, String outputFilePath)
            throws IOException {
        processHTMLFile(data, outputFilePath, null, null, null);
    }
    
    public static void processLotteHomePage(String data, CrawlerConfiguration config, String outputFilePath)
            throws IOException {
        String startSubstring = config.HOME_START;
        String endSubstring = config.HOME_END;
        processHTMLFile(data, outputFilePath, startSubstring, endSubstring, null);
    }
    
    public static void processLotteDetailMovie(String data, CrawlerConfiguration config, String outputFilePath)
            throws IOException {
        String startSubstring = config.DETAIL_START;
        String endSubstring = config.DETAIL_END;
        processHTMLFile(data, outputFilePath, startSubstring, endSubstring, null);
    }
    
    public static void processLotteMovieDescription(String data, CrawlerConfiguration config, String outputFilePath)
            throws IOException {
        String startSubstring = config.DESCRIPTION_START;
        String endSubstring = config.DESCRIPTION_END;
        processHTMLFile(data, outputFilePath, startSubstring, endSubstring, null);
    }
    
    public static void processLotteSchedule(String data, CrawlerConfiguration config, String outputFilePath)
            throws IOException {
        String startSubstring = config.SCHEDULE_START;
        String endSubstring = config.SCHEDULE_END;
        String[] removeList = config.SCHEDULE_REMOVE_LIST;
        processHTMLFile(data, outputFilePath, startSubstring, endSubstring, removeList);
    }
    
    private static void processHTMLFile(String data, String outputFilePath, String start, String end, String[] removeList)
            throws IOException {
        String tmpStr = data;
        if (start != null) {
            int index = data.indexOf(start);
            tmpStr = tmpStr.substring(index);
        }
        
        if (end != null) {
            int index = tmpStr.indexOf(end);
            tmpStr = tmpStr.substring(0, index);
        }
        
        tmpStr = tmpStr.replaceAll("&nbsp;?", "");
        tmpStr = tmpStr.replaceAll("&", "");
        tmpStr = tmpStr.replaceAll(">\\s*<", "><");
        
        if (removeList != null) {
            for (String string : removeList) {
                tmpStr = tmpStr.replaceAll(string, "");
            }
        }
        
        tmpStr = "<?xml version='1.0' encoding='UTF-8' ?>" + tmpStr;
        
        FileUtils.writeFile(outputFilePath, tmpStr.trim());
    }
}
