<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : recipeDetail.xsl
    Created on : June 19, 2019, 10:17 AM
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
        <recipe xmlns="http://www.ezco.com/XMLSchema/ezco">
            <title>
                <xsl:value-of select="//h1"/>
            </title>
            <image>
                <xsl:value-of select="//img[@class='img-responsive']/@src"/>
            </image>
            <description>
                <xsl:value-of select="//div[@class='info-intro']"/>                
            </description>
            <ration>
                <xsl:variable name="ration" select="//div[@class='entry-detail_meta mt30']//p[2]"/>
                <xsl:value-of select="translate($ration, translate($ration, '0123456789', ''), '')"/>
            </ration>
            <preparetime>
                <xsl:variable name="ration" select="//div[@class='entry-detail_meta mt30']//p[3]"/>
                <xsl:value-of select="translate($ration, translate($ration, '0123456789', ''), '')"/>
            </preparetime>
            <cookingtime>
                <xsl:variable name="ration" select="//div[@class='entry-detail_meta mt30']//p[4]"/>
                <xsl:value-of select="translate($ration, translate($ration, '0123456789', ''), '')"/>
            </cookingtime>
            <ingredientmenu>
                <xsl:for-each select="//ul[@class='menu-ingredients']/li">
                    <ingredientdetail>
                        <xsl:variable name="quan" select="."/>
                        <xsl:variable name="quantitive" select="translate($quan, translate($quan, '0123456789', ''), '')"/>
                        <xsl:variable name="name" select=".//a"/>
                        <xsl:variable name="unit" select="substring-before(substring-after($quan,$quantitive),$name)" />
                        <quantitive>
                            <xsl:value-of select="$quantitive"/>
                        </quantitive>
                        <unit>
                            <xsl:value-of select="$unit"/>
                        </unit>
                        <name>
                            <xsl:value-of select="$name"/>                            
                        </name>
                    </ingredientdetail>
                </xsl:for-each>
            </ingredientmenu>
            <instructionmenu>
                <xsl:for-each select="//ul[@class='menu-directions']/li">
                    <instructiondetail>
                        <numstep>
                            <xsl:value-of select=".//div/span[@class='num-step']"/>
                        </numstep>
                        <detail>
                            <xsl:value-of select=".//div[@class='it-intro']"/>
                        </detail>
                    </instructiondetail>
                </xsl:for-each>
            </instructionmenu>
        </recipe>
    </xsl:template>

</xsl:stylesheet>
