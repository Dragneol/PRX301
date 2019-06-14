<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : productLink.xsl
    Created on : October 21, 2018, 9:29 PM
    Author     : Decen
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns="http://www.thang.com/XMLSchema/thang">
    <xsl:output method="xml"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="//div[@id='contentsp']">
        <products>
<!--            <xsl:apply-templates select="./div[@class='col-lg-3 col-md-4 bot10 top10 ' and .//div/h5/span/text() != 'Hết hàng']"/>-->
            <xsl:for-each select="div[@class='col-lg-3 col-md-4 bot10 top10 ']">
                <product>
                    <link>
                        <xsl:value-of select=".//a[img]/@href" />
                    </link>
                    <name>
                        <xsl:value-of select=".//h5/text()" />
                    </name>
                </product>
            </xsl:for-each>
        </products>
    </xsl:template>
<!--    <xsl:template match="div[@class='col-lg-3 col-md-4 bot10 top10 ' and .//div/h5/span/text() != 'Hết hàng']">
        <product>
            <link>
                <xsl:value-of select="//a[img]/@href" />
            </link>
            <name>
                <xsl:value-of select="//h5/text()" />
            </name>
            <price>
                <xsl:value-of select="//div/h5/span/text()" />
            </price>
        </product>
    </xsl:template>-->
</xsl:stylesheet>
