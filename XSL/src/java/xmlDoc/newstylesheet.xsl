<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : newstylesheet.xsl
    Created on : May 29, 2019, 1:30 PM
    Author     : dragn
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>newstylesheet.xsl</title>
            </head>
            <body>
                <ul>
                    <xsl:apply-template select="persons/person">
                        <xsl:sort select="lastname"/>
                    </xsl:apply-template>
                </ul>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="person[not(@real='no')]">
        <li style="color:blue">
            <xsl:apply-templates/>
        </li>
    </xsl:template>
    <xsl:template match="person">
        <li style="color:red">
            <xsl:apply-templates/>
        </li>
    </xsl:template>
    <xsl:template match="name">
        <xsl:value-of select="lastname""
    </xsl:template>
</xsl:stylesheet>
