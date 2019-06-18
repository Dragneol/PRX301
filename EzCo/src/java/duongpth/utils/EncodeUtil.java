/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.utils;

import java.io.Serializable;

/**
 *
 * @author dragn
 */
public class EncodeUtil implements Serializable {

    public static String encode(String string) {
        String s = string;
        s = s.replaceAll("itemscope", "");
        s = s.replaceAll("&ndash;", "-");
        s = s.replaceAll("&rarr;", "→");
        s = s.replaceAll("&hellip;", "…");
        s = s.replaceAll("&<br>;", "");
        s = s.replaceAll("&<br/>;", "");
        s = s.replaceAll("&</br>;", "");
        s = s.replaceAll("&nbsp;", " ");
        s = s.replaceAll("&Ccedil;", "Ç");
        s = s.replaceAll("&ccedil;", "ç");
        s = s.replaceAll("&Euml;", "Ë");
        s = s.replaceAll("&euml;", "ë");
        s = s.replaceAll("&#8363;", "");
        s = s.replaceAll("&#262;", "Ć");
        s = s.replaceAll("&#263;", "ć");
        s = s.replaceAll("&#268;", "Č");
        s = s.replaceAll("&#269;", "č");
        s = s.replaceAll("&#272;", "Đ");
        s = s.replaceAll("&#273;", "đ");
        s = s.replaceAll("&#352;", "Š");
        s = s.replaceAll("&#353;", "š");
        s = s.replaceAll("&#381;", "Ž");
        s = s.replaceAll("&#382;", "ž");
        s = s.replaceAll("&Agrave;", "À");
        s = s.replaceAll("&agrave;", "à");
        s = s.replaceAll("&Ccedil;", "Ç");
        s = s.replaceAll("&ccedil;", "ç");
        s = s.replaceAll("&Egrave;", "È");
        s = s.replaceAll("&egrave;", "è");
        s = s.replaceAll("&Eacute;", "É");
        s = s.replaceAll("&eacute;", "é");
        s = s.replaceAll("&Iacute;", "Í");
        s = s.replaceAll("&iacute;", "í");
        s = s.replaceAll("&Iuml;", "Ï");
        s = s.replaceAll("&iuml;", "ï");
        s = s.replaceAll("&Ograve;", "Ò");
        s = s.replaceAll("&ograve;", "ò");
        s = s.replaceAll("&Oacute;", "Ó");
        s = s.replaceAll("&oacute;", "ó");
        s = s.replaceAll("&Uacute;", "Ú");
        s = s.replaceAll("&uacute;", "ú");
        s = s.replaceAll("&Uuml;", "Ü");
        s = s.replaceAll("&uuml;", "ü");
        s = s.replaceAll("&middot;", "·");
        s = s.replaceAll("&#262;", "Ć");
        s = s.replaceAll("&#263;", "ć");
        s = s.replaceAll("&#268;", "Č");
        s = s.replaceAll("&#269;", "č");
        s = s.replaceAll("&#272;", "Đ");
        s = s.replaceAll("&#273;", "đ");
        s = s.replaceAll("&#352;", "Š");
        s = s.replaceAll("&#353;", "š");
        s = s.replaceAll("&#381;", "Ž");
        s = s.replaceAll("&#382;", "ž");
        s = s.replaceAll("&Aacute;", "Á");
        s = s.replaceAll("&aacute;", "á");
        s = s.replaceAll("&#268;", "Č");
        s = s.replaceAll("&#269;", "č");
        s = s.replaceAll("&#270;", "Ď");
        s = s.replaceAll("&#271;", "ď");
        s = s.replaceAll("&Eacute;", "É");
        s = s.replaceAll("&eacute;", "é");
        s = s.replaceAll("&#282;", "Ě");
        s = s.replaceAll("&#283;", "ě");
        s = s.replaceAll("&Iacute;", "Í");
        s = s.replaceAll("&iacute;", "í");
        s = s.replaceAll("&#327;", "Ň");
        s = s.replaceAll("&#328;", "ň");
        s = s.replaceAll("&Oacute;", "Ó");
        s = s.replaceAll("&oacute;", "ó");
        s = s.replaceAll("&#344;", "Ř");
        s = s.replaceAll("&#345;", "ř");
        s = s.replaceAll("&#352;", "Š");
        s = s.replaceAll("&#353;", "š");
        s = s.replaceAll("&#356;", "Ť");
        s = s.replaceAll("&#357;", "ť");
        s = s.replaceAll("&Uacute;", "Ú");
        s = s.replaceAll("&uacute;", "ú");
        s = s.replaceAll("&#366;", "Ů");
        s = s.replaceAll("&#367;", "ů");
        s = s.replaceAll("&Yacute;", "Ý");
        s = s.replaceAll("&yacute;", "ý");
        s = s.replaceAll("&#381;", "Ž");
        s = s.replaceAll("&#382;", "ž");
        s = s.replaceAll("&AElig;", "Æ");
        s = s.replaceAll("&aelig;", "æ");
        s = s.replaceAll("&Oslash;", "Ø");
        s = s.replaceAll("&oslash;", "ø");
        s = s.replaceAll("&Aring;", "Å");
        s = s.replaceAll("&aring;", "å");
        s = s.replaceAll("&Eacute;", "É");
        s = s.replaceAll("&eacute;", "é");
        s = s.replaceAll("&Euml;", "Ë");
        s = s.replaceAll("&euml;", "ë");
        s = s.replaceAll("&Iuml;", "Ï");
        s = s.replaceAll("&iuml;", "ï");
        s = s.replaceAll("&Oacute;", "Ó");
        s = s.replaceAll("&oacute;", "ó");
        s = s.replaceAll("&#264;", "Ĉ");
        s = s.replaceAll("&#265;", "ĉ");
        s = s.replaceAll("&#284;", "Ĝ");
        s = s.replaceAll("&#285;", "ĝ");
        s = s.replaceAll("&#292;", "Ĥ");
        s = s.replaceAll("&#293;", "ĥ");
        s = s.replaceAll("&#308;", "Ĵ");
        s = s.replaceAll("&#309;", "ĵ");
        s = s.replaceAll("&#348;", "Ŝ");
        s = s.replaceAll("&#349;", "ŝ");
        s = s.replaceAll("&#364;", "Ŭ");
        s = s.replaceAll("&#365;", "ŭ");
        s = s.replaceAll("&Auml;", "Ä");
        s = s.replaceAll("&auml;", "ä");
        s = s.replaceAll("&Ouml;", "Ö");
        s = s.replaceAll("&ouml;", "ö");
        s = s.replaceAll("&Otilde;", "Õ");
        s = s.replaceAll("&otilde;", "õ");
        s = s.replaceAll("&Uuml;", "Ü");
        s = s.replaceAll("&uuml;", "ü");
        s = s.replaceAll("&Aacute;", "Á");
        s = s.replaceAll("&aacute;", "á");
        s = s.replaceAll("&ETH;", "Ð");
        s = s.replaceAll("&eth;", "ð");
        s = s.replaceAll("&Iacute;", "Í");
        s = s.replaceAll("&iacute;", "í");
        s = s.replaceAll("&Oacute;", "Ó");
        s = s.replaceAll("&oacute;", "ó");
        s = s.replaceAll("&Uacute;", "Ú");
        s = s.replaceAll("&uacute;", "ú");
        s = s.replaceAll("&Yacute;", "Ý");
        s = s.replaceAll("&yacute;", "ý");
        s = s.replaceAll("&AElig;", "Æ");
        s = s.replaceAll("&aelig;", "æ");
        s = s.replaceAll("&Oslash;", "Ø");
        s = s.replaceAll("&oslash;", "ø");
        s = s.replaceAll("&Auml;", "Ä");
        s = s.replaceAll("&auml;", "ä");
        s = s.replaceAll("&Ouml;", "Ö");
        s = s.replaceAll("&ouml;", "ö");
        s = s.replaceAll("&Agrave;", "À");
        s = s.replaceAll("&agrave;", "à");
        s = s.replaceAll("&Acirc;", "Â");
        s = s.replaceAll("&acirc;", "â");
        s = s.replaceAll("&Ccedil;", "Ç");
        s = s.replaceAll("&ccedil;", "ç");
        s = s.replaceAll("&Egrave;", "È");
        s = s.replaceAll("&egrave;", "è");
        s = s.replaceAll("&Eacute;", "É");
        s = s.replaceAll("&eacute;", "é");
        s = s.replaceAll("&Ecirc;", "Ê");
        s = s.replaceAll("&ecirc;", "ê");
        s = s.replaceAll("&Euml;", "Ë");
        s = s.replaceAll("&euml;", "ë");
        s = s.replaceAll("&Icirc;", "Î");
        s = s.replaceAll("&icirc;", "î");
        s = s.replaceAll("&Iuml;", "Ï");
        s = s.replaceAll("&iuml;", "ï");
        s = s.replaceAll("&Ocirc;", "Ô");
        s = s.replaceAll("&ocirc;", "ô");
        s = s.replaceAll("&OElig;", "Œ");
        s = s.replaceAll("&oelig;", "œ");
        s = s.replaceAll("&Ugrave;", "Ù");
        s = s.replaceAll("&ugrave;", "ù");
        s = s.replaceAll("&Ucirc;", "Û");
        s = s.replaceAll("&ucirc;", "û");
        s = s.replaceAll("&Uuml;", "Ü");
        s = s.replaceAll("&uuml;", "ü");
        s = s.replaceAll("&#376;", "Ÿ");
        s = s.replaceAll("&yuml;", "ÿ");
        s = s.replaceAll("&Auml;", "Ä");
        s = s.replaceAll("&auml;", "ä");
        s = s.replaceAll("&Ouml;", "Ö");
        s = s.replaceAll("&ouml;", "ö");
        s = s.replaceAll("&Uuml;", "Ü");
        s = s.replaceAll("&uuml;", "ü");
        s = s.replaceAll("&szlig;", "ß");
        s = s.replaceAll("&Aacute;", "Á");
        s = s.replaceAll("&aacute;", "á");
        s = s.replaceAll("&Acirc;", "Â");
        s = s.replaceAll("&acirc;", "â");
        s = s.replaceAll("&Atilde;", "Ã");
        s = s.replaceAll("&atilde;", "ã");
        s = s.replaceAll("&Iacute;", "Í");
        s = s.replaceAll("&iacute;", "í");
        s = s.replaceAll("&Icirc;", "Î");
        s = s.replaceAll("&icirc;", "î");
        s = s.replaceAll("&#296;", "Ĩ");
        s = s.replaceAll("&#297;", "ĩ");
        s = s.replaceAll("&Uacute;", "Ú");
        s = s.replaceAll("&ugrave;", "ù");
        s = s.replaceAll("&Ucirc;", "Û");
        s = s.replaceAll("&ucirc;", "û");
        s = s.replaceAll("&#360;", "Ũ");
        s = s.replaceAll("&#361;", "ũ");
        s = s.replaceAll("&#312;", "ĸ");
        s = s.replaceAll("&Aacute;", "Á");
        s = s.replaceAll("&aacute;", "á");
        s = s.replaceAll("&Eacute;", "É");
        s = s.replaceAll("&eacute;", "é");
        s = s.replaceAll("&Iacute;", "Í");
        s = s.replaceAll("&iacute;", "í");
        s = s.replaceAll("&Oacute;", "Ó");
        s = s.replaceAll("&oacute;", "ó");
        s = s.replaceAll("&Ouml;", "Ö");
        s = s.replaceAll("&ouml;", "ö");
        s = s.replaceAll("&#336;", "Ő");
        s = s.replaceAll("&#337;", "ő");
        s = s.replaceAll("&Uacute;", "Ú");
        s = s.replaceAll("&uacute;", "ú");
        s = s.replaceAll("&Uuml;", "Ü");
        s = s.replaceAll("&uuml;", "ü");
        s = s.replaceAll("&#368;", "Ű");
        s = s.replaceAll("&#369;", "ű");
        s = s.replaceAll("&Aacute;", "Á");
        s = s.replaceAll("&aacute;", "á");
        s = s.replaceAll("&ETH;", "Ð");
        s = s.replaceAll("&eth;", "ð");
        s = s.replaceAll("&Eacute;", "É");
        s = s.replaceAll("&eacute;", "é");
        s = s.replaceAll("&Iacute;", "Í");
        s = s.replaceAll("&iacute;", "í");
        s = s.replaceAll("&Oacute;", "Ó");
        s = s.replaceAll("&oacute;", "ó");
        s = s.replaceAll("&Uacute;", "Ú");
        s = s.replaceAll("&uacute;", "ú");
        s = s.replaceAll("&Yacute;", "Ý");
        s = s.replaceAll("&yacute;", "ý");
        s = s.replaceAll("&THORN;", "Þ");
        s = s.replaceAll("&thorn;", "þ");
        s = s.replaceAll("&AElig;", "Æ");
        s = s.replaceAll("&aelig;", "æ");
        s = s.replaceAll("&Ouml;", "Ö");
        s = s.replaceAll("&uml;", "ö");
        s = s.replaceAll("&Aacute;", "Á");
        s = s.replaceAll("&aacute;", "á");
        s = s.replaceAll("&Eacute;", "É");
        s = s.replaceAll("&eacute;", "é");
        s = s.replaceAll("&Iacute;", "Í");
        s = s.replaceAll("&iacute;", "í");
        s = s.replaceAll("&Oacute;", "Ó");
        s = s.replaceAll("&oacute;", "ó");
        s = s.replaceAll("&Uacute;", "Ú");
        s = s.replaceAll("&uacute;", "ú");
        s = s.replaceAll("&Agrave;", "À");
        s = s.replaceAll("&agrave;", "à");
        s = s.replaceAll("&Acirc;", "Â");
        s = s.replaceAll("&acirc;", "â");
        s = s.replaceAll("&Egrave;", "È");
        s = s.replaceAll("&egrave;", "è");
        s = s.replaceAll("&Eacute;", "É");
        s = s.replaceAll("&eacute;", "é");
        s = s.replaceAll("&Ecirc;", "Ê");
        s = s.replaceAll("&ecirc;", "ê");
        s = s.replaceAll("&Igrave;", "Ì");
        s = s.replaceAll("&igrave;", "ì");
        s = s.replaceAll("&Iacute;", "Í");
        s = s.replaceAll("&iacute;", "í");
        s = s.replaceAll("&Icirc;", "Î");
        s = s.replaceAll("&icirc;", "î");
        s = s.replaceAll("&Iuml;", "Ï");
        s = s.replaceAll("&iuml;", "ï");
        s = s.replaceAll("&Ograve;", "Ò");
        s = s.replaceAll("&ograve;", "ò");
        s = s.replaceAll("&Ocirc;", "Ô");
        s = s.replaceAll("&ocirc;", "ô");
        s = s.replaceAll("&Ugrave;", "Ù");
        s = s.replaceAll("&ugrave;", "ù");
        s = s.replaceAll("&Ucirc;", "Û");
        s = s.replaceAll("&ucirc;", "û");
        s = s.replaceAll("&#256;", "Ā");
        s = s.replaceAll("&#257;", "ā");
        s = s.replaceAll("&#268;", "Č");
        s = s.replaceAll("&#269;", "č");
        s = s.replaceAll("&#274;", "Ē");
        s = s.replaceAll("&#275;", "ē");
        s = s.replaceAll("&#290;", "Ģ");
        s = s.replaceAll("&#291;", "ģ");
        s = s.replaceAll("&#298;", "Ī");
        s = s.replaceAll("&#299;", "ī");
        s = s.replaceAll("&#310;", "Ķ");
        s = s.replaceAll("&#311;", "ķ");
        s = s.replaceAll("&#315;", "Ļ");
        s = s.replaceAll("&#316;", "ļ");
        s = s.replaceAll("&#325;", "Ņ");
        s = s.replaceAll("&#326;", "ņ");
        s = s.replaceAll("&#342;", "Ŗ");
        s = s.replaceAll("&#343;", "ŗ");
        s = s.replaceAll("&#352;", "Š");
        s = s.replaceAll("&#353;", "š");
        s = s.replaceAll("&#362;", "Ū");
        s = s.replaceAll("&#363;", "ū");
        s = s.replaceAll("&#381;", "Ž");
        s = s.replaceAll("&#382;", "ž");
        s = s.replaceAll("&AElig;", "Æ");
        s = s.replaceAll("&aelig;", "æ");
        s = s.replaceAll("&Oslash;", "Ø");
        s = s.replaceAll("&oslash;", "ø");
        s = s.replaceAll("&Aring;", "Å");
        s = s.replaceAll("&aring;", "å");
        s = s.replaceAll("&#260;", "Ą");
        s = s.replaceAll("&#261;", "ą");
        s = s.replaceAll("&#262;", "Ć");
        s = s.replaceAll("&#263;", "ć");
        s = s.replaceAll("&#280;", "Ę");
        s = s.replaceAll("&#281;", "ę");
        s = s.replaceAll("&#321;", "Ł");
        s = s.replaceAll("&#322;", "ł");
        s = s.replaceAll("&#323;", "Ń");
        s = s.replaceAll("&#324;", "ń");
        s = s.replaceAll("&Oacute;", "Ó");
        s = s.replaceAll("&oacute;", "ó");
        s = s.replaceAll("&#346;", "Ś");
        s = s.replaceAll("&#347;", "ś");
        s = s.replaceAll("&#377;", "Ź");
        s = s.replaceAll("&#378;", "ź");
        s = s.replaceAll("&#379;", "Ż");
        s = s.replaceAll("&#380;", "ż");
        s = s.replaceAll("&Agrave;", "À");
        s = s.replaceAll("&agrave;", "à");
        s = s.replaceAll("&Aacute;", "Á");
        s = s.replaceAll("&aacute;", "á");
        s = s.replaceAll("&Acirc;", "Â");
        s = s.replaceAll("&acirc;", "â");
        s = s.replaceAll("&Atilde;", "Ã");
        s = s.replaceAll("&atilde;", "ã");
        s = s.replaceAll("&Ccedil;", "Ç");
        s = s.replaceAll("&ccedil;", "ç");
        s = s.replaceAll("&Egrave;", "È");
        s = s.replaceAll("&egrave;", "è");
        s = s.replaceAll("&Eacute;", "É");
        s = s.replaceAll("&eacute;", "é");
        s = s.replaceAll("&Ecirc;", "Ê");
        s = s.replaceAll("&ecirc;", "ê");
        s = s.replaceAll("&Igrave;", "Ì");
        s = s.replaceAll("&igrave;", "ì");
        s = s.replaceAll("&Iacute;", "Í");
        s = s.replaceAll("&iacute;", "í");
        s = s.replaceAll("&Iuml;", "Ï");
        s = s.replaceAll("&iuml;", "ï");
        s = s.replaceAll("&Ograve;", "Ò");
        s = s.replaceAll("&ograve;", "ò");
        s = s.replaceAll("&Oacute;", "Ó");
        s = s.replaceAll("&oacute;", "ó");
        s = s.replaceAll("&Otilde;", "Õ");
        s = s.replaceAll("&otilde;", "õ");
        s = s.replaceAll("&Ugrave;", "Ù");
        s = s.replaceAll("&ugrave;", "ù");
        s = s.replaceAll("&Uacute;", "Ú");
        s = s.replaceAll("&uacute;", "ú");
        s = s.replaceAll("&Uuml;", "Ü");
        s = s.replaceAll("&uuml;", "ü");
        s = s.replaceAll("&ordf;", "ª");
        s = s.replaceAll("&ordm;", "º");
        s = s.replaceAll("&#258;", "Ă");
        s = s.replaceAll("&#259;", "ă");
        s = s.replaceAll("&Acirc;", "Â");
        s = s.replaceAll("&acirc;", "â");
        s = s.replaceAll("&Icirc;", "Î");
        s = s.replaceAll("&icirc;", "î");
        s = s.replaceAll("&#350;", "Ş");
        s = s.replaceAll("&#351;", "ş");
        s = s.replaceAll("&#354;", "Ţ");
        s = s.replaceAll("&#355;", "ţ");
        s = s.replaceAll("&Aacute;", "Á");
        s = s.replaceAll("&aacute;", "á");
        s = s.replaceAll("&#268;", "Č");
        s = s.replaceAll("&#269;", "č");
        s = s.replaceAll("&#272;", "Đ");
        s = s.replaceAll("&#273;", "đ");
        s = s.replaceAll("&#330;", "Ŋ");
        s = s.replaceAll("&#331;", "ŋ");
        s = s.replaceAll("&#352;", "Š");
        s = s.replaceAll("&#353;", "š");
        s = s.replaceAll("&#358;", "Ŧ");
        s = s.replaceAll("&#359;", "ŧ");
        s = s.replaceAll("&#381;", "Ž");
        s = s.replaceAll("&#382;", "ž");
        s = s.replaceAll("&Agrave;", "À");
        s = s.replaceAll("&agrave;", "à");
        s = s.replaceAll("&Egrave;", "È");
        s = s.replaceAll("&egrave;", "è");
        s = s.replaceAll("&Eacute;", "É");
        s = s.replaceAll("&eacute;", "é");
        s = s.replaceAll("&Igrave;", "Ì");
        s = s.replaceAll("&igrave;", "ì");
        s = s.replaceAll("&Ograve;", "Ò");
        s = s.replaceAll("&ograve;", "ò");
        s = s.replaceAll("&Oacute;", "Ó");
        s = s.replaceAll("&oacute;", "ó");
        s = s.replaceAll("&Ugrave;", "Ù");
        s = s.replaceAll("&ugrave;", "ù");
        s = s.replaceAll("&Aacute;", "Á");
        s = s.replaceAll("&aacute;", "á");
        s = s.replaceAll("&Auml;", "Ä");
        s = s.replaceAll("&auml;", "ä");
        s = s.replaceAll("&#268;", "Č");
        s = s.replaceAll("&#269;", "č");
        s = s.replaceAll("&#270;", "Ď");
        s = s.replaceAll("&#271;", "ď");
        s = s.replaceAll("&Eacute;", "É");
        s = s.replaceAll("&eacute;", "é");
        s = s.replaceAll("&#313;", "Ĺ");
        s = s.replaceAll("&#314;", "ĺ");
        s = s.replaceAll("&#317;", "Ľ");
        s = s.replaceAll("&#318;", "ľ");
        s = s.replaceAll("&#327;", "Ň");
        s = s.replaceAll("&#328;", "ň");
        s = s.replaceAll("&Oacute;", "Ó");
        s = s.replaceAll("&oacute;", "ó");
        s = s.replaceAll("&Ocirc;", "Ô");
        s = s.replaceAll("&ocirc;", "ô");
        s = s.replaceAll("&#340;", "Ŕ");
        s = s.replaceAll("&#341;", "ŕ");
        s = s.replaceAll("&#352;", "Š");
        s = s.replaceAll("&#353;", "š");
        s = s.replaceAll("&#356;", "Ť");
        s = s.replaceAll("&#357;", "ť");
        s = s.replaceAll("&Uacute;", "Ú");
        s = s.replaceAll("&uacute;", "ú");
        s = s.replaceAll("&Yacute;", "Ý");
        s = s.replaceAll("&yacute;", "ý");
        s = s.replaceAll("&#381;", "Ž");
        s = s.replaceAll("&#382;", "ž");
        s = s.replaceAll("&#268;", "Č");
        s = s.replaceAll("&#269;", "č");
        s = s.replaceAll("&#352;", "Š");
        s = s.replaceAll("&#353;", "š");
        s = s.replaceAll("&#381;", "Ž");
        s = s.replaceAll("&#382;", "ž");
        s = s.replaceAll("&Aacute;", "Á");
        s = s.replaceAll("&aacute;", "á");
        s = s.replaceAll("&Eacute;", "É");
        s = s.replaceAll("&eacute;", "é");
        s = s.replaceAll("&Iacute;", "Í");
        s = s.replaceAll("&iacute;", "í");
        s = s.replaceAll("&Oacute;", "Ó");
        s = s.replaceAll("&oacute;", "ó");
        s = s.replaceAll("&Ntilde;", "Ñ");
        s = s.replaceAll("&ntilde;", "ñ");
        s = s.replaceAll("&Uacute;", "Ú");
        s = s.replaceAll("&uacute;", "ú");
        s = s.replaceAll("&Uuml;", "Ü");
        s = s.replaceAll("&uuml;", "ü");
        s = s.replaceAll("&iexcl;", "¡");
        s = s.replaceAll("&ordf;", "ª");
        s = s.replaceAll("&iquest;", "¿");
        s = s.replaceAll("&ordm;", "º");
        s = s.replaceAll("&Aring;", "Å");
        s = s.replaceAll("&aring;", "å");
        s = s.replaceAll("&Auml;", "Ä");
        s = s.replaceAll("&auml;", "ä");
        s = s.replaceAll("&Ouml;", "Ö");
        s = s.replaceAll("&ouml;", "ö");
        s = s.replaceAll("&Ccedil;", "Ç");
        s = s.replaceAll("&ccedil;", "ç");
        s = s.replaceAll("&#286;", "Ğ");
        s = s.replaceAll("&#287;", "ğ");
        s = s.replaceAll("&#304;", "İ");
        s = s.replaceAll("&#305;", "ı");
        s = s.replaceAll("&Ouml;", "Ö");
        s = s.replaceAll("&ouml;", "ö");
        s = s.replaceAll("&#350;", "Ş");
        s = s.replaceAll("&#351;", "ş");
        s = s.replaceAll("&Uuml;", "Ü");
        s = s.replaceAll("&uuml;", "ü");
        s = s.replaceAll("&euro;", "€");
        s = s.replaceAll("&pound;", "£");
        s = s.replaceAll("&laquo;", "«");
        s = s.replaceAll("&raquo;", "»");
        s = s.replaceAll("&bull;", "•");
        s = s.replaceAll("&dagger;", "†");
        s = s.replaceAll("&copy;", "©");
        s = s.replaceAll("&reg;", "®");
        s = s.replaceAll("&trade;", "™");
        s = s.replaceAll("&deg;", "°");
        s = s.replaceAll("&permil;", "‰");
        s = s.replaceAll("&micro;", "µ");
        s = s.replaceAll("&middot;", "·");
        s = s.replaceAll("&ndash;", "–");
        s = s.replaceAll("&mdash;", "—");
        s = s.replaceAll("&#8470;", "№");
        s = s.replaceAll("&", "&amp;");
        return s;
    }
}
