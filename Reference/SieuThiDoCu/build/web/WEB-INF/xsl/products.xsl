<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : products.xsl
    Created on : October 22, 2018, 1:42 PM
    Author     : Decen
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns="http://www.thang.com/XMLSchema/thang">
    <xsl:output method="xml"/>
    <xsl:param name="mainUrl"/>
    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <products>
            <product>
                <xsl:apply-templates select="//div[@class='col-sm-6 ']"/>
                <xsl:apply-templates select="//div[@class='mar thumbs']"/>
            </product>
        </products>
    </xsl:template>
    <xsl:template match="//div[@class='col-sm-6 ']">
        <xsl:for-each select="//h5[@style]">
<!--            <xsl:if test="contains(. ,'Ngày đăng')">
                <date>
                    <xsl:value-of select="substring-after(., 'Ngày đăng:')" />
                </date>
            </xsl:if>-->
            <xsl:if test="contains(. ,'Giá:')">
                <price>
                    <xsl:if test="contains(span, 'Hết hàng')">1</xsl:if>
                    <xsl:if test="not(contains(span, 'Hết hàng'))">
                        <xsl:value-of select="translate(substring-before(span, 'đ'), ',', '')" />
                    </xsl:if>
                </price>
            </xsl:if>
            <xsl:if test="contains(. ,'Tình trạng:')">
                <status>
                    <xsl:variable name="statusString" select="substring-before(substring-after(., 'Tình trạng:'), '%')"/>
                    <xsl:variable name="status" select="translate($statusString, translate($statusString, '1234567890,.', ''), '')"/>
                    <xsl:value-of select="translate($status, ',', '.')" />
                </status>
            </xsl:if>
        </xsl:for-each>
        <description>
            <xsl:value-of select="h5[not(@style)]" />
        </description>
    </xsl:template>
    <xsl:template match="//div[@class='mar thumbs']">
        <images>
            <xsl:for-each select="//a[@style='display:none;']/img">
                <imageUrl>
                    <xsl:choose>
                        <xsl:when test="starts-with(@src,'http')">
                            <xsl:value-of select="@src" />                    
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:value-of select="$mainUrl" /><xsl:value-of select="@src" />
                        </xsl:otherwise>
                    </xsl:choose>
                </imageUrl>
            </xsl:for-each>
        </images>
    </xsl:template>
</xsl:stylesheet>
