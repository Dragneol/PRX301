<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : categoryLink.xsl
    Created on : October 18, 2018, 3:49 PM
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
    <xsl:template match="/">
        <categories>
            <xsl:apply-templates select="//ul/li/a"/>
        </categories>
    </xsl:template>
    <xsl:template match="//ul/li/a">
        <category>
            <name>
                <xsl:value-of select="@title"></xsl:value-of>
            </name>
            <link>
                <xsl:value-of select="@href"></xsl:value-of>
            </link>
        </category>
    </xsl:template>

</xsl:stylesheet>
