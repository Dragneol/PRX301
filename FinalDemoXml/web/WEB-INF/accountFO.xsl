<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" encoding="UTF-8"/>
    <xsl:param name="pathFile" select="'test'"/>
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="x" page-height="8.5in"
                    page-width="11in" margin-top="0.5in" margin-bottom="0.5in"
                    margin-left="1in" margin-right="1in">
                    <fo:region-body margin-top="0.5in" />
                    <fo:region-before extent="1in" />
                    <fo:region-after extent=".75in" />
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="x">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block font-size="14pt" font-family="Arial, Helvetica"
                        line-height="24pt" background-color="cyan"
                        space-after.optimum="15pt" text-align="center"
                        padding-top="3pt">
                        FO Demo - ThanhPC
                    </fo:block>
                </fo:static-content>
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block font-size="18pt" font-family="Arial, Helvetica"
                        line-height="24pt" space-after.optimum="15pt"
                        text-align="center" padding-top="3pt">
                    </fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <fo:table border-collapse="separate" table-layout="fixed">
                            <fo:table-column column-width="5cm"/>
                            <fo:table-column column-width="5cm"/>
                            <fo:table-column column-width="5cm"/>
                            <fo:table-column column-width="7cm"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell border-color="blue"
                                            border-width="0.5pt"
                                            border-style="solid">
                                        <fo:block text-align="center">No.</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-color="blue"
                                            border-width="0.5pt" border-style="solid">
                                        <fo:block text-align="center">Username</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-color="blue"
                                            border-width="0.5pt" border-style="solid">
                                        <fo:block text-align="center">Fullname</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-color="blue"
                                            border-width="0.5pt" border-style="solid">
                                        <fo:block text-align="center">Roles</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <xsl:for-each select="accounts/account">
                                    <fo:table-row>
                                        <fo:table-cell border-color="blue"
                                            border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center" font-family="Arial">
                                                <xsl:number level="single" count="account"/>.
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="blue"
                                            border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center" font-family="Arial">
                                                <xsl:value-of select="username"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="blue"
                                            border-width="0.5pt" border-style="solid" >
                                            <fo:block text-align="center" font-family="Arial">
                                                <xsl:value-of select="fullname"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="blue"
                                            border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center" font-family="Arial">
                                                <xsl:value-of select="role"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:for-each>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>
                    </fo:flow>
                </fo:page-sequence>
            </fo:root>
        </xsl:template>
    </xsl:stylesheet>
