package com.newsapi.params;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RequestParamsTest {

    @Test
    void countryInvalid () {
    }

    @Test
    void categoryInvalid () {
    }

    @Test
    void languageInvalid () {
    }

    @Test
    void sortByInvalid () {
    }

    @Test
    void pageSizeInvalid () {
    }

    @Test
    void searchQueryInvalid () {
    }

    @Test
    void searchQueryInTitleInvalid () {
    }

    @Test
    void domainsInvalid () {
        String validDomain1 = "bbc.co.uk";
        String validDomain2 = "techcrunch.com";
        String validDomain3 = "engadget.com";
        String invalidDomain1 = "bbc.co.u";
        String invalidDomain2 = "techcrunch.";
        String invalidDomain3 = ".techcrunch.com";

        assertFalse(RequestParams.domainsInvalid(validDomain1));
        assertFalse(RequestParams.domainsInvalid(validDomain2));
        assertFalse(RequestParams.domainsInvalid(validDomain3));
        assertTrue(RequestParams.domainsInvalid(invalidDomain1));
        assertTrue(RequestParams.domainsInvalid(invalidDomain2));
        assertTrue(RequestParams.domainsInvalid(invalidDomain3));
    }

    @Test
    void excludeDomainsInvalid () {
        String validDomain1 = "bbc.co.uk";
        String validDomain2 = "techcrunch.com";
        String validDomain3 = "engadget.com";
        String invalidDomain1 = "bbc.co.u";
        String invalidDomain2 = "techcrunch.";
        String invalidDomain3 = ".techcrunch.com";

        assertFalse(RequestParams.domainsInvalid(validDomain1));
        assertFalse(RequestParams.domainsInvalid(validDomain2));
        assertFalse(RequestParams.domainsInvalid(validDomain3));
        assertTrue(RequestParams.domainsInvalid(invalidDomain1));
        assertTrue(RequestParams.domainsInvalid(invalidDomain2));
        assertTrue(RequestParams.domainsInvalid(invalidDomain3));
    }

    @Test
    void dateInvalid () {
    }
}