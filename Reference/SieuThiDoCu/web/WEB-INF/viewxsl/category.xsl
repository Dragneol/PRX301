<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : category.xsl
    Created on : November 1, 2018, 12:32 PM
    Author     : Decen
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <div class="category_list_contain">
            <xsl:for-each select="/*[local-name()='categories']/*[local-name()='category']">
                <xsl:if test="*[local-name()='name'] != 'Khác'">
                    <div class="category block--height-medium">
                        <xsl:attribute name="id">category<xsl:value-of select="*[local-name()='id']" /></xsl:attribute>
                        <xsl:attribute name="onclick">clickCategory(<xsl:value-of select="*[local-name()='id']" />)</xsl:attribute>   
                        <span>
                            <xsl:value-of select="*[local-name()='name']" />
                        </span>
                    </div>
                </xsl:if>
            </xsl:for-each>
            <xsl:for-each select="/*[local-name()='categories']/*[local-name()='category']">
                <xsl:if test="*[local-name()='name'] = 'Khác'">
                    <div class="category block--height-medium">
                        <xsl:attribute name="id">category<xsl:value-of select="*[local-name()='id']" /></xsl:attribute>
                        <xsl:attribute name="onclick">clickCategory(<xsl:value-of select="*[local-name()='id']" />)</xsl:attribute>  
                        <span>
                            <xsl:value-of select="*[local-name()='name']" /> 
                        </span> 
                    </div>
                </xsl:if>
            </xsl:for-each>
        </div>
    </xsl:template>

</xsl:stylesheet>
