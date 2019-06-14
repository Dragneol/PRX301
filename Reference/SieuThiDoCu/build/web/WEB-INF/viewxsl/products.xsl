<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : products.xsl
    Created on : October 31, 2018, 12:42 PM
    Author     : Decen
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns="http://www.thang.com/XMLSchema/thang">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="//*[local-name()='products']/*[local-name()='product' and 1]">
        <div>
            <div style="display:none">
                <input type='hidden' id='productId'>
                    <xsl:attribute name="value">
                        <xsl:value-of select="*[local-name()='id']" />
                    </xsl:attribute>
                </input>
                <input type='hidden' id='productName'>
                    <xsl:attribute name="value">
                        <xsl:value-of select="*[local-name()='name']" />
                    </xsl:attribute>
                </input>
                <input type='hidden' id='productPrice'>
                    <xsl:attribute name="value">
                        <xsl:value-of select="*[local-name()='price']" />
                    </xsl:attribute>
                </input>
            </div>
            <div class="product__detail block--width-full">
                <div class="product_detail__thumbnails">
                    <div class="product_detail__image">
                        <img id="productImage">
                            <xsl:attribute name="src">
                                <xsl:value-of select="*[local-name()='images']//*[local-name()='imageUrl' and 1]" />
                            </xsl:attribute>
                        </img>
                    </div>
                    <div class="thumbnails">
                        <xsl:for-each select="*[local-name()='images']//*[local-name()='imageUrl']">
                            <img>
                                <xsl:attribute name="src">
                                    <xsl:value-of select="." />
                                </xsl:attribute>
                                <xsl:attribute name="onclick">changeImage('<xsl:value-of select="." />')</xsl:attribute>
                            </img>
                        </xsl:for-each>
                    </div>
                </div>
                <div  class="product_detail__info">
                    <div class="product_detail__name">
                        <h1>
                            <xsl:value-of select="*[local-name()='name']" />
                        </h1>
                    </div>
                    <div>
                        <p>
                            <span>Giá: </span>    
                            <span class="product_detail__price">
                                <xsl:if test="*[local-name()='price'] &gt; 500">
                                    <xsl:value-of select="*[local-name()='price']" /> đ
                                </xsl:if>
                                <xsl:if test="*[local-name()='price'] &lt; 500">Liên hệ</xsl:if>
                            </span>
                        </p>
                        <p>
                            <span>Giá cũ: </span>
                            <span>
                                <xsl:if test="*[local-name()='priceOld'] &gt; *[local-name()='price']">
                                    <xsl:value-of select="*[local-name()='priceOld']" /> đ
                                </xsl:if>
                                <xsl:if test="*[local-name()='priceOld'] &lt;= *[local-name()='price']">Không cung cấp</xsl:if>
                            </span>
                        </p>
                        <p>
                            <span>Tình trạng: </span>
                            <span class="product_detail__status">
                                <xsl:value-of select="*[local-name()='status']" />%
                            </span>
                        </p>
                        <p>
                            Mô tả: <br/>
                            <xsl:value-of select="*[local-name()='description']" />
                        </p>
                    </div>
                    <div>
                        <xsl:if test="*[local-name()='price'] &gt; 500">
                            <button class="btn btn_addToCart" id='btnAddToCart'>Thêm vào giỏ hàng</button>
                        </xsl:if>
                    </div>
                </div>
                <div class="clearfloat"></div>
            </div>
        </div>
    </xsl:template>

</xsl:stylesheet>
