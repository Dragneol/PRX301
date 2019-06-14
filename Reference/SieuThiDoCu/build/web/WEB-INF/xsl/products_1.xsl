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

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <products>
            <product>
                <xsl:apply-templates select="//div[@class='sanpham_info']"/>
                <xsl:apply-templates select="//div[@class='noidung_img1']"/>
            </product>
        </products>
    </xsl:template>
    <xsl:template match="//div[@class='sanpham_info']">
        <price>
            <xsl:value-of select="translate(substring-before(//div[@class='gia'], 'đ'), ',' ,'')" />
        </price>
        <priceOld>
            <xsl:value-of select="translate(substring-before(//div[@class='giagoc'], 'đ'), ',' ,'')" />
        </priceOld>
        <status>
            <xsl:variable name="statusString" select="substring-after(substring-before(//div[@class='row_chitiet mota']/div[@class='row_noidung'], '%'), 'mới')"/>
            <xsl:variable name="status" select="translate($statusString, translate($statusString, '1234567890,.', ''), '')"/>
            <xsl:value-of select="translate($status, ',', '.')"/>
        </status>
        <description>
            <xsl:value-of select="//div[@class='row_chitiet mota']/div[@class='row_noidung']" />
        </description>
    </xsl:template>
    <xsl:template match="//div[@class='noidung_img1']">
        <images>
            <xsl:for-each select="//div[@class='full_thumblist']//a">
                <imageUrl>
                    <xsl:value-of select="@data-image" />
                </imageUrl>
            </xsl:for-each>
        </images>
    </xsl:template>
</xsl:stylesheet>
