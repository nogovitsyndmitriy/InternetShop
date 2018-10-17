package com.gmail.nogovitsyndmitriy.service.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PanginationUtil {
    private static final Logger log = LogManager.getLogger(PanginationUtil.class);

    public static Long quantityOfPages(Long quantityOfEntities, int maxResult) {
        long quantityOfPages = 0L;
        try {
            if (quantityOfEntities > 0 && quantityOfEntities <= maxResult) {
                quantityOfPages = 1L;
            } else if (quantityOfEntities % maxResult == 0) {
                quantityOfPages = quantityOfEntities / maxResult;
            } else {
                quantityOfPages = (quantityOfEntities / maxResult) + 1;
            }
            log.info("Successful get quantity of pages!");
        } catch (Exception e) {
            log.error("Failed to get Quantity of pages!");
        }
        return quantityOfPages;
    }
}
