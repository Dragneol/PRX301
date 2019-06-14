<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : MovieList.xsl
    Created on : October 28, 2018, 4:02 PM
    Author     : ahhun
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes" />

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <div class="movie-slide-container">
            <div class="movie-slide">
                <xsl:for-each select="//*[local-name()='Movie']">
                    <div class="movie-slide-item">
                        <a href="/FinalProject/detail?id={*[local-name()='id']}">
                            <div class="movie-slide-image-container">
                                <div class="movie-slide-image" style="background-image: url('{*[local-name()='imageUrl']}');"></div>
                                <div class="movie-slide-image-overlay"></div>
                            </div>
                            <div class="movie-slide-info">
                                <h1><xsl:value-of select="*[local-name()='VietnameseName']"/></h1>
                                <span class="movie-slide-info-attribute">
                                    <b>Đạo diễn: </b><xsl:value-of select="*[local-name()='director']"/>
                                </span>
                                <span class="movie-slide-info-attribute">
                                    <b>Diễn viên: </b><xsl:value-of select="*[local-name()='stars']"/>
                                </span>
                                <span class="movie-slide-info-attribute">
                                    <b>Thời lượng: </b><xsl:value-of select="*[local-name()='duration']"/> phút
                                </span>
                                <span class="movie-slide-info-attribute">
                                    <b>Thể loại: </b><xsl:value-of select="*[local-name()='category']"/>
                                </span>
                                <span class="movie-slide-info-attribute">
                                    <xsl:value-of select="*[local-name()='description']"/>
                                </span>
                            </div>
                        </a>
                    </div>
                </xsl:for-each>
            </div>
        </div>
        <div class="container">
            <h2>PHIM ĐANG CHIẾU</h2>
        
            <div class="movie-list">
                <xsl:for-each select="//*[local-name()='Movie']">
                    <div class="movie-item">
                        <a href="/FinalProject/detail?id={*[local-name()='id']}">
                            <div class="movie-image" style="background-image: url('{*[local-name()='imageUrl']}');"></div>
                            <h1><xsl:value-of select="*[local-name()='VietnameseName']"/></h1>
                            <span><b>Thể loại: </b><xsl:value-of select="*[local-name()='category']"/></span>
                            <br/>
                            <span><b>Thời lượng: </b><xsl:value-of select="*[local-name()='duration']"/> phút</span>
                        </a>
                    </div>
                </xsl:for-each>
            </div>
        </div>
    </xsl:template>
</xsl:stylesheet>
