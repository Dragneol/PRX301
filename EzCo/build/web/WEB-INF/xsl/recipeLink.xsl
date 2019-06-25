<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : recipeLink.xsl
    Created on : June 15, 2019, 10:28 PM
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
        <recipes xmlns="http://www.ezco.com/XMLSchema/ezco">
            <xsl:for-each select="//div[@class='it-body_bottom col-md-8 col-sm-8']">
                <recipe>
                    <xsl:variable name="link" select=".//h3/a/@href" />
                    <id>
                        <xsl:value-of select="translate($link, translate($link, '0123456789', ''), '')"/>
                    </id>
                    <link>
                        <xsl:value-of select="$link"/>
                    </link>
                </recipe>
            </xsl:for-each>
            <nextpage>
                <xsl:value-of select="//a[@class='next']/@href"/>
            </nextpage>
        </recipes>
    </xsl:template>

</xsl:stylesheet>
