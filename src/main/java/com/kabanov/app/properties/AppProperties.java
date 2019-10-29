package com.kabanov.app.properties;

/**
 * @author kabaale
 */
public class AppProperties {
    
    private final int maxNumberOfLinesToLoad;

    public AppProperties(int maxNumberOfLinesToLoad) {
        this.maxNumberOfLinesToLoad = maxNumberOfLinesToLoad;
    }

    public int getMaxNumberOfLinesToLoad() {
        return maxNumberOfLinesToLoad;
    }
}
