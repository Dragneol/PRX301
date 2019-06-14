<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : DepartmentSchedules.xsl
    Created on : October 30, 2018, 6:56 PM
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
        <xsl:param name="departmentId"/>
        <xsl:param name="moviesXML"/>
        <xsl:variable name="movies" select="document($moviesXML)"/>
        
        <div class="department-movie-list">
            <xsl:for-each select="$movies//*[local-name()='Movie']">
                <xsl:variable name="movieId" select="*[local-name()='id']"/>
                
                <xsl:if test="//*[local-name()='movieId']=$movieId">
                    <div class="department-movie">
                        <a
                            class="department-movie-image"
                            style="background-image: url('{*[local-name()='imageUrl']}');"
                            href="/FinalProject/detail?id={$movieId}">
                        </a>
                        <div class="department-movie-content">
                            <a class="department-movie-name" href="/FinalProject/detail?id={$movieId}">
                                <b>
                                    <xsl:value-of select="*[local-name()='VietnameseName']"/>
                                </b>
                            </a>
                            <span class="department-movie-attribute">
                                <b>Thể loại: </b>
                                <xsl:value-of select="*[local-name()='category']"/>
                            </span>
                            <span class="department-movie-attribute">
                                <b>Thời lượng: </b>
                                <xsl:value-of select="*[local-name()='duration']"/> phút
                            </span>
                            <span class="department-movie-attribute">
                                <xsl:value-of select="*[local-name()='description']"/>
                            </span>

                            <span class="department-movie-showing-title">Lịch chiếu phim</span>
                            <table>
                                <xsl:for-each select="//*[local-name()='MovieSchedule'][*[local-name()='movieId']=$movieId]">
                                    <tr>
                                        <xsl:variable name="bookingUrl" select="*[local-name()='bookingUrl']"/>
                                        <xsl:variable name="showingTimeList" select="*[local-name()='showingTimeList']"/>
                                        
                                        <td class="department-movie-showing-row">
                                            <span class="department-movie-showing-date">
                                                <xsl:call-template name="formatDate">
                                                    <xsl:with-param name="dateTime" select="*[local-name()='date']" />
                                                </xsl:call-template>
                                            </span>
                                        </td>
                                        <td class="department-movie-showing-list">
                                            <xsl:for-each select="$showingTimeList//*[local-name()='time']">
                                                <a href="{$bookingUrl}"><xsl:value-of select="current()"/></a>
                                            </xsl:for-each>
                                        </td>
                                    </tr>
                                </xsl:for-each>
                            </table>
                        </div>
                    </div>
                </xsl:if>
            </xsl:for-each>
        </div>
    </xsl:template>

    <xsl:template name="formatDate">
        <xsl:param name="dateTime" />
        <xsl:variable name="dateString" select="substring-before($dateTime, '+')" />
        <xsl:variable name="year" select="substring-before($dateString, '-')" />
        <xsl:variable name="month" select="substring-before(substring-after($dateString, '-'), '-')" />
        <xsl:variable name="day" select="substring-after(substring-after($dateString, '-'), '-')" />
        <xsl:value-of select="concat($day, '-', $month, '-', $year)" />
    </xsl:template>
</xsl:stylesheet>
