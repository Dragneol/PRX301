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
            <id>
                <xsl:value-of select="//span[@class='sku']"/>
            </id>
            <name>
                <xsl:value-of select="//h1"/>
            </name>
            <price>
                <xsl:value-of select="//meta[@itemprop='price']/@content"/>
            </price>
            <unit>
                <xsl:variable name="text" select="//div[@itemprop='description']"/>
                <xsl:value-of select="translate($text, translate($text, '0123456789', ''), '')"/>                
            </unit>
        </ingredient>
    </xsl:template>

</xsl:stylesheet>
