<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : ingredientLink.xsl
    Created on : June 16, 2019, 10:15 AM
    Author     : dragn
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <ingredients>
            <ingredient>
                <xsl:for-each select="//ul[@class='products']/li">
                    <link>
                        <xsl:value-of select=".//a/@href"/>
                    </link>
                </xsl:for-each>
            </ingredient>
            <nextpage>
                <xsl:value-of select="//a[@class='next page-numbers']/@href"/>
            </nextpage>
        </ingredients>
    </xsl:template>

</xsl:stylesheet>
