<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : recipeLink.xsl
    Created on : June 15, 2019, 10:28 PM
    Author     : dragn
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <recipes>
            <recipe>
                <xsl:for-each select="//div[@class='it-body_bottom col-md-8 col-sm-8']">
                    <link>
                        <xsl:value-of select=".//h3/a/@href"/>
                    </link>
                </xsl:for-each>
            </recipe>
            <nextpage>
                <xsl:value-of select="//a[@class='next']/@href"/>
            </nextpage>
        </recipes>
    </xsl:template>

</xsl:stylesheet>
