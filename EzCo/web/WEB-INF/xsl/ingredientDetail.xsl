<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : ingredientDetail.xsl
    Created on : June 17, 2019, 10:55 AM
    Author     : dragn
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" encoding="UTF-8"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <ingredient xmlns="http://www.ezco.com/XMLSchema/ezco">
            <xsl:variable name="titletext" select="//h1" />
            <xsl:variable name="destext" select="//div[@itemprop='description']"/>
            <id>
                <xsl:value-of select="//span[@class='sku']"/>
            </id>
            <name>
                <xsl:value-of select="$titletext"/>
            </name>
            <price>
                <xsl:value-of select="//meta[@itemprop='price']/@content"/>
            </price>
            <unit>
                <xsl:choose>
                    <xsl:when test="$titletext[contains(text(),'0')]">
                        <xsl:value-of select="translate($titletext, translate($titletext, '0123456789', ''), '')"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="translate($destext, translate($destext, '0123456789', ''), '')"/>
                    </xsl:otherwise>
                </xsl:choose>        
            </unit>
        </ingredient>
    </xsl:template>

</xsl:stylesheet>
